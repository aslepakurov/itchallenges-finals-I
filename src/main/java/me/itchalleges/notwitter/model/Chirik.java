package me.itchalleges.notwitter.model;

import java.util.List;

/**
 * Date: 11/05/2016 12:28 PM
 */
public class Chirik {
    private String id;
    private String text;
    private String userId;

    public Chirik() {
    }

    public Chirik(String id, String text, String userId) {
        this.id = id;
        this.text = text;
        this.userId = userId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

}