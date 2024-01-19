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
        String inputFile = null;

        try {
            CommandLine commandLine = parser.parse(options,args);
            if(commandLine.hasOption("i") || commandLine.hasOption("input")){
                inputFile = commandLine.getOptionValue("i");
                Maze maze = generateMaze(inputFile);
                return maze;
            }

        } catch(Exception e) {
            logger.error("/!\\ An error has occured /!\\");
        }
        return new Maze();
        
    }

    private static Maze generateMaze(String inputFile){
        logger.info("**** Reading the maze from file " + inputFile);
        try{
            BufferedReader reader = new BufferedReader(new FileReader(inputFile));
            String line;
            while ((line = reader.readLine()) != null) {
                for (int idx = 0; idx < line.length(); idx++) {
                    if (line.charAt(idx) == '#') {
                        System.out.print("WALL ");
                    } else if (line.charAt(idx) == ' ') {
                        System.out.print("PASS ");
                    }
                }
                System.out.print((System.lineSeparator()));
            }
            return new Maze();
        }
        catch(Exception e){
            logger.error("/!\\ An error has occured /!\\");
        }
        return new Maze();
       
    }

}