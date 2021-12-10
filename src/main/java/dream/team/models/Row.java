package dream.team.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
@NoArgsConstructor
@Data
public class Row {
String rowIndex;
    ArrayList<Cell> ceils;
    public Row(String rowIndex, ArrayList<Cell> ceils) {
        this.rowIndex = rowIndex;
        this.ceils = ceils;
    }


}
