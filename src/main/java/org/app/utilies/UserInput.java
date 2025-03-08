package org.app.utilies;

import java.util.Scanner;
import java.util.regex.Pattern;

public class UserInput {
    public static Scanner sc = new Scanner(System.in);
    static int count = 0;

    public static String Input(String request, String regex, String message) {
        System.out.print(request);
        String value = sc.nextLine();

        if (count >= 3 && request.equals("Enter Backup Id To Restore : ")) {
            System.out.println("The Backup version that you Input, Doesn't exist");
            count = 0;
            while (true){
                System.out.println("Do You Want To Continues (y/n)? ");
                value = sc.nextLine();

                if (value.equals("y") || value.equals("Y")) {
                    return Input(request, regex, message);
                }

                if (value.equals("n") || value.equals("N")) {
                    return "0";
                }
            }
        }

        if (value == null || value.trim().isEmpty()) {
            System.out.println("The field should not be empty");
            count++;
            return Input(request, regex, message);
        } else if (!value.matches(regex)) {
            System.out.println(message);
            count++;
            return Input(request, regex, message);
        } else
            return value.trim();
    }

    public static void closeScanner() {
        sc.close();
        System.out.println("Close Scanner Successfully!");
    }
}
