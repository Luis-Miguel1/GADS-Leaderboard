package com.example.gadsleaderboard.model;

public class LeadersScore {

    private String name, score, country,badgeUrl;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getBadgeUrl() {
        return badgeUrl;
    }

    public void setBadgeUrl(String badgeUrl) {
        this.badgeUrl = badgeUrl;
    }

    public LeadersScore(String name, String score, String skillCountry, String badgeUrl) {
        this.name = name;
        this.score = score;
        this.country = skillCountry;
        this.badgeUrl = badgeUrl;
    }
}
