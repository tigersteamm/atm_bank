package dream.team.services.admin;

import dream.team.services.menu.MenuService;
import dream.team.utils.Color;
import dream.team.utils.Print;

public class AdminService {
    public static void AdminMenu() {
        String choice = MenuService.Menu("Create park/Delete park/Block parking/Unblock park/List parking/List blocked/Add employee/Delete employee/Block employee/Unblock employee/List employee/Blocked employees/Logout", 13);
        switch (choice) {
            case "1" -> ParkingService.createPark();
            case "2" -> ParkingService.deletePark();
            case "3" -> ParkingService.blockPark();
            case "4" -> ParkingService.unblockPark();
            case "5" -> ParkingService.listParking();
            case "6" -> ParkingService.blockedParkingList();
            case "7" -> addEmployee();
            case "8" -> deleteEmployee();
            case "9" -> blockEmployee();
            case "10" -> unblockEmployee();
            case "11" -> listEmployees();
            case "12" -> blockedEmployees();
            default -> {
                Print.println(Color.GREEN, "Logged out...");
                return;
            }
        }
        AdminMenu();
    }

    private static void blockedEmployees() {

    }

    private static void listEmployees() {

    }

    private static void unblockEmployee() {

    }

    private static void blockEmployee() {

    }

    private static void deleteEmployee() {

    }

    private static void addEmployee() {

    }
}
