package dream.team.services.auth;

import dream.team.Dao.OrgDao;
import dream.team.Dao.ParkingDao;
import dream.team.Dao.UserDao;
import dream.team.models.Organization;
import dream.team.models.Parking;
import dream.team.models.User;
import dream.team.services.admin.ParkingService;
import dream.team.services.menu.MainMenuService;
import dream.team.utils.Color;

import java.util.Objects;

import static dream.team.utils.Print.println;

public class UserService {
    public static long hash(String str) {
        String myAlphabet = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!@#$%^&*()_+";
        long p = 1l;
        long m = 1000000007l;
        long hash = 0l;
        for (int i = 0; i < str.length(); i++) {
            int x = 0;
            for (int j = 0; j < myAlphabet.length(); j++) {
                if (myAlphabet.charAt(j) == str.charAt(i)) {
                    x = j + 1;
                    break;
                }
            }
            hash = (hash + x * p) % m;
            p = (p * 79) % m;
        }
        return hash;
    }

    public static User findByUsername(String username) {
        for (User user : UserDao.users) {
            if (user.getUsername().equals(username)) return user;
        }
        return null;
    }

    public static boolean validToLogin(User user, String username, String password) {
        if (Objects.isNull(user)) {
            println(Color.RED, username + " not found");
            return false;
        }
        if (!user.getPassword().equals(password)) {
            println(Color.RED, "Username or password incorrect!");
            return false;
        }
        if (user.isBlocked()) {
            println(Color.RED, username + " is blocked!");
            return false;
        }
        return true;
    }

    public static void gotoMenuByUserStatus(User user) {
        for (Organization organization : OrgDao.organizations) {
            if (user.getOrgId().equals(organization.getId())) {
                if (user.getUserStatus() == 0) {
                    MainMenuService.gotoSuperMenu();
                    return;
                } else if (user.getUserStatus() == 1) {
                    MainMenuService.gotoAdminMenu();
                    return;
                } else {

                    Parking parking = ParkingService.findParking();
                    if (Objects.isNull(parking)) println(Color.RED, "Parking not found!");
                    ParkingDao.sessionPark = parking;
                    MainMenuService.gotoEmployeeMenu();
                }
            }
        }
    }
}
