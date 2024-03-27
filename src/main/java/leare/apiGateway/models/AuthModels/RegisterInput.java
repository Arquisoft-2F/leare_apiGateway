package leare.apiGateway.models.AuthModels;

public class RegisterInput {
    private String name;
    private String email;
    private String password;
    private String confirm_password;
    private Number role;
    private String user_id;


    // Constructor, getters, and setters
    public RegisterInput(String name, String email, String password, String confirm_password, Number role, String user_id) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.confirm_password = confirm_password;
        this.role = role;
        this.user_id = user_id;
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

    public String getConfirm_password() {
        return confirm_password;
    }

    public void setConfirm_password(String confirm_password) {
        this.confirm_password = confirm_password;
    }

    public Number getRole() {
        return role;
    }

    public void setRole(Number role) {
        this.role = role;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }



}
