package com.example.satish.quizup;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button suru;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        suru = findViewById(R.id.btq);

        // TestQuiz button click event
        suru.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Switching to Login screen
                Intent intent = new Intent(MainActivity.this, LoginPage.class);
                startActivity(intent);
            }
        });
    }
}
