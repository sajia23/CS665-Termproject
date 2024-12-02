package edu.bu.met.cs665;

import java.util.Iterator;
public class DeleteCommand extends CommonCommand{
    private static volatile DeleteCommand deleteCommand;
    /**
     * Construction method
     */
    private DeleteCommand() {

    }

    /**
     * Get a singleton instance.
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

    public void execute(Directory directory, String fileName, Boolean ifPushIntoStack) {
        Iterator<FileSystemComponent> iterator = directory.getComponents().iterator();
        while (iterator.hasNext()) {
            FileSystemComponent element = iterator.next();
            if (element.getName().equals(fileName)) {
                if(ifPushIntoStack) {
                    Action action = new Action(ActionType.DELETE, directory, element);
                    super.undoStack.add(action);
                }
                iterator.remove();
            }
        }
    }
}
