package ca.mcmaster.se2aa4.mazerunner;
import java.io.BufferedReader;
import java.io.FileReader;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Options;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class MazeConfiguration{

    private static final Logger logger = LogManager.getLogger();

    public static Maze config(String[] args){
        CommandLineParser parser = new DefaultParser();
        Options options = new Options();
        options.addOption("i", "input", true, "Input file path");
        options.addOption("p",true,"User Path");
        String inputFile = null;
        String path = null;

        try {
            CommandLine commandLine = parser.parse(options,args);
            if(commandLine.hasOption("i") || commandLine.hasOption("input")){
                inputFile = commandLine.getOptionValue("i");
            }

            if(commandLine.hasOption("p")){
                path = commandLine.getOptionValue("p");
            }

            if (inputFile != null) 
                return generateMaze(inputFile, path);

        } catch(Exception e) {
            logger.error("Missing input",e);
        }
        return null;
        
    }

    private static Maze generateMaze(String inputFile, String path){
        logger.info("**** Reading the maze from file " + inputFile);
        try{
            BufferedReader reader = new BufferedReader(new FileReader(inputFile));
            String line;
            String mazeString = "";
            while ((line = reader.readLine()) != null) {
                for (int idx = 0; idx < line.length(); idx++) {
                    if (line.charAt(idx) == '#') {
                        mazeString += "1";

                    } else if (line.charAt(idx) == ' ') {
                        mazeString += "0";
                    }
                }
                mazeString += System.lineSeparator();
            }
            Maze maze = new Maze(mazeString);
            
            if(path != null){
                boolean startToEnd = MazeSolver.verifyPath(maze,path,true);
                boolean endToSTart = MazeSolver.verifyPath(maze,path,false);
                if(startToEnd||endToSTart){
                    System.out.println("Correct Path");
                }
                else{
                    System.out.println("Incorrect Path");
                }
            }
            return maze;
        }
        catch(Exception e){
            logger.error("/!\\ An error has occured /!\\",e);
            return null;
            
        }
       
    }

}