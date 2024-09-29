package ua.edu.sumdu.j2se.BabychZlata.tasks;

/**
 * Class Task
 */
public class Task {
    private String title;
    private boolean active;
    private int time;
    private int start;
    private int end;
    private int interval;
    private boolean repeated;

    /**
     * Constructor
     * @param title The title of the task.
     * @param time The time the task is set to be executed.
     */
    public Task(String title, int time) {
        this.title = title;
        this.time = time;
        this.active = false;
        this.repeated = false;
    }

    /**
     * Constructor
     * @param title The title of the task.
     * @param start The start time of the task.
     * @param end The end time of the task.
     * @param interval The interval at which the task repeats.
     */
    public Task(String title, int start, int end, int interval) {
        this.title = title;
        this.start = start;
        this.end = end;
        this.interval = interval;
        this.active = false;
        this.repeated = true;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    /**
     * Gets the time
     * @return The time of the task.
     */
    public int getTime() {
        return repeated ? start : time;
    }

    /**
     * Sets the time
     * @param time The time for the non-repetitive task.
     */
    public void setTime(int time) {
        this.time = time;
        this.repeated = false;
    }

    /**
     * Gets the start time
     * @return The start time of the task.
     */
    public int getStartTime() {
        return repeated ? start : time;
    }

    /**
     * Gets the end time
     * @return The end time of the task.
     */
    public int getEndTime() {
        return repeated ? end : time;
    }

    /**
     * Gets the repeat interval
     * @return The repeat interval.
     */
    public int getRepeatInterval() {
        return repeated ? interval : 0;
    }

    /**
     * Sets the task as repetitive with a start time, end time, and interval
     * @param start The start time.
     * @param end The end time.
     * @param interval The interval for the repetitive task.
     */
    public void setTime(int start, int end, int interval) {
        this.start = start;
        this.end = end;
        this.interval = interval;
        this.repeated = true;
    }

    /**
     * Checks if the task is repetitive.
     * @return True if the task is repetitive, false otherwise.
     */
    public boolean isRepeated() {
        return repeated;
    }

    /**
     * Finds the next execution time of the task after the specified current time.
     * If there is no next execution time, returns -1.
     * @param current The current time.
     * @return The next execution time, or -1 if the task does not execute anymore.
     */
    public int nextTimeAfter(int current) {
        if (!active) {
            return -1;
        }
        if (!repeated) {
            return current < time ? time : -1;
        }
        if (current < start) {
            return start;
        }
        if (current >= end) {
            return -1;
        }
        int nextTime = start + ((current - start) / interval + 1) * interval;
        return nextTime <= end ? nextTime : -1;
    }

    /**
     * Checks if the task is equal to another task.
     * @param other The other task to compare.
     * @return True if the tasks are equal, false otherwise.
     */
    public boolean equals(Task other) {
        // Check if obj is a reference to the same object
        if (this == other) {
            return true;
        }

        // Compare all fields for equality (null-safe for title)
        return (title != null ? title.equals(other.title) : other.title == null) &&
                active == other.active &&
                time == other.time &&
                start == other.start &&
                end == other.end &&
                interval == other.interval &&
                repeated == other.repeated;
    }
}
