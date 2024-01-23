package ca.mcmaster.se2aa4.mazerunner;

import java.io.BufferedReader;
import java.io.FileReader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import ca.mcmaster.se2aa4.mazerunner.Maze.Direction;

public class MazeSolver {
    
    private static final Logger logger = LogManager.getLogger();

    public static String generatePath( Maze maze){
        String resultPath = "";
        int [] startPosition = new int[2];
        startPosition[0] = maze.getStartRow();
        startPosition[1] = maze.getStartColumn();
        maze.setCurrentPosition(startPosition);

        startPosition = maze.getPosition();

        while(startPosition[0] != maze.getEndRow() || startPosition[1] != maze.getEndColumn()){
            if(validMove(maze)){
                moveForward(maze);
                resultPath += "F";
            }
            else{
                maze.updateDirection(Direction.RIGHT);
                resultPath += "R";
                if(!validMove(maze)){
                   maze.updateDirection(Direction.RIGHT);
                   maze.updateDirection(Direction.RIGHT);
                   resultPath += "RR"; 
                }
            }
            startPosition = maze.getPosition();

        }
        return resultPath;
    }

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

    public static boolean verifyPath(Maze maze, String path, boolean isStart){

        int [] startPosition = new int[2];

        if(!isStart){
            startPosition[0] = maze.getEndRow();
            startPosition[1] = maze.getEndColumn();
            maze.setCurrentPosition(startPosition);
            Direction newDirection = maze.getEndDirection();
            maze.setDirection(newDirection);
        }
        else{
            startPosition[0] = maze.getStartRow();
            startPosition[1] = maze.getStartColumn();
            maze.setCurrentPosition(startPosition);
        }

        int[] currentPosition = new int[2];

        for(int i = 0; i < path.length(); i++){
            char currentMove = path.charAt(i);
            currentPosition = maze.getPosition();
            //logger.info(maze.getDirection());
            switch(currentMove){
                case('F'):
                    if(maze.getDirection() == Direction.RIGHT)
                    { 
                        if((maze.getValue(currentPosition[0],currentPosition[1]+ 1)) == '0'){
                            currentPosition[1] = currentPosition[1] + 1;
                            maze.setCurrentPosition(currentPosition);
                            break;
                        }
                        else{
                            logger.info("Invalid Move");
                            return false;
                        }
                        
                       
                    }
                    else if(maze.getDirection() == Direction.LEFT){
                        if((maze.getValue(currentPosition[0],currentPosition[1] - 1)) == '0'){
                            currentPosition[1] = currentPosition[1] - 1;
                            maze.setCurrentPosition(currentPosition);
                            break;
        
                        }
                        else{
                            logger.info("Invalid Move");
                            return false;
                        }
                        
                    }
                    else if(maze.getDirection() == Direction.UP){
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
                    else if(maze.getDirection() == Direction.DOWN){
                        if((maze.getValue(currentPosition[0] + 1,currentPosition[1])) == '0'){
                            currentPosition[0] = currentPosition[0] + 1;
                            maze.setCurrentPosition(currentPosition);
                            break;
                        }
                        else{
                            logger.info("Invalid Move");
                            return false;
                        }
                
                    }
                    
                case('R'):
                    maze.updateDirection(Direction.RIGHT);
                    break;
                
                case('L'):
                    maze.updateDirection(Direction.LEFT);
                    break;
            }
          // logger.info(maze.getDirection());
          // logger.info(currentPosition[0]);
          // logger.info(currentPosition[1]);

        }

        if(currentPosition[0] == maze.getEndRow() && currentPosition[1] == maze.getEndColumn() && isStart || 
        currentPosition[0] == maze.getStartRow() && currentPosition[1] == maze.getStartColumn() && isStart == false){
            return true;
        }
        else{
            return false;
        }

    }

    
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


}
