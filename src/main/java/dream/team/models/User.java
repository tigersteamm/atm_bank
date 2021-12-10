package dream.team.models;

import lombok.Data;

import static dream.team.utils.BaseUtil.generateUniqueId;

@Data
public class User {
    private String id;
    private String name;
    private String username;
    private String password;
    private String orgId;
    private String parkId;
    private int userStatus;
    private boolean blocked;

    public User(String username, String password,String name, int userStatus, String orgId, String parkId) {
        this.id = generateUniqueId();
        this.username = username;
        this.name = name;
        this.password = password;
        this.orgId = orgId;
        this.parkId = parkId;
        this.userStatus = userStatus;
    }


}
