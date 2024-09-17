package edu.ncsu.csc216.wolf_tasks.model.notebook;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import edu.ncsu.csc216.wolf_tasks.model.tasks.Task;
import edu.ncsu.csc216.wolf_tasks.model.tasks.TaskList;

/**
 * Tests the Notebook class
 * @author Foster Nelson
 */
public class NotebookTest {
	/**
	 * Tests the Notebook constructor
	 */
	@Test
	public void testNotebook() {
		Notebook n = assertDoesNotThrow(
				() -> new Notebook("Notebook"),
				"Should not throw exceptioin.");
		assertAll("Notebook",
				() -> assertEquals("Notebook", n.getNotebookName()),
				() -> assertTrue(n.isChanged()));
		
	}
	/**
	 * Testing the getTaskListsNames method
	 */
	@Test
	public void testGetTaskListsNames() {
		Notebook n = new Notebook("Notebook");
		TaskList tasklist1 = new TaskList("newsss", 1);
		TaskList tasklist3 = new TaskList("newss", 3);
		TaskList tasklist5 = new TaskList("newsss", 5);
		n.addTaskList(tasklist1);
		assertEquals(2, n.getTaskListsNames().length);
		Exception e = assertThrows(IllegalArgumentException.class,
				() -> n.addTaskList(tasklist5));
		assertEquals("Invalid name.", e.getMessage());
		n.addTaskList(tasklist3);
		assertEquals(3, n.getTaskListsNames().length);
		
	}
	/**
	 * Testing the editTaskList method
	 */
	@Test
	public void testEditTaskList() {
		Notebook n = new Notebook("Notebook");
		TaskList tasklist1 = new TaskList("newsss", 1);
		n.addTaskList(tasklist1);
		String[] s = n.getTaskListsNames();
		assertEquals("newsss", s[1]);
		n.editTaskList("news");
		assertEquals("news", n.getCurrentTaskList().getTaskListName());
		n.removeTaskList();
		assertEquals(1, n.getTaskListsNames().length);
	}

	/**
	 * Testing the add and edit task list methods
	 */
	@Test
	public void testAddAndEditTask() {
		Notebook n = new Notebook("Notebook");
		TaskList tasklist1 = new TaskList("newsss", 1);
		n.addTaskList(tasklist1);
		Task task1 = new Task("new", "new des", false, false);
		Task task2 = new Task("news", "news des", false, true);
		n.addTask(task1);
		n.addTask(task2);
		String[][] s = n.getCurrentTaskList().getTasksAsArray();
		assertEquals("new", s[0][1]);
		assertEquals("news", s[1][1]);
		n.editTask(0, "newss", "newss des", false, true);
	}
	
}
