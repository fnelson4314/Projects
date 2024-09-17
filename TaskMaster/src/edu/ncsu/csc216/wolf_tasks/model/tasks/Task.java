/**
 * 
 */
package edu.ncsu.csc216.wolf_tasks.model.tasks;

import edu.ncsu.csc216.wolf_tasks.model.util.ISwapList;
import edu.ncsu.csc216.wolf_tasks.model.util.SwapList;

/**
 * Class that holds the information for a task with the ability to grab and set
 * the info as well
 * 
 * @author Drew Bodnar
 */
public class Task implements Cloneable {
	/** Variable to hold the current task name */
	private String taskName;
	/** Variable to hold the current task description */
	private String taskDescription;
	/** Variable to tell if a task is set as recurring or not */
	private boolean recurring;
	/** Variable to tell if a task is set as active or not */
	private boolean active;
	/** Variable to hold the current task lists */
	private ISwapList<AbstractTaskList> taskLists;

	/**
	 * Constructor for the Task class
	 * 
	 * @param taskName        name assigned to the current task
	 * @param taskDescription description assigned to the current task
	 * @param recurring       tells whether or not the current task is recurring
	 * @param active          tells whether or not the current task is active
	 */
	public Task(String taskName, String taskDescription, boolean recurring, boolean active) {
		setTaskName(taskName);
		setTaskDescription(taskDescription);
		setRecurring(recurring);
		setActive(active);
		this.taskLists = new SwapList<>();
	}

	/**
	 * Method to get the current task name
	 * 
	 * @return a string of the name
	 */
	public String getTaskName() {
		return taskName;
	}

	/**
	 * Method to set the current task name
	 * 
	 * @param taskName the name to be set to
	 * @throws IllegalArgumentException if the name is null or empty
	 */
	public void setTaskName(String taskName) {
		if (taskName == null || taskName.isEmpty()) {
			throw new IllegalArgumentException("Incomplete task information.");
		}
		this.taskName = taskName;
	}

	/**
	 * Method to get the current task description
	 * 
	 * @return a string of the description
	 */
	public String getTaskDescription() {
		return taskDescription;
	}

	/**
	 * Method to set the current task description
	 * 
	 * @param taskDescription the description to be set to
	 * @throws IllegalArgumentException if the task description is null
	 */
	public void setTaskDescription(String taskDescription) {
		if (taskDescription == null) {
			throw new IllegalArgumentException("Incomplete task information.");
		}
		this.taskDescription = taskDescription;
	}

	/**
	 * Method to get whether a task is recurring or not
	 * 
	 * @return whether the task is recurring
	 */
	public boolean isRecurring() {
		return recurring;
	}

	/**
	 * Method to set the recurring value
	 * 
	 * @param recurring the recurring value being set
	 */
	public void setRecurring(boolean recurring) {
		this.recurring = recurring;
	}

	/**
	 * Method to set whether a task is active or not
	 * 
	 * @return whether the task is active
	 */
	public boolean isActive() {
		return active;
	}

	/**
	 * Method to set a task as active or not
	 * 
	 * @param active the value to set active as
	 */
	public void setActive(boolean active) {
		this.active = active;
	}

	/**
	 * Method to get the current task list name
	 * 
	 * @return a string of the task list name
	 */
	public String getTaskListName() {
		if (taskLists == null || taskLists.size() == 0) {
			return "";
		}
		return taskLists.get(0).getTaskListName();
	}

	/**
	 * Add a Task List to the current list
	 * 
	 * @param taskList the AbstractTaskList to be added
	 * @throws IllegalArgumentException if the task list is null
	 */
	public void addTaskList(AbstractTaskList taskList) {
		if (taskList == null) {
			throw new IllegalArgumentException("Invalid task information.");
		}
		taskLists.add(taskList);
	}

	/**
	 * Update a task to be shown as completed
	 * 
	 * @throws IllegalArgumentException if the task list cannot be cloned
	 */
	public void completeTask() {
		if (recurring) {
			try {
				if (taskLists == null) {
					throw new CloneNotSupportedException();
				}
				Task clonedTask = (Task) this.clone();
				for (int i = 0; i < taskLists.size(); i++) {
					for (int j = 0; j < taskLists.get(i).getTasks().size(); j++) {
						if (taskLists.get(i).getTasks().get(j) == this) {
							taskLists.get(i).completeTask(this);
							taskLists.get(i).addTask(clonedTask);
						}
					}
				}
			} catch (CloneNotSupportedException e) {
				throw new IllegalArgumentException("Cannot clone.");
			}
		} else {
			for (int i = 0; i < taskLists.size(); i++) {
				for (int j = 0; j < taskLists.get(i).getTasks().size(); j++) {
					if (taskLists.get(i).getTasks().get(j) == this) {
						taskLists.get(i).completeTask(this);
					}
				}
			}
			active = false;
		}
	}

	/**
	 * Create a clone of a Task
	 * 
	 * @return an Object that is a clone of another Task
	 * @throws CloneNotSupportedException if the taskList cannot be cloned
	 */
	public Object clone() throws CloneNotSupportedException {
		if (taskLists == null || taskLists.size() == 0) {
			throw new CloneNotSupportedException("Cannot clone.");
		}
		Task clonedTask = (Task) super.clone();
		clonedTask.taskLists = new SwapList<>();
		clonedTask.taskName = this.taskName;
		clonedTask.taskDescription = this.taskDescription;
		clonedTask.recurring = this.recurring;
		clonedTask.active = this.active;
		for (int i = 0; i < taskLists.size(); i++) {
			clonedTask.taskLists.add(taskLists.get(i));
		}
		return clonedTask;

	}

	/**
	 * Returns a toString representation of the current task
	 * 
	 * @return a string of the current task in proper format
	 */
	public String toString() {
		if (active && recurring) {
			return "* " + taskName + ",recurring,active\n" + taskDescription;
		} else if (active && !recurring) {
			return "* " + taskName + ",active\n" + taskDescription;
		} else if (!active && recurring) {
			return "* " + taskName + ",recurring\n" + taskDescription;
		} else if (!active && !recurring) {
			return "* " + taskName + "\n" + taskDescription;
		}
		return "";

	}
}
