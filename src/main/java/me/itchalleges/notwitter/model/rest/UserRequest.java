package me.itchalleges.notwitter.model.rest;

/**
 * Date: 11/05/2016 1:20 PM
 */
public class UserRequest {
    private String name;
    private String password;

    public UserRequest() {
    }

    public UserRequest(String name, String password) {
        this.name = name;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
