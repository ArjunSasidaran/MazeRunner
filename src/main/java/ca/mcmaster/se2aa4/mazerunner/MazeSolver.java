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
            factorizedPath += String.valueOf(counter) + current + " ";
            //System.out.println("hello");
        }
        return factorizedPath;
    }

    private static String generatePath( Maze maze){
        String resultPath = "";
        int [] startPosition = new int[2];
        startPosition[0] = maze.getStartRow();
        startPosition[1] = maze.getStartColumn();
        Player runner = new Player(maze, startPosition, maze.getStartDirection());
        //maze.setCurrentPosition(startPosition);

        //startPosition = maze.getPosition();
        //Direction startDirection = maze.getStartDirection();
        //maze.setDirection(startDirection);

        int [] currentPosition = new int[2];
        currentPosition = runner.getPosition(); 

        while(currentPosition[0] != maze.getEndRow() || currentPosition[1] != maze.getEndColumn()){

            //maze.updateDirection(Direction.RIGHT);
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
            /* 
            if(!validMove(maze)){
                maze.updateDirection(Direction.LEFT);
                if(validMove(maze)){
                    moveForward(maze);
                    resultPath += "F";
                }
                else{
                    maze.updateDirection(Direction.LEFT);
                    if(validMove(maze)){
                        resultPath += "LF";
                        moveForward(maze);
                    }
                    else{
                        maze.updateDirection(Direction.RIGHT);
                        maze.updateDirection(Direction.RIGHT);
                        maze.updateDirection(Direction.RIGHT);
                        moveForward(maze);
                        resultPath += "RRF";
                    }
                }
            }
            else{
                resultPath += "RF";
                moveForward(maze);
            }
            */
        }
    
        return resultPath;
    }

    /* 
    public static void moveForward(Maze maze){
        int[] currentPosition = new int[2];
        currentPosition = maze.getPosition();
    
        if(validMove(maze)){
            if(maze.getDirection() == Direction.RIGHT){
                currentPosition[1] = currentPosition[1] + 1;
                maze.setCurrentPosition(currentPosition);
            }
            if(maze.getDirection() == Direction.LEFT){
                currentPosition[1] = currentPosition[1] - 1;
                maze.setCurrentPosition(currentPosition);
            }
            if(maze.getDirection() == Direction.UP){
                currentPosition[0] = currentPosition[0] - 1;
                maze.setCurrentPosition(currentPosition);
            }
            if(maze.getDirection() == Direction.DOWN){
                currentPosition[0] = currentPosition[0] + 1;
                maze.setCurrentPosition(currentPosition);
            }
            
            


        }
        
    }
    */

    public static boolean verifyPath(Maze maze, String path, boolean isStart){

        int [] startPosition = new int[2];
        Direction startingDirection;
       
        if(!isStart){
            startPosition[0] = maze.getEndRow();
            startPosition[1] = maze.getEndColumn();
            
            //maze.setCurrentPosition(startPosition);
             startingDirection = maze.getEndDirection();
            //maze.setDirection(newDirection);
            
        }
        else{
            startPosition[0] = maze.getStartRow();
            startPosition[1] = maze.getStartColumn();
            startingDirection = maze.getStartDirection();
           // maze.setCurrentPosition(startPosition);
           // maze.setDirection(maze.getStartDirection());
           
        }

        Player runner = new Player(maze, startPosition, startingDirection);
        //int[] currentPosition = new int[2];

        for(int i = 0; i < path.length(); i++){
            char currentMove = path.charAt(i);
          //  currentPosition = runner.getPosition();
            //logger.info(maze.getDirection());

            //System.out.println(runner.getPosition()[0] + runner.getPosition()[1]);
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
                /* 
                    if(runner.getDirection() == Direction.RIGHT)
                    { 
                        if((maze.getValue(currentPosition[0],currentPosition[1]+ 1)) == '0'){
                            currentPosition[1] = currentPosition[1] + 1;
                            runner.setCurrentPosition(currentPosition);
                            break;
                        }
                        else{
                            logger.info("Invalid Move");
                            return false;
                        }
                        
                       
                    }
                    else if(runner.getDirection() == Direction.LEFT){
                        if((maze.getValue(currentPosition[0],currentPosition[1] - 1)) == '0'){
                            currentPosition[1] = currentPosition[1] - 1;
                            runner.setCurrentPosition(currentPosition);
                            break;
        
                        }
                        else{
                            logger.info("Invalid Move");
                            return false;
                        }
                        
                    }
                    else if(runner.getDirection() == Direction.UP){
                        if((maze.getValue(currentPosition[0] - 1,currentPosition[1])) == '0'){
                            currentPosition[0] = currentPosition[0] - 1;
                            maze.setCurrentPosition(currentPosition);
                            break;
                        }
                        else{
                            logger.info("Invalid Move");
                            return false;
                        }
                       
                    }
                    else if(runner.getDirection() == Direction.DOWN){
                        if((maze.getValue(currentPosition[0] + 1,currentPosition[1])) == '0'){
                            currentPosition[0] = currentPosition[0] + 1;
                            runner.setCurrentPosition(currentPosition);
                            break;
                        }
                        else{
                            logger.info("Invalid Move");
                            return false;
                        }
                
                    }
                    
                    */
                case('R'):
                    runner.turnRight();
                    break;
                
                case('L'):
                    runner.turnLeft();
                    break;
            }

        }

        if(runner.getPosition()[0] == maze.getEndRow() && runner.getPosition()[1] == maze.getEndColumn() && isStart || 
        runner.getPosition()[0] == maze.getStartRow() && runner.getPosition()[1] == maze.getStartColumn() && isStart == false){
            return true;
        }
        else{
            return false;
        }

    }

    /* 
    public static boolean validMove(Maze maze ){
        int currentPosition [] = new int[2];
        currentPosition = maze.getPosition();

        if(maze.getDirection() == Direction.RIGHT)
        { 
            if((maze.getValue(currentPosition[0],currentPosition[1] + 1)) == '0'){
                return true;
            }
            else{
                return false;
            }
        }
        else if(maze.getDirection() == Direction.LEFT){
            if((maze.getValue(currentPosition[0],currentPosition[1] - 1)) == '0'){
                return true;
            }
            else{
                return false;
            }
            
        }
        else if(maze.getDirection() == Direction.UP){
            if((maze.getValue(currentPosition[0] - 1,currentPosition[1])) == '0'){
                return true;
            }
            else{
                return false;
            }
           
        }
        else if(maze.getDirection() == Direction.DOWN){
            if((maze.getValue(currentPosition[0] + 1,currentPosition[1])) == '0'){
                return true;
            }
            else{
                return false;
            }
    
        }
        return false;
    }
    */

}
