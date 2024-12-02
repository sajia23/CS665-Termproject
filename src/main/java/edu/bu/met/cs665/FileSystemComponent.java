package edu.bu.met.cs665;

public interface FileSystemComponent {
    void displayDetails();  // To display file or directory details
    int getSize();          // To get size of a file or directory
    void accept(FileSystemVisitor visitor);  // For Visitor pattern

    String getName();

    FileSystemComponent getSup();
}