package org.app.utilies;

import java.util.Scanner;

public class UserInput {
    public static Scanner sc = new Scanner(System.in);
    static int count = 0;

    public static String Input(String request, String regex, String message) {
        System.out.print(Color.BRIGHT_YELLOW + request + Color.RESET);
        String value = sc.nextLine();

        if (count > 1 && request.equals("Enter Backup Id To Restore : ")) {
            System.out.println(Color.BRIGHT_RED + "[!] The Backup version that you Input, Doesn't exist!" + Color.RESET);
            count = 0;
            while (true){
                System.out.print(Color.BRIGHT_YELLOW + "Do You Want To Continues (y/n)? " + Color.RESET);
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
            System.out.println(Color.BRIGHT_RED + "[!] The field should not be empty!" + Color.RESET);
            count++;
            return Input(request, regex, message);
        } else if (!value.matches(regex)) {
            System.out.println(Color.BRIGHT_RED + message + Color.RESET);
            count++;
            return Input(request, regex, message);
        } else
            return value.trim();
    }

    public static void closeScanner() {
        sc.close();
    }
}
