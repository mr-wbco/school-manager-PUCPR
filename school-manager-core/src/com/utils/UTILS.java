package com.utils;

import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Scanner;

public class UTILS {

    public static final String MESSAGE_BUNDLE = "resources/messages_pt_BR";
    public static final String BACK_TO_MAIN_MENU = "BACK_TO_MAIN_MENU";

    public static final String INSERT_NEW = "INSERT_NEW";
    public static final String LIST_ALL = "LIST_ALL";
    public static final String UPDATE_INFORMATION = "UPDATE_INFORMATION";
    public static final String DELETE_RECORD = "DELETE_RECORD";
    public static final String DELETE_ALL_RECORDS = "DELETE_ALL_RECORDS";

    public static final String INCORRECT_NUMBER_ERROR_MESSAGE = "INCORRECT_NUMBER_ERROR_MESSAGE";
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

    public static void printMainMenuOptions() {
        System.out.println("Você está em: [MENU PRINCIPAL]");
        System.out.println("(1) Gerenciar Estudantes");
        System.out.println("(2) Gerenciar Professores");
        System.out.println("(3) Gerenciar Disciplinas");
        System.out.println("(4) Gerenciar Turmas");
        System.out.println("(5) Gerenciar Matrículas");
        System.out.println("(0) Sair");
        System.out.println(" ");
        System.out.println("Digite a opcão desejada: ");
    }

    public static void printSecundaryMenuOptions(String mainMenuOptionsEnumName) {
        System.out.printf(("Você está em: [MENU PRINCIPAL] > [MENU DE %s]") + "%n", UTILS.translate(mainMenuOptionsEnumName));
        System.out.println("(1) Incluir");
        System.out.println("(2) Listar");
        System.out.println("(3) Atualizar");
        System.out.println("(4) Remover");
        System.out.println("(5) Limpar todos os registros");
        System.out.println("(0) Voltar ao menu principal");
        System.out.println(" ");
        System.out.println("Digite a opcão desejada: ");
    }

    public static void printCurrentSecundaryMenuPosition(String mainMenuOptionsEnumName, String secondaryMenuName) {
        System.out.printf(("Você está em: [MENU PRINCIPAL] > [MENU DE %s] > [%s]") + "%n", UTILS.translate(mainMenuOptionsEnumName), UTILS.translate(secondaryMenuName));
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
