package ca.mcmaster.se2aa4.mazerunner;

public class Maze {

    public enum Direction{
        UP,DOWN,RIGHT,LEFT
    }

    private char [][] maze;
    private int rows;
    private int columns;
    private int startRow;
    private int startColumn;
    private Direction startDirection;
    private int endRow;
    private int endColumn;
    private Direction endDirection;
    private int [] currPosition;
    private Direction currDirection;

    public Maze(String mazeString){
        storeMaze(mazeString);
        findStart();
        findEnd();
    }

    private void storeMaze(String mazeString) {
        String[] lines = mazeString.split(System.lineSeparator());
        rows = lines.length;
        columns = lines[0].length();
        maze = new char[rows][columns];
    
        for(int i = 0; i < rows; i++){
            for(int j = 0; j < columns; j++){
                maze[i][j] = lines[i].charAt(j);
                
            }
        }
    }

    private void findEnd(){       
        for(int i = 0; i < rows; i++){
            if(maze[i][columns-1] == '0'){
                this.endRow = i;
                this.endColumn = columns - 1;
                endDirection = Direction.LEFT;
                return;
            }
        }
    }


    private void findStart(){
        currPosition = new int[2];
        for(int i = 0; i < rows; i++){
            if(maze[i][0] == '0'){
                this.startRow = i;
                this.startColumn = 0;
                currDirection = Direction.RIGHT;
                startDirection = Direction.RIGHT;
                this.currPosition[0] = i;
                this.currPosition[1] = 0;
                return;
            }
            
        }
    }

    public void printMaze(){
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                System.out.print(maze[i][j]);
            }
            System.out.println();
        }
    }


    public int getRows(){
        return this.rows;
    }

    public int getColumns(){
        return this.columns;
    }


    public int getStartRow(){
        return this.startRow;
       
    }

    public int getStartColumn(){
        return this.startColumn;
    }

    public int getEndRow(){
        return this.endRow;
    }

    public int getEndColumn(){
        return this.endColumn;
    }

    public Direction getDirection(){
        return this.currDirection;
    }

    public Direction getStartDirection(){
        return this.startDirection;
    }

    public Direction getEndDirection(){
        return this.endDirection;
    }

    public void setDirection(Direction direction){
        this.currDirection = direction;
    }

    public int [] getPosition(){
        return this.currPosition;
    }

    public char getValue(int row, int column){
        return this.maze[row][column];
    }

    public void setCurrentPosition(int [] updated_position){
        this.currPosition = updated_position;
    }

    public void updateDirection(Direction direction){
        if(direction == Direction.LEFT){
            if(currDirection == Direction.UP){
                currDirection = Direction.LEFT;
            }
            else if(currDirection == Direction.DOWN){
                currDirection = Direction.RIGHT;
            }
            else if(currDirection == Direction.RIGHT){
                currDirection = Direction.UP;
            }
            else
                currDirection = Direction.DOWN;
            
        }
        else if(direction == Direction.RIGHT){
            if(currDirection == Direction.UP){
                currDirection = Direction.RIGHT;
            }
            else if(currDirection == Direction.DOWN){
                currDirection = Direction.LEFT;
            }
            else if(currDirection == Direction.RIGHT){
                currDirection = Direction.DOWN;
            }
            else
                currDirection = Direction.UP;
        
        }
    }

}
