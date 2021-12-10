package dream.team.services.superAdmin;

import dream.team.utils.Color;
import dream.team.utils.Print;

import static dream.team.services.menu.MenuService.Menu;

public class SuperAdminService {
    public static void SuperMenu() {
        String choice = Menu("Create org/Delete org/Lock org/Unlock org/List org/Add admin/Add superAdmin/Logout", 8);
        switch (choice) {
            case "1" -> OrganizationService.createOrg();
            case "2" -> OrganizationService.deleteOrg();
            case "3" -> OrganizationService.blockOrg();
            case "4" -> OrganizationService.unblockOrg();
            case "5" -> OrganizationService.listOrg();
            case "6" -> OrganizationService.addAdmin();
            case "7" -> OrganizationService.addSuperAdmin();
            default -> {
                Print.println(Color.GREEN, "Logged out!");
                return;
            }
        }
        SuperMenu();
    }
}
