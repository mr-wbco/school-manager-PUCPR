import com.enums.ActionsMenuOptionsEnum;
import com.enums.MainMenuOptionsEnum;
import com.objects.DataVO;
import com.system.SystemBO;
import com.system.SystemServiceBean;
import com.utils.UTILS;

public class Main {

    public static SystemServiceBean systemServiceBean = new SystemServiceBean();

    public static void main(String[] args) {

        DataVO dataVO = new SystemBO().readData(SystemBO.DATA_JSON_FILE_NAME);

        while (true) {
            MainMenuOptionsEnum mainMenuOptionsEnum = systemServiceBean.showMainMenu();

            if (mainMenuOptionsEnum == null) {
                UTILS.printConsoleMessage(UTILS.INCORRECT_NUMBER_ERROR_MESSAGE);
                UTILS.pressEnterToContinue();
                continue;
            }

            if (MainMenuOptionsEnum.EXIT.equals(mainMenuOptionsEnum)) {
                UTILS.printConsoleMessage(UTILS.EXIT_SYSTEM_MESSAGE);
                break;
            }

            while (true) {
                ActionsMenuOptionsEnum actionsMenuOptionsEnum = systemServiceBean.showSecundaryMenu(mainMenuOptionsEnum);

                if (actionsMenuOptionsEnum == null) {
                    UTILS.printConsoleMessage(UTILS.INCORRECT_NUMBER_ERROR_MESSAGE);
                    UTILS.pressEnterToContinue();
                    continue;
                }

                if (ActionsMenuOptionsEnum.RETURN.equals(actionsMenuOptionsEnum)) {
                    break;
                }

                switch (actionsMenuOptionsEnum) {
                    case INSERT -> systemServiceBean.insertMenu(mainMenuOptionsEnum, dataVO);
                    case VIEW -> systemServiceBean.viewMenu(mainMenuOptionsEnum, dataVO);
                    case UPDATE -> systemServiceBean.updateMenu(mainMenuOptionsEnum, dataVO);
                    case DELETE -> systemServiceBean.deleteMenu(mainMenuOptionsEnum, dataVO);
                    case CLEAR_ALL -> systemServiceBean.clearListMenu(mainMenuOptionsEnum, dataVO);
                }
            }
        }
    }
}