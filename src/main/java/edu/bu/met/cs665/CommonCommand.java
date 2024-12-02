package edu.bu.met.cs665;

import java.util.*;
import java.util.Iterator;

public abstract class CommonCommand {
    protected static Deque<Action> undoStack = new ArrayDeque<>();
    protected static Deque<Action> redoStack = new ArrayDeque<>();
    protected static Map<String, List<String>> catalog = new HashMap<>();

    static Directory root;

//    CommonCommand() {
//        undoStack = new ArrayDeque<>();
//        redoStack = new ArrayDeque<>();
//        catalog = new HashMap<>();
//    }

    public void setRoot(Directory root) {
        this.root = root;
    }

    public FileSystemComponent pathToFileOrDirectory(String path) {
        String[] paths = path.split("/");
        Directory tempRoot = root;
        if(!paths[0].equals(tempRoot.getName())) return null;
        for(int i = 1; i < path.length(); i ++) {
            Iterator<FileSystemComponent> iterator = tempRoot.getComponents().iterator();
            while (iterator.hasNext()) {
                FileSystemComponent element = iterator.next();
                if (element.getName().equals(paths[i])) {
                    if(i == path.length()-1) return tempRoot;
                    tempRoot = (Directory) element;
                }
            }
        }
        return null;
    }

    public String fileOrDirectoryToPath(FileSystemComponent fileSystemComponent) {
        StringBuilder sb = new StringBuilder();
        while(fileSystemComponent.getSup() != null) {
            if(fileSystemComponent instanceof Directory) sb.insert(0, "/");
            sb.insert(0, fileSystemComponent.getName());
            fileSystemComponent = fileSystemComponent.getSup();
        }
        return sb.toString();
    }
    public <T> void execute(T currentDirectory, T parameter){}
}
