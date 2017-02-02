package me.itchalleges.notwitter.dao.impl;

import me.itchalleges.notwitter.dao.IChirikDao;
import me.itchalleges.notwitter.model.Chirik;
import me.itchalleges.notwitter.service.IMessageService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

/**
 * Date: 11/05/2016 2:12 PM
 */
@Repository
public class ChirikDao implements IChirikDao {

    private static final Logger LOG = Logger.getLogger(ChirikDao.class);

    @Autowired
    private MongoOperations mongoOperations;
    @Autowired
    private IMessageService messages;

    @Override
    public String createChirik(String text, String userId) {
        if (!collectionExist()) {
            LOG.info(messages.getMessage("dao.create.scheme", Chirik.class.toString()));
            mongoOperations.createCollection(Chirik.class);
        }
        String id = UUID.randomUUID().toString();
        Chirik chirik = new Chirik(id, text, userId);
        mongoOperations.save(chirik);
        return id;
    }

    @Override
    public Chirik getChirik(String chirikId) {
        Query query = new Query(Criteria.where("id").is(chirikId));
        return mongoOperations.findOne(query, Chirik.class);
    }

    @Override
    public List<Chirik> getChirikByUserId(String userId) {
        Query query = new Query(Criteria.where("userId").is(userId));
        return mongoOperations.find(query, Chirik.class);
    }

    private boolean collectionExist() {
        return mongoOperations.collectionExists(Chirik.class);
    }

}
