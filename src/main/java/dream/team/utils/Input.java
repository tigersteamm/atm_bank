package dream.team.utils;

import dream.team.services.menu.MainMenuService;
import dream.team.services.menu.MenuService;

import java.util.Objects;
import java.util.Scanner;

import static dream.team.utils.Print.print;
import static dream.team.utils.Print.println;

public class Input {
    public static final Scanner SCANNER_NUM = new Scanner(System.in);
    public static final Scanner SCANNER_STR = new Scanner(System.in);

    public static Integer getNum() {
        return getNum("");
    }

    public static Integer getNum(String str) {
        print(str);
        return SCANNER_NUM.nextInt();
    }

    public static Integer getParkArgs(String str) {
        print(str);
        String a = SCANNER_STR.nextLine();
        for (int i = 0; i < a.length(); i++) {
            if (!Character.isDigit(a.charAt(i))) {
                println(Color.RED, "Don't type letter");
                MainMenuService.gotoAdminMenu();
            }
        }
        return Integer.parseInt(a);
    }

    public static String getStr() {
        return getStr("");
    }

    public static String getStr(String str) {
        print(str);
        return SCANNER_STR.nextLine();
    }

    public static String getMenu(String str) {
        return SCANNER_STR.nextLine();
    }
}
