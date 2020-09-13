package com.example.gadsleaderboard;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

public class ProjectSubmissionActivity extends AppCompatActivity {
     Button submission;
     EditText fisrtName, LastName, emailAdress, gitHubLink;
    Dialog myDialog;
    ProgressBar mprogressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project_submission);
        fisrtName=findViewById(R.id.firstname);
        LastName =findViewById(R.id.lastname);
        emailAdress =findViewById(R.id.email);
        gitHubLink =findViewById(R.id.limkgithub);
        submission =findViewById(R.id.buttomsubbmit);
        myDialog = new Dialog(this);

        submission.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(getApplicationContext(), AgroFrescoActivity.class);
//                startActivity(intent);
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
        Button yes;
        myDialog.setContentView(R.layout.popup_error);
        yes = myDialog.findViewById(R.id.yes);
        yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // myDialog.dismiss();
            }
        });
        myDialog.show();

    }




    private void hideRemoveProgressBar() {
        if (mprogressBar.isShown()) {
            mprogressBar.setVisibility(View.GONE);
        }

    }
}