package com.utils;

import javax.swing.*;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Scanner;

public class UTILS {

    public static final String MESSAGE_BUNDLE = "resources/messages_pt_BR";

    public static final String INSERT_NEW = "INSERT_NEW";
    public static final String LIST_ALL = "LIST_ALL";
    public static final String UPDATE_INFORMATION = "UPDATE_INFORMATION";
    public static final String DELETE_RECORD = "DELETE_RECORD";
    public static final String DELETE_ALL_RECORDS = "DELETE_ALL_RECORDS";

    public static final String INCORRECT_NUMBER_ERROR_MESSAGE = "INCORRECT_NUMBER_ERROR_MESSAGE";
    public static final String ALREADY_EXISTS_ERROR_MESSAGE = "ALREADY_EXISTS_ERROR_MESSAGE";
    public static final String EMPTY_LIST_ERROR_MESSAGE = "EMPTY_LIST_ERROR_MESSAGE";
    public static final String CODE_NOT_FOUND_ERROR_MESSAGE = "CODE_NOT_FOUND_ERROR_MESSAGE";
    public static final String EMPTY_STUDENT_LIST_ERROR_MESSAGE = "EMPTY_STUDENT_LIST_ERROR_MESSAGE";
    public static final String EMPTY_PROFESSOR_LIST_ERROR_MESSAGE = "EMPTY_PROFESSOR_LIST_ERROR_MESSAGE";
    public static final String EMPTY_CLASSROOM_LIST_ERROR_MESSAGE = "EMPTY_CLASSROOM_LIST_ERROR_MESSAGE";
    public static final String EMPTY_SUBJECT_LIST_ERROR_MESSAGE = "EMPTY_SUBJECT_LIST_ERROR_MESSAGE";

    public static final String INSERT_NEW_INFORMATION_MESSAGE = "INSERT_NEW_INFORMATION_MESSAGE";
    public static final String INSERT_NEW_SUCCESS_MESSAGE = "INSERT_NEW_SUCCESS_MESSAGE";
    public static final String DELETE_RECORD_SUCCESS_MESSAGE = "DELETE_RECORD_SUCCESS_MESSAGE";
    public static final String DELETE_ALL_RECORDS_QUESTION_MESSAGE = "DELETE_ALL_RECORDS_QUESTION_MESSAGE";
    public static final String DELETE_ALL_RECORDS_CANCEL_MESSAGE = "DELETE_ALL_RECORDS_CANCEL_MESSAGE";
    public static final String DELETE_ALL_RECORDS_SUCCESS_MESSAGE = "DELETE_ALL_RECORDS_SUCCESS_MESSAGE";

    public static final String CODE = "CODE";
    public static final String NAME = "NAME";
    public static final String AGE = "AGE";
    public static final String FEDERAL_IDENTIFICATION = "FEDERAL_IDENTIFICATION";

    public static final String BACK_TO_MAIN_MENU_MESSAGE = "BACK_TO_MAIN_MENU_MESSAGE";
    public static final String EXIT_SYSTEM_MESSAGE = "EXIT_SYSTEM_MESSAGE";

    public static String translate(String string) {
        return ResourceBundle.getBundle(MESSAGE_BUNDLE, Locale.getDefault()).getString(string);
    }

    public static void clearScreen() {
        for (int i = 0; i < 50; ++i) {
            System.out.println();
        }
    }

    public static void printHeaderManager() {
        UTILS.clearScreen();
        System.out.println("*********************************");
        System.out.println("GERENCIADOR DE SISTEMA ACADÊMICO");
        System.out.println("*********************************");
        System.out.println(" ");
    }

    public static String headerManagerString() {
        UTILS.clearScreen();
        return String.format("%nGERENCIADOR DE SISTEMA ACADÊMICO%n");
    }

    public static String mainMenuOptionsString() {
        return String.format("Você está em: [MENU PRINCIPAL]%n%n(1) Gerenciar Estudantes%n(2) Gerenciar Professores%n(3) Gerenciar Disciplinas%n(4) Gerenciar Turmas%n(5) Gerenciar Matrículas%n(9) Limpar todos os registros%n%n(0) Sair");
    }

    public static String secundaryMenuOptionsString(String mainMenuOptionsEnumName) {
        return String.format("Você está em: [MENU PRINCIPAL] > [MENU DE %s]%n%n(1) Incluir%n(2) Listar%n(3) Atualizar%n(4) Remover%n(5) Limpar todos os registros%n%n(0) Voltar ao menu principal%n", UTILS.translate(mainMenuOptionsEnumName));
    }

    public static void showMessageDialogWithoutTranslate(String message) {
        JOptionPane.showMessageDialog(null, headerManagerString() + message);
    }

    public static void showMessageDialog(String message) {
        JOptionPane.showMessageDialog(null, headerManagerString() + UTILS.translate(message));
    }

    public static String showInputStringDialog(String string) {
        return JOptionPane.showInputDialog(null, headerManagerString() + UTILS.translate(string) + ": ");
    }

    public static int showInputIntegerDialog(String string) {
        String entry = JOptionPane.showInputDialog(null, headerManagerString() + UTILS.translate(string) + ": ");

        while (!validInteger(entry)) {
            entry = JOptionPane.showInputDialog(null, "Valor incorreto!\n\nDigite um número inteiro.");
        }

        return Integer.parseInt(entry);
    }

    public static int showInputIntegerDialog(String header, String options) {
        String entry = JOptionPane.showInputDialog(null, header + "\n\n" + options + "\n\nDigite a opção desejada: ");

        while (!validInteger(entry)) {
            entry = JOptionPane.showInputDialog(null, "Valor incorreto!\n\nDigite um número inteiro.");
        }

        return Integer.parseInt(entry);
    }

    public static boolean validInteger(String string) {
        boolean result;

        try {
            Integer.parseInt(string);
            result = true;
        } catch (NumberFormatException e) {
            result = false;
        }

        return result;
    }

    public static int scannerIntValue() {
        int number = 0;
        boolean isValidNumber = false;
        while (!isValidNumber) {
            try {
                number = new Scanner(System.in).nextInt();
                isValidNumber = true;
            } catch (Exception e) {
                System.out.println(UTILS.translate(UTILS.INCORRECT_NUMBER_ERROR_MESSAGE));
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
                System.out.println(UTILS.translate(UTILS.INCORRECT_NUMBER_ERROR_MESSAGE));
                new Scanner(System.in).next();
            }
        }

        return number;
    }
}
