/**
 * Name: Shaohua Yue
 * Course: CS-665 Software Designs & Patterns
 * Date: 12/07/2024
 * File Name: CreateDirectoryCommand.java
 * Description: This class implements the command to create a new directory,
 * using both Singleton and Command patterns.
 */
package edu.bu.met.cs665;

public class CreateDirectoryCommand extends CommonCommand{

    private static volatile CreateDirectoryCommand createDirectoryCommand;
    /**
     * Private constructor for Singleton pattern
     */
    private CreateDirectoryCommand() {

    }

    /**
     * Get the singleton instance of CreateDirectoryCommand
     * @return The singleton instance
     */
    public static CreateDirectoryCommand getInstance() {
        if(createDirectoryCommand == null) {
            synchronized (CreateDirectoryCommand.class) {
                if(createDirectoryCommand == null) {
                    createDirectoryCommand = new CreateDirectoryCommand();
                }
            }
        }
        return createDirectoryCommand;
    }
    /**
     * Execute the create directory command
     * @param directory The parent directory
     * @param newDirectory The new directory to create
     * @param ifPushIntoStack Whether to add this action to the undo stack
     */
    public void execute(Directory directory, Directory newDirectory, Boolean ifPushIntoStack) {
        if(ifPushIntoStack) {
            Action action = new Action(ActionType.CREATE, directory, newDirectory);
            undoStack.push(action);
        }
        newDirectory.setSup(directory);
        directory.add(newDirectory);
    }
}
