import java.io.*;
public class FileBoat implements Serializable {




    protected enum BoatEnum {SAILING, POWER};
    BoatEnum boatType;
    String boatName, boatModel;
    int boatYear;
    int boatLength;
    double purchasePrice, boatExpenses;


    public FileBoat() {
        boatType = null;
        boatName = null;
        boatYear = 0000;
        boatModel = null;
        boatLength = 0;
        purchasePrice = 0.0;
        boatExpenses = 0.0;
    }

    public FileBoat(BoatEnum boatType, String boatName, int boatYear, String boatModel, int boatLength, double purchasePrice, double boatExpenses) {

        this.boatType = boatType;
        this.boatName = boatName;
        this.boatYear = boatYear;
        this.boatModel = boatModel;
        this.boatLength = boatLength;
        this.purchasePrice = purchasePrice;
        this.boatExpenses = boatExpenses;
    }


    public String toString() {
        String print;
        print = String.format("\t%-8s%-21s%4d %-12s%2d\' : Paid $%9.2f : Spent $%9.2f", boatType,boatName,boatYear ,boatModel  , boatLength , purchasePrice , boatExpenses);
        return(print);
    }


    public String getBoatName(){

        return boatName;
    }

    public void setBoatExpenses (double expense){

        this.boatExpenses = this.boatExpenses + expense;
    }

    public boolean checkBoatExpenses (double expense) {
        if ((boatExpenses + expense) < purchasePrice) {
            return true;
        } else {
            return false;
        }
    }

    public double getBoatExpenses() {

        return boatExpenses;
    }

    public double remainingExpenses(){

        return purchasePrice - boatExpenses;
    }

    public double getPurchasePrice() {

        return purchasePrice;
    }


}
