package com.system;

import com.enums.ActionsMenuOptionsEnum;
import com.enums.MainMenuOptionsEnum;
import com.objects.DataVO;

import java.util.HashMap;

public interface SystemService {

    MainMenuOptionsEnum showMainMenu();

    ActionsMenuOptionsEnum showSecundaryMenu(MainMenuOptionsEnum mainMenuOptionsEnum);

    void insertMenu(MainMenuOptionsEnum mainMenuOptionsEnum, ActionsMenuOptionsEnum actionsMenuOptionsEnum, DataVO dataVOp);

    void viewMenu(MainMenuOptionsEnum mainMenuOptionsEnum, ActionsMenuOptionsEnum actionsMenuOptionsEnum, DataVO dataVO);

    void updateMenu(MainMenuOptionsEnum mainMenuOptionsEnum, ActionsMenuOptionsEnum actionsMenuOptionsEnum, DataVO dataVO);

    void deleteMenu(MainMenuOptionsEnum mainMenuOptionsEnum, ActionsMenuOptionsEnum actionsMenuOptionsEnum, DataVO dataVO);

    void clearListMenu(MainMenuOptionsEnum mainMenuOptionsEnum, ActionsMenuOptionsEnum actionsMenuOptionsEnum, DataVO dataVO);
}
