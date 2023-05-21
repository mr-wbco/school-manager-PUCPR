import com.enums.ActionsMenuOptionsEnum;
import com.enums.MainMenuOptionsEnum;
import com.system.SystemServiceBean;

public class Main {

    public static SystemServiceBean systemServiceBean = new SystemServiceBean();

    public static void main(String[] args) {

        while (true) {
            MainMenuOptionsEnum mainMenuOptionsEnum = systemServiceBean.showMainMenu();

            if (mainMenuOptionsEnum == null) {
                System.out.println("Número inválido. Tente novamente.");
                continue;
            }

            if (MainMenuOptionsEnum.EXIT.equals(mainMenuOptionsEnum)) {
                System.out.println("Saindo do sistema. Teste de commit.");
                break;
            }

            while (true) {
                ActionsMenuOptionsEnum actionsMenuOptionsEnum = systemServiceBean.showSecundaryMenu(mainMenuOptionsEnum);

                if (actionsMenuOptionsEnum == null) {
                    System.out.println("Número inválido. Tente novamente.");
                    continue;
                }

                if (ActionsMenuOptionsEnum.RETURN.equals(actionsMenuOptionsEnum)) {
                    break;
                }

                switch (actionsMenuOptionsEnum) {
                    case INSERT -> systemServiceBean.insertNew(mainMenuOptionsEnum, actionsMenuOptionsEnum);
                    case VIEW -> systemServiceBean.viewList(mainMenuOptionsEnum, actionsMenuOptionsEnum);
                    case UPDATE -> systemServiceBean.updateList(mainMenuOptionsEnum, actionsMenuOptionsEnum);
                    case DELETE -> systemServiceBean.deleteItem(mainMenuOptionsEnum, actionsMenuOptionsEnum);
                    case CLEAR_ALL -> systemServiceBean.clearList(mainMenuOptionsEnum, actionsMenuOptionsEnum);
                }
            }
        }
    }
}