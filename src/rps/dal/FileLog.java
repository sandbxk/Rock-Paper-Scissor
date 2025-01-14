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

    public void writeToFile(String filename, Result result) throws IOException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filename)))
        {
            bw.write(""+ result.getWinnerPlayer() +"," + result.getWinnerMove()+","+result.getLoserMove()+"\"");
        }
    }

    private List<String> readFromFile(String filename) throws IOException {
        String winnerPlayer;
        String winnerMove;
        String loserMove;
        String loaded;
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            while (br.readLine() != null) {
                loaded = br.readLine();
                System.out.println(loaded);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void writeMatrix(String filename, float[][] matrix) {
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

    public float[][] readMatrix(String filename) throws FileNotFoundException {
        float[][] nMatrix = new float[3][3];
        try (BufferedReader br = new BufferedReader(new FileReader(filename)))
        {
            for (int i = 0; i < 3; i++) {
                String[] split = br.readLine().split(",");
                for (int j = 0; j < 3; j++) {
                    nMatrix[i][j] = Float.parseFloat(split[j]);

                    
                }
            }
        } catch (IOException e) {
        }
        return nMatrix;
    }

    public static void main(String[] args) {
        FileLog fl = new FileLog();
        Result result = new Result();
    }
}
