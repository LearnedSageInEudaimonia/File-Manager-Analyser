package file;

import java.io.File;
import java.io.IOException;

public class FileManager {
    public void createFile(String filename){
        File file = new File(filename);
        try{
            if(file.createNewFile()){
                System.out.println("File Created : " + filename);
            }
            else{
                System.out.println("File Already Exists");
            }
        }catch (IOException e){
            System.out.println("Error creating file : " + e.getMessage());
        }
    }

    public void deleteFile(String filename){
        File file = new File(filename);
        if(file.delete()){
            System.out.println("File deleted : " + filename);
        }
        else{
            System.out.println("Unable to delete file.");
        }
    }
}
