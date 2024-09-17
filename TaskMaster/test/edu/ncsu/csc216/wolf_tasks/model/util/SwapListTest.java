/**
 * 
 */
package edu.ncsu.csc216.wolf_tasks.model.util;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import edu.ncsu.csc216.wolf_tasks.model.tasks.Task;

/**
 * Tests the SwapList class
 * 
 * @author Foster Nelson
 */
class SwapListTest {
	/**
	 * Tests the add and remove methods
	 */
	@Test
	public void testAddAndRemove() {
		ISwapList<Task> list = new SwapList<Task>();
		Task task1 = new Task("new", "new des", false, false);
		Task task2 = new Task("news", "news des", false, true);

		list.add(task1);
		assertEquals(1, list.size());
		Exception e = assertThrows(NullPointerException.class, () -> list.add(null));
		assertEquals("Cannot add null element.", e.getMessage());
		list.add(task2);
		assertEquals(2, list.size());
		assertEquals(task2, list.get(1));
		Task t = list.remove(1);
		assertEquals(1, list.size());
		assertEquals(t, task2);
		list.add(task2);
		assertEquals(2, list.size());
		list.remove(0);
		assertEquals(1, list.size());
		Exception e1 = assertThrows(IndexOutOfBoundsException.class, () -> list.remove(-1));
		assertEquals("Invalid index.", e1.getMessage());
		Exception e2 = assertThrows(IndexOutOfBoundsException.class, () -> list.remove(3));
		assertEquals("Invalid index.", e2.getMessage());

	}

	/**
	 * Tests all the move methods
	 */
	@Test
	public void testMove() {
		ISwapList<Task> list = new SwapList<Task>();
		Task task1 = new Task("new", "new des", false, false);
		Task task2 = new Task("news", "news des", false, true);
		Task task3 = new Task("newss", "newss des", false, true);

		list.add(task1);
		assertEquals(1, list.size());
		list.add(task2);
		assertEquals(2, list.size());
		assertEquals(task2, list.get(1));
		list.add(task3);
		assertEquals(3, list.size());
		list.moveUp(2);
		assertEquals(task3, list.get(1));
		list.moveDown(1);
		assertEquals(task3, list.get(2));
		list.moveToFront(2);
		assertEquals(task3, list.get(0));
		list.moveToBack(0);
		assertEquals(task3, list.get(2));

	}

}
