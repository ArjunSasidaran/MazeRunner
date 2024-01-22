package ca.mcmaster.se2aa4.mazerunner;

public class Maze {

    private char [][] maze;
    private int rows;
    private int columns;
    private int startRow;
    private int startColumn;

    public Maze(){
        
    }

    public Maze(String mazeString){
        storeMaze(mazeString);
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

    private void findStart(){
        for(int i = 0; i < rows; i++){
            if(maze[i][0] == ' '){
                startRow = i;
                startColumn = 0;
                return;
            }
            else if(maze[i][columns-1] == ' '){
                startRow = i;
                startColumn = columns - 1;
                return;
            }
        }

        for(int i = 0; i < columns; i++){
            if(maze[0][i] == ' '){
                startRow = 0;
                startColumn = i;
                return;
            }
            else if(maze[rows-1][i] == ' '){
                startRow = rows-1;
                startColumn = i;
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



}
