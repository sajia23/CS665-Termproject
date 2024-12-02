package edu.bu.met.cs665;

public class Action<T> {
    ActionType actionType;
    Directory directory;
    FileSystemComponent file;

    T arg1;

    T arg2;

    Action(ActionType actionType, T arg1, T arg2) {
        this.actionType = actionType;
        this.arg1 = arg1;
        this.arg2 = arg2;
    }

}
