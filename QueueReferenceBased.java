/*
 * @author Skully (https://github.com/ImSkully)
 * @email contact@skully.tech
*/

public class QueueReferenceBased implements QueueInterface {
    private Node lastNode;
    private int size;

    // Default constructor.
    public QueueReferenceBased() {
        lastNode = null;
    }

    /**
     * dequeueAll()
     * Removes all items from the queue.
     */
    public void dequeueAll() {
        lastNode = null;
    }

    /**
     * enqueue(Object newItem)
     * Adds a new item to the end of the queue.
     * 
     * @param newItem The item to be added to the queue.
     * @returns true if the item was successfully added, false otherwise.
     */
    public void enqueue(Object newItem) {
        Node newNode = new Node(newItem);
        size++; // Increment the queue size.

        // Insert the new node.
        if (isEmpty()) {
            // Insertion into empty queue.
            newNode.setNext(newNode);
        } else {
            // Insertion into non-empty queue.
            newNode.setNext(lastNode.getNext());
            lastNode.setNext(newNode);
        }

        lastNode = newNode; // new node is at back.
    }

    /**
     * dequeue()
     * Removes the item at the front of the queue.
     * 
     * @return The item that was removed from the queue.
     * @throws QueueException if the queue is empty.
     */
    public Object dequeue() throws QueueException {
        if (!isEmpty()) {
            size--; // Decrement the queue size.
            // Remove the front node since the queue is not empty.
            Node firstNode = lastNode.getNext();
            if (firstNode == lastNode) {
                lastNode = null;
            } else {
                lastNode.setNext(firstNode.getNext());
            }
            return firstNode.getItem();
        } else {
            throw new QueueException("QueueException on dequeue: queue empty");
        }
    }

    /**
     * peek()
     * 
     * @return The object at the front of the queue.
     */
    public Object peek() throws QueueException {
        if (!isEmpty()) {
            // Queue is not empty; get the first node.
            Node firstNode = lastNode.getNext();
            return firstNode.getItem();
        } else {
            throw new QueueException("QueueException on peek: queue empty");
        }
    }

    /**
     * getSize()
     * Gets the current size of the queue.
     * 
     * @return The size of the queue.
     */
    public int getSize() {
        return this.size;
    }

    /**
     * isEmpty()
     * Checks if the queue is empty.
     * 
     * @return true if the queue is empty, false otherwise.
     */
    public boolean isEmpty() {
        return lastNode == null;
    }
}