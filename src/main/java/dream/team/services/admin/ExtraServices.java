package dream.team.services.admin;

import dream.team.Dao.ParkingDao;
import dream.team.Dao.UserDao;
import dream.team.models.Cell;
import dream.team.models.Floor;
import dream.team.models.Parking;
import dream.team.models.Row;
import dream.team.utils.BaseUtil;
import dream.team.utils.Color;
import dream.team.utils.Print;

import java.util.ArrayList;
import java.util.Objects;

public class ExtraServices {

    public static boolean isEmpty(String parkName) {
        for (Parking park : ParkingDao.parks) {
            if (park.getName().equalsIgnoreCase(parkName))
                return false;
        }
        return true;
    }

    public static void buildParkByArg(String parkName, int floorCount, int rowCount, int cellCount) {
        ArrayList<Floor> floors = new ArrayList<>();
        for (int i = 0; i < floorCount; i++) {
            Floor floor = getFloor(rowCount, cellCount);
            floor.setFloorIndex(i + 1);
            floors.add(floor);
        }
        Parking parking = new Parking(parkName, UserDao.sessionUser.getOrgId(), floorCount, rowCount, cellCount, floors);
        ParkingDao.parks.add(parking);
        Print.println(Color.GREEN, "Added!");
    }

    private static Floor getFloor(int rowCount, int cellCount) {
        ArrayList<Row> rows = new ArrayList<>(0);
        for (int i = 0; i < rowCount; i++) {
            rows.add(getRow(BaseUtil.getChar(i), cellCount));
        }
        return new Floor(rows);
    }

    private static Row getRow(String index, int cellCount) {
        ArrayList<Cell> cells = new ArrayList<>();
        for (int i = 0; i < cellCount; i++) {
            cells.add(getCells());
        }
        return new Row(index, cells);
    }

    private static Cell getCells() {
        return new Cell();
    }

    public static boolean checkPark(String parkName) {
        for (Parking park : ParkingDao.parks) {
            if (Objects.nonNull(park) && UserDao.sessionUser.getOrgId().equals(park.getId()) && park.getName().equals(parkName)) {
                return true;
            }
        }
        return false;
    }
}
