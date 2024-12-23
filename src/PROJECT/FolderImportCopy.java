package PROJECT;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;

public class FolderImportCopy {
    public static void main(String[] args) {
        Path sourceFolder = Paths.get("C:\\Users\\bhagw\\Documents\\Employee.csv"); // Replace with the actual source folder
        Path destinationFolder = Paths.get("C:\\Users\\bhagw\\Documents\\import_data.csv"); // Replace with the actual destination folder

        try {
            // If the destination folder doesn't exist, create it
            if (Files.notExists(destinationFolder)) {
                Files.createDirectories(destinationFolder);
            }

            // Walk through the source folder and copy each file to the destination folder
            Files.walkFileTree(sourceFolder, new SimpleFileVisitor<Path>() {
                @Override
                public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                    Path destinationPath = destinationFolder.resolve(sourceFolder.relativize(file));
                    Files.copy(file, destinationPath, StandardCopyOption.REPLACE_EXISTING);
                    return FileVisitResult.CONTINUE;
                }

                @Override
                public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
                    Path destinationPath = destinationFolder.resolve(sourceFolder.relativize(dir));
                    if (Files.notExists(destinationPath)) {
                        Files.createDirectory(destinationPath);
                    }
                    return FileVisitResult.CONTINUE;
                }
            });

            System.out.println("Folder copied successfully!");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
