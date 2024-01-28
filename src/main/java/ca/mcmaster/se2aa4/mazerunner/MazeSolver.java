package ca.mcmaster.se2aa4.mazerunner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import ca.mcmaster.se2aa4.mazerunner.Maze.Direction;

public class MazeSolver {
    
    private static final Logger logger = LogManager.getLogger();

    public static void generateBothPaths(Maze maze){
        String canonicalPath = generatePath(maze);
        String factoriezedPath = convertToFactorized(canonicalPath);
        System.out.println("Canonical Path: " + canonicalPath);
        System.out.println("Factorized Path: " + factoriezedPath);
    }
    
    private static String convertToFactorized(String path){
        String factorizedPath = "";
        int i = 0;
        while(i < path.length()){
            char current = path.charAt(i);
            int counter = 0;
            while(i < path.length() && path.charAt(i) == current){
                counter += 1;
                i++;
            }
            if(counter == 1)
                factorizedPath += current + " ";
            else
                factorizedPath += String.valueOf(counter) + current + " ";
        }
        return factorizedPath;
    }

    public static String convertToCanonical(String path){
        String result = "";
        String repeat = "";
        int i = 0;
        String modifiedPath = path.replaceAll(" ", "");
        while(i < modifiedPath.length()){
            if(Character.isDigit(modifiedPath.charAt(i))){
                while(Character.isDigit(modifiedPath.charAt(i)) && i < modifiedPath.length()){
                    repeat += String.valueOf(modifiedPath.charAt(i));
                    i++;
                }
                int val = Integer.valueOf(repeat);
                if(i < modifiedPath.length()){
                    char repeatedLetter = modifiedPath.charAt(i);
                    for (int x = 0; x < val; x++) {
                        result += repeatedLetter;
                    }
                    repeat = "";
                    i++;
                }
            }
            else{
                result += modifiedPath.charAt(i);
                i++;
            }
        }
        return result;

    }

    public static String generatePath( Maze maze){
        String resultPath = "";
        int [] startPosition = new int[2];
        startPosition[0] = maze.getStartRow();
        startPosition[1] = maze.getStartColumn();
        Player runner = new Player(maze, startPosition, maze.getStartDirection());
    
        int [] currentPosition = new int[2];
        currentPosition = runner.getPosition(); 

        while(currentPosition[0] != maze.getEndRow() || currentPosition[1] != maze.getEndColumn()){

            runner.turnRight();
            if(!runner.validMove()){
                runner.turnLeft();
                if(runner.validMove()){
                    runner.moveForward();
                    resultPath += "F";
                }
                else{
                    runner.turnLeft();
                    if(runner.validMove()){
                        resultPath += "LF";
                        runner.moveForward();
                    }
                    else{
                        runner.turnRight();
                        runner.turnRight();
                        runner.turnRight();
                        runner.moveForward();
                        resultPath += "RRF";
                    }
                }
            }
            else{
                runner.moveForward();
                resultPath += "RF";

            }
        }
    
        return resultPath;
    }

    public static boolean verifyPath(Maze maze, String path, boolean isStart){

        int [] startPosition = new int[2];
        Direction startingDirection;
       
        if(!isStart){
            startPosition[0] = maze.getEndRow();
            startPosition[1] = maze.getEndColumn();
            startingDirection = maze.getEndDirection();
        }
        else{
            startPosition[0] = maze.getStartRow();
            startPosition[1] = maze.getStartColumn();
            startingDirection = maze.getStartDirection();
           
        }

        Player runner = new Player(maze, startPosition, startingDirection);
        String canonicalPath = convertToCanonical(path);
        for(int i = 0; i < canonicalPath.length(); i++){
            char currentMove = canonicalPath.charAt(i);
    
            switch(currentMove){
                case('F'):
                    if(runner.validMove()){
                        runner.moveForward();
                        break;
                    }
                    else{
                        logger.info("Invalid Move");
                        return false;
                    }
             
                case('R'):
                    runner.turnRight();
                    break;
                
                case('L'):
                    runner.turnLeft();
                    break;
            }

        }
        //end condition
        if(runner.getPosition()[0] == maze.getEndRow() && runner.getPosition()[1] == maze.getEndColumn() && isStart || 
        runner.getPosition()[0] == maze.getStartRow() && runner.getPosition()[1] == maze.getStartColumn() && isStart == false)
            return true;
        else
            return false;

    }

}