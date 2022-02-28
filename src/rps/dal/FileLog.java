package rps.dal;

import rps.bll.game.Result;

import java.io.IOException;
import java.nio.file.*;
import static java.nio.file.StandardOpenOption.*;
import java.util.*;
import java.util.stream.Collectors;

public class FileLog {

    String path ="statistic.txt";

    public void writeToFile(Result result) throws IOException {
        String toLog = result.toString()+ "\n";
        Files.write(Path.of(path), toLog.getBytes(), APPEND);
    }

    public void readFromFile()
    {
        List<String> result = new ArrayList<>();
    }
}
