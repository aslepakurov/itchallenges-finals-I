package me.itchalleges.notwitter.dao;

import me.itchalleges.notwitter.exception.ValidationException;
import me.itchalleges.notwitter.model.Chirik;
import me.itchalleges.notwitter.model.User;

import java.util.List;

/**
 * Date: 11/05/2016 12:34 PM
 */
public interface IUserDao {
    String create(String userName, String password) throws ValidationException;
    User getById(String id);
    User getByName(String userName);
    User getByNameAndPassword(String userName, String hashPassword);
    boolean validateUser(String userName, String password);
    boolean exists(String userName);
    List<String> getUserIds(List<String> userNames);
    String follow(String followerId, String followeeId);
    void mention(String userId, String chirikId);
    List<String> getMentionsId(String userId);
}
