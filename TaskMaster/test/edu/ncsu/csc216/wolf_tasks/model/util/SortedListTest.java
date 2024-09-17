/**
 * 
 */
package edu.ncsu.csc216.wolf_tasks.model.util;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import edu.ncsu.csc216.wolf_tasks.model.tasks.TaskList;

/**
 * Tests the SortedList class
 * 
 * @author Foster Nelson
 */
public class SortedListTest {

	/**
	 * Tests the add and remove methods
	 */
	@Test
	public void testAddAndRemove() {
		ISortedList<TaskList> list = new SortedList<TaskList>();
		TaskList tasklist1 = new TaskList("newsss", 1);
		TaskList tasklist2 = new TaskList("news", 2);
		TaskList tasklist3 = new TaskList("newss", 3);
		TaskList tasklist4 = new TaskList("new", 4);

		list.add(tasklist2);
		assertEquals(1, list.size());
		list.add(tasklist1);
		assertEquals(2, list.size());
		assertEquals(tasklist1, list.get(1));
		list.add(tasklist3);
		assertEquals(3, list.size());
		assertEquals(tasklist3, list.get(1));
		TaskList t1 = list.remove(1);
		assertEquals(tasklist3, t1);
		assertEquals(2, list.size());
		assertEquals(tasklist1, list.get(1));
		list.add(tasklist4);
		assertEquals(3, list.size());
		list.remove(0);
		assertEquals(tasklist2, list.get(0));
	}

	/**
	 * Tests the contains method
	 */
	@Test
	public void testContains() {
		ISortedList<TaskList> list = new SortedList<TaskList>();
		TaskList tasklist1 = new TaskList("newsss", 1);
		TaskList tasklist2 = new TaskList("news", 2);
		TaskList tasklist3 = new TaskList("newss", 3);

		list.add(tasklist1);
		list.add(tasklist2);
		assertTrue(list.contains(tasklist2));
		assertTrue(list.contains(tasklist1));
		Exception e = assertThrows(IllegalArgumentException.class, () -> list.contains(null));
		assertEquals("Invalid data.", e.getMessage());
		assertFalse(list.contains(tasklist3));

	}
}
