package zadachi;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/*
Программа показывает сколько папок, файлов содержится в папке, которую ты вводишь в косноль.
*/

public class FileAndArchive extends SimpleFileVisitor<Path> {
    public static int numOfFiles = 0;
    public static int numOfDirs = -1;
    public static long byteSizeOfDirectories = 0;

    @Override
    public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
        numOfDirs++;
        return super.preVisitDirectory(dir, attrs);
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        numOfFiles++;
        byteSizeOfDirectories += Files.size(file);
        return super.visitFile(file, attrs);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String fileName = reader.readLine();
        Path p = Paths.get(fileName);
        FileAndArchive solution = new FileAndArchive();
        if(!Files.isDirectory(p)){
            System.out.println(p.toAbsolutePath() + " - не папка");
        }
        else{
            Files.walkFileTree(p, solution);
        }
        System.out.println("Всего папок - " + FileAndArchive.numOfDirs);
        System.out.println("Всего файлов - " + FileAndArchive.numOfFiles);
        System.out.println("Общий размер - " + FileAndArchive.byteSizeOfDirectories);
    }
}

