/**
 * Name: Shaohua Yue
 * Course: CS-665 Software Designs & Patterns
 * Date: 12/07/2024
 * File Name: Action.java
 * Description: This class represents an action that can be performed on the file system,
 * used for implementing undo/redo functionality.
 */
package edu.bu.met.cs665;

public class Action<T> {
    ActionType actionType;

    T arg1;
    T arg2;

    /**
     * Constructor for creating a new action
     * @param actionType The type of action (CREATE, DELETE, MOVE)
     * @param arg1 The first argument for the action
     * @param arg2 The second argument for the action
     */
    Action(ActionType actionType, T arg1, T arg2) {
        this.actionType = actionType;
        this.arg1 = arg1;
        this.arg2 = arg2;
    }

}
