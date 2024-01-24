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

            if (inputFile != null) {
                return generateMaze(inputFile, path);
            } else {
                logger.error("Please provide an input file using the -i option.");
            }

        } catch(Exception e) {
            logger.error("/!\\ An error has occured /!\\",e);
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
                        //System.out.print("WALL ");

                    } else if (line.charAt(idx) == ' ') {
                        mazeString += "0";
                        //System.out.print("PASS ");
                    }
                }
                mazeString += System.lineSeparator();
                //System.out.print((System.lineSeparator()));
            }
            
            Maze maze = new Maze(mazeString);
            
            if(path != null){
                String newPath = convertFactorizedForm(path);
                boolean startToEnd = MazeSolver.verifyPath(maze,newPath,true);
                boolean endToSTart = MazeSolver.verifyPath(maze,newPath,false);
                if(startToEnd||endToSTart){
                    logger.info("User Path is vaild " + path);
                }
                else{
                    logger.info("User Path is not vaild " + path);
                }
            }
            return maze;
        }
        catch(Exception e){
            logger.error("/!\\ An error has occured /!\\",e);
            
        }
        return null;
       
    }

    private static String convertFactorizedForm(String path){
        String result = "";
        String repeat = "";
        int i = 0;
        String modifiedPath = path.replaceAll(" ", "");
        while(i < modifiedPath.length()){
            if(Character.isDigit(modifiedPath.charAt(i))){
                while(Character.isDigit(modifiedPath.charAt(i)) && i < modifiedPath.length()){
                    repeat += String.valueOf(modifiedPath.charAt(i));
                    i++;
                }
                int val = Integer.valueOf(repeat);
                System.out.println(val);

                if(i < modifiedPath.length()){
                    char repeatedLetter = modifiedPath.charAt(i);
                    for (int x = 0; x < val; x++) {
                        result += repeatedLetter;
                    }
                    repeat = "";
                    i++;
                }
               
            }
            else{
                result += modifiedPath.charAt(i);
                i++;
            }
        }
        System.out.println(result);
        return result;

    }

}