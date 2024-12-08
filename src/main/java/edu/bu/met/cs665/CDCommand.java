/**
 * Name: Shaohua Yue
 * Course: CS-665 Software Designs & Patterns
 * Date: 12/07/2024
 * File Name: CDCommand.java
 * Description: This class implements the change directory command using
 * the Singleton pattern and Command pattern.
 */
package edu.bu.met.cs665;

public class CDCommand extends CommonCommand {
    private static volatile CDCommand cdCommand;

    /**
     * Private constructor for Singleton pattern
     */
    private CDCommand() {
    }

    /**
     * Get the singleton instance of CDCommand
     * @return The singleton instance
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

    /**
     * Execute the change directory command
     * @param rootDirectory The current directory
     * @param directory The target directory name
     * @return The new current directory or null if target not found
     */
    public Directory execute(Directory rootDirectory, String directory) {
        for(int i = 0; i < rootDirectory.getComponents().size(); i ++) {
            if(rootDirectory.getComponents().get(i).getName().equals(directory) 
               && rootDirectory.getComponents().get(i) instanceof Directory) {
                return ((Directory) rootDirectory.getComponents().get(i));
            }
        }
        System.out.println("Cannot find the directory!");
        return null;
    }
}
