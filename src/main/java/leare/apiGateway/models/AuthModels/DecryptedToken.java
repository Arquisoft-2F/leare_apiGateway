package leare.apiGateway.models.AuthModels;

public class DecryptedToken {
    private String UserID;
    private String Role;

    public DecryptedToken() {
    }

    public DecryptedToken(String UserID, String Role) {
        this.UserID = UserID;
        this.Role = Role;
    }

    public String getUserID() {
        return this.UserID;
    }

    public void setUserID(String UserID) {
        this.UserID = UserID;
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

    public DecryptedToken Role(String Role) {
        setRole(Role);
        return this;
    }    
    
}
