package dream.team.models;

import dream.team.utils.BaseUtil;
import lombok.AllArgsConstructor;
import lombok.Data;

import static dream.team.utils.BaseUtil.*;

@Data

public class Organization {
    private String id;
    private String name;
    private boolean blocked;

    public Organization(String name) {
        this.id = generateUniqueId();
        this.name = name;
    }
}
