/**
 * 
 */
package edu.ncsu.csc216.wolf_tasks.model.io;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

import java.io.File;

import org.junit.jupiter.api.Test;

import edu.ncsu.csc216.wolf_tasks.model.notebook.Notebook;

/**
 * Tests the NotebookReader class
 * 
 * @author Foster Nelson
 * @author Drew Bodnar
 */
public class NotebookReaderTest {
	/**
	 * Tests the readNodebookFile method
	 */
	@Test
	public void testReadNodebookFile() {
		// Tests a valid file
		File validFile = new File("test-files/notebook0.txt");
		Notebook notebook = NotebookReader.readNotebookFile(validFile);

		assertEquals("Summer Plans", notebook.getNotebookName());

		// Test a file with more than just a title!
		File validFile2 = new File("test-files/notebook1.txt");
		Notebook notebook2 = NotebookReader.readNotebookFile(validFile2);
		assertEquals("School", notebook2.getNotebookName());
		assertEquals(notebook2.getTaskListsNames().length, 4);

		File validFile7 = new File("test-files/notebook7.txt");
		Notebook notebook7 = NotebookReader.readNotebookFile(validFile7);
		assertEquals("Personal", notebook7.getNotebookName());

		File invalidFile = new File("test-files/doesnt-exist.txt");
		assertThrows(IllegalArgumentException.class, () -> NotebookReader.readNotebookFile(invalidFile));
	}
}
