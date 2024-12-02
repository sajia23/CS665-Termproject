package edu.bu.met.cs665;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class MoveCommand extends CommonCommand {

    private static volatile MoveCommand moveCommand;
    /**
     * Construction method
     */
    private MoveCommand() {

    }

    /**
     * Get a singleton instance.
     */
    public static MoveCommand getInstance() {
        if(moveCommand == null) {
            synchronized (MoveCommand.class) {
                if(moveCommand == null) {
                    moveCommand = new MoveCommand();
                }
            }
        }
        return moveCommand;
    }

    public FileSystemComponent parseFileOrDirectory(List<FileSystemComponent> roots, String[] paths, int index) {
        for(int i = 0; i < roots.size(); i ++) {
            if(roots.get(i).getName().equals(paths[index])) {
                if(roots.get(i) instanceof Directory) {
                    if(index == paths.length - 1) return roots.get(i);
                    return parseFileOrDirectory(((Directory) roots.get(i)).getComponents(), paths, index+1);
                } else {
                    return roots.get(i);
                }
            }
        }
        return null;
    }
    public void execute(String path1, String path2, Boolean ifPushIntoStack) {
        FileSystemComponent file = parseFileOrDirectory(new ArrayList<>(Arrays.asList(root)), path1.split("/"), 0);
        if(file == null) System.out.println("Cannot find file in arg1!");
        FileSystemComponent directory = parseFileOrDirectory(new ArrayList<>(Arrays.asList(root)), path2.split("/"), 0);
        if(directory == null) System.out.println("Cannot find directory in arg2!");

        Iterator<FileSystemComponent> iterator = ((Directory)((File) file).getSup()).getComponents().iterator();
        while (iterator.hasNext()) {
            FileSystemComponent element = iterator.next();
            if (element.getName().equals(file.getName())) {
                iterator.remove(); // 使用迭代器删除元素
            }
        }
        ((File)file).setSup(directory);
        ((Directory) directory).add(file);
        if(ifPushIntoStack) {
            Action action = new Action(ActionType.MOVE, path1, path2);
            undoStack.push(action);
        }
    }
}
