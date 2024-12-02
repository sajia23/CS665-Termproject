package edu.bu.met.cs665;

import java.util.ArrayList;
import java.util.List;

public class CreateFileCommand extends CommonCommand {

    private static volatile CreateFileCommand createFileCommand;
    /**
     * Construction method
     */
    private CreateFileCommand() {

    }

    /**
     * Get a singleton instance.
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

    public void execute(Directory directory, FileSystemComponent fileOrDirectory, Boolean ifPushIntoStack) {
        if(ifPushIntoStack) {
            Action action = new Action(ActionType.CREATE, directory, fileOrDirectory);
            super.undoStack.push(action);
        }
        List<String> temp = catalog.getOrDefault(fileOrDirectory.getName(), new ArrayList<>());
        temp.add(directory.getName());
        directory.add(fileOrDirectory);
        ((File) fileOrDirectory).setSup(directory);
    }
}