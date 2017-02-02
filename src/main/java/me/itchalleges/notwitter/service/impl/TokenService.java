package me.itchalleges.notwitter.service.impl;

import me.itchalleges.notwitter.dao.ITokenDao;
import me.itchalleges.notwitter.service.ITokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * Date: 11/05/2016 1:49 PM
 */
@Service
public class TokenService implements ITokenService {

    @Autowired
    private ITokenDao tokenDao;

    @Override
    public String createToken(String userId) {
        String id = UUID.randomUUID().toString();
        return tokenDao.createToken(id, userId);
    }

    @Override
    public String getUserIdByToken(String tokenId) {
        return tokenDao.getUserIdByToken(tokenId);
    }

    @Override
    public void removeToken(String tokenId) {
        tokenDao.removeToken(tokenId);
    }

    @Override
    public boolean tokenExists(String tokenId) {
        return tokenDao.tokenExist(tokenId);
    }
}
