package ca.mcmaster.se2aa4.mazerunner;

public class Player {

    private Maze maze;
    private int [] currentPosition = new int[2];
    private Direction currentDirection;
    
    public Player(Maze maze, int[] position, Direction direction ){
        this.maze = maze;
        this.currentPosition = position;
        this.currentDirection = direction;
    }

    public void turnRight(){
       currentDirection = currentDirection.getNextRight();
    }

    public void turnLeft(){
       currentDirection = currentDirection.getNextLeft();
    }

    public void moveForward(){
        if(validMove()){
            if(currentDirection == Direction.RIGHT)
                currentPosition[1] = currentPosition[1] + 1;
            else if(currentDirection == Direction.LEFT)
                currentPosition[1] = currentPosition[1] - 1;
            else if(currentDirection == Direction.UP)
                currentPosition[0] = currentPosition[0] - 1;
            else if(currentDirection == Direction.DOWN)
                currentPosition[0] = currentPosition[0] + 1;
        }
    }

    public boolean validMove(){
     
        if(currentDirection == Direction.RIGHT && currentPosition[1] + 1 < maze.getColumns())
        { 
            if((maze.getValue(currentPosition[0],currentPosition[1] + 1)) == '0')
                return true;
            else
                return false;
        }
        else if(currentDirection == Direction.LEFT && currentPosition[1] - 1 >= 0){
            if((maze.getValue(currentPosition[0],currentPosition[1] - 1)) == '0')
                return true;
            else
                return false;
        }
        else if(currentDirection == Direction.UP && currentPosition[0] - 1 >= 0 ){
            if((maze.getValue(currentPosition[0] - 1,currentPosition[1])) == '0')
                return true;
            else
                return false;
        }
        else if(currentDirection == Direction.DOWN && currentPosition[0] + 1 < maze.getRows()){
            if((maze.getValue(currentPosition[0] + 1,currentPosition[1])) == '0')
                return true;
            else
                return false;
        }
        else
            return false;
        
    }

    public int [] getPosition(){
        return this.currentPosition;
    }

    public void setCurrentPosition(int [] updated_position){
        this.currentPosition = updated_position;
    }

    public Direction getDirection() {
       return this.currentDirection;
    }

}
