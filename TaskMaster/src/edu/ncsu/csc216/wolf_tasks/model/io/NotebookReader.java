/**
 * 
 */
package edu.ncsu.csc216.wolf_tasks.model.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

import edu.ncsu.csc216.wolf_tasks.model.notebook.Notebook;
import edu.ncsu.csc216.wolf_tasks.model.tasks.AbstractTaskList;
import edu.ncsu.csc216.wolf_tasks.model.tasks.Task;
import edu.ncsu.csc216.wolf_tasks.model.tasks.TaskList;

/**
 * Class that reads in information for a notebook
 * 
 * @author Foster Nelson
 * @author Drew Bodnar
 */
public class NotebookReader {
	/** Variable to tell if a task is recurring or not */
	public static boolean recurring = false;
	/** Variable to tell if a task is active or not */
	public static boolean active = false;

	/**
	 * Method to read in info from a file and create a notebook
	 * 
	 * @param fileName file to read in info from
	 * @return a notebook object with given info
	 * @throws IllegalArgumentException if the file cannot be loaded
	 */
	public static Notebook readNotebookFile(File fileName) {
		try (Scanner fileReader = new Scanner(new FileInputStream(fileName))) {
			String notebookName = "";
			if (fileReader.hasNextLine()) {
				notebookName = fileReader.nextLine().trim();
				if (notebookName.isEmpty() || !notebookName.startsWith("!")) {
					throw new IllegalArgumentException("Unable to load file.");
				}
				notebookName = notebookName.substring(1).trim();
			} else {
				throw new IllegalArgumentException("Unable to load file.");
			}
			Notebook notebook = new Notebook(notebookName);
			while (fileReader.hasNextLine()) {
				String taskListToken = fileReader.nextLine().trim();
				if (taskListToken.startsWith("#")) {
					notebook.addTaskList(processTaskList(taskListToken.substring(1)));
				} else if (taskListToken.startsWith("*")) {
					notebook.addTask(processTask(notebook.getCurrentTaskList(), taskListToken.substring(1)));
				}
			}
			notebook.setCurrentTaskList(Notebook.ACTIVE_TASKS_NAME);
			return notebook;

		} catch (FileNotFoundException e) {
			throw new IllegalArgumentException("Unable to load file.");
		}
	}

	/**
	 * Method to process a task lisk given from the file
	 * 
	 * @param s a string of task list info to be pulled apart
	 * @return the processed task list
	 */
	private static TaskList processTaskList(String s) {
		Scanner scnr2 = new Scanner(s);
		scnr2.useDelimiter(",");
		String taskListToken = scnr2.next();
		String taskListName = taskListToken.trim();
		if (taskListName.isEmpty()) {
			scnr2.close();
			return null;
		}
		taskListName = taskListName.trim();
		if ("Active Tasks".equals(taskListName)) {
			scnr2.close();
			return null;
		}
		if (!scnr2.hasNext()) {
			scnr2.close();
			return null;
		}
		String completed = scnr2.next();
		int completed2 = Integer.parseInt(completed);
		if (completed2 < 0) {
			scnr2.close();
			return null;
		}
		TaskList taskList = new TaskList(taskListName, completed2);

		scnr2.close();
		return taskList;
	}

	/**
	 * Method to process a Task given from the file
	 * 
	 * @param list list to put the processed task in
	 * @param s    a string of the task info to be pulled apart
	 * @return the processed task object
	 */
	private static Task processTask(AbstractTaskList list, String s) {
		Scanner scnr3 = new Scanner(s);
		scnr3.useDelimiter(",");
		String taskName = scnr3.next();
		taskName = taskName.substring(1, taskName.length());
		if (taskName.isEmpty()) {
			scnr3.close();
			return null;
		}
		while (scnr3.hasNext()) {
			String check = scnr3.next().trim();
			if ("recurring".equals(check)) {
				recurring = true;
			} else if ("active".equals(check)) {
				active = true;
			}
		}
		String taskDescription = "";
		while (scnr3.hasNextLine()) {
			taskDescription += scnr3.nextLine().trim() + "\n";
		}
		taskDescription = taskDescription.trim();

		Task task = new Task(taskName, taskDescription, recurring, active);

		if (active && list.getTaskListName().equals(Notebook.ACTIVE_TASKS_NAME)) {
			list.addTask(task);
		}

		recurring = false;
		active = false;
		scnr3.close();
		return task;

	}
}