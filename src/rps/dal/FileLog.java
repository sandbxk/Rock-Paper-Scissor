package rps.dal;

import rps.bll.game.Move;
import rps.bll.game.Result;
import rps.bll.game.ResultType;
import rps.bll.player.IPlayer;

import java.io.*;
import java.nio.file.*;
import static java.nio.file.StandardOpenOption.*;

import java.util.*;
import java.util.stream.Collectors;

public class FileLog {

    String pathResult = "statistic.txt";
    String pathMatrix = "matrix.txt";

    public void writeToFile(Result result) throws IOException {
        String toLog = result.toString() + "\n";
        Files.write(Path.of(pathResult), toLog.getBytes(), APPEND);
    }

    private List<String> readFromFile() {
        List<String> result = new ArrayList<>();
        Scanner scanner = new Scanner(pathResult);
        while (scanner.hasNextLine()) {
            String newLine = scanner.nextLine();
            result.add(newLine);
        }
        System.out.println(result);
        return result;
    }

    void writeMatrix(String filename, double[][] matrix) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filename))) {
            for (int i = 0; i < matrix.length; i++) {
                for (int j = 0; j < matrix[i].length; j++) {
                    bw.write(matrix[i][j] + ((j == matrix[i].length - 1) ? "" : ","));
                }
                bw.newLine();
            }
            bw.flush();
        } catch (IOException e) {
        }
    }

    public double[][] readMatrix(String filename) throws FileNotFoundException {
        double[][] nMatrix = new double[3][3];
        try (BufferedReader br = new BufferedReader(new FileReader(filename)))
        {
            for (int i = 0; i < 3; i++) {
                String[] split = br.readLine().split(",");
                for (int j = 0; j < 3; j++) {
                    nMatrix[i][j] = Double.parseDouble(split[j]);

                    
                }
            }
        } catch (IOException e) {
        }
        return nMatrix;
    }
}
