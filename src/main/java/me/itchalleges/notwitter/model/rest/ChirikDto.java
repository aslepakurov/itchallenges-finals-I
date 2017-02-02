package me.itchalleges.notwitter.model.rest;

import me.itchalleges.notwitter.model.Chirik;

/**
 * Date: 11/05/2016 3:57 PM
 */
public class ChirikDto {
    private String text;
    private String createdBy;

    public ChirikDto() {
    }

    public ChirikDto(String text, String createdBy) {
        this.text = text;
        this.createdBy = createdBy;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public static ChirikDto to(Chirik chirik) {
        ChirikDto dto = new ChirikDto();
        dto.setCreatedBy(chirik.getUserId());
        dto.setText(chirik.getText());
        return dto;
    }
}
