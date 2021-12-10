package dream.team.services.employee;

import dream.team.services.menu.MenuService;

public class EmployeeService {
    public static void employeeMenu() {
        String choice = MenuService.Menu("Show all/In/Out/Logout", 4);
        switch (choice) {
            case "1" -> showAllPlaces();
            case "2" -> in();
            case "3" -> out();
            default -> {
                System.out.println("Bye");
                return;
            }
        }
        employeeMenu();
    }

    private static void out() {
    }

    private static void in() {
    }

    private static void showAllPlaces() {
    }
}
