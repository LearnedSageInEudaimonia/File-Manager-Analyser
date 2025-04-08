package utils;

import java.io.*;

public class MultiThreadedFileCopier {
    public void copy(String source, String dest){
        Thread thread = new Thread(()->{
           try(BufferedReader reader = new BufferedReader(new FileReader(source));
               BufferedWriter writer = new BufferedWriter(new FileWriter(dest))){

               String line;
               while((line = reader.readLine()) != null){
                   writer.write(line);
                   writer.newLine();
               }
               System.out.println("Copied from " + source + " to " + dest);
           } catch (IOException e) {
               throw new RuntimeException(e);
           }
        });
        thread.start();
    }
}
