package edu.bu.met.cs665;

public class SizeVisitor implements FileSystemVisitor {
    private int totalSize = 0;

    public void visit(File file) {
        totalSize += file.getSize();
    }

    public void visit(Directory directory) {
        totalSize += directory.getSize();
    }

    public int getTotalSize() {
        return totalSize;
    }
}
