package edu.bu.met.cs665;

class FileSystemBuilder {
    private Directory rootDirectory;

    public FileSystemBuilder() {
        rootDirectory = new Directory("root");
    }

    // Add a directory to the root directory
    public FileSystemBuilder addDirectory(String directoryName) {
        Directory newDirectory = new Directory(directoryName);
        CreateDirectoryCommand.getInstance().execute(rootDirectory, newDirectory, false);
        //rootDirectory.add(newDirectory);
        return this;
    }

    // Add a file to a specific directory
    public FileSystemBuilder addFile(String directoryName, String fileName, int fileSize) {
        File file = new File(fileName, fileSize, null);
        Directory directory = findDirectory(rootDirectory, directoryName);
        if (directory != null) {
            //directory.add(file);
            CreateFileCommand.getInstance().execute(directory, file, false);
            //file.setSup(directory);
        }
        return this;
    }

    // Find a directory recursively by name
    private Directory findDirectory(Directory directory, String name) {
        if (directory.getName().equals(name)) {
            return directory;
        }
        for (FileSystemComponent component : directory.getComponents()) {
            if (component instanceof Directory) {
                Directory found = findDirectory((Directory) component, name);
                if (found != null) {
                    return found;
                }
            }
        }
        return null;
    }

    // Build and return the constructed file system
    public Directory build() {
        return rootDirectory;
    }
}
