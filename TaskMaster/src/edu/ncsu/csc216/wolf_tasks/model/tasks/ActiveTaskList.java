package edu.ncsu.csc216.wolf_tasks.model.tasks;

/**
 * Class that controls the currently active task list
 * 
 * @author Drew Bodnar
 */
public class ActiveTaskList extends AbstractTaskList {
	/** Variable that holds the name for the active task list */
	public static final String ACTIVE_TASKS_NAME = "Active Tasks";

	/**
	 * Constructor for the ActiveTaskList class
	 */
	public ActiveTaskList() {
		super(ACTIVE_TASKS_NAME, 0);
	}

	/**
	 * Method that adds a task to the list
	 * 
	 * @param t task to be added to the list
	 * @throws IllegalArgumentException if the task is not active
	 */
	@Override
	public void addTask(Task t) {
		if (!t.isActive()) {
			throw new IllegalArgumentException("Cannot add tasks to ActiveTasks");
		}
		super.addTask(t);
	}

	/**
	 * Method that sets the task list name
	 * 
	 * @param taskListName name to set the list name to
	 * @throws IllegalArgumentException if the task list name doesn't equal the
	 *                                  active tasks name
	 */
	@Override
	public void setTaskListName(String taskListName) {
		if (!taskListName.equals(ACTIVE_TASKS_NAME)) {
			throw new IllegalArgumentException("The Active Tasks list may not be edited.");
		}
		super.setTaskListName(taskListName);
	}

	/**
	 * Method that gets the current tasks as a 2d array
	 * 
	 * @return a 2d string array of tasks
	 */
	public String[][] getTasksAsArray() {
		String[][] tasksArray = new String[super.getTasks().size()][2];
		for (int i = 0; i < super.getTasks().size(); i++) {
			Task task = super.getTasks().get(i);
			tasksArray[i][0] = task.getTaskListName();
			tasksArray[i][1] = task.getTaskName();
		}
		return tasksArray;
	}

	/**
	 * Method that clears the tasks in the task list
	 */
	public void clearTasks() {
		while (super.getTasks().size() > 0) {
			super.getTasks().remove(0);
		}
	}
}
