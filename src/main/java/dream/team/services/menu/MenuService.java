package dream.team.services.menu;

import dream.team.utils.Input;
import dream.team.utils.Print;

import static dream.team.utils.Print.println;
import static dream.team.utils.Print.print;

import static dream.team.utils.Color.RED;

public class MenuService {
    public static String Menu(String text, int end) {
        designedMenu(text);
        String choice = Input.getMenu(text);
        if (isValid(end, choice)) return Menu(text, end);
        return choice;
    }

    private static boolean isValid(int end, String choice) {
        try {
            if (menuRange(end, Integer.parseInt(choice))) {
                Print.println(RED, "Wrong menu!");
                return true;
            }
            return false;
        } catch (Exception ignored) {
            Print.println(RED, "Please enter number!");
            return true;
        }
    }

    private static boolean menuRange(int menu_end, int i) {
        return i < 1 || i > menu_end;
    }

    private static void designedMenu(String menu) {
        String[] menus = menu.split("/");
        int l = menus.length;
        int h = (l + 1) / 2;
        for (int i = 0; i < h; i++) {
            print("◾ " + menus[i] + "-" + (i + 1) + "\t  ");
            if (i + h < l) {
                println("◾ " + menus[i + h] + "-" + (i + h + 1));
                if (i == (l - 1) / 2)
                    print("-> ");
            } else {
                print("\n-> ");
            }
        }
    }
}
