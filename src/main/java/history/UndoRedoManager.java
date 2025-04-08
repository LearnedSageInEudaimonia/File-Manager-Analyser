package history;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class UndoRedoManager {
    private final Stack<String> undoStack = new Stack<>();
    private final Queue<String> redoQueue = new LinkedList<>();

    public void addAction(String action){
        undoStack.push(action);
        redoQueue.clear();
    }

    public void undo(){
        if(undoStack.isEmpty()){
            System.out.println("Nothing to undo");
            return;
        }
        String lastAction = undoStack.pop();
        redoQueue.offer(lastAction);
        System.out.println("Undid : " + lastAction);
    }

    public void redo(){
        if(redoQueue.isEmpty()){
            System.out.println("Nothing to redo");
            return;
        }
        String nextAction = redoQueue.poll();
        undoStack.push(nextAction);
        System.out.println("Redid : " + nextAction);
    }
}
