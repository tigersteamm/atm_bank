package dream.team.services.menu;

import dream.team.services.auth.AuthService;
import dream.team.services.admin.AdminService;
import dream.team.services.employee.EmployeeService;
import dream.team.services.superAdmin.*;
import dream.team.utils.*;

import static dream.team.services.menu.MenuService.Menu;

public class MainMenuService {
    public static void run() {
        OrganizationService.build();
        mainMenu();
    }

    private static void mainMenu() {
        String choice = Menu("login/exit", 2);
        switch (choice) {
            case "1" -> AuthService.login();
            default -> {
                Print.print("Bye ✋🏻");
                return;
            }
        }
        mainMenu();
    }

    public static void gotoSuperMenu() {
        Print.println(Color.PURPLE, "SUPER ADMIN");
        SuperAdminService.SuperMenu();
    }

    public static void gotoAdminMenu() {
        Print.println(Color.PURPLE, "ADMIN");
        AdminService.AdminMenu();
    }

    public static void gotoEmployeeMenu() {
        Print.println(Color.PURPLE, "EMPLOYEE");
        EmployeeService.employeeMenu();

    }
}
