package rps.dal;

import rps.bll.game.Move;
import rps.bll.game.Result;
import rps.bll.game.ResultType;
import rps.bll.player.IPlayer;

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

    private List<String> readFromFile()
    {
        List<String> result = new ArrayList<>();
        Scanner scanner = new Scanner(path);
        while (scanner.hasNextLine())
        {
            String newLine = scanner.nextLine();
            result.add(newLine);
        }
        System.out.println(result);
        return result;
    }

}
