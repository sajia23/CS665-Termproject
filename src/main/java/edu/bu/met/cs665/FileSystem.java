/**
 * Name: Shaohua Yue
 * Course: CS-665 Software Designs & Patterns
 * Date: 12/07/2024
 * File Name: FileSystem.java
 * Description: This is the main class that implements a file system with various operations
 * like creating, deleting, moving files and directories, and supporting undo/redo operations.
 */
package edu.bu.met.cs665;

import java.util.List;
import java.util.Scanner;

public class FileSystem {
    public Directory root;

    /**
     * Constructor that initializes the file system with a basic structure
     * Creates initial directories and files using FileSystemBuilder
     */
    public FileSystem() {
        FileSystemBuilder builder = new FileSystemBuilder();
        // Build the file system structure
        builder.addDirectory("directory1")
                .addFile("directory1", "directory1_file1", 500)
                .addFile("directory1", "directory1_file2", 1000)
                .addDirectory("directory2")
                .addFile("directory2", "directory2_file1", 200);

        // Get the built file system
        Directory fileSystem = builder.build();
        root = fileSystem;

        System.out.println("File system initialization finished!");
        CDCommand.getInstance().setRoot(root);
    }

    /**
     * Main method that handles user input and executes file system commands
     * @param args Command line arguments (not used)
     */
    public static void main(String[] args) {
        FileSystem fs = new FileSystem();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Enter a command (ls, cd, mkd, mkf, undo, redo, search): ");
            String command = scanner.nextLine();
            String[] commands = command.split("\\s+");
            
            // Handle different commands
            if (commands[0].equals("ls")) {
                SizeVisitor treeSizeVisitor = new SizeVisitor();
                fs.root.accept(treeSizeVisitor, 0);
            } else if (commands[0].equals("cd")){
                if(commands[1].equals("..") && fs.root.getSup() != null) {
                    fs.root = (Directory) fs.root.getSup();
                } else {
                    Directory result = CDCommand.getInstance().execute(fs.root, commands[1]);
                    if(result != null) fs.root = result;
                }
            } else if (commands[0].equals("undo")) {
                UndoCommand.getInstance().execute(null, null);
            } else if (commands[0].equals("redo")) {
                RedoCommand.getInstance().execute(null, null);
            } else if (commands[0].equals("search")) {
                List<String> results = SearchCommand.getInstance().execute(null, commands[1]);
                System.out.print(commands[1]);
                System.out.print(":  ");
                for(int i = 0; i < results.size(); i ++) {
                    System.out.println(results.get(i));
                }
            } else if (commands[0].equals("mv")) {
                String path1 = commands[1];
                String path2 = commands[2];
                MoveCommand.getInstance().execute(path1, path2, true);
            } else if (commands[0].equals("mkd")) {
                Directory directory = new Directory(commands[1], fs.root);
                CreateFileCommand.getInstance().execute(fs.root, directory, true);
            } else if (commands[0].equals("mkf")) {
                File file = new File(commands[1], Integer.parseInt(commands[2]), fs.root);
                CreateFileCommand.getInstance().execute(fs.root, file, true);
            } else if (commands[0].equals("delf")) {
                DeleteCommand.getInstance().execute(fs.root, commands[1], true);
            } else if (commands[0].equals("exit")){
                break;
            }
        }

        scanner.close();
    }
}

