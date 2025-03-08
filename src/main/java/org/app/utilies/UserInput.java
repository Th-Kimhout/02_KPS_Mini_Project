package org.app.utilies;

import java.util.Scanner;

public class UserInput {
    public static Scanner sc = new Scanner(System.in);

    public static String Input(String request, String regex, String message) {
        System.out.print(request);
        String value = sc.nextLine();

        if (value == null || value.trim().isEmpty()) {
            System.out.println("The field should not be empty");
            return Input(request, regex, message);
        } else if (!value.matches(regex)) {
            System.out.println(message);
            return Input(request, regex, message);
        } else
            return value.trim();
    }
}
