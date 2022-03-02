package rps.dal;

import rps.bll.game.Result;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ObjectIO {
FileOutputStream FileOut = new FileOutputStream("statistic.txt");
FileInputStream FileIn = new FileInputStream("statistic.txt");
ObjectOutputStream ObjOut = new ObjectOutputStream(FileOut);
ObjectInputStream ObjIn = new ObjectInputStream(FileIn);


    public ObjectIO() throws IOException {
    }

    public void writeObject(Result result) throws IOException {
        ObjOut.writeObject(result);
    }

    public void writeAllObjects(List<Result> results) throws IOException {
        //ObjectOutputStream can only handle an object or an ArrayList of Objects.
        ArrayList<Result> ListOfResults = new ArrayList<>();
        ListOfResults.addAll(results);
        ObjOut.writeObject(ListOfResults);
    }

    public void readAllObjects() throws IOException
    {
        ObjIn
    }

}
