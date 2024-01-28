package ca.mcmaster.se2aa4.mazerunner;

public class PathConverter {

    public static String convertToFactorized(String path){
        String factorizedPath = "";
        int i = 0;
        while(i < path.length()){
            char current = path.charAt(i);
            int counter = 0;
            while(i < path.length() && path.charAt(i) == current){
                counter += 1;
                i++;
            }
            if(counter == 1)
                factorizedPath += current + " ";
            else
                factorizedPath += String.valueOf(counter) + current + " ";
        }
        return factorizedPath;
    }

    public static String convertToCanonical(String path){
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
        return result;

    }

    
}