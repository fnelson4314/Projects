/**
 * 
 */
package edu.ncsu.csc216.wolf_tasks.model.tasks;

import edu.ncsu.csc216.wolf_tasks.model.util.ISwapList;

/**
 * Class that extends the AbstractTaskList class and creates a general task list
 * 
 * @author Drew Bodnar
 */
public class TaskList extends AbstractTaskList implements Comparable<TaskList> {
	/**
	 * Constructor for the TaskList class
	 * 
	 * @param taskListName name of the task list
	 * @param priority     priority of the current task list
	 * @throws IllegalArgumentException if the name is Active Tasks
	 */
	public TaskList(String taskListName, int priority) {
		super(taskListName, priority);
		if ("Active Tasks".equals(taskListName)) {
			throw new IllegalArgumentException("Invalid name.");
		}

	}

	/**
	 * Method to return the task list as an array with the priority in the first
	 * column
	 * 
	 * @return String array of task information
	 */
	public String[][] getTasksAsArray() {
		ISwapList<Task> tasks = super.getTasks();
		String[][] taskArray = new String[tasks.size()][2];
		for (int i = 0; i < tasks.size(); i++) {
			Task task = tasks.get(i);
			// This gives the priority
			taskArray[i][0] = String.valueOf(i + 1);
			// This gives the task name
			taskArray[i][1] = task.getTaskName();
		}
		return taskArray;
	}

	/**
	 * Method to compare task list names
	 * 
	 * @return int of the difference between the task lists
	 * @param t the task list being compared
	 */
	public int compareTo(TaskList t) {
		// Compares the taskListName to ts taskListName
		return getTaskListName().compareTo(t.getTaskListName());
	}
}
