package dream.team.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
@NoArgsConstructor
@Data
public class Floor {
    private int floorIndex;
    ArrayList<Row> rows;

    public Floor(ArrayList<Row> rows) {
        this.rows = rows;
    }
}
