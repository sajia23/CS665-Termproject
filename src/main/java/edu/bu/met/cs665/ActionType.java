/**
 * Name: Shaohua Yue
 * Course: CS-665 Software Designs & Patterns
 * Date: 12/07/2024
 * File Name: ActionType.java
 * Description: This enum defines the types of actions that can be performed
 * on the file system, used in the undo/redo functionality.
 */
package edu.bu.met.cs665;

public enum ActionType {
    CREATE,  // For file/directory creation actions
    DELETE,  // For file/directory deletion actions
    MOVE     // For file/directory move actions
}
