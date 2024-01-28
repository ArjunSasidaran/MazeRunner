package ca.mcmaster.se2aa4.mazerunner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import ca.mcmaster.se2aa4.mazerunner.Maze.Direction;

public class MazeSolver {
    
    private static final Logger logger = LogManager.getLogger();

    public static void generateBothPaths(Maze maze){
        String canonicalPath = solveMaze(maze);
        String factoriezedPath = PathConverter.convertToFactorized(canonicalPath);
        System.out.println("Canonical Path: " + canonicalPath);
        System.out.println("Factorized Path: " + factoriezedPath);
    }
    

    public static String solveMaze( Maze maze){
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
        String canonicalPath = PathConverter.convertToCanonical(path);
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