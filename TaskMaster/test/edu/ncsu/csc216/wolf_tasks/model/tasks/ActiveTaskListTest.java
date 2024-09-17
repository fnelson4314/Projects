/**
 * 
 */
package edu.ncsu.csc216.wolf_tasks.model.tasks;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

import org.junit.jupiter.api.Test;

/**
 * Tests the ActiveTaskList class
 * 
 * @author Drew Bodnar
 */
public class ActiveTaskListTest {
	/**
	 * Tests the ActiveTaskList constructor
	 */
	@Test
	public void testConstructor() {
		ActiveTaskList taskList = new ActiveTaskList();
		assertEquals("Active Tasks", taskList.getTaskListName());
		assertEquals(taskList.getCompletedCount(), 0);
	}

	/**
	 * Tests the add and clear tasks methods
	 */
	@Test
	public void testAddAndClearTasks() {
		ActiveTaskList taskList = new ActiveTaskList();
		Task task1 = new Task("Task 1", "Description", false, false);
		Task task2 = new Task("Task 2", "Description", false, false);
		assertThrows(IllegalArgumentException.class, () -> taskList.addTask(task1));
		task1.setActive(true);
		task2.setActive(true);
		taskList.addTask(task1);
		taskList.addTask(task2);
		taskList.getTasksAsArray();
		assertEquals(taskList.getTasks().size(), 2);
		taskList.clearTasks();
		assertEquals(taskList.getTasks().size(), 0);
	}

	/**
	 * Tests the setTaskListName method
	 */
	@Test
	public void testSetTaskListName() {
		ActiveTaskList taskList = new ActiveTaskList();
		assertThrows(IllegalArgumentException.class, () -> taskList.setTaskListName("Insert name here"));
	}

	/**
	 * Tests the getTasksAsArray method
	 */
	@Test
	public void testGetTasksAsArray() {
		ActiveTaskList taskList = new ActiveTaskList();
		Task task1 = new Task("Task 1", "Description", true, true);
		Task task2 = new Task("Task 2", "Description", true, true);
		taskList.addTask(task1);
		taskList.addTask(task2);
		String[][] taskArray = taskList.getTasksAsArray();
		assertEquals(taskArray.length, 2);
		assertEquals("Active Tasks", taskArray[0][0]);
		assertEquals("Active Tasks", taskArray[1][0]);
	}
}
