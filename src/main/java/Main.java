import db.LogExporter;
import file.FileManager;
import file.FileSearcher;
import history.ActionHistory;
import history.UndoRedoManager;
import utils.MultiThreadedFileCopier;

import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws SQLException {
        Scanner read = new Scanner(System.in);
        FileManager fileManager = new FileManager();
        FileSearcher fileSearcher = new FileSearcher();
        ActionHistory history = new ActionHistory();
        UndoRedoManager undoRedo = new UndoRedoManager();

        int choice;

        do{
            System.out.println("\n←←←File Manager→→→");
            System.out.println("1. Create File");
            System.out.println("2. Delete File");
            System.out.println("3. Search in File");
            System.out.println("4. View History");
            System.out.println("5. Undo Last Action");
            System.out.println("6. Redo Last Action");
            System.out.println("7. Copy File (Multithreaded)");
            System.out.println("8. Export Logs to DB");
            System.out.println("0. Exit");
            System.out.print("Choose: ");
            choice = read.nextInt();
            read.nextLine();
            switch (choice) {
                case 1 -> {
                    System.out.print("Enter file name: ");
                    String filename = read.nextLine();
                    fileManager.createFile(filename);
                    history.add("Created file: " + filename);
                    undoRedo.addAction("CREATE " + filename);
                }
                case 2 -> {
                    System.out.print("Enter file name to delete: ");
                    String filename = read.nextLine();
                    fileManager.deleteFile(filename);
                    history.add("Deleted file: " + filename);
                    undoRedo.addAction("DELETE " + filename);
                }
                case 3 -> {
                    System.out.print("Enter file to search in: ");
                    String file = read.nextLine();
                    System.out.print("Enter pattern: ");
                    String pattern = read.nextLine();
                    fileSearcher.searchPattern(file, pattern);
                    history.add("Searched pattern '" + pattern + "' in " + file);
                }
                case 4 -> history.display();
                case 5 -> undoRedo.undo();
                case 6 -> undoRedo.redo();
                case 7 -> {
                    System.out.print("Source file: ");
                    String source = read.nextLine();
                    System.out.print("Destination file: ");
                    String dest = read.nextLine();
                    new MultiThreadedFileCopier().copy(source, dest);
                    history.add("Copied file from " + source + " to " + dest);
                }
                case 8 -> LogExporter.exportToDB(history);
                case 0 -> System.out.println("Exiting...");
                default -> System.out.println("Invalid choice.");
            }

        } while (choice != 0);
    }
}
        
    

