package edu.bu.met.cs665;

public class CDCommand extends CommonCommand{

    private static volatile CDCommand cdCommand;
    /**
     * Construction method
     */
    private CDCommand() {

    }

    /**
     * Get a singleton instance.
     */
    public static CDCommand getInstance() {
        if(cdCommand == null) {
            synchronized (CDCommand.class) {
                if(cdCommand == null) {
                    cdCommand = new CDCommand();
                }
            }
        }
        return cdCommand;
    }
    public Directory execute(Directory rootDirectory, String directory) {
        for(int i = 0; i < rootDirectory.getComponents().size(); i ++) {
            if(rootDirectory.getComponents().get(i).getName().equals(directory) && rootDirectory.getComponents().get(i) instanceof Directory) {
                return ((Directory) rootDirectory.getComponents().get(i));
            }
        }
        System.out.println("Cannot find the directory!");
        return null;
    }
}
