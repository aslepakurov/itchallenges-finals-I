package me.itchalleges.notwitter.dao;

import me.itchalleges.notwitter.model.Chirik;

import java.util.List;

/**
 * Date: 11/05/2016 2:11 PM
 */
public interface IChirikDao {
    String createChirik(String text, String userId);
    Chirik getChirik(String chirikId);
    List<Chirik> getChirikByUserId(String userId);
}
