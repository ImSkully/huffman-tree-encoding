/*
 * @author Skully (https://github.com/ImSkully)
 * @email contact@skully.tech
*/

public interface QueueInterface {
	/**
	 * isEmpty()
	 * Checks if the queue is empty.
	 * 
	 * @return true if the queue is empty, false otherwise.
	 */
	public boolean isEmpty();

	/**
	 * enqueue(Object newItem)
	 * Adds a new item to the back of the queue.
	 * 
	 * @param newItem The item to be added to the queue.
	 * @returns true if the item was successfully added, false otherwise.
	 * @throws QueueException if the queue is full.
	 */
	public void enqueue(Object newItem) throws QueueException;

	/**
	 * dequeue()
	 * Removes the front item from the queue.
	 * 
	 * @returns The item that was removed from the queue.
	 * @throws QueueException if the queue is empty.
	 */
	public Object dequeue() throws QueueException;

	/**
	 * dequeueAll()
	 * Removes all items from the queue.
	 * 
	 * @returns true if the queue was successfully emptied, false otherwise.
	 * @throws QueueException if the queue is empty.
	 */
	public void dequeueAll();

	/**
	 * peek()
	 * Returns the front item in the queue.
	 * 
	 * @returns The front item in the queue.
	 * @throws QueueException if the queue is empty.
	 */
	public Object peek() throws QueueException;
}