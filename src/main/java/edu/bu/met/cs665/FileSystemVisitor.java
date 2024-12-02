package edu.bu.met.cs665;

public interface FileSystemVisitor {
    void visit(File file);
    void visit(Directory directory);
}
