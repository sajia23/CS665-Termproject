package edu.bu.met.cs665;

/**
 * Name: Shaohua Yue
 * Course: CS-665 Software Designs & Patterns
 * Date: 12/07/2024
 * File Name: UndoCommand.java
 * Description: This class implements the command to undo previous operations,
 * using both Singleton and Command patterns.
 */

public class UndoCommand extends CommonCommand{

    private static volatile UndoCommand undoCommand;
    /**
     * Construction method
     */
    private UndoCommand() {

    }

    /**
     * Get a singleton instance.
     */
    public static UndoCommand getInstance() {
        if(undoCommand == null) {
            synchronized (UndoCommand.class) {
                if(undoCommand == null) {
                    undoCommand = new UndoCommand();
                }
            }
        }
        return undoCommand;
    }

    /**
     * Execute the undo command
     * @param currentDirectory The current working directory
     * @param file The file being operated on
     */
    public void execute(FileSystemComponent currentDirectory, FileSystemComponent file) {
        Action action = super.undoStack.pop();
        super.redoStack.push(action);
        if(action.actionType == ActionType.CREATE) {
            DeleteCommand.getInstance().execute(((Directory) action.arg1), ((File)action.arg2).getName(),false);
        }
        if(action.actionType == ActionType.DELETE) {
            CreateFileCommand.getInstance().execute(((Directory) action.arg1), ((File)action.arg2), false);
        }
        if(action.actionType == ActionType.MOVE) {
            String path1 = (String)action.arg1;
            String path2 = (String)action.arg2;
            String[] path1s = path1.split("/");
            path1 = path1s[0];
            for(int i = 1; i < path1s.length - 1; i ++) {
                if(i != path1s.length - 1) path1 += "/";
                path1 += path1s[i];
            }
            path2 = path2 + "/" + path1s[path1s.length - 1];
            MoveCommand.getInstance().execute(path2, path1, false);
        }
    }
}
