package dream.team.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import static dream.team.utils.BaseUtil.generateUniqueId;

@NoArgsConstructor
@Data
public class Car {
    private String id;
    private String parkId;
    private String number;
    private long inTime;
    private long outTime;

    public Car(String number) {
        this.id = generateUniqueId();
        this.number = number;
    }
}
