/**
 * Name: Shaohua Yue
 * Course: CS-665 Software Designs & Patterns
 * Date: 12/07/2024
 * File Name: FileSystemVisitor.java
 * Description: This interface defines the Visitor pattern for traversing the file system,
 * allowing different operations to be performed on files and directories.
 */
package edu.bu.met.cs665;

public interface FileSystemVisitor {
    /**
     * Visit a file component
     * @param file The file to visit
     * @param depth The current depth in the file system tree
     */
    void visit(File file, int depth);

    /**
     * Visit a directory component
     * @param directory The directory to visit
     * @param depth The current depth in the file system tree
     */
    void visit(Directory directory, int depth);
}
