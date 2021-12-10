package dream.team.services.superAdmin;

import dream.team.Dao.UserDao;
import dream.team.models.Organization;
import dream.team.models.User;
import dream.team.utils.Color;
import dream.team.utils.Print;

import static dream.team.utils.Input.*;
import static dream.team.utils.Print.*;
import static dream.team.Dao.OrgDao.organizations;

public class OrganizationService {

    public static void build() {
        Organization dreamTeam = new Organization("DT");
        organizations.add(dreamTeam);
        User superUser = new User("super", "123", "Alex", 0, dreamTeam.getId(), null);
        UserDao.users.add(superUser);
    }

    public static void createOrg() {
        String name = getStr("Enter name: ");
        if (ExtraServices.orgAlreadyExist(name)) {
            println(Color.RED, name + " already exist!");
            return;
        }
        Organization newOrg = new Organization(name);
        addAdminToOrg(newOrg);
        organizations.add(newOrg);
        Print.println(Color.GREEN,"Added!");
    }

    public static void deleteOrg() {
        listOrg();
        String name = getStr("Choose organization: ");
        if (ExtraServices.checkOrgName(name)) {
            ExtraServices.deleteOrg(name);
        }
    }

    public static void blockOrg() {
        ExtraServices.listOrgWithoutAdmins();
        String name = getStr("Choose organization: ");
        if (ExtraServices.checkOrgName(name)) {
            ExtraServices.blockOrg(name);
        }
    }

    public static void unblockOrg() {
        ExtraServices.listBlockedOrg();
        String name = getStr("Choose organization: ");
        if (ExtraServices.checkOrgName(name)) {
            ExtraServices.unBlockOrg(name);
        }
    }

    public static void listOrg() {
        int count = 1;
        for (Organization organization : organizations) {
            if (!organization.isBlocked()) {
                println(Color.PURPLE, count++ + ". " + organization.getName().toUpperCase());
                ExtraServices.printUserByOrg(organization.getId());
            } else {
                println(Color.RED, count++ + ". " + organization.getName().toUpperCase() + " ❗(blocked)");
            }
        }
    }

    public static void addAdmin() {
        if (organizations.size() < 2) {
            println(Color.RED, "Please add organization first!");
            return;
        }
        String username = getStr("Username: ");
        if (ExtraServices.userAlreadyExist(username)) {
            println(Color.RED, username + " already exist!");
            addAdmin();
        }
        String fullName = getStr("Full name: ");
        String password = getStr("Password: ");
        ExtraServices.checkOrgNameAndAddUserToOrg(username, fullName, password);
    }

    public static void addSuperAdmin() {
        String username = getStr("Username: ");
        if (ExtraServices.userAlreadyExist(username)) {
            println(Color.RED, username + " already exist!");
            return;
        }
        String fullName = getStr("Full name: ");
        String password = getStr("Password: ");
        User user = new User(username, password, fullName, 1, organizations.get(0).getId(), null);
        UserDao.users.add(user);
        Print.println(Color.GREEN,"Added!");
    }

    private static void addAdminToOrg(Organization organization) {
        String username = getStr("Username: ");
        if (ExtraServices.userAlreadyExist(username)) {
            println(Color.RED, username + " already exist!");
            addAdminToOrg(organization);
        }
        String fullName = getStr("Full name: ");
        String password = getStr("Password: ");
        User user = new User(username, password, fullName, 1, organization.getId(), null);
        UserDao.users.add(user);
    }
}
