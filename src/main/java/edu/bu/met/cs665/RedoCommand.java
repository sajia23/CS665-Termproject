package edu.bu.met.cs665;

/**
 * Name: Shaohua Yue
 * Course: CS-665 Software Designs & Patterns
 * Date: 12/07/2024
 * File Name: RedoCommand.java
 * Description: This class implements the command to redo previously undone operations,
 * using both Singleton and Command patterns.
 */

public class RedoCommand extends CommonCommand{

    private static volatile RedoCommand redoCommand;
    /**
     * Construction method
     */
    private RedoCommand() {

    }

    /**
     * Get a singleton instance.
     */
    public static RedoCommand getInstance() {
        if(redoCommand == null) {
            synchronized (RedoCommand.class) {
                if(redoCommand == null) {
                    redoCommand = new RedoCommand();
                }
            }
        }
        return redoCommand;
    }
    /**
     * Execute the redo command
     * @param currentDirectory The current working directory
     * @param file The file being operated on
     */
    public void execute(FileSystemComponent currentDirectory, FileSystemComponent file) {
        Action action = super.redoStack.pop();
        super.undoStack.push(action);
        if(action.actionType == ActionType.CREATE) {
            CreateFileCommand.getInstance().execute(((Directory)action.arg1), ((File)action.arg2), false);
        }
        if(action.actionType == ActionType.DELETE) {
            DeleteCommand.getInstance().execute(((Directory)action.arg1), ((File)action.arg2).getName(), false);
        }
        if(action.actionType == ActionType.MOVE) {
            MoveCommand.getInstance().execute((String)action.arg1, (String)action.arg2, false);
        }
    }
}
