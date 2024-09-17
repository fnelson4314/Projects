/**
 * 
 */
package edu.ncsu.csc216.wolf_tasks.model.io;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import org.junit.jupiter.api.Test;

import edu.ncsu.csc216.wolf_tasks.model.notebook.Notebook;
import edu.ncsu.csc216.wolf_tasks.model.tasks.Task;
import edu.ncsu.csc216.wolf_tasks.model.tasks.TaskList;
import edu.ncsu.csc216.wolf_tasks.model.util.ISortedList;
import edu.ncsu.csc216.wolf_tasks.model.util.SortedList;

/**
 * Tests the NotebookWriter class
 * 
 * @author Foster Nelson
 */
public class NotebookWriterTest {
	/**
	 * Tests the writeNotebookFile method
	 */
	@Test
	public void testWriteNotebookFile() {
		Notebook n = new Notebook("Notebook");
		ISortedList<TaskList> list = new SortedList<TaskList>();
		TaskList taskList1 = new TaskList("ATaskList", 0);
		TaskList taskList2 = new TaskList("Tasks1", 0);
		TaskList taskList3 = new TaskList("Tasks2", 0);
		Task task1 = new Task("Task1", "Task1Description", true, false);
		Task task2 = new Task("Task2", "Task2Description", true, true);
		Task task3 = new Task("Task3", "Task3Description", false, false);
		Task task4 = new Task("Task4", "Task4Description", false, true);
		Task task5 = new Task("Task5", "Task5Description", true, false);
		taskList2.addTask(task1);
		taskList2.addTask(task2);
		taskList3.addTask(task3);
		taskList3.addTask(task4);
		taskList3.addTask(task5);
		list.add(taskList1);
		list.add(taskList2);
		list.add(taskList3);

		try {
			File file = new File("test-files/actual_notebook.txt");
			NotebookWriter.writeNotebookFile(file, n.getNotebookName(), list);
		} catch (Exception e) {
			fail("Cannot write to notebook file.");
		}

		checkFiles("test-files/expected_out.txt", "test-files/actual_notebook.txt");

	}

	/**
	 * Helper method to compare two files for the same contents
	 * 
	 * @param expFile expected output
	 * @param actFile actual output
	 */
	private void checkFiles(String expFile, String actFile) {
		try (Scanner expScanner = new Scanner(new File(expFile));
				Scanner actScanner = new Scanner(new File(actFile));) {

			while (expScanner.hasNextLine()) {
				assertEquals(expScanner.nextLine(), actScanner.nextLine());
			}

			expScanner.close();
			actScanner.close();
		} catch (IOException e) {
			fail("Error reading files.");
		}
	}
}
