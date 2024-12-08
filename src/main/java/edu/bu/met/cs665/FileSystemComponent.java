/**
 * Name: Shaohua Yue
 * Course: CS-665 Software Designs & Patterns
 * Date: 12/07/2024
 * File Name: FileSystemComponent.java
 * Description: This interface defines the common operations for both files and directories
 * in the file system, implementing the Component interface in the Composite pattern.
 */
package edu.bu.met.cs665;

public interface FileSystemComponent {
    /**
     * Get the size of the component
     * @return The size in bytes
     */
    int getSize();

    /**
     * Accept a visitor to perform operations on this component
     * @param visitor The visitor that will operate on this component
     * @param depth The current depth in the file system tree
     */
    void accept(FileSystemVisitor visitor, int depth);

    /**
     * Get the name of the component
     * @return The component name
     */
    String getName();

    /**
     * Get the parent component
     * @return The parent component
     */
    FileSystemComponent getSup();
}