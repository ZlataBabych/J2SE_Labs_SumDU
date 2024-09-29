package ua.edu.sumdu.j2se.BabychZlata.tasks;

/**
 * Class LinkedTaskList implements a linked list
 * to store tasks. Each task is stored in a Node.
 * The list has a head and a size.
 */
public class LinkedTaskList {

    /**
     * Class Node represents a single element in the list.
     * Each node contains a task and a reference to the next node.
     * The last node has a null reference.
     */
    private class Node {
        Task task;
        Node next;

        /**
         * Constructor for a new node.
         * @param task
         */
        Node(Task task) {
            this.task = task;
            this.next = null;
        }
    }

    // Head of the list
    private Node head;
    // Size of the list
    private int size;

    /**
     * Constructor for a new linked list.
     */
    public LinkedTaskList() {
        head = null;
        size = 0;
    }

    /**
     * Method to add a task to the list.
     * The task is added to the end of the list.
     * @param task
     * @throws IllegalArgumentException if the task is null
     */
    public void add(Task task) {
        if (task == null) {
            throw new IllegalArgumentException("Task cannot be null.");
        }
        Node newNode = new Node(task);
        if (head == null) {
            head = newNode;
        } else {
            Node current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newNode;
        }
        size++;
    }

    /**
     * Method to remove a task from the list.
     * The task is removed if it exists in the list.
     * @param task
     * @return true if the task was removed, false otherwise
     * @throws IllegalArgumentException if the task is null
     */
    public boolean remove(Task task) {
        if (task == null) {
            throw new IllegalArgumentException("Task cannot be null.");
        }
        if (head == null) {
            return false;
        }
        if (head.task.equals(task)) {
            head = head.next;
            size--;
            return true;
        }
        Node current = head;
        while (current.next != null) {
            if (current.next.task.equals(task)) {
                current.next = current.next.next;
                size--;
                return true;
            }
        }
        return false;
    }

    /**
     * Method to get the size of the list.
     *  The size is the number of tasks in the list.
     * @return size
     */
    public int size() {
        return size;
    }

    /**
     * Method to get a task at a specific index.
     * The index must be within the bounds of the list.
     * @param index
     * @return task at the specified index
     * @throws IndexOutOfBoundsException if the index is out of bounds
     */
    public Task getTask(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index is out of bounds.");
        }
        Node current = head;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        return current.task;
    }

    /**
     * Method to get a string representation of the list.
     *  The string contains the tasks in the list.
     * @return string representation of the list
     */
    public LinkedTaskList incoming(int from, int to) {
        LinkedTaskList result = new LinkedTaskList();
        Node current = head;
        while (current != null) {
            Task task = current.task;
            int nextTime = task.nextTimeAfter(from);
            if (nextTime != -1 && nextTime <= to) {
                result.add(task);
            }
            current = current.next;
        }
        return result;
    }
}
