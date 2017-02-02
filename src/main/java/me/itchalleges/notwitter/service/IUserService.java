package me.itchalleges.notwitter.service;

import me.itchalleges.notwitter.exception.ValidationException;
import me.itchalleges.notwitter.model.Chirik;
import me.itchalleges.notwitter.model.rest.FollowRequest;
import me.itchalleges.notwitter.model.rest.UserRequest;

import java.util.List;

/**
 * Date: 11/05/2016 1:10 PM
 */
public interface IUserService {
    String create(UserRequest userRequest) throws ValidationException;
    String getUserName(String id) throws ValidationException;
    String login(UserRequest userRequest) throws ValidationException;
    void logout(String tokenId);
    String getUserId(String userName) throws ValidationException;
    List<String> getUserIds(List<String> userNames);
    List<String> getFollowing(String id) throws ValidationException;
    String follow(FollowRequest followRequest) throws ValidationException;
    void mention(String userId, String chirikId) throws ValidationException;
    List<Chirik> getMentions(String userId);
}
