package dream.team.models;

import lombok.Data;

import java.util.ArrayList;

import static dream.team.utils.BaseUtil.generateUniqueId;

@Data
public class Parking {
    private String id;
    private String name;
    private String orgId;
    private int floorCount;
    private int rowCount;
    private int cellCount;
    private boolean blocked;
    ArrayList<Floor> floors;

    public Parking(String name, String orgId, int floorCount, int rowCount, int cellCount, ArrayList<Floor> floors) {
        this.name = name;
        this.orgId = orgId;
        this.floorCount = floorCount;
        this.rowCount = rowCount;
        this.cellCount = cellCount;
        this.floors = floors;
    }
}
