package rps.dal;

import rps.bll.game.Result;


import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ObjectIO {

    public ObjectIO() throws IOException {
    }

    public void writeObject(Result result) throws IOException {
        //ObjectOutputStream can only handle a single Object or ArrayList of Objects
        ArrayList<Result> results = new ArrayList<>();
        try (ObjectOutputStream ObjOut = new ObjectOutputStream(new FileOutputStream("statistic.txt"))) {
            results.addAll(readObject());
            results.add(result);
            ObjOut.writeObject(results);
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public List<Result> readObject() throws FileNotFoundException {

        try (ObjectInputStream ObjIn = new ObjectInputStream(new FileInputStream("statistic.txt")))
        {
               return (List<Result>) ObjIn.readObject();

        }
        catch (IOException | ClassNotFoundException e) {
            return new ArrayList<>();
        }
    }

    public static void main(String[] args) throws IOException {
        ObjectIO IO = new ObjectIO();
        System.out.println(IO.readObject());
    }

}
