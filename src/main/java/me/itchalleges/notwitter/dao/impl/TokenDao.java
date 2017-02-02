package me.itchalleges.notwitter.dao.impl;

import me.itchalleges.notwitter.dao.ITokenDao;
import me.itchalleges.notwitter.model.Token;
import me.itchalleges.notwitter.service.IMessageService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

/**
 * Date: 11/05/2016 1:49 PM
 */
@Repository
public class TokenDao implements ITokenDao {
    private static final Logger LOG = Logger.getLogger(TokenDao.class);

    @Autowired
    private MongoOperations mongoOperations;
    @Autowired
    private IMessageService messages;
    @Override
    public String createToken(String id, String userId) {
        if (!collectionExist()) {
            LOG.info(messages.getMessage("dao.create.scheme", Token.class.toString()));
            mongoOperations.createCollection(Token.class);
        }
        mongoOperations.save(new Token(id, userId));
        return id;
    }

    @Override
    public String getUserIdByToken(String tokenId) {
        Query query = new Query(Criteria.where("id").is(tokenId));
        Token token = mongoOperations.findOne(query, Token.class);
        return token.getUserId();
    }

    @Override
    public boolean tokenExist(String tokenId) {
        Query query = new Query(Criteria.where("id").is(tokenId));
        return mongoOperations.exists(query, Token.class);
    }

    @Override
    public void removeToken(String tokenId) {
        Query query = new Query(Criteria.where("_id").is(tokenId));
        mongoOperations.remove(query, Token.class);
    }

    private boolean collectionExist() {
        return mongoOperations.collectionExists(Token.class);
    }
}
