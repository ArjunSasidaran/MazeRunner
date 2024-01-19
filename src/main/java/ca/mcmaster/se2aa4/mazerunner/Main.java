package ca.mcmaster.se2aa4.mazerunner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Main {

    private static final Logger logger = LogManager.getLogger();

    public static void main(String[] args) {
        logger.info("** Starting Maze Runner");
        MazeConfiguration mazeConfig = new MazeConfiguration();
        Maze maze = mazeConfig.config(args);
        MazeSolver mazeSolver = new MazeSolver();
        mazeSolver.generatePath(maze);
        logger.info("**** Computing path");
        logger.info("PATH NOT COMPUTED");
        logger.info("** End of MazeRunner");
    }


}
