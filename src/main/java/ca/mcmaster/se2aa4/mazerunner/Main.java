package ca.mcmaster.se2aa4.mazerunner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Main {

    private static final Logger logger = LogManager.getLogger();

    public static void main(String[] args) {
        logger.info("** Starting Maze Runner");
        MazeConfiguration mazeConfig = new MazeConfiguration();
        Maze maze = mazeConfig.config(args);
        MazeSolver solver = new MazeSolver();
        solver.generateBothPaths(maze);

    }


}
