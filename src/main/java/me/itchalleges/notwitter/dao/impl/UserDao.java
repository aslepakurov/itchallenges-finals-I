package me.itchalleges.notwitter.dao.impl;

import me.itchalleges.notwitter.dao.IUserDao;
import me.itchalleges.notwitter.exception.ValidationException;
import me.itchalleges.notwitter.model.User;
import me.itchalleges.notwitter.service.IMessageService;
import me.itchalleges.notwitter.utils.Sha2Util;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Date: 11/05/2016 12:36 PM
 */
@Repository
public class UserDao implements IUserDao {
    private static final Logger LOG = Logger.getLogger(UserDao.class);

    @Autowired
    private MongoOperations mongoOperations;
    @Autowired
    private IMessageService messages;

    @Override
    public String create(String userName, String password) throws ValidationException {
        if (!collectionExist()) {
            LOG.info(messages.getMessage("dao.create.scheme", User.class.toString()));
            mongoOperations.createCollection(User.class);
        }
        if (exists(userName)) {
            throw new ValidationException(messages.getMessage("dao.user.exists", userName));
        }
        User user = new User();
        String id = UUID.randomUUID().toString();
        user.setId(id);
        user.setUserName(userName);
        user.setPassword(password);
        mongoOperations.save(user);
        return id;
    }

    @Override
    public User getById(String id) {
        Query query = new Query(Criteria.where("id").is(id));
        return mongoOperations.findOne(query, User.class);
    }

    @Override
    public User getByName(String userName) {
        Query query = new Query(Criteria.where("userName").is(userName));
        return mongoOperations.findOne(query, User.class);
    }

    @Override
    public User getByNameAndPassword(String userName, String hashPassword) {
        Query query = new Query(Criteria.where("userName").is(userName)
                .andOperator(Criteria.where("password").is(hashPassword)));
        return mongoOperations.findOne(query, User.class);
    }

    @Override
    public boolean validateUser(String userName, String password) {
        if (!exists(userName)) {
            return false;
        }
        String hashPassword = Sha2Util.sha2(password);
        Query query = new Query(Criteria.where("userName").is(userName)
                .andOperator(Criteria.where("password").is(hashPassword)));
        return mongoOperations.exists(query, User.class);
    }

    @Override
    public boolean exists(String userName) {
        Query query = new Query(Criteria.where("userName").is(userName));
        return mongoOperations.exists(query, User.class);
    }

    @Override
    public List<String> getUserIds(List<String> userNames) {
        List<String> ids = new ArrayList<>();
        for (String userName : userNames) {
            User user = getByName(userName);
            if (user != null) {
                ids.add(user.getId());
            }
        }
        return ids;
    }

    @Override
    public String follow(String followerId, String followeeId) {
        User followee = getById(followeeId);
        User follower = getById(followerId);
        List<String> following = follower.getFollowing();
        following.add(followeeId);
        mongoOperations.updateFirst(new Query(Criteria.where("id").is(followerId)),
                Update.update("following", following), User.class);
        String message = messages.getMessage("service.follower.added", follower.getUserName(), followee.getUserName());
        LOG.info(message);
        return message;
    }

    @Override
    public void mention(String userId, String chirikId) {
        User mentionedUser = getById(userId);
        List<String> mentioned = mentionedUser.getMentions();
        mentioned.add(chirikId);
        mongoOperations.updateFirst(new Query(Criteria.where("id").is(userId)),
                Update.update("mentions", mentioned), User.class);
    }

    @Override
    public List<String> getMentionsId(String userId) {
        User user = getById(userId);
        return user.getMentions();
    }

    private boolean collectionExist() {
        return mongoOperations.collectionExists(User.class);
    }
}
