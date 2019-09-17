package model;

import java.io.Serializable;

public class UserLog  {
    private String username;
    private String password;

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public UserLog(String username, String password) {
        this.username = username;
        this.password = password;
    }



}
