import java.util.Scanner;
//------------------------------------
public class FloridaDentalRecords {
//------------------------------------
    private static final Scanner keyboard = new Scanner(System.in);
    private static final int ROWS = 2;
    private static final int COLUMNS = 8;
    private static final int MAXIMUM_TEETH = 8;
    private static final int MAXIMUM_PEOPLE = 7;
//------------------------------------
    public static void main(String[] args) {

        int numPeopleInFamily;
        String [] familyNames;
        char [][][] toothInputs;
        char letter;

// -------------------------------------------Introduction Message
        System.out.println("Welcome to the Floridian Tooth Records");
        System.out.println("--------------------------------------");
//Method checks and receives proper number of people in family
        numPeopleInFamily = getNumOfFamilyMembers();

//---------------------------------Initialization of size of arrays
        familyNames = new String [numPeopleInFamily];
        toothInputs = new char [numPeopleInFamily][ROWS][COLUMNS];

//--------------------Loop gets family member name and upper/lower teeth
        int familyMember;
        for(familyMember = 0; familyMember < familyNames.length; familyMember ++) {
            System.out.print("Please enter the name for family member " + (familyMember + 1) + "            : ");
            familyNames[familyMember] = keyboard.next();

//-------------------------calls method to ensure proper inputs for teeth
            String acceptedTeeth = teethRow("uppers", familyNames[familyMember]);
            for(int teeth = 0; teeth < acceptedTeeth.length(); teeth++){
                toothInputs[familyMember][0][teeth] = acceptedTeeth.charAt(teeth);
            }
            acceptedTeeth = teethRow("lowers", familyNames[familyMember]);
            for (int teeth = 0; teeth < acceptedTeeth.length(); teeth++){
                toothInputs[familyMember][1][teeth] = acceptedTeeth.charAt(teeth);
            }
        }
//---------------------- end of loop; names and tooth strings have been recorded

        System.out.println();
        System.out.print("(P)rint, (E)xtract, (R)oot, e(X)it                   : ");

//
        do {
            letter = keyboard.next().toUpperCase().charAt(0);
            switch (letter) {
                case 'P':
                    printTeethRecord(toothInputs, familyNames);
                    System.out.print("(P)rint, (E)xtract, (R)oot, e(X)it                   : ");
                    break;
                case 'E':
                    toothExtraction(toothInputs, familyNames);
                    System.out.print("(P)rint, (E)xtract, (R)oot, e(X)it                   : ");
                    break;
                case 'R':
                    rootIndices(toothInputs);
                    System.out.print("(P)rint, (E)xtract, (R)oot, e(X)it                   : ");
                    break;
                case 'X':
                    System.out.print("Exiting the Floridian Tooth Records :)");
                    break;
                default:
                    System.out.print("Invalid menu options, try again                      : ");
            }
        } while (letter != 'X');



    }



    private static int getNumOfFamilyMembers() {
        int numPeople;
        System.out.print("Please enter number of people in the family          : ");
        numPeople = keyboard.nextInt();

//---------- person number must be positive and at maximum 6 people
        while(numPeople >= MAXIMUM_PEOPLE || numPeople <= 0) {
            System.out.print("Invalid number of people, try again                  : ");
            numPeople = keyboard.nextInt();
        }
        return numPeople;
    }

//------------------------ Ensures Proper teeth inputs
    private static String teethRow(String row, String name){
        String toothLetter;
        boolean correctInput = true;
        int incorrectInput = 0;

        System.out.print("Please enter the " + row + " for " + name +  "                      : ");
        do {

            correctInput = true;
            toothLetter = keyboard.next().toUpperCase();
            if(toothLetter.length() > 8 ) {
                System.out.print("Too many teeth, try again                            : ");
                incorrectInput = 0;
            } else if (toothLetter.length() <= 8) {
                for(int tooth = 0; tooth < toothLetter.length(); tooth++) {

//--------------------- States input characters must be I, B, or M
                    if((toothLetter.charAt(tooth) != 'M') && (toothLetter.charAt(tooth) != 'B') && (toothLetter.charAt(tooth) != 'I') && (toothLetter.charAt(tooth) != 'm') && (toothLetter.charAt(tooth) != 'b') && (toothLetter.charAt(tooth) != 'i')) {
                        correctInput = false;
                    }


                }
 //---------------- if invalid character is given prompts user to try again
                if (correctInput == false) {
                    System.out.print("Invalid teeth types, try again                       :");
                    incorrectInput = 0;
                }
                else {
                    incorrectInput = 1;
                }

            }

        }

        while(incorrectInput == 0);
        return toothLetter;
    }



//------------------------------------ print users teeth records
    private static void printTeethRecord(char[][][] toothInputs, String[] familyNames) {
        System.out.println(" ");
        for (int name = 0; name < familyNames.length; name++) {
            System.out.println(familyNames[name]);
            for (int row = 0; row < toothInputs[name].length; row++) {
                if (row == 0) {
                    System.out.print("   Uppers: ");
                } else {
                    System.out.print("   Lowers: ");
                }
                for (int toothIndex = 0; toothIndex < toothInputs[name][row].length;toothIndex++){
                    System.out.print((toothIndex + 1) + ":" + toothInputs[name][row][toothIndex] + " ");
                }
               System.out.println();
            }
        }

        }



    private static void toothExtraction(char[][][] toothInputs, String[] familyNames) {
        String tempName;
        char toothRow;
        int toothIndex;
        int toothRowIndex = 0;

        System.out.print("Which family member                                  : ");
        tempName = keyboard.next();

//-------------------- ensures inputted name for extarction is stored in array
        int valid;
        valid = nameCheck(familyNames, tempName );

        while (valid < 0) {
            System.out.print("Invalid family member, try again                     : ");
            tempName = keyboard.next();
            valid = nameCheck(familyNames, tempName);
        }
        System.out.print("Which tooth layer (U)pper or (L)ower                 : ");
        toothRow = keyboard.next().charAt(0);

        while (!((toothRow == 'U') || (toothRow == 'u') || (toothRow == 'L') || (toothRow == 'l'))) {
            System.out.print("Invalid layer, try again                             : ");
            toothRow = keyboard.next().charAt(0);
        }
 //------------------------------------ lower row of teeth has index 0
        if (toothRow == 'L' || toothRow == 'l') {
            toothRowIndex = 1;
        }
//----------------------------------------- upper row of teeth has index 1
        else if (toothRow == 'U' || toothRow == 'u') {
            toothRowIndex = 0;
        }

        System.out.print("Which tooth number                                   : ");
        toothIndex = keyboard.nextInt();

        do {
            if (toothIndex < 0 || toothIndex > MAXIMUM_TEETH ) {
                System.out.print("Invalid tooth number, try again                      : ");
                toothIndex = keyboard.nextInt();
            }
            if (toothInputs[valid][toothRowIndex][toothIndex-1] == 'M') {
                System.out.print("Missing tooth, try again                             : ");
                toothIndex = keyboard.nextInt();
            }
        } while (toothIndex < 0 || toothIndex > MAXIMUM_TEETH || toothInputs[valid][toothRowIndex][toothIndex-1] == 'M' );

        toothInputs[valid][toothRowIndex][toothIndex-1] = 'M';



    }

//---------------------------- make sure user inputted name is name stored in array
   private static int nameCheck(String [] familyNames, String tempName) {
        for ( int i = 0; i < familyNames.length; i++) {
            if (familyNames[i].equalsIgnoreCase(tempName)) {
                return i;
            }
        }
        return -1;
   }

    private static void rootIndices(char[][][] toothInputs) {
        int incisor = 0;
        int bicupsid = 0;
        int missing = 0;
        for (int i = 0; i < toothInputs.length; i++) {
            for(int j = 0; j < toothInputs[i][0].length; j++) {
//------------------------------------ Gathers first row of teeth
                if (toothInputs[i][0][j] == 'I') {
                    incisor ++;
                }
                else if (toothInputs[i][0][j] == 'B') {
                    bicupsid++;
                }
                else if (toothInputs[i][0][j] == 'M') {
                    missing++;
                }
            }
//------------------------------------- Gathers second row of teeth
            for (int j = 0; j < toothInputs[i][1].length; j++) {
                if (toothInputs[i][1][j] == 'I') {
                    incisor ++;
                }
                else if (toothInputs[i][1][j] == 'B') {
                    bicupsid ++;
                }
                else if (toothInputs[i][1][j] == 'M') {
                    missing ++;
                }
            }
        }

        double rootPlus;
        double rootMinus;

        double underRadical = ((bicupsid * bicupsid) - (4 * incisor * (-missing)));
        rootPlus = (((-bicupsid) + Math.sqrt(underRadical)) / (2 * incisor));
        rootMinus = (((-bicupsid) - Math.sqrt(underRadical)) / (2 * incisor));

        System.out.printf("One root canal at %.2f%n",rootPlus);
        System.out.printf("Another root canal at %.2f%n",rootMinus);

//------------------ when value under radical is negative we get imaginary number
        if (underRadical < 0) {
            System.out.println("Error, no real root canal.");
        }


    }


}
