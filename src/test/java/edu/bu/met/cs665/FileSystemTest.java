/**
 * Name: Shaohua Yue
 * Course: CS-665 Software Designs & Patterns
 * Date: 12/07/2024
 * File Name: FileSystemTest.java
 * Description: This class contains unit tests for the file system operations,
 * testing the functionality of various commands including create, delete, move,
 * search, undo and redo operations.
 */
package edu.bu.met.cs665;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class FileSystemTest {
    private FileSystem fileSystem;

    /**
     * Set up method that runs before each test
     * Creates a new file system instance
     */
    @Before
    public void setUp() {
        fileSystem = new FileSystem();
    }

    /**
     * Test the initial state of the file system
     * Verifies that root directory is properly initialized
     */
    @Test
    public void testInitialFileSystem() {

        assertNotNull(fileSystem.root);
        assertEquals("root", fileSystem.root.getName());
    }

    /**
     * Test file creation and deletion operations
     * Verifies that files can be created and deleted correctly
     */
    @Test
    public void testCreateAndDeleteOperations() {

        File newFile = new File("test.txt", 100, fileSystem.root);
        CreateFileCommand.getInstance().execute(fileSystem.root, newFile, true);

        boolean fileFound = false;
        for (FileSystemComponent component : fileSystem.root.getComponents()) {
            if (component.getName().equals("test.txt")) {
                fileFound = true;
                assertEquals(100, component.getSize());
                break;
            }
        }
        assertTrue(fileFound);

        DeleteCommand.getInstance().execute(fileSystem.root, "test.txt", true);

        fileFound = false;
        for (FileSystemComponent component : fileSystem.root.getComponents()) {
            if (component.getName().equals("test.txt")) {
                fileFound = true;
                break;
            }
        }
        assertFalse(fileFound);
    }

    /**
     * Test undo and redo operations
     * Verifies that operations can be undone and redone properly
     */
    @Test
    public void testUndoRedoOperations() {
        File testFile = new File("undoTest.txt", 200, fileSystem.root);
        CreateFileCommand.getInstance().execute(fileSystem.root, testFile, true);

        DeleteCommand.getInstance().execute(fileSystem.root, "undoTest.txt", true);

        UndoCommand.getInstance().execute(null, null);

        boolean fileFound = false;
        for (FileSystemComponent component : fileSystem.root.getComponents()) {
            if (component.getName().equals("undoTest.txt")) {
                fileFound = true;
                break;
            }
        }
        assertTrue(fileFound);

        RedoCommand.getInstance().execute(null, null);

        fileFound = false;
        for (FileSystemComponent component : fileSystem.root.getComponents()) {
            if (component.getName().equals("undoTest.txt")) {
                fileFound = true;
                break;
            }
        }
        assertFalse(fileFound);
    }

    /**
     * Test file move operation
     * Verifies that files can be moved between directories correctly
     */
    @Test
    public void testMoveOperation() {

        MoveCommand.getInstance().execute("root/directory1/directory1_file1", "root/directory2", true);

        boolean fileFoundInTarget = false;
        Directory directory1 = null;
        for (FileSystemComponent component : fileSystem.root.getComponents()) {
            if(component.getName().equals("directory1")) {
                directory1 = (Directory) component;
            }
        }
        for (FileSystemComponent component : directory1.getComponents()) {
            if (component.getName().equals("directory1_file1")) {
                fileFoundInTarget = true;
                break;
            }
        }
        assertFalse(fileFoundInTarget);

        boolean fileFoundInSource = false;
        Directory directory2 = null;
        for (FileSystemComponent component : fileSystem.root.getComponents()) {
            if(component.getName().equals("directory2")) {
                directory2 = (Directory) component;
            }
        }
        for (FileSystemComponent component : directory2.getComponents()) {
            if (component.getName().equals("directory1_file1")) {
                fileFoundInSource = true;
                break;
            }
        }
        assertTrue(fileFoundInSource);
    }

    /**
     * Test file search operation
     * Verifies that files can be found using the search command
     */
    @Test
    public void testSearchOperation() {

        File searchFile = new File("searchTest.txt", 400, fileSystem.root);
        CreateFileCommand.getInstance().execute(fileSystem.root, searchFile, true);

        List<String> searchResults = SearchCommand.getInstance().execute(null, "searchTest.txt");
        
        assertNotNull(searchResults);
        assertTrue(searchResults.contains(fileSystem.root.getName()));
    }
}