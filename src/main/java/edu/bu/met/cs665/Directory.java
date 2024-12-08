/**
 * Name: Shaohua Yue
 * Course: CS-665 Software Designs & Patterns
 * Date: 12/07/2024
 * File Name: Directory.java
 * Description: This class represents a directory in the file system that can contain other files
 * and directories. It implements the Composite pattern as a container component.
 */
package edu.bu.met.cs665;

import java.util.ArrayList;
import java.util.List;

public class Directory implements FileSystemComponent {
    private String name;
    private FileSystemComponent sup;
    private List<FileSystemComponent> components = new ArrayList<>();

    /**
     * Constructor for creating a new directory
     * @param name The name of the directory
     */
    public Directory(String name) {
        this.name = name;
    }

    /**
     * Constructor for creating a new directory with parent directory
     * @param name The name of the directory
     * @param sup The parent directory
     */
    public Directory(String name, Directory sup) {
        this.name = name;
        this.sup = sup;
    }

    /**
     * Get the name of the directory
     * @return The directory name
     */
    public String getName() {
        return name;
    }

    /**
     * Add a component to this directory
     * @param component The component (file or directory) to add
     */
    public void add(FileSystemComponent component) {
        components.add(component);
    }

    /**
     * Calculate the total size of all components in this directory
     * @return The total size in bytes
     */
    public int getSize() {
        int totalSize = 0;
        for (FileSystemComponent component : components) {
            totalSize += component.getSize();
        }
        return totalSize;
    }

    /**
     * Accept a visitor to perform operations on this directory
     * @param visitor The visitor that will operate on this directory
     * @param depth The current depth in the file system tree
     */
    @Override
    public void accept(FileSystemVisitor visitor, int depth) {
        visitor.visit(this, depth);
    }

    /**
     * Get all components in this directory
     * @return List of components in this directory
     */
    public List<FileSystemComponent> getComponents() {
        return components;
    }

    /**
     * Get the parent component of this directory
     * @return The parent component
     */
    public FileSystemComponent getSup() {
        return sup;
    }

    /**
     * Set the parent component of this directory
     * @param directory The new parent component
     */
    public void setSup(FileSystemComponent directory) {
        sup = directory;
    }
}