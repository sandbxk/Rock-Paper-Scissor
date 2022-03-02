package rps.dal;

import rps.bll.game.Result;


import java.io.*;
import java.util.ArrayList;

public class ObjectIO {

    public ObjectIO() throws IOException {
    }

    public void writeObject(Result result) throws IOException {
        //ObjectOutputStream can only handle a single Object or ArrayList of Objects
        ArrayList<Result> results = new ArrayList<>();
        try (ObjectOutputStream ObjOut = new ObjectOutputStream(new FileOutputStream("statistic.txt"))) {
            results.addAll(readObject());
            results.add(result);
            ObjOut.writeObject(result);
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public ArrayList<Result> readObject() throws FileNotFoundException {
        ArrayList<Result> results = new ArrayList<>();
        try (ObjectInputStream ObjIn = new ObjectInputStream(new FileInputStream("statistic.txt"))) {
            if (ObjIn.readObject() != null)
            results = (ArrayList<Result>) ObjIn.readObject();
        } catch (IOException | ClassNotFoundException e ) {
            e.printStackTrace();
        }
        return results;
    }

}
