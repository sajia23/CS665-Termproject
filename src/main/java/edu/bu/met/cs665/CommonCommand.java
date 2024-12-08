/**
 * Name: Shaohua Yue
 * Course: CS-665 Software Designs & Patterns
 * Date: 12/07/2024
 * File Name: CommonCommand.java
 * Description: This abstract class provides common functionality for all commands
 * in the file system, implementing the Command pattern.
 */
package edu.bu.met.cs665;

import java.util.*;
import java.util.Iterator;

public abstract class CommonCommand {
    protected static Deque<Action> undoStack = new ArrayDeque<>();
    protected static Deque<Action> redoStack = new ArrayDeque<>();
    protected static Map<String, List<String>> catalog = new HashMap<>();

    static Directory root;

    /**
     * Set the root directory for the file system
     * @param root The root directory to set
     */
    public void setRoot(Directory root) {
        this.root = root;
    }

    /**
     * Convert a path string to a FileSystemComponent
     * @param path The path to convert
     * @return The corresponding FileSystemComponent or null if not found
     */
    public FileSystemComponent pathToFileOrDirectory(String path) {
        String[] paths = path.split("/");
        Directory tempRoot = root;
        if(!paths[0].equals(tempRoot.getName())) return null;
        for(int i = 1; i < path.length(); i ++) {
            Iterator<FileSystemComponent> iterator = tempRoot.getComponents().iterator();
            while (iterator.hasNext()) {
                FileSystemComponent element = iterator.next();
                if (element.getName().equals(paths[i])) {
                    if(i == path.length()-1) return tempRoot;
                    tempRoot = (Directory) element;
                }
            }
        }
        return null;
    }

    /**
     * Convert a FileSystemComponent to its path string
     * @param fileSystemComponent The component to convert
     * @return The path string representation
     */
    public String fileOrDirectoryToPath(FileSystemComponent fileSystemComponent) {
        StringBuilder sb = new StringBuilder();
        while(fileSystemComponent.getSup() != null) {
            if(fileSystemComponent instanceof Directory) sb.insert(0, "/");
            sb.insert(0, fileSystemComponent.getName());
            fileSystemComponent = fileSystemComponent.getSup();
        }
        return sb.toString();
    }

    /**
     * Generic execute method to be implemented by specific commands
     * @param currentDirectory The current directory
     * @param parameter The command parameter
     */
    public <T> void execute(T currentDirectory, T parameter){}
}
