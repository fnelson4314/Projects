/**
 * 
 */
package edu.ncsu.csc216.wolf_tasks.model.util;

/**
 * Class to create a custom ArrayList to manage task lists
 * 
 * @author Foster Nelson
 * @param <E> object to be used in the list
 */
public class SwapList<E> implements ISwapList<E> {
	/** Variable to control the initial capacity of a list */
	private static final int INITIAL_CAPACITY = 10;
	/** Variable to hold a list of E objects */
	private E[] list;

	/** Variable to keep track of the size of the list */
	private int size;

	/**
	 * Constructor for the SwapList class
	 */
	@SuppressWarnings("unchecked")
	public SwapList() {
		Object[] s = new Object[INITIAL_CAPACITY];
		list = (E[]) s;
	}

	/**
	 * Add an object to the current list
	 * 
	 * @param e object to be added
	 * @throws NullPointerException if e is null
	 */
	public void add(E e) {
		checkCapacity(size);
		if (e == null) {
			throw new NullPointerException("Cannot add null element.");
		}
		list[size] = e;
		size++;
	}

	/**
	 * Change the current max capacity of the list
	 * 
	 * @param size the current list size
	 */
	@SuppressWarnings("unchecked")
	private void checkCapacity(int size) {
		Object[] s = new Object[size() + 1];
		E[] temp = (E[]) s;

		for (int idx = 0; idx < size(); idx++) {
			temp[idx] = list[idx];
		}

		list = temp;
	}

	/**
	 * Method to remove an object from the list at a certain index
	 * 
	 * @param index index to be removed
	 * @return E the object that was removed
	 */
	public E remove(int index) {
		checkIndex(index);
		E value = list[index];
		for (int i = index; i < size - 1; i++) {
			list[i] = list[i + 1];
		}
		list[size() - 1] = null;
		size--;
		return value;
	}

	/**
	 * Method to check if the given index is valid
	 * 
	 * @param index is the index to check
	 */
	private void checkIndex(int index) {
		if (index > size - 1 || index < 0) {
			throw new IndexOutOfBoundsException("Invalid index.");
		}
	}

	/**
	 * Method to move an object up a given amount
	 * 
	 * @param i is the index to be moved up
	 */
	public void moveUp(int i) {
		checkIndex(i);
		if (i > 0) {
			E temp = list[i - 1];
			list[i - 1] = list[i];
			list[i] = temp;
		}
	}

	/**
	 * Method to move an object down a given amount
	 * 
	 * @param i index to be moved down
	 */
	public void moveDown(int i) {
		checkIndex(i);
		if (i < size() - 1) {
			E temp = list[i + 1];
			list[i + 1] = list[i];
			list[i] = temp;
		}
	}

	/**
	 * Method to move an object to the front of the list
	 * 
	 * @param i index to move to
	 */
	public void moveToFront(int i) {
		checkIndex(i);
		E temp = list[i];
		for (int idx = i; idx > 0; idx--) {
			list[idx] = list[idx - 1];
		}
		list[0] = temp;
	}

	/**
	 * Method to move an object to the back of the list
	 * 
	 * @param i index to move to
	 */
	public void moveToBack(int i) {
		checkIndex(i);
		E temp = list[i];
		for (int idx = i; idx < size() - 1; idx++) {
			list[idx] = list[idx + 1];
		}
		list[size() - 1] = temp;
	}

	/**
	 * Method to get an object at a given index
	 * 
	 * @param i is the index to search and get
	 * @return the object at the given index
	 */
	public E get(int i) {
		checkIndex(i);
		return list[i];
	}

	/**
	 * Method to return the current size of the list
	 * 
	 * @return int of the size of the list
	 */
	public int size() {
		return size;
	}
}
