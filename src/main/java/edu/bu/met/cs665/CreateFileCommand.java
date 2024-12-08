/**
 * Name: Shaohua Yue
 * Course: CS-665 Software Designs & Patterns
 * Date: 12/07/2024
 * File Name: CreateFileCommand.java
 * Description: This class implements the command to create a new file,
 * using both Singleton and Command patterns.
 */
package edu.bu.met.cs665;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CreateFileCommand extends CommonCommand {
    private static volatile CreateFileCommand createFileCommand;

    /**
     * Private constructor for Singleton pattern
     */
    private CreateFileCommand() {
    }

    /**
     * Get the singleton instance of CreateFileCommand
     * @return The singleton instance
     */
    public static CreateFileCommand getInstance() {
        if(createFileCommand == null) {
            synchronized (CreateFileCommand.class) {
                if(createFileCommand == null) {
                    createFileCommand = new CreateFileCommand();
                }
            }
        }
        return createFileCommand;
    }

    /**
     * Execute the create file command
     * @param directory The parent directory
     * @param fileOrDirectory The new file to create
     * @param ifPushIntoStack Whether to add this action to the undo stack
     */
    public void execute(Directory directory, FileSystemComponent fileOrDirectory, Boolean ifPushIntoStack) {
        if(ifPushIntoStack) {
            Action action = new Action(ActionType.CREATE, directory, fileOrDirectory);
            super.undoStack.push(action);
        }
        List<String> temp = catalog.get(fileOrDirectory.getName());
        if(temp == null) {
            catalog.put(fileOrDirectory.getName(), new ArrayList<>(Arrays.asList(directory.getName())));
        } else {
            temp.add(directory.getName());
        }
        directory.add(fileOrDirectory);
        ((File) fileOrDirectory).setSup(directory);
    }
}