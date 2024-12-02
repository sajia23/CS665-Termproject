package edu.bu.met.cs665;

import java.util.List;

public class SearchCommand extends CommonCommand {
    private static volatile SearchCommand searchCommand;
    /**
     * Construction method
     */
    private SearchCommand() {

    }

    /**
     * Get a singleton instance.
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
    public List<String> execute(FileSystemComponent directory, String fileName) {
        // Add the file to the system or directory
        return catalog.get(fileName);
    }
}
