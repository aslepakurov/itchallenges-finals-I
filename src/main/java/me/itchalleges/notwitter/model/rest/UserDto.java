package me.itchalleges.notwitter.model.rest;

import me.itchalleges.notwitter.model.User;

/**
 * Date: 11/05/2016 1:12 PM
 */
public class UserDto {
    private String id;
    private String name;

    public UserDto() {
    }

    public UserDto(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static UserDto to(User user) {
        return new UserDto(user.getId(), user.getUserName());
    }
}
