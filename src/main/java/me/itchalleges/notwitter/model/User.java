package me.itchalleges.notwitter.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Date: 11/05/2016 12:28 PM
 */
public class User {
    private String id;
    private String userName;
    private String password;
    private List<String> following;
    private List<String> mentions;

    public User() {
        following = new ArrayList<>();
        mentions = new ArrayList<>();
    }

    public User(String id, String userName, String password, List<String> following, List<String> mentions) {
        this.id = id;
        this.userName = userName;
        this.password = password;
        this.following = following;
        this.mentions = mentions;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<String> getFollowing() {
        return following;
    }

    public void setFollowing(List<String> following) {
        this.following = following;
    }

    public List<String> getMentions() {
        return mentions;
    }

    public void setMentions(List<String> mentions) {
        this.mentions = mentions;
    }
}
