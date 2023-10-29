import java.util.Scanner;
//-------------------
public class InterstateDriving {
//------------------------------ Final Variables
    private static final Scanner keyboard = new Scanner(System.in);
    private static final int SPEED_LIMIT = 65;
//--------------------- Main method
    public static void main(String[] args) {

        double speed, timeAvailable, travelTime, stopTime, distance;
        boolean overSpeedLimit;
//-------------- user instructions method
        userOutput();
//---------------- getting user inputs
        System.out.println("Enter distance to travel : ");
        distance = keyboard.nextDouble();
        System.out.println("Enter time available     : ");
        travelTime = ((keyboard.nextDouble()) * 60.0);
//----------------- perform calculations
        stopTime = computeStopTime(distance);
        timeAvailable = computeTimeAvailable(travelTime, stopTime);
        speed = computeSpeed(distance, timeAvailable);
        overSpeedLimit = computeOverSpeedLimit(speed, SPEED_LIMIT);
//----------------- Output computed statements
        printDriverCalculations(speed, overSpeedLimit);
    }
//------------------------ User instructions method
    private static void userOutput() {
        System.out.println("Welcome to the I95 Speed Machine");
        System.out.println(" ");
        System.out.println("You will have to supply: ");
        System.out.println("+ The distance you want to travel, in miles");
        System.out.println("+ The time you have available, in hours");
        System.out.println(" ");
    }
//-------------------- method compute stop time
    private static double computeStopTime(double userDistance) {
        double additionalTime = 0;

        if (userDistance % 100.0 == 0) {
            additionalTime =  ((userDistance / 100.0) * 5);
        }
        else if (userDistance % 100.0 != 0) {
            additionalTime = (((int)Math.ceil(userDistance / 100.0)) * 5.0);
        }
        return(additionalTime);

    }
//------------------------- method compute travel time
    private static double computeTimeAvailable(double travelTime, double stopTime ){
        double time;
        time = (travelTime - stopTime) / 60.0;
        return(time);
    }
//------------------------- method compute speed
    private static double computeSpeed(double distance, double timeAvailable) {
        double driveSpeed;
        driveSpeed = (distance / timeAvailable);
        return(driveSpeed);
    }
//------------------------- Boolean computation
    private static boolean computeOverSpeedLimit(double speed, int SPEED_LIMIT) {
        boolean exceedSpeedLimit;
        exceedSpeedLimit = speed > SPEED_LIMIT;
        return(exceedSpeedLimit);
    }
//------------------------- Output message
    private static void printDriverCalculations(double speed, boolean overSpeedLimit) {
        System.out.println("You will have to travel at " + speed + "mph");
        System.out.println("Over the speed limit     : " + overSpeedLimit);
    }
//--------------------------------------------
}
