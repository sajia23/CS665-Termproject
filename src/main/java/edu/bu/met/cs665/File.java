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

    public void displayDetails(int i) {
        for(int j = 0; j < i; j ++) {
            System.out.print("    ");
        }
        System.out.print("File: " + name + ", Size: " + size + " KB");
        System.out.println();
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
