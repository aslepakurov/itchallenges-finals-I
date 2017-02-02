package me.itchalleges.notwitter.model.rest;

/**
 * Date: 11/05/2016 2:55 PM
 */
public class ChirikRequest {
    private String text;
    private String tokenId;

    public ChirikRequest() {
    }

    public ChirikRequest(String text, String tokenId) {
        this.text = text;
        this.tokenId = tokenId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getTokenId() {
        return tokenId;
    }

    public void setTokenId(String tokenId) {
        this.tokenId = tokenId;
    }
}
