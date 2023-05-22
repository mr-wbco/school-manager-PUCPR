package com.utils;

import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Scanner;

public class UTILS {

    public static final String MESSAGE_BUNDLE = "resources/messages_pt_BR";
    public static final String INCORRET_NUMBER_ERROR_MESSAGE = "INCORRECT_NUMBER_ERROR_MESSAGE";
    public static final String EXIT_SYSTEM_MESSAGE = "EXIT_SYSTEM_MESSAGE";
    public static final String ENTER_TO_CONTINUE = "ENTER_TO_CONTINUE";

    public static String translate(String string) {
        return ResourceBundle.getBundle(MESSAGE_BUNDLE, Locale.getDefault()).getString(string);
    }

    public static void clearScreen() {
        for (int i = 0; i < 50; ++i) {
            System.out.println();
        }
    }

    public static void pressEnterToContinue() {
        System.out.println(" ");
        System.out.println(UTILS.translate(UTILS.ENTER_TO_CONTINUE));
        String continueOption = new Scanner(System.in).next();
    }

    public static void printHeaderManager() {
        UTILS.clearScreen();
        System.out.println("*********************************");
        System.out.println("GERENCIADOR DE SISTEMA ACADÊMICO");
        System.out.println("*********************************");
        System.out.println(" ");
    }

    public static int scannerIntValue() {
        int number = 0;
        boolean isValidNumber = false;
        while (!isValidNumber) {
            try {
                number = new Scanner(System.in).nextInt();
                isValidNumber = true;
            } catch (Exception e) {
                System.out.println("\nDIGITE UM NÚMERO VÁLIDO!");
                new Scanner(System.in).next();
            }
        }

        return number;
    }

    public static long scannerLongValue() {
        long number = 0;
        boolean isValidNumber = false;
        while (!isValidNumber) {
            try {
                number = new Scanner(System.in).nextLong();
                isValidNumber = true;
            } catch (Exception e) {
                System.out.println("\nDIGITE UM NÚMERO VÁLIDO!");
                new Scanner(System.in).next();
            }
        }

        return number;
    }
}
