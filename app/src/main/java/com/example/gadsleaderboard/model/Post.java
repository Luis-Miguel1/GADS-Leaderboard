package com.example.gadsleaderboard.model;
import com.google.gson.annotations.SerializedName;
public class Post {


    @SerializedName("firstName")
    private String firstName;
    @SerializedName("linkGitHub")
    private String linkGitHub;
    @SerializedName("email")
    private String email;
    @SerializedName("lastName")
    private String lastName;


    public String getLinkGitHub() {
        return linkGitHub;
    }

    public void setLinkGitHub(String linkGitHub) {
        this.linkGitHub = linkGitHub;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
}


