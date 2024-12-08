/**
 * Name: Shaohua Yue
 * Course: CS-665 Software Designs & Patterns
 * Date: 12/07/2024
 * File Name: FileSystemBuilder.java
 * Description: This class implements the Builder pattern to construct
 * a file system structure in a fluent way.
 */
package edu.bu.met.cs665;

import java.util.ArrayList;
import java.util.Arrays;

class FileSystemBuilder {
    private Directory rootDirectory;

    /**
     * Constructor that initializes the root directory
     */
    public FileSystemBuilder() {
        rootDirectory = new Directory("root");
    }

    /**
     * Add a new directory to the file system
     * @param directoryName The name of the directory to add
     * @return The builder instance for method chaining
     */
    public FileSystemBuilder addDirectory(String directoryName) {
        Directory newDirectory = new Directory(directoryName);
        CreateDirectoryCommand.getInstance().execute(rootDirectory, newDirectory, false);
        return this;
    }

    /**
     * Add a new file to a specific directory
     * @param directoryName The name of the directory to add the file to
     * @param fileName The name of the file to add
     * @param fileSize The size of the file in bytes
     * @return The builder instance for method chaining
     */
    public FileSystemBuilder addFile(String directoryName, String fileName, int fileSize) {
        File file = new File(fileName, fileSize, null);
        Directory directory = findDirectory(rootDirectory, directoryName);
        if (directory != null) {
            CreateFileCommand.getInstance().execute(directory, file, false);
        }
        return this;
    }

    /**
     * Helper method to find a directory by name
     * @param directory The directory to start searching from
     * @param name The name of the directory to find
     * @return The found directory or null if not found
     */
    private Directory findDirectory(Directory directory, String name) {
        if (directory.getName().equals(name)) {
            return directory;
        }
        for (FileSystemComponent component : directory.getComponents()) {
            if (component instanceof Directory) {
                Directory found = findDirectory((Directory) component, name);
                if (found != null) {
                    return found;
                }
            }
        }
        return null;
    }

    /**
     * Build and return the complete file system
     * @return The root directory of the built file system
     */
    public Directory build() {
        return rootDirectory;
    }
}
