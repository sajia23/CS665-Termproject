/**
 * Name: Shaohua Yue
 * Course: CS-665 Software Designs & Patterns
 * Date: 12/07/2024
 * File Name: DeleteCommand.java
 * Description: This class implements the command to delete a file or directory,
 * using both Singleton and Command patterns.
 */
package edu.bu.met.cs665;

import java.util.Iterator;

public class DeleteCommand extends CommonCommand {
    private static volatile DeleteCommand deleteCommand;

    /**
     * Private constructor for Singleton pattern
     */
    private DeleteCommand() {
    }

    /**
     * Get the singleton instance of DeleteCommand
     * @return The singleton instance
     */
    public static DeleteCommand getInstance() {
        if(deleteCommand == null) {
            synchronized (DeleteCommand.class) {
                if(deleteCommand == null) {
                    deleteCommand = new DeleteCommand();
                }
            }
        }
        return deleteCommand;
    }

    /**
     * Execute the delete command
     * @param directory The directory containing the item to delete
     * @param fileName The name of the file to delete
     * @param ifPushIntoStack Whether to add this action to the undo stack
     */
    public void execute(Directory directory, String fileName, Boolean ifPushIntoStack) {
        Iterator<FileSystemComponent> iterator = directory.getComponents().iterator();
        while (iterator.hasNext()) {
            FileSystemComponent element = iterator.next();
            if (element.getName().equals(fileName)) {
                if(ifPushIntoStack) {
                    Action action = new Action(ActionType.DELETE, directory, element);
                    super.undoStack.push(action);
                }
                iterator.remove();
            }
        }
    }
}
