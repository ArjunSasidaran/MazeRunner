package ca.mcmaster.se2aa4.mazerunner;

import java.io.BufferedReader;
import java.io.FileReader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import ca.mcmaster.se2aa4.mazerunner.Maze.Direction;

public class MazeSolver {
    
    private static final Logger logger = LogManager.getLogger();

    public String generatePath( Maze maze){
        return "FFFRLF";
    }

    public static boolean verifyPath(Maze maze, String path, boolean isStart){
    
        if(!isStart){
            int [] position = new int[2];
            position[0] = maze.getEndRow();
            position[1] = maze.getEndColumn();
            maze.setCurrentPosition(position);
            Direction newDirection = maze.getEndDirection();
            maze.setDirection(newDirection);
           // int test[] = maze.getPosition();
           //logger.info(maze.getDirection());
           //logger.info(test[0]);
           //logger.info(test[1]);
        }
        else{
            int [] start = new int[2];
            start[0] = maze.getStartRow();
            start[1] = maze.getStartColumn();
            maze.setCurrentPosition(start);
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
                    else if(maze.getDirection() == Direction.DOWN){
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

    



}
