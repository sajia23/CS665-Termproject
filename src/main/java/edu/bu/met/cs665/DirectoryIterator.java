package edu.bu.met.cs665;

import java.util.List;

public class DirectoryIterator implements Iterator {
    private List<FileSystemComponent> components;
    private int index = 0;

    public DirectoryIterator(List<FileSystemComponent> components) {
        this.components = components;
    }

    public boolean hasNext() {
        return index < components.size();
    }

    public FileSystemComponent next() {
        return components.get(index++);
    }
}
