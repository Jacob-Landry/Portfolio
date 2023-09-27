package util;

import java.util.Scanner;

public class Display {

    public void print(String str) {
        System.out.println(str);
    }

    public String promptUntilCorrectFormat(String prompt, String regex) {
        Scanner sc = new Scanner(System.in);
        String input;

        do {
            System.out.println(prompt);
            input = sc.next();

        } while (input == null || !input.matches(regex));

        return input;
    }
}
