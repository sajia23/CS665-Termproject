/**
 * Name: Shaohua Yue
 * Course: CS-665 Software Designs & Patterns
 * Date: 12/07/2024
 * File Name: SizeVisitor.java
 * Description: This class implements the FileSystemVisitor interface to display
 * the size and structure of files and directories in the file system.
 */
package edu.bu.met.cs665;

public class SizeVisitor implements FileSystemVisitor {

    /**
     * Visit a file and print its name and size
     * @param file The file to visit
     * @param depth The current depth for indentation
     */
    @Override
    public void visit(File file, int depth) {
        printWithIndent(depth, file.getName() + " (" + file.getSize() + " bytes)");
    }

    /**
     * Visit a directory and print its contents recursively
     * @param directory The directory to visit
     * @param depth The current depth for indentation
     */
    @Override
    public void visit(Directory directory, int depth) {
        printWithIndent(depth, directory.getName() + " (directory)");
        
        for (FileSystemComponent child : directory.getComponents()) {
            child.accept(this, depth + 1);
        }
    }

    /**
     * Helper method to print with proper indentation
     * @param depth The indentation level
     * @param message The message to print
     */
    private void printWithIndent(int depth, String message) {
        for(int i = 0; i < depth; i ++) {
            System.out.print("  ");
        }
        System.out.print(message);
        System.out.println();
    }
}
