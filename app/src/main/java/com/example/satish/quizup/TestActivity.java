package com.example.satish.quizup;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class TestActivity extends AppCompatActivity {
    private Button Subject1;
    private Button Subject2;
    private Button Subject3;
    private Button Subject4;
    private Button Subject5;
    private Button Subject6;
    private Button Subject7;
    private Button Subject8;
    private Button Subject9;
    private Button Subject10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        Subject1 = findViewById(R.id.button1);
        Subject2 = findViewById(R.id.button2);
        Subject3 = findViewById(R.id.button3);
        Subject4 = findViewById(R.id.button4);
        Subject5 = findViewById(R.id.button5);
        Subject6 = findViewById(R.id.button6);
        Subject7 = findViewById(R.id.button7);
        Subject8 = findViewById(R.id.button8);
        Subject9 = findViewById(R.id.button9);
        Subject10 = findViewById(R.id.button10);

        Subject1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog();
            }
        });

        // Subject button click event
        Subject2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog();
            }
        });
        Subject3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog();
            }
        });
        Subject4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog();
            }
        });
        Subject5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog();
            }
        });
        Subject6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog();
            }
        });
        Subject7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog();
            }
        });
        Subject8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog();
            }
        });
        Subject9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog();
            }
        });
        Subject10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog();
            }
        });
    }

    private void openDialog() {
        Subject1Dialog subject1Dialog = new Subject1Dialog();
        subject1Dialog.show(getSupportFragmentManager(), "subject1 dialog");
    }


}
