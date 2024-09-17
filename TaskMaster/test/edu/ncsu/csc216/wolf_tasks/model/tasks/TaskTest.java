/**
 * 
 */
package edu.ncsu.csc216.wolf_tasks.model.tasks;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.jupiter.api.Test;

/**
 * Tests the Task class
 * 
 * @author Drew Bodnar
 */
public class TaskTest {
	/**
	 * Tests the Task constructor
	 */
	@Test
	public void testConstructor() {
		Task task = new Task("Task", "Description", false, true);
		assertEquals(task.getTaskName(), "Task");
		assertEquals(task.getTaskDescription(), "Description");
		assertFalse(task.isRecurring());
		assertTrue(task.isActive());

		assertThrows(IllegalArgumentException.class, () -> new Task(null, "Description", false, true));
		assertThrows(IllegalArgumentException.class, () -> new Task("", "Description", false, true));
		assertThrows(IllegalArgumentException.class, () -> new Task("Task", null, false, true));
	}

	/**
	 * Tests the getTaskListName method
	 */
	@Test
	public void testGetTaskListName() {
		Task task = new Task("Task", "Description", false, true);
		assertEquals(task.getTaskListName(), "");
		AbstractTaskList taskList = new TaskList("Task", 0);
		task.addTaskList(taskList);
		assertEquals(task.getTaskListName(), "Task");

	}

	/**
	 * Tests the addTaskList method
	 */
	@Test
	public void testAddTaskList() {
		Task task = new Task("Task", "Description", false, true);
		AbstractTaskList taskList = new TaskList("Task List", 0);
		taskList.addTask(task);
		assertEquals("Task List", task.getTaskListName());

	}

	/**
	 * Tests the completeTask method
	 */
	@Test
	public void testCompleteTask() {
		Task task = new Task("Task", "Description", false, true);
		Task task2 = new Task("Task2", "Description", true, true);
		TaskList tasklist = new TaskList("Task List", 0);
		tasklist.addTask(task);
		tasklist.addTask(task2);
		assertTrue(task.isActive());
		assertTrue(task2.isRecurring());

		task.completeTask();
		assertFalse(task.isActive());
		task2.completeTask();
	}

	/**
	 * Tests the clone method
	 */
	@Test
	public void testClone() {
		Task task = new Task("Task", "Description", false, true);
		AbstractTaskList taskList = new TaskList("Task", 0);
		task.addTaskList(taskList);
		try {
			Task clonedTask = (Task) task.clone();
			System.out.print(clonedTask);
			assertEquals(task.getTaskName(), clonedTask.getTaskName());
			assertEquals(task.getTaskDescription(), clonedTask.getTaskDescription());
			assertFalse(clonedTask.isRecurring());
			assertTrue(clonedTask.isActive());
			assertEquals(task.getTaskListName(), clonedTask.getTaskListName());
		} catch (CloneNotSupportedException e) {
			fail();
		}
	}
}
