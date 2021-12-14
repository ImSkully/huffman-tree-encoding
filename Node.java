/*
 * @author Skully (https://github.com/ImSkully)
 * @email contact@skully.tech
*/

public class Node {
    private Object item;
    private Node next;

    // Default constructor.
    public Node(Object newItem) {
        item = newItem;
        next = null;
    }

    // Argument constructor.
    public Node(Object newItem, Node nextNode) {
        item = newItem;
        next = nextNode;
    }

    // Getters.
    public Object getItem() {
        return item;
    }

    public Node getNext() {
        return next;
    }

    // Setters.
    public void setItem(Object newItem) {
        item = newItem;
    }

    public void setNext(Node nextNode) {
        next = nextNode;
    }

}
