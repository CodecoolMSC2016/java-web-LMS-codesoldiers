package app;

public class User {
    private String username;
    private final String emailAddress;
    private String role;
    private String password;

    // TODO: SHA-1 pass
    public User(String username, String emailAddress, String role, String password) {
        this.username = username;
        this.emailAddress = emailAddress;
        this.role = role;
        this.password = password;
    }

    @Override
    public String toString() {
        return username + ";" + emailAddress + ";" + role + ";" + password;
    }

    public String getPassword() {
        return password;
    }

    public String getRole() {
        return role;
    }

    public String getEmail() {
        return emailAddress;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}



