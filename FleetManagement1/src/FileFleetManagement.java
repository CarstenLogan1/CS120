import java.io.*;
import java.util.*;
import java.util.Scanner;
//--------------------------------------------------------------------
public class FileFleetManagement implements Serializable{
//--------------------------------------------------------------------
    private static final Scanner keyboard = new Scanner(System.in);
    private enum BoatEnum {SAILING, POWER};
    private static final String FILE_NAME = "FleetData.db";
    private static final int ARGS = 1;
//---------------------------------------------------------------------
    public static void main(String[] args) throws IOException {


        char letter;
        ArrayList<FileBoat> fleet = new ArrayList<>();



        if (args.length == ARGS) {

            BufferedReader fromBufferedReader;
            String line;
            String items[];

            try {
//--------------------------------------------------- Feeds in command line arguement and reads each line
                fromBufferedReader = new BufferedReader(new FileReader(args[0]));
                line = fromBufferedReader.readLine();

                while (line != null) {
//------------------------------------------------- splits string at commas and inserts them as string inputs of array
                    items = line.split(",");
//------------------------------------------------ Typecasts all elements of array and creates new boat object
                    FileBoat newBoat = new FileBoat(FileBoat.BoatEnum.valueOf(items[0]), items[1], Integer.parseInt(items[2]), items[3], Integer.parseInt(items[4]), Double.parseDouble(items[5]), 0.0);
//------------------------------------------------ puts each boat object into arraylist
                    fleet.add(newBoat);
                    line = fromBufferedReader.readLine();
                }
                fromBufferedReader.close();
            } catch (IOException e) {
                System.out.print(e.getMessage());
            }
        } else {
            loadBoat(fleet);
        }



        System.out.println("Welcome to the Fleet Management System");
        System.out.println("--------------------------------------");
        System.out.println("(P)rint, (A)dd, (R)emove, (E)xpense, e(X)it : ");

        do {
            letter = keyboard.next().toUpperCase().charAt(0);
            keyboard.nextLine();
            switch (letter){
                case 'P':
                    print(fleet);
                    break;

                case 'A':
                    addBoat(fleet);
                    break;

                case 'R':
                    removeBoat(fleet);
                    break;

                case 'E':
                    expense(fleet);
                    break;

                case 'X':
                    exit(fleet);
                    break;

                default:
                    System.out.println("Invalid menu option, try again");
                    System.out.println("(P)rint, (A)dd, (R)emove, (E)xpense, e(X)it : ");
            }
        } while (letter != 'X');




    }


    public static void loadBoat (ArrayList<FileBoat> fleet) {

        ObjectInputStream fromStream = null;
        FileBoat newBoat;

        try{
            fromStream = new ObjectInputStream(new FileInputStream(FILE_NAME));
            newBoat = (FileBoat) fromStream.readObject();

            while(newBoat != null) {
                fleet.add(newBoat);
                newBoat = (FileBoat) fromStream.readObject();
            }
        }  catch (EOFException e) {
        }  catch(IOException e) {
            System.out.println(e.getMessage());
        }  catch (ClassNotFoundException e){
            System.out.println(e.getMessage());
        }

        finally {
            if (fromStream != null)
                try{
                    fromStream.close();
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }
        }

    }



    private static void saveBoat (ArrayList<FileBoat> fleet) {

        ObjectOutputStream toStream = null;
        int i;

        try{
            toStream = new ObjectOutputStream(new FileOutputStream(FILE_NAME));

            for (i = 0; i < fleet.size(); i++){
                toStream.writeObject(fleet.get(i));
            }
        } catch (IOException e){
            System.out.println(e.getMessage());
        } finally {
            if (toStream != null) {
                try {
                    toStream.close();
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }
            }
        }
    }




//----------------------------- ensures that String of boat name given is actually one of the objects
    private static boolean boatCheck(ArrayList<FileBoat> boatReadFromFile, String inputBoat) {
        for ( int i = 0; i < boatReadFromFile.size(); i++) {
            if (boatReadFromFile.get(i).getBoatName().equalsIgnoreCase(inputBoat)) {
                return true;
            }
        }
        return false;
    }

//------------------------------ prints out fleet management report
    private static void print(ArrayList<FileBoat> fleet) {
        System.out.println("\n Fleet Report");
        for (int i = 0; i < fleet.size(); i++){
//------------------------------ iterates through arraylist and prints out each object
            System.out.println(fleet.get(i));
        }
        System.out.printf("\tTotal                                             : Paid $%9.2f : Spent $%9.2f" , totalPurchasePrice(fleet), totalExpenses(fleet));
        System.out.println();
        System.out.println("(P)rint, (A)dd, (R)emove, (E)xpense, e(X)it : ");
    }

//----------------------------- Adds boat to the arraylist
    private static ArrayList<FileBoat> addBoat (ArrayList<FileBoat> fleet){

        String line;

//-------------------------------------------------------------------- Enters CSV Data
        System.out.println("Please enter the new boat CSV data          : ");
        line = keyboard.nextLine();
//------------------------------------------------ Takes the comma separated string and splits it into string array inputs
        String[] items = line.split("\\,");
//-------------------------------------------------type casts each array input into the necessary data type

//---------------------------------------- feeds the data into boat class and stores new boat in ArrayList
        FileBoat newBoat = new FileBoat(FileBoat.BoatEnum.valueOf(items[0]), items[1], Integer.parseInt(items[2]), items[3], Integer.parseInt(items[4]), Double.parseDouble(items[5]), 0.0);
        fleet.add(newBoat);

        System.out.println("(P)rint, (A)dd, (R)emove, (E)xpense, e(X)it : ");

        return (fleet);
    }

//----------------------------------------------- remove a boat from the array list
    private static ArrayList<FileBoat> removeBoat (ArrayList<FileBoat> fleet) {

        String removeBoat;
        boolean valid;

        System.out.println("Which boat do you want to remove?           : ");
        removeBoat = keyboard.nextLine();
//---------------------------------------------- send to boatcheck method to validate user input
        valid = boatCheck(fleet, removeBoat);

//---------------------------------------- boat is found then removed from arraylist
        if(valid == true) {
            for ( int i = 0; i < fleet.size(); i++) {
                if (fleet.get(i).getBoatName().equalsIgnoreCase(removeBoat)) {
                    fleet.remove(i);
                }
            }
        } else {
            System.out.println("Cannot find boat " + removeBoat);
        }


        System.out.println("(P)rint, (A)dd, (R)emove, (E)xpense, e(X)it : ");

        return (fleet);
    }


//---------------------------------------- returns the expneses for each boat
    private static ArrayList<FileBoat> expense (ArrayList<FileBoat> fleet) {

        String inputBoat;
        boolean valid;
        double inputExpenses;

        System.out.println("Which boat do you want to spend on?         : ");
        inputBoat = keyboard.nextLine();
        valid = boatCheck(fleet, inputBoat);
//-------------------------------------- sends to boat check and makes sure it is a valid string
        if (valid == true){
            System.out.println("How much do you want to spend?                 : ");
            inputExpenses = keyboard.nextDouble();

//--------------------------------------- iterates till at correct boat object and adds expenses
            for ( int i = 0; i < fleet.size(); i++) {
                if (fleet.get(i).getBoatName().equalsIgnoreCase(inputBoat)) {
                    if(fleet.get(i).checkBoatExpenses(inputExpenses) == true){
                        fleet.get(i).setBoatExpenses(inputExpenses);
                        System.out.printf("Expense authorized, $%9.2f spent ", fleet.get(i).getBoatExpenses());
                        System.out.println();
                    }
                    else {
                        System.out.printf("Expense not permitted, only $%9.2f left to spend", fleet.get(i).remainingExpenses());
                        System.out.println();
                    }

                }
            }
        } else  {
            System.out.println("Cannot find boat " + inputBoat);
        }
        System.out.println("(P)rint, (A)dd, (R)emove, (E)xpense, e(X)it : ");

        return (fleet);
    }

//------------------------------------------------- returns the total purchase amount of all the boats
    private static double totalPurchasePrice(ArrayList<FileBoat> boatsReadFromFile) {
        double totalPurchasePrice = 0;
        for (int i = 0; i < boatsReadFromFile.size(); i++) {
            totalPurchasePrice += boatsReadFromFile.get(i).getPurchasePrice();
        }
        return totalPurchasePrice;
    }

//-------------------------------------------------- returns the total amount of expenses for all the boats
    private static double totalExpenses(ArrayList<FileBoat> boatsReadFromFile) {
        double totalExpenses = 0;
        for (int i = 0; i < boatsReadFromFile.size(); i++) {
            totalExpenses += boatsReadFromFile.get(i).getBoatExpenses();
        }
        return  totalExpenses;
    }


//-------------------------------------------------- exit program and save data
    private static void exit (ArrayList<FileBoat> fleet) {
        saveBoat(fleet);
        System.out.println("Exiting the Fleet Management System");
        System.exit(0);
    }
}




