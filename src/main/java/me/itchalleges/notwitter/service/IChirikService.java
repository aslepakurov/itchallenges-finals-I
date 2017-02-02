package me.itchalleges.notwitter.service;

import me.itchalleges.notwitter.exception.ValidationException;
import me.itchalleges.notwitter.model.rest.ChirikDto;
import me.itchalleges.notwitter.model.rest.ChirikRequest;

import java.util.List;

/**
 * Date: 11/05/2016 2:16 PM
 */
public interface IChirikService {
    String createChirik(ChirikRequest chirikRequest) throws ValidationException;
    List<ChirikDto> getOwn(String tokenId) throws ValidationException;
    List<ChirikDto> getFollowing(String tokenId) throws ValidationException;
    List<ChirikDto> getMention(String tokenId) throws ValidationException;
    List<ChirikDto> getWall(String tokenId) throws ValidationException;
    List<ChirikDto> getMentionByUser(String userName) throws ValidationException;
}
