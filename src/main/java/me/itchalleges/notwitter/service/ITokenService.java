package me.itchalleges.notwitter.service;

/**
 * Date: 11/05/2016 1:45 PM
 */
public interface ITokenService {
    String createToken(String userId);
    String getUserIdByToken(String tokenId);
    void removeToken(String tokenId);
    boolean tokenExists(String tokenId);
}
