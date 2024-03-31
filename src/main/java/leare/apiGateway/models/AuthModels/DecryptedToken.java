package leare.apiGateway.models.AuthModels;

public class DecryptedToken {
    private String UserID;
    private String Username;
    private String Role;

    public DecryptedToken(String UserID, String Username, String Role) {
        this.UserID = UserID;
        this.Username = Username;
        this.Role = Role;
    }

    public String getUserID() {
        return this.UserID;
    }

    public void setUserID(String UserID) {
        this.UserID = UserID;
    }

    public String getUsername() {
        return this.Username;
    }

    public void setUsername(String Username) {
        this.Username = Username;
    }

    public String getRole() {
        return this.Role;
    }

    public void setRole(String Role) {
        this.Role = Role;
    }

    public DecryptedToken UserID(String UserID) {
        setUserID(UserID);
        return this;
    }

    public DecryptedToken Username(String Username) {
        setUsername(Username);
        return this;
    }

    public DecryptedToken Role(String Role) {
        setRole(Role);
        return this;
    }

    
}
