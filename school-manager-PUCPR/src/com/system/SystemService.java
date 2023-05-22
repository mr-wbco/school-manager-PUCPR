package com.system;

import com.enums.ActionsMenuOptionsEnum;
import com.enums.MainMenuOptionsEnum;
import com.objects.DataVO;

public interface SystemService {

    MainMenuOptionsEnum showMainMenu();

    ActionsMenuOptionsEnum showSecundaryMenu(MainMenuOptionsEnum mainMenuOptionsEnum);

    void insertMenu(MainMenuOptionsEnum mainMenuOptionsEnum, DataVO dataVOp);

    void viewMenu(MainMenuOptionsEnum mainMenuOptionsEnum, DataVO dataVO);

    void updateMenu(MainMenuOptionsEnum mainMenuOptionsEnum, DataVO dataVO);

    void deleteMenu(MainMenuOptionsEnum mainMenuOptionsEnum, DataVO dataVO);

    void clearListMenu(MainMenuOptionsEnum mainMenuOptionsEnum, DataVO dataVO);
}
