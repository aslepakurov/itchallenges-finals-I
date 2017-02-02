package me.itchalleges.notwitter.dao;

/**
 * Date: 11/05/2016 1:42 PM
 */
public interface ITokenDao {
    String createToken(String id, String userId);
    String getUserIdByToken(String tokenId);
    boolean tokenExist(String tokenId);
    void removeToken(String tokenId);
}
