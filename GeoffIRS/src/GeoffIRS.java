import java.util.Scanner;
//----------------------------------
public class GeoffIRS {

    private static final Scanner keyboard = new Scanner(System.in);
    private static final char STINKING_RICH = 'S';
    private static final char QUITE_RICH = 'Q';
    private static final char MIAMI_POOR = 'M';
    private static final char AVERAGE = 'A';
    private static final char REALISTIC = 'R';
    private static final char POOR = 'P';
    private static final double S_Q_TAXRATE = 0.25;
    private static final double M_TAXRATE = 0.10;
    private static final double A_R_TAXRATE = 0.03;
    private static final double P_TAXRATE = 0.0;
//----------------------------------
public static void main(String[] args) {
//---------------------------------- variables
    double income, deduction, taxableIncome, tax;
    int userInput;
    char taxGroup;

    income = 0.0;
    deduction = 0.0;
//---------------------------------- System output message Loop
    System.out.println("Welcome to King Geoff's IRS!");

    do {
        System.out.print("Enter next amount : ");
        userInput = keyboard.nextInt();
        if (userInput > 0) {
            income= income + userInput;
        } else {
            deduction = Math.abs(deduction) + Math.abs(userInput);
        }
    } while (userInput != 0);
//------------------------------------Methods
    taxableIncome = computeTaxableIncome(income, deduction);
    taxGroup = computeTaxGroup(taxableIncome);
    tax = computeTax(taxGroup, taxableIncome);
    printTaxInformation(income, deduction, taxableIncome, taxGroup, tax);
    }
//-------------------------------------Compute taxable Income
    private static double computeTaxableIncome(double userIncome, double userDeduction) {

        if (userIncome >= userDeduction ) {
             return userIncome - userDeduction;
        } else {
            return 0.0;
        }

    }
//---------------------------------------- compute tax group
    private static char computeTaxGroup(double taxableMoney) {
        char taxGroupLetter;

    if (taxableMoney >= 500000) {
        taxGroupLetter = STINKING_RICH;
    }
    else if (taxableMoney >= 200000){
        taxGroupLetter = QUITE_RICH;
    }
    else if (taxableMoney >= 100000) {
        taxGroupLetter = MIAMI_POOR;
    }
    else if (taxableMoney >= 50000) {
        taxGroupLetter = AVERAGE;
    }
    else if (taxableMoney >= 20000) {
        taxGroupLetter = REALISTIC;
    }
    else {
        taxGroupLetter = POOR;
    }
    return (taxGroupLetter);
    }

//--------------------------------------------- Compute tax owed
    private static double computeTax(char taxGroup, double incomeTaxable){
        //double owedTax =0.0;

        if (taxGroup == 'S' || taxGroup =='Q'){
            return S_Q_TAXRATE * incomeTaxable;
        }
        else if (taxGroup == 'M'){
            return M_TAXRATE * incomeTaxable;
        }
        else if (taxGroup == 'A' || taxGroup == 'R'){
            return A_R_TAXRATE * incomeTaxable;
        }
        else{
            return P_TAXRATE * incomeTaxable;
        }
        
    }
//----------------------------------------------- print tax information
    private static void printTaxInformation (double income, double deduction, double taxableIncome, char taxGroup, double taxOwed) {
        System.out.println("Income            = $" + income );
        System.out.println("Deductions        = $" + deduction);
        System.out.println("Taxable income    = $" + taxableIncome);
        System.out.println("Tax group         = " + taxGroup);
        System.out.println("Tax owed          = $" + taxOwed);
    }
}

