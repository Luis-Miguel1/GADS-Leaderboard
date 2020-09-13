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
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gadsleaderboard.model.LeadersHour;
import com.example.gadsleaderboard.model.Post;

import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ProjectSubmissionActivity extends AppCompatActivity {
    Button submission;
    Button yes;
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

//        final String firtName=fisrtName.getText().toString();
//        final String lastName=LastName.getText().toString();
//        final String emailAdress=emailAdress.getText().toString();
//        final String gitHubLink=gitHubLink.getText().toString();

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        submission.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    showpopComnfirmation();
            }
        });

    }

    private void request() {


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        Post post = new Post();
        post.setFirstMame(fisrtName.getText().toString());
        post.setLastName(LastName.getText().toString());
        post.setEmail(emailAdress.getText().toString());
        post.setLinkGitHub(Objects.requireNonNull(gitHubLink.getText()).toString());

        Api api = retrofit.create(Api.class);
        api.sendPost(post).enqueue(new Callback<Post>() {
            @Override
            public void onResponse(Call<Post> call, Response<Post> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(getApplicationContext(), response.body().toString(), Toast.LENGTH_LONG).show();
                    //hideRemoveProgressBar();
                    showpopUpSucess();

                }
            }

            @Override
            public void onFailure(Call<Post> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.toString(), Toast.LENGTH_LONG).show();
                Log.e("teste", "erro" + t.toString());
             //   hideRemoveProgressBar();
                showpopUpErro();
            }
        });

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