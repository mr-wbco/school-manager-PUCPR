import com.enums.ActionsMenuOptionsEnum;
import com.enums.MainMenuOptionsEnum;
import com.objects.DataVO;
import com.system.SystemServiceBean;
import com.utils.UTILS;

import java.util.HashMap;

public class Main {

    public static SystemServiceBean systemServiceBean = new SystemServiceBean();

    public static void main(String[] args) {

        DataVO dataVO = new DataVO();

        while (true) {
            MainMenuOptionsEnum mainMenuOptionsEnum = systemServiceBean.showMainMenu();

            if (mainMenuOptionsEnum == null) {
                System.out.println(UTILS.translate(UTILS.INCORRET_NUMBER_ERROR_MESSAGE));
                UTILS.pressEnterToContinue();
                continue;
            }

            if (MainMenuOptionsEnum.EXIT.equals(mainMenuOptionsEnum)) {
                System.out.println(UTILS.translate(UTILS.EXIT_SYSTEM_MESSAGE));
                break;
            }

            while (true) {
                ActionsMenuOptionsEnum actionsMenuOptionsEnum = systemServiceBean.showSecundaryMenu(mainMenuOptionsEnum);

                if (actionsMenuOptionsEnum == null) {
                    System.out.println(UTILS.translate(UTILS.INCORRET_NUMBER_ERROR_MESSAGE));
                    UTILS.pressEnterToContinue();
                    continue;
                }

                if (ActionsMenuOptionsEnum.RETURN.equals(actionsMenuOptionsEnum)) {
                    break;
                }

                switch (actionsMenuOptionsEnum) {
                    case INSERT -> systemServiceBean.insertMenu(mainMenuOptionsEnum, actionsMenuOptionsEnum, dataVO);
                    case VIEW -> systemServiceBean.viewMenu(mainMenuOptionsEnum, actionsMenuOptionsEnum, dataVO);
                    case UPDATE -> systemServiceBean.updateMenu(mainMenuOptionsEnum, actionsMenuOptionsEnum, dataVO);
                    case DELETE -> systemServiceBean.deleteMenu(mainMenuOptionsEnum, actionsMenuOptionsEnum, dataVO);
                    case CLEAR_ALL -> systemServiceBean.clearListMenu(mainMenuOptionsEnum, actionsMenuOptionsEnum, dataVO);
                }
            }
        }
    }
}