/**
 * 
 */
package edu.ncsu.csc216.wolf_tasks.model.notebook;

import java.io.File;

import edu.ncsu.csc216.wolf_tasks.model.io.NotebookWriter;
import edu.ncsu.csc216.wolf_tasks.model.tasks.AbstractTaskList;
import edu.ncsu.csc216.wolf_tasks.model.tasks.ActiveTaskList;
import edu.ncsu.csc216.wolf_tasks.model.tasks.Task;
import edu.ncsu.csc216.wolf_tasks.model.tasks.TaskList;
import edu.ncsu.csc216.wolf_tasks.model.util.ISortedList;
import edu.ncsu.csc216.wolf_tasks.model.util.ISwapList;
import edu.ncsu.csc216.wolf_tasks.model.util.SortedList;

/**
 * Class that controls the main actions of the task lists
 * 
 * @author Foster Nelson
 */
public class Notebook {
	/** Variable to hold the notebook name */
	private String notebookName;
	/** Variable to tell if the notebook has changed or not */
	private boolean isChanged;
	/** Variable to hold a sorted list of all task lists */
	private ISortedList<TaskList> taskLists;
	/** Variable to hold an ActiveTaskList object */
	private ActiveTaskList activeTaskList;
	/** Variable to hold an AbstractTaskList for the current task list */
	private AbstractTaskList currentTaskList;
	/** Variable for the active task list name */
	public static final String ACTIVE_TASKS_NAME = "Active Tasks";

	/**
	 * Constructor for the Notebook class
	 * 
	 * @param notebookName name of the notebook
	 */
	public Notebook(String notebookName) {
		setNotebookName(notebookName);
		taskLists = new SortedList<TaskList>();
		activeTaskList = new ActiveTaskList();
		currentTaskList = activeTaskList;
		setChanged(true);
	}

	/**
	 * Method to save the current notebook information to a file
	 * 
	 * @param notebookFile file to save the information to
	 */
	public void saveNotebook(File notebookFile) {
		NotebookWriter.writeNotebookFile(notebookFile, notebookName, taskLists);
		isChanged = false;
	}

	/**
	 * Method to get the current notebook name
	 * 
	 * @return a string of the notebook name
	 */
	public String getNotebookName() {
		return notebookName;
	}

	/**
	 * Method to set the notebook name
	 * 
	 * @param notebookName given name to set the notebook to
	 * @throws IllegalArgumentException it the notebookName is null or equals Active
	 *                                  Tasks
	 */
	private void setNotebookName(String notebookName) {
		if (notebookName == null || "".equals(notebookName) || notebookName.equals(ACTIVE_TASKS_NAME)) {
			throw new IllegalArgumentException("Invalid name.");
		}
		this.notebookName = notebookName;
	}

	/**
	 * Method to tell if the notebook has changed since last time
	 * 
	 * @return a boolean telling if the notebook has changed
	 */
	public boolean isChanged() {
		return isChanged;
	}

	/**
	 * Method that sets whether a notebook has been changed or not
	 * 
	 * @param isChanged boolean telling whether the notebook has been changed
	 */
	public void setChanged(boolean isChanged) {
		this.isChanged = isChanged;
	}

	/**
	 * Method to add a task list to the list of task lists
	 * 
	 * @param taskList task list to add
	 * @throws IllegalArgumentException if the taskName equals ActiveTasks or the
	 *                                  list has already been added
	 */
	public void addTaskList(TaskList taskList) {
		for (int i = 0; i < taskLists.size(); i++) {
			if (taskList.getTaskListName().equals(ACTIVE_TASKS_NAME)
					|| taskList.getTaskListName().equals(taskLists.get(i).getTaskListName())) {
				throw new IllegalArgumentException("Invalid name.");
			}
		}
		if (taskList != null) {
			taskLists.add(taskList);
			setCurrentTaskList(taskList.getTaskListName());
			isChanged = true;
		}
	}

	/**
	 * Method to get a String array of the task list names
	 * 
	 * @return a String array of task list names
	 */
	public String[] getTaskListsNames() {
		String[] s = new String[taskLists.size() + 1];
		s[0] = "Active Tasks";
		for (int i = 0; i < taskLists.size(); i++) {
			s[i + 1] = taskLists.get(i).getTaskListName();
		}
		return s;
	}

	/**
	 * Method to get the currently active task list
	 */
	private void getActiveTaskList() {
		activeTaskList.clearTasks();
		for (int i = 0; i < taskLists.size(); i++) {
			ISwapList<Task> tasks = taskLists.get(i).getTasks();
			for (int j = 0; j < tasks.size(); j++) {
				if (tasks.get(j).isActive()) {
					activeTaskList.addTask(tasks.get(j));
				}
			}
		}
	}

	/**
	 * Method to set the current task list
	 * 
	 * @param s name of the task list to set as current
	 */
	public void setCurrentTaskList(String s) {
		int count = 0;
		for (int i = 0; i < taskLists.size(); i++) {
			if (s.equals(taskLists.get(i).getTaskListName())) {
				currentTaskList = taskLists.get(i);
				count = 1;
			}
		}
		if (count == 0) {
			currentTaskList = activeTaskList;
		}
	}

	/**
	 * Method to get the current task list
	 * 
	 * @return an AbstractTaskList of the current task list
	 */
	public AbstractTaskList getCurrentTaskList() {
		return currentTaskList;
	}

	/**
	 * Method to edit the given task list
	 * 
	 * @param taskListName name of the task list to be edited
	 * @throws IllegalArgumentException if the active tasks lists is edited
	 * @throws IllegalArgumentException if the name is Active Tasks or the name
	 *                                  already exists
	 */
	public void editTaskList(String taskListName) {
		for (int i = 0; i < taskLists.size(); i++) {
			if (currentTaskList instanceof ActiveTaskList) {
				throw new IllegalArgumentException("The Active Tasks list may not be edited.");
			}

			else if ("Active Tasks".equals(taskListName) || taskLists.get(i).getTaskListName().equals(taskListName)) {
				throw new IllegalArgumentException("Invalid name.");
			}
		}

		for (int i = 0; i < taskLists.size(); i++) {
			if (taskLists.get(i).getTaskListName().equals(currentTaskList.getTaskListName())) {
				TaskList list2 = taskLists.remove(i);
				list2.setTaskListName(taskListName);
				taskLists.add(list2);
				setChanged(true);
				break;
			}
		}

	}

	/**
	 * Method to remove a task list from the list
	 * 
	 * @throws IllegalArgumentException if the Active Tasks lit attempts to be
	 *                                  deleted
	 */
	public void removeTaskList() {
		if (currentTaskList instanceof ActiveTaskList) {
			throw new IllegalArgumentException("The Active Tasks list may not be deleted.");
		}

		for (int i = 0; i < taskLists.size(); i++) {
			if (taskLists.get(i).getTaskListName().equals(currentTaskList.getTaskListName())) {
				taskLists.remove(i);
			}
		}
		currentTaskList = activeTaskList;
		setChanged(true);
	}

	/**
	 * Method to add the given task to the task list
	 * 
	 * @param t task to be added
	 */
	public void addTask(Task t) {
		if (t != null && currentTaskList instanceof TaskList) {
			currentTaskList.addTask(t);
			if (t.isActive()) {
				getActiveTaskList();
			}
			setChanged(true);
		}

	}

	/**
	 * Method to edit the given task
	 * 
	 * @param idx             index of the task
	 * @param taskName        name of the task
	 * @param taskDescription description for the task
	 * @param recurring       whether the task is recurring
	 * @param active          whether the task is active
	 */
	public void editTask(int idx, String taskName, String taskDescription, boolean recurring, boolean active) {
		if (currentTaskList instanceof TaskList) {
			boolean active1 = currentTaskList.getTask(idx).isActive();
			currentTaskList.getTasks().get(idx).setTaskName(taskName);
			currentTaskList.getTasks().get(idx).setTaskDescription(taskDescription);
			currentTaskList.getTasks().get(idx).setRecurring(recurring);
			currentTaskList.getTasks().get(idx).setActive(active);
			if (active && !active1) {
				getActiveTaskList();
				Task task = new Task(taskName, taskDescription, recurring, active);
				task.addTaskList(currentTaskList);
			} else if (active1) {
				activeTaskList.clearTasks();
				ISwapList<Task> tasks = currentTaskList.getTasks();
				for (int j = 0; j < tasks.size(); j++) {
					if (tasks.get(j).isActive()) {
						activeTaskList.addTask(tasks.get(j));
					}
				}
			}
			setChanged(true);

		}

	}
}
