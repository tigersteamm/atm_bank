package dream.team.services.auth;

import dream.team.Dao.UserDao;
import dream.team.models.User;

import static dream.team.utils.Input.*;

public class AuthService {

    public static void login() {
        String username = getStr("Username: ");
        String password = getStr("Password: ");
        User user = UserService.findByUsername(username);
        if (UserService.validToLogin(user, username, password)) {
            UserDao.sessionUser = user;
            UserService.gotoMenuByUserStatus(user);
        }
    }
}
