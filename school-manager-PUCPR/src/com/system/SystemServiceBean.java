package com.system;

import com.enums.ActionsMenuOptionsEnum;
import com.enums.MainMenuOptionsEnum;
import com.objects.DataVO;
import com.student.StudentServiceBean;
import com.utils.UTILS;

import java.util.Scanner;

public class SystemServiceBean implements SystemService {

    public static StudentServiceBean studentServiceBean = new StudentServiceBean();

    @Override
    public MainMenuOptionsEnum showMainMenu() {
        UTILS.printHeaderManager();

        System.out.println("Você está em: [MENU PRINCIPAL]");
        System.out.println("(1) Gerenciar Estudantes");
        System.out.println("(2) Gerenciar Professores");
        System.out.println("(3) Gerenciar Disciplinas");
        System.out.println("(4) Gerenciar Turmas");
        System.out.println("(5) Gerenciar Matrículas");
        System.out.println("(0) Sair");
        System.out.println(" ");
        System.out.println("Digite a opcão desejada: ");

        while (true) {
            try {
                MainMenuOptionsEnum mainMenuOptionsEnum = null;
                int option = new Scanner(System.in).nextInt();

                switch (option) {
                    case 1 -> mainMenuOptionsEnum = MainMenuOptionsEnum.STUDENT;
                    case 2 -> mainMenuOptionsEnum = MainMenuOptionsEnum.PROFESSOR;
                    case 3 -> mainMenuOptionsEnum = MainMenuOptionsEnum.SUBJECT;
                    case 4 -> mainMenuOptionsEnum = MainMenuOptionsEnum.CLASS;
                    case 5 -> mainMenuOptionsEnum = MainMenuOptionsEnum.ENROLLING;
                    case 0 -> mainMenuOptionsEnum = MainMenuOptionsEnum.EXIT;
                }

                return mainMenuOptionsEnum;

            } catch (Exception e) {
                System.out.println(UTILS.translate(UTILS.INCORRET_NUMBER_ERROR_MESSAGE));
                UTILS.pressEnterToContinue();
            }
        }
    }

    @Override
    public ActionsMenuOptionsEnum showSecundaryMenu(MainMenuOptionsEnum mainMenuOptionsEnum) {
        UTILS.printHeaderManager();

        System.out.printf(("Você está em: [MENU PRINCIPAL] > [MENU DE %s]") + "%n", UTILS.translate(mainMenuOptionsEnum.toString()));
        System.out.println("(1) Incluir");
        System.out.println("(2) Listar");
        System.out.println("(3) Atualizar");
        System.out.println("(4) Remover");
        System.out.println("(5) Limpar todos os registros");
        System.out.println("(0) Voltar ao menu principal");
        System.out.println(" ");
        System.out.println("Digite a opcão desejada: ");

        while (true) {
            try {
                ActionsMenuOptionsEnum actionsMenuOptionsEnum = null;
                int option = new Scanner(System.in).nextInt();

                switch (option) {
                    case 1 -> actionsMenuOptionsEnum = ActionsMenuOptionsEnum.INSERT;
                    case 2 -> actionsMenuOptionsEnum = ActionsMenuOptionsEnum.VIEW;
                    case 3 -> actionsMenuOptionsEnum = ActionsMenuOptionsEnum.UPDATE;
                    case 4 -> actionsMenuOptionsEnum = ActionsMenuOptionsEnum.DELETE;
                    case 5 -> actionsMenuOptionsEnum = ActionsMenuOptionsEnum.CLEAR_ALL;
                    case 0 -> actionsMenuOptionsEnum = ActionsMenuOptionsEnum.RETURN;
                }

                return actionsMenuOptionsEnum;

            } catch (Exception e) {
                System.out.println(UTILS.translate(UTILS.INCORRET_NUMBER_ERROR_MESSAGE));
                UTILS.pressEnterToContinue();
            }
        }
    }

    @Override
    public void insertMenu(MainMenuOptionsEnum mainMenuOptionsEnum, ActionsMenuOptionsEnum actionsMenuOptionsEnum, DataVO dataVO) {
        UTILS.printHeaderManager();

        System.out.printf(("Você está em: [MENU PRINCIPAL] > [MENU DE %s] > [INCLUIR NOVO]") + "%n", UTILS.translate(mainMenuOptionsEnum.toString()));

        switch (mainMenuOptionsEnum) {
            case STUDENT -> studentServiceBean.insertNewStudent(mainMenuOptionsEnum, actionsMenuOptionsEnum, dataVO);
            case PROFESSOR -> System.out.println("INSERINDO NOVO PROFESSOR");
            case SUBJECT -> System.out.println("INSERINDO NOVO DISCIPLINA");
            case CLASS -> System.out.println("INSERINDO NOVA TURMA");
            case ENROLLING -> System.out.println("INSERINDO NOVA MATRÍCULA");
            case EXIT -> System.out.println("RETORNANDO AO MENU ANTERIOR");
        }

        UTILS.pressEnterToContinue();
    }

    @Override
    public void viewMenu(MainMenuOptionsEnum mainMenuOptionsEnum, ActionsMenuOptionsEnum actionsMenuOptionsEnum, DataVO dataVO) {
        UTILS.printHeaderManager();

        System.out.printf(("Você está em: [MENU PRINCIPAL] > [MENU DE %s] > [LISTAR TODOS]") + "%n", UTILS.translate(mainMenuOptionsEnum.toString()));

        switch (mainMenuOptionsEnum) {
            case STUDENT -> studentServiceBean.viewStudentList(mainMenuOptionsEnum, actionsMenuOptionsEnum, dataVO);
            case PROFESSOR -> System.out.println("INSERINDO NOVO PROFESSOR");
            case SUBJECT -> System.out.println("INSERINDO NOVO DISCIPLINA");
            case CLASS -> System.out.println("INSERINDO NOVA TURMA");
            case ENROLLING -> System.out.println("INSERINDO NOVA MATRÍCULA");
            case EXIT -> System.out.println("RETORNANDO AO MENU ANTERIOR");
        }

        UTILS.pressEnterToContinue();
    }

    @Override
    public void updateMenu(MainMenuOptionsEnum mainMenuOptionsEnum, ActionsMenuOptionsEnum actionsMenuOptionsEnum, DataVO dataVO) {
        UTILS.printHeaderManager();

        System.out.printf(("Você está em: [MENU PRINCIPAL] > [MENU DE %s] > [ATUALIZAR INFORMACÕES]") + "%n", UTILS.translate(mainMenuOptionsEnum.toString()));

        switch (mainMenuOptionsEnum) {
            case STUDENT -> studentServiceBean.updateStudent(mainMenuOptionsEnum, actionsMenuOptionsEnum, dataVO);
            case PROFESSOR -> System.out.println("INSERINDO NOVO PROFESSOR");
            case SUBJECT -> System.out.println("INSERINDO NOVO DISCIPLINA");
            case CLASS -> System.out.println("INSERINDO NOVA TURMA");
            case ENROLLING -> System.out.println("INSERINDO NOVA MATRÍCULA");
            case EXIT -> System.out.println("RETORNANDO AO MENU ANTERIOR");
        }

        UTILS.pressEnterToContinue();
    }

    @Override
    public void deleteMenu(MainMenuOptionsEnum mainMenuOptionsEnum, ActionsMenuOptionsEnum actionsMenuOptionsEnum, DataVO dataVO) {
        System.out.println("ENTROU NO DELETE MENU");
        UTILS.pressEnterToContinue();
    }

    @Override
    public void clearListMenu(MainMenuOptionsEnum mainMenuOptionsEnum, ActionsMenuOptionsEnum actionsMenuOptionsEnum, DataVO dataVO) {
        System.out.println("ENTROU NO CLEAR LIST MENU");
        UTILS.pressEnterToContinue();
    }
}
