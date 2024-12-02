package edu.bu.met.cs665;

import java.util.ArrayList;
import java.util.List;

public class Directory implements FileSystemComponent {
    private String name;
    private FileSystemComponent sup;
    private List<FileSystemComponent> components = new ArrayList<>();

    public Directory(String name) {
        this.name = name;
    }
    public Directory(String name, Directory sup) {
        this.name = name;
        this.sup = sup;
    }

    public String getName() {
        return name;
    }
    public void add(FileSystemComponent component) {
        components.add(component);
    }

    public void displayDetails(int i) {
        for(int j = 0; j < i; j ++) {
            System.out.print("    ");
        }
        System.out.println("Directory: " + name);
        for (FileSystemComponent component : components) {

            component.displayDetails(i+1);
        }
    }

    public int getSize() {
        int totalSize = 0;
        for (FileSystemComponent component : components) {
            totalSize += component.getSize();
        }
        return totalSize;
    }

    @Override
    public void accept(FileSystemVisitor visitor) {
        for (FileSystemComponent component : components) {
            component.accept(visitor);
        }
    }

    public List<FileSystemComponent> getComponents() {
        return components;
    }

    public FileSystemComponent getSup() {
        return sup;
    }

    public void setSup(FileSystemComponent directory) {
        sup = directory;
    }
}