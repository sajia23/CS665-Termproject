package edu.bu.met.cs665;

public class CreateDirectoryCommand extends CommonCommand{

    private static volatile CreateDirectoryCommand createDirectoryCommand;
    /**
     * Construction method
     */
    private CreateDirectoryCommand() {

    }

    /**
     * Get a singleton instance.
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
    public void execute(Directory directory, Directory newDirectory, Boolean ifPushIntoStack) {
        if(ifPushIntoStack) {
            Action action = new Action(ActionType.CREATE, directory, newDirectory);
            undoStack.push(action);
        }
        newDirectory.setSup(directory);
        directory.add(newDirectory);
    }
}
