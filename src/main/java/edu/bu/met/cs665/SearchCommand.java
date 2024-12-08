/**
 * Name: Shaohua Yue
 * Course: CS-665 Software Designs & Patterns
 * Date: 12/07/2024
 * File Name: SearchCommand.java
 * Description: This class implements the command to search for files in the system,
 * using both Singleton and Command patterns.
 */
package edu.bu.met.cs665;

import java.util.List;

public class SearchCommand extends CommonCommand {
    private static volatile SearchCommand searchCommand;
    
    /**
     * Private constructor for Singleton pattern
     */
    private SearchCommand() {
    }

    /**
     * Get the singleton instance of SearchCommand
     * @return The singleton instance
     */
    public static SearchCommand getInstance() {
        if(searchCommand == null) {
            synchronized (SearchCommand.class) {
                if(searchCommand == null) {
                    searchCommand = new SearchCommand();
                }
            }
        }
        return searchCommand;
    }

    /**
     * Execute the search command
     * @param directory The directory to search in
     * @param fileName The name of the file to search for
     * @return List of matching file paths
     */
    public List<String> execute(FileSystemComponent directory, String fileName) {
        return catalog.get(fileName);
    }
}
