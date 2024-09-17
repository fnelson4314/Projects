/**
 * 
 */
package edu.ncsu.csc216.wolf_tasks.model.tasks;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

import org.junit.jupiter.api.Test;

/**
 * Tests the AbstractTaskList class
 * 
 * @author Drew Bodnar
 */
public class AbstractTaskListTest {
	/**
	 * Tests the AbstractTaskList constructor
	 */
	@Test
	public void testConstructor() {
		AbstractTaskList taskList = new TaskList("List name", 0);
		assertEquals("List name", taskList.getTaskListName());
		assertEquals(0, taskList.getCompletedCount());
		assertThrows(IllegalArgumentException.class, () -> new TaskList(null, 0));
		assertThrows(IllegalArgumentException.class, () -> new TaskList("", 0));
		assertThrows(IllegalArgumentException.class, () -> new TaskList("List name", -1));
	}

	/**
	 * Tests the getTasks method
	 */
	@Test
	public void testGetTasks() {
		AbstractTaskList taskList = new TaskList("List name", 0);
		Task task = new Task("Task", "Description", false, false);
		taskList.addTask(task);
		assertEquals(taskList.getTasks().size(), 1);
		assertEquals(taskList.getTask(0), task);
	}

	/**
	 * Tests the getCompletedCount method
	 */
	@Test
	public void testGetCompletedCount() {
		AbstractTaskList taskList = new TaskList("List name", 0);
		assertEquals(taskList.getCompletedCount(), 0);
		Task task = new Task("Task", "Description", false, false);
		taskList.addTask(task);
		taskList.completeTask(task);
		assertEquals(taskList.getCompletedCount(), 1);
	}

	/**
	 * Tests the add and remove methods
	 */
	@Test
	public void testAddAndRemoveTask() {
		AbstractTaskList taskList = new TaskList("List name", 0);
		Task task = new Task("Task", "Description", false, false);
		taskList.addTask(task);
		assertEquals(taskList.getTasks().size(), 1);
		taskList.removeTask(0);
		assertEquals(taskList.getTasks().size(), 0);
	}

}
