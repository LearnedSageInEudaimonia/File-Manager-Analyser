package history;

public class ActionHistory {

    private static class Node {
        String action;
        Node next;

        Node(String action) {
            this.action = action;
        }
    }

    private Node head;

    public void add(String action) {
        Node newNode = new Node(action);
        newNode.next = head;
        head = newNode;
    }

    public void display() {
        if (head == null) {
            System.out.println("📭 No history yet.");
            return;
        }

        System.out.println("📜 Action History:");
        Node current = head;
        while (current != null) {
            System.out.println("- " + current.action);
            current = current.next;
        }
    }

    public String[] getAll() {
        StringBuilder builder = new StringBuilder();
        Node current = head;
        while (current != null) {
            builder.append(current.action).append("\n");
            current = current.next;
        }
        return builder.toString().split("\n");
    }
}
