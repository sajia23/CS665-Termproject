package edu.bu.met.cs665;

public class File implements FileSystemComponent {
    private String name;
    private int size;

    private FileSystemComponent sup;

    public File(String name, int size) {
        this.name = name;
        this.size = size;
        this.sup = sup;
    }

    public File(String name, int size, FileSystemComponent sup) {
        this.name = name;
        this.size = size;
        this.sup = sup;
    }

    public void displayDetails() {
        System.out.println("File: " + name + ", Size: " + size + " KB");
    }

    public int getSize() {
        return size;
    }

    @Override
    public void accept(FileSystemVisitor visitor) {
        visitor.visit(this);
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public FileSystemComponent getSup() {
        return sup;
    }

    public void setSup(FileSystemComponent directory) {
        sup = directory;
    }
}
