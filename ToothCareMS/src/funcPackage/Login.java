package funcPackage;

import java.util.Scanner;

public class Login {
    public static void main() {

        String user;
        String passW;
        String confPW;

        Scanner sc = new Scanner(System.in);
        System.out.println("Enter Username");
        String userName = sc.nextLine();
        user = userName;

        System.out.println("Enter Password");
        String passWord = sc.nextLine();
        passW= passWord;

        System.out.println("Confirm Password");
        String confirmPass = sc.nextLine();
        confPW = confirmPass;

        // Username
        switch (user.trim()) {
            case "Admin":
                passwordVerifAdmin(passW, confPW);
                System.out.print("Login Successful! ");
                Surgeon.main();
                break;

            case "Appointments":
                passwordVerifOperator(passW, confPW);
                System.out.print("Login Successful! ");
                Operator.main();
                break;

            default:
                System.out.println("The username doesn't exist :/");
                System.exit(0);
        }
    }

    static void passwordVerifAdmin(String passW, String confPW) {
        // Password Verification Admin
        if (passW.equals(confPW)) {
            switch (passW.trim()) {
                case "admin123":
                    System.out.println("Password Verified!");
                    clearScreen();
                    break;
                default:
                    System.out.println("Invalid Password");
                    System.exit(0);
            }
        } else {
            System.out.println("Passwords do not match :/");
            System.exit(0);
        }
    }
    static void passwordVerifOperator(String passW, String confPW) {
        // Password Verification Admin
        if (passW.equals(confPW)) {
            switch (passW.trim()) {
                case "op123":
                    System.out.println("Password Verified!");
                    clearScreen();
                    break;
                default:
                    System.out.println("Invalid Password");
                    System.exit(0);
            }
        } else {
            System.out.println("Passwords do not match :/");
            System.exit(0);
        }
    }
    public static void clearScreen() {
        //print enough lines to clear screen
        for (int i = 0; i < 50; i++) {
            System.out.println();
        }
//        try {
//            if (System.getProperty("os.name").contains("Windows")) {
//                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
//            } else {
//                System.out.print("\033[H\033[2J");
//                System.out.flush();
//            }
//        } catch (Exception e) {
//            System.out.println("Error clearing the screen: " + e.getMessage());
//        }
    }
}
