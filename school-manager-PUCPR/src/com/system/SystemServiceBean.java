package com.system;

import com.enums.ActionsMenuOptionsEnum;
import com.enums.MainMenuOptionsEnum;
import com.system.service.SystemService;
import com.utils.UTILS;

import java.util.Scanner;

public class SystemServiceBean implements SystemService {

    @Override
    public MainMenuOptionsEnum showMainMenu() {
        System.out.println("*********************************");
        System.out.println("GERENCIADOR DE SISTEMA ACADÊMICO");
        System.out.println("*********************************");
        System.out.println(" ");
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
                System.out.println("Você digitou uma letra em vez de um número inteiro. Digite um número válido.");
            }
        }
    }

    @Override
    public ActionsMenuOptionsEnum showSecundaryMenu(MainMenuOptionsEnum mainMenuOptionsEnum) {

        System.out.println("*********************************");
        System.out.println("GERENCIADOR DE SISTEMA ACADÊMICO");
        System.out.println("*********************************");
        System.out.println(" ");
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
                System.out.println("Você digitou uma letra em vez de um número inteiro. Digite um número válido.");
            }
        }
    }

    @Override
    public void insertNew(MainMenuOptionsEnum mainMenuOptionsEnum, ActionsMenuOptionsEnum actionsMenuOptionsEnum) {
        System.out.println("ENTROU NO INSERT MENU");
    }

    @Override
    public void viewList(MainMenuOptionsEnum mainMenuOptionsEnum, ActionsMenuOptionsEnum actionsMenuOptionsEnum) {
        System.out.println("ENTROU NO VIEW MENU");
    }

    @Override
    public void updateList(MainMenuOptionsEnum mainMenuOptionsEnum, ActionsMenuOptionsEnum actionsMenuOptionsEnum) {
        System.out.println("ENTROU NO UPDATE MENU");
    }

    @Override
    public void deleteItem(MainMenuOptionsEnum mainMenuOptionsEnum, ActionsMenuOptionsEnum actionsMenuOptionsEnum) {
        System.out.println("ENTROU NO DELETE MENU");
    }

    @Override
    public void clearList(MainMenuOptionsEnum mainMenuOptionsEnum, ActionsMenuOptionsEnum actionsMenuOptionsEnum) {
        System.out.println("ENTROU NO CLEAR LIST MENU");
    }
}
