import org.springframework.stereotype.Component;

@Component
public class GlobalSettings {
    private Long loginId;

    private int loginRole;
    public void setLoggedInUser(Long userId, int role) {
        this.loginId = userId;
        this.loginRole = role;
    }

    public Long getLoginId() {
        return loginId;
    }

    public int getLoginRole() {
        return loginRole;
    }
}