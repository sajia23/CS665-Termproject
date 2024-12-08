/**
 * Name: Shaohua Yue
 * Course: CS-665 Software Designs & Patterns
 * Date: 12/07/2024
 * File Name: File.java
 * Description: This class represents a file in the file system. It implements FileSystemComponent
 * interface and contains basic file properties like name and size.
 */
package edu.bu.met.cs665;

public class File implements FileSystemComponent {
    private String name;
    private int size;
    private FileSystemComponent sup;

    /**
     * Constructor for creating a new file
     * @param name The name of the file
     * @param size The size of the file in bytes
     */
    public File(String name, int size) {
        this.name = name;
        this.size = size;
    }

    /**
     * Constructor for creating a new file with parent component
     * @param name The name of the file
     * @param size The size of the file in bytes
     * @param sup The parent component of this file
     */
    public File(String name, int size, FileSystemComponent sup) {
        this.name = name;
        this.size = size;
        this.sup = sup;
    }

    /**
     * Get the size of the file
     * @return The size of the file in bytes
     */
    public int getSize() {
        return size;
    }

    /**
     * Accept a visitor to perform operations on this file
     * @param visitor The visitor that will operate on this file
     * @param depth The current depth in the file system tree
     */
    @Override
    public void accept(FileSystemVisitor visitor, int depth) {
        visitor.visit(this, depth);
    }

    /**
     * Get the name of the file
     * @return The name of the file
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * Get the parent component of this file
     * @return The parent component
     */
    @Override
    public FileSystemComponent getSup() {
        return sup;
    }

    /**
     * Set the parent component of this file
     * @param directory The new parent component
     */
    public void setSup(FileSystemComponent directory) {
        sup = directory;
    }
}
