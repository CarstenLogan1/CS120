import java.util.Scanner;
//-----------------------------------
public class Nitrox {

    private static final Scanner keyboard = new Scanner(System.in);
//-------------- Calculation Constant
    private static final double FPA_CONSTANT = 33.0;
    private static final double MAX_O2 = 1.4;
    private static final double MAX_CONT = 1.6;
//----------------------------------------------------------------
    public static void main(String[] args) {
//------------ variables
        int depth, percentO2;
        double ambientPressure, oxygenPressure;
        char oxygenGroup;
        boolean exceedMaxO2, exceedContingentO2;
//---------------- User Inputs
        System.out.print("Please enter depth and percentage O2 :  ");
        depth = keyboard.nextInt();
        percentO2 = keyboard.nextInt();
//------------------ Calculations
        ambientPressure = (depth / FPA_CONSTANT) + 1;
        oxygenPressure = (percentO2 / 100.0) * ambientPressure;
        oxygenGroup = (char)((int)(oxygenPressure * 10 )+ (int)('A'));
/*------------------ Oxygen Group
    if (oxygenPressure <= 0.1) {
        oxygenGroup = 'A';
    } else if (oxygenPressure <=0.2) {
        oxygenGroup = 'B';
    } else if (oxygenPressure <=0.3) {
        oxygenGroup = 'C';
    } else if (oxygenPressure <= 0.4) {
        oxygenGroup = 'D';
    } else if (oxygenPressure <= 0.5) {
        oxygenGroup = 'E';
    } else if (oxygenPressure <= 0.6) {
        oxygenGroup = 'F';
    } else if (oxygenPressure <= 0.7) {
        oxygenGroup = 'G';
    } else if (oxygenPressure <= 0.8) {
        oxygenGroup = 'H';
    } else if (oxygenPressure <= 0.9) {
        oxygenGroup = 'I';
    } else if (oxygenPressure <= 1.0) {
        oxygenGroup = 'J';
    } else if (oxygenPressure <= 1.1) {
       oxygenGroup = 'K';
    } else if (oxygenPressure <= 1.2) {
        oxygenGroup = 'L';
    } else if (oxygenPressure <= 1.3) {
        oxygenGroup = 'M';
    } else if (oxygenPressure <= 1.4) {
        oxygenGroup = 'N';
    } else if (oxygenPressure <= 1.5) {
        oxygenGroup = 'O';
    } else if (oxygenPressure <=1.6) {
        oxygenGroup = 'P';
    } else if (oxygenPressure <= 1.7) {
        oxygenGroup = 'Q';
    } else if (oxygenPressure <= 1.8) {
        oxygenGroup = 'R';
    } else if (oxygenPressure <=1.9) {
        oxygenGroup = 'S';
    } else if (oxygenPressure <= 2.0) {
        oxygenGroup = 'T';
    } else if (oxygenPressure <= 2.1) {
        oxygenGroup = 'U';
    } else if (oxygenPressure <= 2.2) {
        oxygenGroup = 'V';
    } else if (oxygenPressure <= 2.3) {
        oxygenGroup = 'W';
    } else if (oxygenPressure <=2.4) {
        oxygenGroup = 'X';
    } else if (oxygenPressure <= 2.5) {
        oxygenGroup = 'Y';
    } else if (oxygenPressure <= 2.6) {
        oxygenGroup = 'Z';
    }
*/
//------------Boolean calculation
        exceedMaxO2 = oxygenPressure > MAX_O2;
        exceedContingentO2 = oxygenPressure > MAX_CONT;
//--------------------- Dive Outputs
        System.out.println("Ambient pressure                     : " + ambientPressure);
        System.out.println("O2 pressure                          : " + oxygenPressure);
        System.out.print("O2 group                             : " + oxygenGroup);
        System.out.println(" ");
        System.out.println("Exceeds maximal O2 pressure          : " + exceedMaxO2);
        System.out.println("Exceeds contingency O2 Pressure      : " + exceedContingentO2);





}

}
