package com.example.objectorientation;

import java.io.File;
import java.nio.file.FileSystems;

public class PathHandler {

    //takes relative path without separators and returns absolute Path depending on os and file structure
    //e.g. relativePath("path to data.txt");
    static String relativePath(String relativePath)
    {
        //absolute Path to root folder of project
        String filePath = new File("").getAbsolutePath();
        String pathSeparator = FileSystems.getDefault().getSeparator();

        for(String word : relativePath.split(" ")) {
            filePath = filePath.concat(pathSeparator);
            filePath = filePath.concat(word);
        }

        return filePath;
    }
}
