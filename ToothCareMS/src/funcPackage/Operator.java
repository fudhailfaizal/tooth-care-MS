package funcPackage;

import java.util.Scanner;

public class Operator {
    public static void main() {

        greeting("Gayani.");

        Scanner sc = new Scanner(System.in);
        System.out.println("-----MENU-----");
        System.out.println("Type in the number of the desired operation.");
        System.out.println("1. View Channeling Times");
        System.out.println("2. Reserve Appointment");
        System.out.println("3. Calculate Patient Fees");
        System.out.println("4. View History");
        System.out.println("5. Exit");

        //Input navigation values
        int nav = sc.nextInt();


        // Switch statement for selection
        switch (nav) {
            case 1:
                Channeling.channelingTimes();
                break;
            case 2:
                Reservation.main();
            case 3:
                // Calculate Fees
            case 4:
                // View History
            case 5:
                System.exit(0);
                break;
            default:
                System.out.println("Menu item not found :/");
        }

    }
    public static void greeting(String opName) {
        System.out.println("Welcome Miss " + opName);
    }

}