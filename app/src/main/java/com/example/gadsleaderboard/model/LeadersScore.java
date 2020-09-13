package com.example.gadsleaderboard.model;

public class LeadersScore {
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getBadgeUrl() {
        return badgeUrl;
    }

    public void setBadgeUrl(String badgeUrl) {
        this.badgeUrl = badgeUrl;
    }

    public LeadersScore(String name, String country, String score, String badgeUrl) {
        this.name = name;
        this.country = country;
        this.score = score;
        this.badgeUrl = badgeUrl;
    }

    private String name, country, score,badgeUrl;
}
