import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

//--------------------------------------------------------------------
public class FileBoatYard implements Serializable {
//--------------------------------------------------------------------
    private String name;
    private ArrayList<FileBoat> boatsInFleet = new ArrayList<>();
//--------------------------------------------------------------------
    public FileBoatYard (){
        int index;

        name = null;
    }
//--------------------------------------------------------------------
    public FileBoatYard (String newName){
        this();
        name = newName;
    }
//--------------------------------------------------------------------
    public void display(){
        int index = 0;

        System.out.print(name);
        while(boatsInFleet.size() > index) {
            System.out.println(boatsInFleet.get(index));
            index++;
        }

    }

//----------------------------------------------------------------------





//--------------------------------------------------------------------
    /*public static FileBoatYard loadBoatYard(String fileName) {
        ObjectInputStream fromStream = null;
        FileBoatYard local;

        try {
            fromStream = new ObjectInputStream(new FileInputStream(fileName));
            local = (FileBoatYard)fromStream.readObject();
        } catch (IOException e) {
            System.out.println("ERROR loading " + e.getMessage());
            return(null);
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
            return(null);
        } finally {
            if (fromStream != null) {
                try {
                    fromStream.close();
                } catch (IOException e) {
                    System.out.println("ERROR closing " + e.getMessage());
                    return(null);
                }
            }
        }
        return(local);
    } */

}
