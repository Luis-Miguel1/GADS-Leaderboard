package com.example.gadsleaderboard;

import com.example.gadsleaderboard.model.LeadersHour;
import com.example.gadsleaderboard.model.LeadersScore;
import com.example.gadsleaderboard.model.Post;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface Api {

   String ID="-1FAIpQLSf9d1TcNU6zc6KR8bSEM41Z1g1zl35cwZr2xyjIhaMAz8WChQ/formResponse";

    @GET("/api/hours")
    Call<List<LeadersHour>> listLeadersHour();

    @GET("/api/skilliq")
    Call<List<LeadersScore>> listLeaderSkilliq();

    @POST(ID)
    Call<Post> sendPost(@Body Post posts);


}


