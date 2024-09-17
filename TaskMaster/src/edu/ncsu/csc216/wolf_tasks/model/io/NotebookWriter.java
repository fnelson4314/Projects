/**
 * 
 */
package edu.ncsu.csc216.wolf_tasks.model.io;

import java.io.File;
import java.io.PrintStream;

import edu.ncsu.csc216.wolf_tasks.model.tasks.TaskList;
import edu.ncsu.csc216.wolf_tasks.model.util.ISortedList;

/**
 * Class to write notebook info to a file
 * 
 * @author Foster Nelson
 * @author Drew Bodnar
 */
public class NotebookWriter {

	/**
	 * Method to write notebook information to a file
	 * 
	 * @param f    file to write the info to
	 * @param s    name of the notebook
	 * @param list the sorted list of task lists to write to the file
	 * @throws IllegalArgumentException if the file cannot be saved
	 */
	public static void writeNotebookFile(File f, String s, ISortedList<TaskList> list) {
		try {
			PrintStream fileWriter = new PrintStream(f);
			fileWriter.println("! " + s);
			for (int i = 0; i < list.size(); i++) {
				fileWriter.println("# " + list.get(i).getTaskListName() + "," + list.get(i).getCompletedCount());
				for (int j = 0; j < list.get(i).getTasksAsArray().length; j++) {
					fileWriter.println(list.get(i).getTask(j).toString());
				}
			}

			fileWriter.close();
		} catch (Exception e) {
			throw new IllegalArgumentException("Unable to save file.");
		}
	}
}
