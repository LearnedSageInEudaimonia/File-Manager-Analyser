package file;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class FileSearcher {
    public void searchPattern(String filepath,String pattern){
        try(BufferedReader reader = new BufferedReader(new FileReader(filepath))){
            String line;
            int lineNum = 1;
            boolean found = false;

            while((line = reader.readLine()) != null){
                if(line.contains(pattern)){
                    System.out.println("Found at line " + lineNum + ":" + line);
                    found = true;
                }
                lineNum++;
            }
            if(!found){
                System.out.println("Pattern not found");
            }

        }catch (IOException e){
            System.out.println("Error : " + e.getMessage());
        }
    }
}
