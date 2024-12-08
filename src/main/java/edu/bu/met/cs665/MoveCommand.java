/**
 * Name: Shaohua Yue
 * Course: CS-665 Software Designs & Patterns
 * Date: 12/07/2024
 * File Name: MoveCommand.java
 * Description: This class implements the command to move files or directories
 * from one location to another, using both Singleton and Command patterns.
 */
package edu.bu.met.cs665;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class MoveCommand extends CommonCommand {

    private static volatile MoveCommand moveCommand;
    /**
     * Private constructor for Singleton pattern
     */
    private MoveCommand() {

    }

    /**
     * Get the singleton instance of MoveCommand
     * @return The singleton instance
     */
    public static MoveCommand getInstance() {
        if(moveCommand == null) {
            synchronized (MoveCommand.class) {
                if(moveCommand == null) {
                    moveCommand = new MoveCommand();
                }
            }
        }
        return moveCommand;
    }

    /**
     * Parse a path to find the corresponding file or directory
     * @param roots List of root components to search
     * @param paths Array of path segments
     * @param index Current index in the path array
     * @return The found component or null if not found
     */
    public FileSystemComponent parseFileOrDirectory(List<FileSystemComponent> roots, String[] paths, int index) {
        for(int i = 0; i < roots.size(); i ++) {
            if(roots.get(i).getName().equals(paths[index])) {
                if(roots.get(i) instanceof Directory) {
                    if(index == paths.length - 1) return roots.get(i);
                    return parseFileOrDirectory(((Directory) roots.get(i)).getComponents(), paths, index+1);
                } else {
                    return roots.get(i);
                }
            }
        }
        return null;
    }

    /**
     * Execute the move command
     * @param path1 Source path
     * @param path2 Destination path
     * @param ifPushIntoStack Whether to add this action to the undo stack
     */
    public void execute(String path1, String path2, Boolean ifPushIntoStack) {
        FileSystemComponent file = parseFileOrDirectory(new ArrayList<>(Arrays.asList(root)), path1.split("/"), 0);
        if(file == null) System.out.println("Cannot find file in arg1!");
        FileSystemComponent directory = parseFileOrDirectory(new ArrayList<>(Arrays.asList(root)), path2.split("/"), 0);
        if(directory == null) System.out.println("Cannot find directory in arg2!");

        Iterator<FileSystemComponent> iterator = ((Directory)((File) file).getSup()).getComponents().iterator();
        while (iterator.hasNext()) {
            FileSystemComponent element = iterator.next();
            if (element.getName().equals(file.getName())) {
                iterator.remove();
            }
        }
        ((File)file).setSup(directory);
        ((Directory) directory).add(file);
        if(ifPushIntoStack) {
            Action action = new Action(ActionType.MOVE, path1, path2);
            undoStack.push(action);
        }
    }
}
