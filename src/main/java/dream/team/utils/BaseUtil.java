package dream.team.utils;

import java.util.Objects;
import java.util.UUID;

import static dream.team.utils.Color.RED;

public class BaseUtil {
    /*public static int menu(String text, int menu_begin, int menu_end) {
        designedMenu(text);
        String choice = Input.getMenu(text);
        if (choice.length() > 2) {
            Print.println(RED, "Wrong menu !");
            return menu(text, menu_begin, menu_end);
        }
        for (int i = 0; i < choice.length(); i++) {
            if (choice.charAt(i) > '0' && choice.charAt(i) < '9') {
                int choiceInt = Integer.parseInt(choice);
                if (menuRange(menu_end, choiceInt, menu_begin)) {
                    Print.println(RED, "Wrong menu !");
                    return menu(text, menu_begin, menu_end);
                }
                return choiceInt;
            }
        }

        Print.println(RED, "Wrong menu !");
        return menu(text, menu_begin, menu_end);
    }*/

    public static String generateUniqueId() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    public static String processMessage(String format, Object... args) {
        return String.format(format, args);
    }

    public static String getLetter(int i) {
        return Character.toString('A' + i);
    }

    public static String getChar(int i) {
        return Character.toString('A' +i);
    }
}
