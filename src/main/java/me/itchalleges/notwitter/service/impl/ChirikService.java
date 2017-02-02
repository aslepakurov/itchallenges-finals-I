package me.itchalleges.notwitter.service.impl;

import com.google.common.collect.Lists;
import me.itchalleges.notwitter.dao.IChirikDao;
import me.itchalleges.notwitter.exception.ValidationException;
import me.itchalleges.notwitter.model.Chirik;
import me.itchalleges.notwitter.model.rest.ChirikDto;
import me.itchalleges.notwitter.model.rest.ChirikRequest;
import me.itchalleges.notwitter.service.IChirikService;
import me.itchalleges.notwitter.service.IMessageService;
import me.itchalleges.notwitter.service.ITokenService;
import me.itchalleges.notwitter.service.IUserService;
import org.apache.commons.lang.StringEscapeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Date: 11/05/2016 2:17 PM
 */
@Service
public class ChirikService implements IChirikService {

    @Autowired
    private IChirikDao chirikDao;
    @Autowired
    private IUserService userService;
    @Autowired
    private ITokenService tokenService;
    @Autowired
    private IMessageService messages;
    @Autowired
    private Integer messageLimit;

    @Override
    public String createChirik(ChirikRequest chirikRequest) throws ValidationException {
        String text = chirikRequest.getText();
        String tokenId = chirikRequest.getTokenId();
        if (text==null || tokenId==null) {
            throw new ValidationException(messages.getMessage("service.text.token.empty"));
        }
        text = StringEscapeUtils.escapeHtml(text);
        if (text.length() > messageLimit) {
            throw new ValidationException(messages.getMessage("service.over.limit", messageLimit));
        }
        if (!tokenService.tokenExists(tokenId)) {
            throw new ValidationException(messages.getMessage("service.no.such.token", tokenId));
        }
        String userId = tokenService.getUserIdByToken(tokenId);
        String[] words = text.split(" ");
        List<String> mentions = new ArrayList<>();
        for (String word : words) {
            if (word.startsWith("@")) {
                mentions.add(word.replace("@", ""));
            }
        }
        List<String> mentionIds = userService.getUserIds(mentions);
        String chirikId = chirikDao.createChirik(text, userId);
        for (String mentionedUserId : mentionIds) {
            userService.mention(mentionedUserId, chirikId);
        }
        return chirikId;
    }

    @Override
    public List<ChirikDto> getOwn(String tokenId) throws ValidationException {
        if (tokenId == null) {
            throw new ValidationException(messages.getMessage("service.token.empty"));
        }
        if (!tokenService.tokenExists(tokenId)) {
            throw new ValidationException(messages.getMessage("service.no.such.token", tokenId));
        }
        String userId = tokenService.getUserIdByToken(tokenId);
        List<Chirik> chirikByUserId = chirikDao.getChirikByUserId(userId);
        return Lists.transform(chirikByUserId, ChirikDto::to);
    }

    @Override
    public List<ChirikDto> getFollowing(String tokenId) throws ValidationException {
        if (tokenId == null) {
            throw new ValidationException(messages.getMessage("service.token.empty"));
        }
        if (!tokenService.tokenExists(tokenId)) {
            throw new ValidationException(messages.getMessage("service.no.such.token", tokenId));
        }
        String userId = tokenService.getUserIdByToken(tokenId);
        List<String> following = userService.getFollowing(userId);
        List<ChirikDto> followChirik = new ArrayList<>();
        for (String followedUser : following) {
            List<Chirik> chirikByUserId = chirikDao.getChirikByUserId(followedUser);
            List<ChirikDto> followChiriks = Lists.transform(chirikByUserId, ChirikDto::to);
            followChirik.addAll(followChiriks);
        }
        return followChirik;
    }

    @Override
    public List<ChirikDto> getMention(String tokenId) throws ValidationException {
        if (tokenId == null) {
            throw new ValidationException(messages.getMessage("service.token.empty"));
        }
        if (!tokenService.tokenExists(tokenId)) {
            throw new ValidationException(messages.getMessage("service.no.such.token", tokenId));
        }
        String userId = tokenService.getUserIdByToken(tokenId);
        List<Chirik> mention = userService.getMentions(userId);
        return Lists.transform(mention, ChirikDto::to);
    }

    @Override
    public List<ChirikDto> getWall(String tokenId) throws ValidationException {
        List<ChirikDto> chiriks = new ArrayList<>();
        chiriks.addAll(getOwn(tokenId));
        chiriks.addAll(getFollowing(tokenId));
        chiriks.addAll(getMention(tokenId));
        return chiriks;
    }

    @Override
    public List<ChirikDto> getMentionByUser(String userName) throws ValidationException {
        if (userName == null) {
            throw new ValidationException(messages.getMessage("service.user.name.empty"));
        }
        String userId = userService.getUserId(userName);
        List<Chirik> mentions = userService.getMentions(userId);
        return Lists.transform(mentions, ChirikDto::to);
    }
}
