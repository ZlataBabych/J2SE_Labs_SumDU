package ua.edu.sumdu.j2se.BabychZlata.tasks;

import java.util.Arrays;

/**
 * Class ArrayTaskList
 * Represents a list of tasks stored in an array
 */
public class ArrayTaskList {
    private Task[] tasks;
    private int size;

    /**
     * Constructor
     * Initializes the array with a default size of 10
     */
    public ArrayTaskList() {
        tasks = new Task[10];
        size = 0;
    }

    /**
     * Method to add a task to the list
     * @param task
     * @throws IllegalArgumentException if the task is null
     */
    public void add(Task task) {
        if (task == null) {
            throw new IllegalArgumentException("Task cannot be null.");
        }
        if (size == tasks.length) {
            tasks = Arrays.copyOf(tasks, size * 2);  // Double the size if array is full
        }
        tasks[size++] = task;
    }

    /**
     * Method to remove a task from the list
     * @param task
     * @throws IllegalArgumentException if the task is null
     * @return true if the task was removed, false otherwise
     */
    public boolean remove(Task task) {
        if (task == null) {
            throw new IllegalArgumentException("Task cannot be null.");
        }
        for (int i = 0; i < size; i++) {
            if (tasks[i].equals(task)) {
                // Shift remaining elements to the left
                System.arraycopy(tasks, i + 1, tasks, i, size - i - 1);
                tasks[--size] = null;  // Decrease size and nullify the last task
                return true;
            }
        }
        return false;
    }

    /**
     * Method to get the size of the list
     * @return size
     */
    public int size() {
        return size;
    }

    /**
     * Method to get a task at a specific index
     * @param index
     * @throws IndexOutOfBoundsException if the index is out of bounds
     * @return task at the specified index
     */
    public Task getTask(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index is out of bounds.");
        }
        return tasks[index];
    }

    /**
     * Method to get a list of tasks that are scheduled to be executed at least once after the specified time
     * @param from
     * @param to
     * @return list of tasks that are scheduled to be executed at least once after the specified time
     */
    public ArrayTaskList incoming(int from, int to) {
        ArrayTaskList result = new ArrayTaskList();
        for (int i = 0; i < size; i++) {
            Task task = tasks[i];
            int nextTime = task.nextTimeAfter(from);
            // If the task is repeated, check if the next execution time is within the specified time range
            if (nextTime != -1 && nextTime <= to) {
                result.add(task);
            }
        }
        return result;
    }
}
