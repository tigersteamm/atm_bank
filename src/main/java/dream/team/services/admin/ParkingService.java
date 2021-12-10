package dream.team.services.admin;

import dream.team.Dao.ParkingDao;
import dream.team.Dao.UserDao;
import dream.team.models.Parking;
import dream.team.models.User;
import dream.team.utils.Color;
import dream.team.utils.Print;

import java.util.Objects;

import static dream.team.utils.Input.*;

public class ParkingService {
    public static Parking findParking() {
        for (Parking park : ParkingDao.parks) {
            if (UserDao.sessionUser.getParkId().equals(park.getId())) {
                return park;
            }
        }
        return null;
    }

    public static void createPark() {
        String parkName = getStr("Park name: ");
        if (ExtraServices.isEmpty(parkName)) {
            int floorCount = getParkArgs("Number of floors: ");
            int rowCount = getParkArgs("Number of rows: ");
            int cellCount = getParkArgs("Number of cells: ");
            ExtraServices.buildParkByArg(parkName, floorCount, rowCount, cellCount);
        }
    }

    public static void deletePark() {
        listParking();
        String name = getStr("Park name: ");
        if (ExtraServices.checkPark(name)) {
            for (Parking park : ParkingDao.parks) {
                if (Objects.nonNull(park) && UserDao.sessionUser.getOrgId().equals(park.getId()) && park.getName().equals(name)) {
                    ParkingDao.parks.remove(park);
                    Print.println(Color.GREEN, name + " successfully deleted!");
                    return;
                }
            }
        }
        Print.println(Color.RED, name + " not found");
    }

    public static void blockPark() {
        listParking();
        String parkName = getStr("Enter name: ");
        if (ExtraServices.checkPark(parkName)) {
            for (Parking park : ParkingDao.parks) {
                if (Objects.nonNull(park) && UserDao.sessionUser.getOrgId().equals(park.getId()) && park.getName().equals(parkName)) {
                    for (User user : UserDao.users) {
                        if (user.getParkId().equals(park.getId())) {
                            user.setBlocked(true);
                        }
                    }
                    park.setBlocked(true);
                    Print.println(Color.GREEN, parkName + " blocked");
                    return;
                }
            }
        }
        Print.println(Color.RED, parkName + " not found");
    }

    public static void unblockPark() {
        listParking();
        String parkName = getStr("Enter name: ");
        if (ExtraServices.checkPark(parkName)) {
            for (Parking park : ParkingDao.parks) {
                if (Objects.nonNull(park) && UserDao.sessionUser.getOrgId().equals(park.getId()) && park.getName().equals(parkName)) {
                    for (User user : UserDao.users) {
                        if (user.getParkId().equals(park.getId())) {
                            user.setBlocked(false);
                        }
                    }
                    park.setBlocked(false);
                    Print.println(Color.GREEN, parkName + " blocked");
                    return;
                }
            }
        }
        Print.println(Color.RED, parkName + " not found");
    }

    public static void listParking() {
        int count = 1;
        for (Parking park : ParkingDao.parks) {
            if (Objects.nonNull(park) && UserDao.sessionUser.getOrgId().equals(park.getOrgId())) {
                if (park.isBlocked())
                    Print.println(Color.RED, count++ + ". " + park.getName() + " ❗(blocked)");
                else
                    Print.println(Color.PURPLE, count++ + ". " + park.getName());
            }
        }
        if (count < 2) {
            Print.println(Color.RED, "There are no any parking");
            AdminService.AdminMenu();
            return;
        }
    }

    public static void blockedParkingList() {
        int count = 1;
        for (Parking park : ParkingDao.parks) {
            if (Objects.nonNull(park) && UserDao.sessionUser.getOrgId().equals(park.getOrgId())) {
                if (!park.isBlocked())
                    Print.println(Color.RED, count++ + ". " + park.getName() + " ❗(blocked)");
            }
        }
        if (count < 2) {
            Print.println(Color.RED, "There are no any parking");
            AdminService.AdminMenu();
            return;
        }

    }
}
