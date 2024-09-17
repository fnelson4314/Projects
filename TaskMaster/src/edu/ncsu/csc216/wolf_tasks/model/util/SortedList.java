/**
 * 
 */
package edu.ncsu.csc216.wolf_tasks.model.util;

/**
 * Class that creates a custom linked list of sorted objects
 * 
 * @author Foster Nelson
 * @param <E> object to be used in the list; must extend comparable
 */
public class SortedList<E extends Comparable<E>> implements ISortedList<E> {
	/** Variable to keep track of the size of the current list */
	private int size;
	/** Variable that holds the front list node */
	private ListNode front;

	/**
	 * Adds the element to the list in sorted order.
	 * 
	 * @param e element element to add
	 * @throws NullPointerException     if element is null
	 * @throws IllegalArgumentException if element cannot be added
	 */
	public void add(E e) {
		if (e == null) {
			throw new NullPointerException("Cannot add null element.");
		}
		if (size == 0) {
			front = new ListNode(e, null);
		}
		if (size == 1) {
			if (e.compareTo(front.data) == 0) {
				throw new IllegalArgumentException("Cannot add duplicate element.");
			}
			if (e.compareTo(front.data) < 0) {
				front = new ListNode(e, front);
			} else {
				front.next = new ListNode(e, null);
			}
		} else {
			ListNode current = front;
			for (int i = 0; i < size - 1; i++) {
				if (i == 0 && e.compareTo(front.data) < 0) {
					front = new ListNode(e, front);
					break;
				}
				if (e.compareTo(current.data) == 0) {
					throw new IllegalArgumentException("Cannot add duplicate element.");
				} else if (e.compareTo(current.next.data) < 0) {
					ListNode l = new ListNode(e, current.next);
					current.next = l;
					break;
				} else {
					if (i == size - 2) {
						current.next.next = new ListNode(e, null);
						break;
					}
					current = current.next;
				}
			}
		}
		size++;
	}

	/**
	 * Returns the element from the given index. The element is removed from the
	 * list.
	 * 
	 * @param i index to remove element from
	 * @return element at given index
	 * @throws IndexOutOfBoundsException if the i is out of bounds for the list
	 */
	public E remove(int i) {
		checkIndex(i);
		ListNode current = front;
		E value;
		if (i == 0) {
			value = front.data;
			front = front.next;
			size--;
			return value;
		}
		for (int idx = 0; idx < size(); idx++) {
			if (idx + 1 == i) {
				value = current.next.data;
				current.next = current.next.next;
				size--;
				return value;
			} else {
				current = current.next;
			}
		}
		return null;

	}

	/**
	 * Method to check what is at the given index
	 * 
	 * @param index the index to be checked
	 * @throws IllegalArgumentException if the index is invalid
	 */
	private void checkIndex(int index) {
		if (index > size - 1 || index < 0) {
			throw new IndexOutOfBoundsException("Invalid index.");
		}
	}

	/**
	 * Returns true if the element is in the list.
	 * 
	 * @param e element element to search for
	 * @return true if element is found
	 * @throws IllegalArgumentException if e is null
	 */
	public boolean contains(E e) {
		if (e == null) {
			throw new IllegalArgumentException("Invalid data.");
		}
		boolean found = false;
		ListNode current = front;
		for (int i = 0; i < size(); i++) {
			if (current.data.equals(e)) {
				return true;
			}
			current = current.next;
		}
		return found;
	}

	/**
	 * Returns the element at the given index.
	 * 
	 * @param i index of the element to retrieve
	 * @return element at the given index
	 */
	public E get(int i) {
		checkIndex(i);
		if (size > 0) {
			ListNode current = front;
			for (int idx = 0; idx < i; idx++) {
				current = current.next;
			}
			return current.data;
		}
		return null;

	}

	/**
	 * Method to get the size of the SortedList
	 * 
	 * @return int of the size of the list
	 */
	public int size() {
		return size;
	}

	/**
	 * Inner class of the SortedList class that will hold the task lists for the
	 * notebook
	 */
	private class ListNode {
		/** Variable to hold the next ListNode */
		public ListNode next;
		/** Variable to hold the data of the current ListNode */
		public E data;

		/**
		 * Constructor for the ListNode class
		 * 
		 * @param e the data of the ListNode
		 * @param l the ListNode being added
		 */
		public ListNode(E e, ListNode l) {
			this.data = e;
			this.next = l;
		}
	}
}
