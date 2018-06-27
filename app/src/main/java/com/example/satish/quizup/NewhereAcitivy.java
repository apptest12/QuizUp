package com.example.satish.quizup;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class NewhereAcitivy extends AppCompatActivity {

    DatabaseHelper helper = new DatabaseHelper(this);

    private Button Register;
    private Button Login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Set View to activity_newhre.xml
        setContentView(R.layout.activity_newhere_acitivy2);

        Register = findViewById(R.id.btnreg);
        Login = findViewById(R.id.btnlogin1);

        Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //popup msg
                Toast pwd = Toast.makeText(NewhereAcitivy.this, "Register Successfully", Toast.LENGTH_SHORT);
                pwd.show();
            }

        });
        // Listening to Login link
        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Closing registration screen
                // Switching to Login /closing register screen
                finish();
            }

        });
    }

    public void RegisterClick(View v) {
        if (v.getId() == R.id.btnreg) {
            EditText fname = findViewById(R.id.etfname);
            EditText lname = findViewById(R.id.etlname);
            EditText email1 = findViewById(R.id.etemail1);
            EditText pass = findViewById(R.id.etpass);
            EditText conpwd = findViewById(R.id.etconpwd);

            String fnamestr = fname.getText().toString();
            String lnamestr = lname.getText().toString();
            String email1str = email1.getText().toString();
            String passstr = pass.getText().toString();
            String conpwdstr = conpwd.getText().toString();

            // Check condition for password and confirm password.
            if (!passstr.equals(conpwdstr)) {
                //popup msg
                Toast pwd = Toast.makeText(NewhereAcitivy.this, "Password don't match", Toast.LENGTH_SHORT);
                pwd.show();
            } else {
                Contact c = new Contact();
                c.setFname(fnamestr);
                c.setLname(lnamestr);
                c.setEmail1(email1str);
                c.setPass(passstr);
                c.setConpwd(conpwdstr);

                helper.insertContact(c);
            }
        }
    }
}
