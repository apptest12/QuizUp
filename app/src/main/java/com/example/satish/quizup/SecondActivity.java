package com.example.satish.quizup;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class SecondActivity extends AppCompatActivity {
    private Button Taketestnow;
    private Button SignOut;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        Taketestnow = findViewById(R.id.btn3);
        SignOut = findViewById(R.id.btn1signout);

        Taketestnow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Switching to TestActivity screen
                Intent intent = new Intent(SecondActivity.this, TestActivity.class);
                startActivity(intent);
            }
        });
        // SignOut button click event
        SignOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // openDialog box
                openDialog();
            }
        });
    }

    private void openDialog() {
        ExampleDialogextends exampleDialogextends = new ExampleDialogextends();
        exampleDialogextends.show(getSupportFragmentManager(), "example dialog");
    }


}
