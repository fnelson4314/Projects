/**
 * 
 */
package edu.ncsu.csc216.wolf_tasks.model.tasks;

import edu.ncsu.csc216.wolf_tasks.model.util.ISwapList;
import edu.ncsu.csc216.wolf_tasks.model.util.SwapList;

/**
 * Abstract class at the top of the hierarchy for task lists
 * 
 * @author Drew Bodnar
 * 
 */
public abstract class AbstractTaskList {
	/** Variable that holds the ISwapList for tasks */
	private ISwapList<Task> tasks;
	/** Variable that holds the current task list name */
	private String taskListName;
	/** Variable that holds the total amount of completed tasks */
	private int completedCount;

	/**
	 * Constructor for the AbstractTaskList class
	 * 
	 * @param taskListName   name of the task list
	 * @param completedCount total amount of completed tasks
	 * @throws IllegalArgumentException if the completed count is less than 0
	 */
	public AbstractTaskList(String taskListName, int completedCount) {
		if (completedCount < 0) {
			throw new IllegalArgumentException("Invalid completed count.");
		}
		setTaskListName(taskListName);
		this.completedCount = completedCount;
		this.tasks = new SwapList<Task>();
	}

	/**
	 * Method to get the current task list name
	 * 
	 * @return a string of the name
	 */
	public String getTaskListName() {
		return taskListName;
	}

	/**
	 * Method that sets the task list name
	 * 
	 * @param taskListName name to be changed to
	 * @throws IllegalArgumentException if the name is null or empty
	 */
	public void setTaskListName(String taskListName) {
		if (taskListName == null || taskListName.isEmpty()) {
			throw new IllegalArgumentException("Invalid name.");
		}
		this.taskListName = taskListName;
	}

	/**
	 * Method that gets a list of the current task list tasks
	 * 
	 * @return an ISwapList of the current tasks
	 */
	public ISwapList<Task> getTasks() {
		return tasks;
	}

	/**
	 * Method to get the number of completed tasks
	 * 
	 * @return an integer of the completed tasks
	 */
	public int getCompletedCount() {
		return completedCount;
	}

	/**
	 * Method to add a task to the list
	 * 
	 * @param task task to be added to the list
	 */
	public void addTask(Task task) {
		if (task != null) {
			task.addTaskList(this);
			tasks.add(task);
		}
	}

	/**
	 * Method to remove a task from the list at a given index
	 * 
	 * @param i index to remove from
	 * @return the task that was removed
	 */
	public Task removeTask(int i) {
		return tasks.remove(i);
	}

	/**
	 * Method to get a task at the given index
	 * 
	 * @param i index of the task
	 * @return the task at the given index
	 */
	public Task getTask(int i) {
		return tasks.get(i);
	}

	/**
	 * Method to update a task as completed and remove it
	 * 
	 * @param task task to be marked as completed and removed
	 */
	public void completeTask(Task task) {
		if (task != null) {
			for (int i = 0; i < tasks.size(); i++) {
				if (tasks.get(i) == task) {
					tasks.remove(i);
					completedCount++;
					break;
				}
			}
		}
	}

	/**
	 * Method to return the current list of tasks as an array
	 * 
	 * @return a 2d string array of the tasks
	 */
	public abstract String[][] getTasksAsArray();

}
