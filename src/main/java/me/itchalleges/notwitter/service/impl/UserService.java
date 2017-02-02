package me.itchalleges.notwitter.service.impl;

import me.itchalleges.notwitter.dao.IChirikDao;
import me.itchalleges.notwitter.dao.IUserDao;
import me.itchalleges.notwitter.exception.ValidationException;
import me.itchalleges.notwitter.model.Chirik;
import me.itchalleges.notwitter.model.User;
import me.itchalleges.notwitter.model.rest.FollowRequest;
import me.itchalleges.notwitter.model.rest.UserRequest;
import me.itchalleges.notwitter.service.IMessageService;
import me.itchalleges.notwitter.service.ITokenService;
import me.itchalleges.notwitter.service.IUserService;
import me.itchalleges.notwitter.utils.Sha2Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Date: 11/05/2016 1:14 PM
 */
@Service
public class UserService implements IUserService {

    @Autowired
    private IMessageService messages;
    @Autowired
    private IUserDao userDao;
    @Autowired
    private IChirikDao chirikDao;
    @Autowired
    private ITokenService tokenService;

    @Override
    public String create(UserRequest userRequest) throws ValidationException {
        String userName = userRequest.getName();
        String password = userRequest.getPassword();
        if (userName==null || password==null) {
            throw new ValidationException(messages.getMessage("service.user.create.empty"));
        }
        return userDao.create(userName, Sha2Util.sha2(password));
    }

    @Override
    public String getUserName(String id) throws ValidationException {
        User user = userDao.getById(id);
        return user.getUserName();
    }

    @Override
    public String login(UserRequest userRequest) throws ValidationException {
        String userName = userRequest.getName();
        String password = userRequest.getPassword();
        if (userName==null || password==null) {
            throw new ValidationException(messages.getMessage("service.user.login.empty"));
        }
        if(!userDao.exists(userName)) {
            throw new ValidationException(messages.getMessage("service.no.such.user", userName));
        }
        User user = userDao.getByNameAndPassword(userName, Sha2Util.sha2(password));
        return tokenService.createToken(user.getId());
    }

    @Override
    public void logout(String tokenId) {
        tokenService.removeToken(tokenId);
    }

    @Override
    public String getUserId(String userName) throws ValidationException {
        if (!userDao.exists(userName)) {
            throw new ValidationException(messages.getMessage("service.no.such.user", userName));
        }
        return userDao.getByName(userName).getId();
    }

    @Override
    public List<String> getUserIds(List<String> userNames) {
        return userDao.getUserIds(userNames);
    }

    @Override
    public List<String> getFollowing(String id) throws ValidationException {
        User user = userDao.getById(id);
        if (user == null) {
            throw new ValidationException(messages.getMessage("dao.user.id.exists", id));
        }
        return user.getFollowing();
    }

    @Override
    public String follow(FollowRequest followRequest) throws ValidationException {
        String tokenId = followRequest.getTokenId();
        String foloweeId = followRequest.getFolloweeId();
        if (tokenId==null || foloweeId==null) {
            throw new ValidationException(messages.getMessage("service.follow.empty"));
        }
        String userId = tokenService.getUserIdByToken(tokenId);
        if (userId == null) {
            throw new ValidationException(messages.getMessage("service.no.such.token", tokenId));
        }
        return userDao.follow(userId, foloweeId);
    }

    @Override
    public void mention(String userId, String chirikId) throws ValidationException {
        userDao.mention(userId, chirikId);
    }

    @Override
    public List<Chirik> getMentions(String userId) {
        List<String> mentionsId = userDao.getMentionsId(userId);
        List<Chirik> chirik = new ArrayList<>();
        for (String chirikId : mentionsId) {
            Chirik chirikCurr = chirikDao.getChirik(chirikId);
            if (chirikCurr != null) {
                chirik.add(chirikCurr);
            }
        }
        return chirik;
    }
}
