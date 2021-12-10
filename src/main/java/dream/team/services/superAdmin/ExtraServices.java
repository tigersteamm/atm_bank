package dream.team.services.superAdmin;

import dream.team.Dao.UserDao;
import dream.team.models.Organization;
import dream.team.models.User;
import dream.team.utils.Color;
import dream.team.utils.Print;

import java.util.Objects;

import static dream.team.Dao.OrgDao.organizations;
import static dream.team.utils.Input.getStr;
import static dream.team.utils.Print.println;

public class ExtraServices {

    protected static void checkOrgNameAndAddUserToOrg(String username, String fullName, String password) {
        listOrgWithoutAdmins();
        String orgName = getStr("Choose organization: ");
        Organization tempOrg = getOrgByName(orgName);
        if (Objects.nonNull(tempOrg) && checkOrgName(orgName)) {
            if (tempOrg.getName().equalsIgnoreCase("dt")) {
                println(Color.RED, "Admin cannot be added to this organization!");
                return;
            }
            User user = new User(username, password, fullName, 1, tempOrg.getId(), null);
            UserDao.users.add(user);
            Print.println(Color.GREEN,"Added!");
        } else {
            println(Color.RED, "Wrong name!");
        }
    }

    protected static boolean checkOrgName(String orgName) {
        for (Organization organization : organizations) {
            if (organization.getName().equalsIgnoreCase(orgName))
                return true;
        }
        return false;
    }

    protected static void printUserByOrg(String orgId) {
        for (User user : UserDao.users) {
            if (user.getOrgId().equals(orgId) && !user.isBlocked())
                println(Color.PURPLE, " ⊢ \uD83D\uDC77\uD83C\uDFFB " + user.getUsername() + " | Name: " + user.getName());
        }
    }

    protected static boolean orgAlreadyExist(String name) {
        for (Organization organization : organizations) {
            if (organization.getName().equals(name))
                return true;
        }
        return false;
    }

    protected static boolean userAlreadyExist(String username) {
        for (User user : UserDao.users) {
            if (user.getUsername().equals(username))
                return true;
        }
        return false;
    }

    public static void listOrgWithoutAdmins() {
        for (Organization organization : organizations) {
            int count = 1;
            if (!organization.isBlocked()) {
                println(Color.PURPLE, count++ + ". " + organization.getName().toUpperCase());
            } else {
                println(Color.RED, count++ + ". " + organization.getName().toUpperCase() + " ❗(blocked)");
            }
        }
    }

    protected static Organization getOrgByName(String orgName) {
        for (Organization organization : organizations) {
            if (organization.getName().equalsIgnoreCase(orgName))
                return organization;
        }
        return null;
    }

    public static void deleteOrg(String name) {
        for (Organization organization : organizations) {
            if (organization.getName().equals("DT")) {
                println(Color.RED, "It is an immortal organization");
                return;
            }
        }
        organizations.removeIf(deletable -> deletable.getName().equals(name));
        Print.println(Color.GREEN,"Deleted!");
    }

    public static void listBlockedOrg() {
        int count = 1;
        for (Organization organization : organizations) {
            if (organization.isBlocked()) {
                println(Color.RED, count++ + ". " + organization.getName().toUpperCase());
            }
        }
    }

    public static void blockOrg(String name) {
        for (Organization organization : organizations) {
            if (organization.getName().equalsIgnoreCase(name) && !organization.isBlocked() && !organization.getName().equalsIgnoreCase("DT")) {
                for (User user : UserDao.users) {
                    if (user.getOrgId().equals(organization.getId()))
                        user.setBlocked(true);
                }
                organization.setBlocked(true);
                Print.println(Color.GREEN,"Blocked!");
                return;
            }
        }
        println(Color.RED, "This organization cannot be blocked!");
    }

    public static void unBlockOrg(String name) {
        for (Organization organization : organizations) {
            if (organization.getName().equalsIgnoreCase(name) && organization.isBlocked()) {
                for (User user : UserDao.users) {
                    if (user.getOrgId().equals(organization.getId()))
                        user.setBlocked(false);
                }
                organization.setBlocked(false);
                Print.println(Color.GREEN,"Unblocked!");
            }
        }
    }
}

