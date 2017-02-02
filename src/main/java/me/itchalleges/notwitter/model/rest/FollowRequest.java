package me.itchalleges.notwitter.model.rest;

/**
 * Date: 11/05/2016 5:23 PM
 */
public class FollowRequest {
    private String tokenId;
    private String followeeId;

    public FollowRequest() {
    }

    public FollowRequest(String tokenId, String followeeId) {
        this.tokenId = tokenId;
        this.followeeId = followeeId;
    }

    public String getTokenId() {
        return tokenId;
    }

    public void setTokenId(String tokenId) {
        this.tokenId = tokenId;
    }

    public String getFolloweeId() {
        return followeeId;
    }

    public void setFolloweeId(String followeeId) {
        this.followeeId = followeeId;
    }
}
