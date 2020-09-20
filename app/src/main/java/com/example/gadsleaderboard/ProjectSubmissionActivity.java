package com.example.gadsleaderboard;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.Toolbar;

import android.app.Dialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gadsleaderboard.model.Post;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ProjectSubmissionActivity extends AppCompatActivity {
    Button submission;
    ImageView back;
    EditText fisrtName, LastName, emailAdress;
    AppCompatEditText gitHubLink;
    Dialog myDialog;
    private String BASE_URL = "https://docs.google.com/forms/d/e/";
    //ProgressBar mprogressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        setContentView(R.layout.activity_project_submission);

        fisrtName = findViewById(R.id.firstname);
        back = findViewById(R.id.back);
        LastName = findViewById(R.id.lastname);
        emailAdress = findViewById(R.id.email);
        gitHubLink = findViewById(R.id.limkgithub);
        submission = findViewById(R.id.buttomsubbmit);
        myDialog = new Dialog(this);


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        submission.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 String firtname = fisrtName.getText().toString();
                 String lastname = LastName.getText().toString();
                 String emailadres = emailAdress.getText().toString();
                 String githubLink = gitHubLink.getText().toString();

                if (TextUtils.isEmpty(firtname) || TextUtils.isEmpty(lastname) || TextUtils.isEmpty(emailadres)
                        || TextUtils.isEmpty(githubLink)) {
                    Toast.makeText(getApplicationContext(), "The fields can not be empty", Toast.LENGTH_SHORT).show();
                } else {
                    showpopComnfirmation();
                }
            }
        });

    }

    private void request() {


        Log.e("testerequest", "request");
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();


        Api api = retrofit.create(Api.class);
        api.sendPost(fisrtName.getText().toString(), LastName.getText().toString(), emailAdress.getText().toString(),
                gitHubLink.getText().toString()).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    Log.e("testerequest", "sucesso");
                    //hideRemoveProgressBar();
                    showpopUpSucess();


                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                //   hideRemoveProgressBar();
                showpopUpErro();

            }
        });
//        api.sendPost(post.getFirstName(), post.getLastName(), post.getEmail(), post.getLinkGitHub()).enqueue(new Callback<Post>() {
//        @Override
//        public void onResponse(Call<Post> call, Response<Post> response) {
//            if (response.isSuccessful()) {
//                Toast.makeText(getApplicationContext(), response.body().toString(), Toast.LENGTH_LONG).show();
//                Log.e("testerequest", "sucesso");
//                //hideRemoveProgressBar();
//                showpopUpSucess();
//
//
//            }
//        }
//
//        @Override
//        public void onFailure(Call<Post> call, Throwable t) {
//            Toast.makeText(getApplicationContext(), t.toString(), Toast.LENGTH_LONG).show();
//            Log.e("teste", "erro" + t.toString());
//            //   hideRemoveProgressBar();
//            showpopUpErro();
//        }
//    });

    }

    public void showpopUpSucess() {
        TextView close;

        myDialog.setContentView(R.layout.popup_sucess);
        close = myDialog.findViewById(R.id.closeSucess);

        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myDialog.dismiss();
            }
        });
        myDialog.show();
    }

    public void showpopUpErro() {
        TextView close;
        myDialog.setContentView(R.layout.popup_error);
        close = myDialog.findViewById(R.id.closeErro);
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myDialog.dismiss();
            }
        });
        myDialog.show();

    }

    public void showpopComnfirmation() {
        TextView close;
        Button yes;


        myDialog.setContentView(R.layout.popup_confirmation);
        yes = myDialog.findViewById(R.id.yes);
        close = myDialog.findViewById(R.id.close);
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myDialog.dismiss();
            }
        });
        yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                request();
                myDialog.dismiss();
                //   mprogressBar.setVisibility(View.VISIBLE);

            }
        });
        myDialog.show();

    }


//    private void hideRemoveProgressBar() {
//        if (mprogressBar.isShown()) {
//            mprogressBar.setVisibility(View.GONE);
//        }
//
//    }
}