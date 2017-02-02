package me.itchalleges.notwitter.model;

/**
 * Date: 11/05/2016 1:42 PM
 */
public class Token {
    String id;
    String userId;

    public Token() {
    }

    public Token(String id, String userId) {
        this.id = id;
        this.userId = userId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
