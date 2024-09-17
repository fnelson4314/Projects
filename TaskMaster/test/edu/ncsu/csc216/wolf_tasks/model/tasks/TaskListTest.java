/**
 * 
 */
package edu.ncsu.csc216.wolf_tasks.model.tasks;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * Tests the TaskList class
 * 
 * @author Drew Bodnar
 */
public class TaskListTest {
	/**
	 * Tests the TaskList constructor
	 */
	@Test
	public void testConstructor() {
		TaskList taskList = new TaskList("Task List Name", 1);
		assertEquals(taskList.getTaskListName(), "Task List Name");
		assertEquals(taskList.getTasks().size(), 0);
	}

	/**
	 * Tests the getTasksAsArray method
	 */
	@Test
	public void testGetTasksAsArray() {
		TaskList taskList = new TaskList("Task List Name", 1);
		taskList.addTask(new Task("Task 1", "Description", false, true));
		taskList.addTask(new Task("Task 2", "Description", false, true));
		taskList.addTask(new Task("Task 3", "Description", false, true));
		taskList.addTask(new Task("Task 4", "Description", false, true));

		String[][] taskArray = taskList.getTasksAsArray();
		assertEquals(taskArray.length, 4);
		assertEquals(taskArray[0][0], "1");
		assertEquals(taskArray[0][1], "Task 1");
		assertEquals(taskArray[1][0], "2");
		assertEquals(taskArray[1][1], "Task 2");
		assertEquals(taskArray[2][0], "3");
		assertEquals(taskArray[2][1], "Task 3");
		assertEquals(taskArray[3][0], "4");
		assertEquals(taskArray[3][1], "Task 4");
	}
}
