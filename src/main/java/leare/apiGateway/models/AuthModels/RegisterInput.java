package leare.apiGateway.models.AuthModels;

public class RegisterInput {
    private String name;
    private String email;
    private String password;
    private String confirmPassword;
    private Number role;
    private String userId;


    // Constructor, getters, and setters
    public RegisterInput(String name, String email, String password, String confirmPassword, Number role, String userId) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.confirmPassword = confirmPassword;
        this.role = role;
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirm_password(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public Number getRole() {
        return role;
    }

    public void setRole(Number role) {
        this.role = role;
    }

    public String getUserId() {
        return userId;
    }

    public void setUser_id(String userId) {
        this.userId = userId;
    }



}
