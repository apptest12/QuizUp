package com.example.satish.quizup;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.OptionalPendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;

import java.util.Arrays;

public class LoginPage extends AppCompatActivity implements View.OnClickListener,
        GoogleApiClient.OnConnectionFailedListener {

    private static final String TAG = MainActivity.class.getSimpleName();
    private static final int RC_SIGN_IN = 10;
    // connect to DatabaseHelper class
    DatabaseHelper helper = new DatabaseHelper(this);
    private EditText EmailId;
    private EditText Password;
    private Button Login;
    private CallbackManager callbackManager;
    private TextView textView;
    private LoginButton loginButton;
    private TextView Newhere;
    private GoogleApiClient mGoogleApiClient;
    private ProgressDialog mProgressDialog;
    private SignInButton btnSignIn;
    private Button btnSignOut, btnRevokeAccess;
    private LinearLayout llProfileLayout;
    private ImageView imgProfilePic;
    private TextView txtName, txtEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        // setting default screen to login.xml
        setContentView(R.layout.login_page);

        // user login code
        EmailId = findViewById(R.id.etemail);
        Password = findViewById(R.id.etPassword);
        Login = findViewById(R.id.btnlogin);
        Newhere = findViewById(R.id.tvnewhere);

        // Listening to login new account link
        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (v.getId() == R.id.btnlogin) {
                    EditText a = findViewById(R.id.etemail);
                    String str = a.getText().toString();
                    EditText b = findViewById(R.id.etPassword);
                    String Password = b.getText().toString();

                    String password = helper.searchPassword(str);
                    if (password.equals(password)) {
                        Intent intent = new Intent(LoginPage.this, SecondActivity.class);
                        intent.putExtra("Email ID", str);
                        startActivity(intent);
                    } else {   // popup msg
                        Toast temp = Toast.makeText(LoginPage.this, "Email Id and Password don't match", Toast.LENGTH_SHORT);
                        temp.show();
                    }
                }
            }

        });

        // Facebook code

        textView = findViewById(R.id.textView);

        // The callbackManager manages tha callbacks into the FacebookSdk from an Activity.
        callbackManager = CallbackManager.Factory.create();

        loginButton = findViewById(R.id.fb_login_btn);
        loginButton.setReadPermissions(Arrays.asList("email"));

        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                //app code
            }

            @Override
            public void onCancel() {
                //app code
            }

            @Override
            public void onError(FacebookException error) {
                //app code
            }
        });

        callbackManager = CallbackManager.Factory.create();
        LoginManager.getInstance().registerCallback(callbackManager,
                new FacebookCallback<LoginResult>() {
                    @Override
                    public void onSuccess(LoginResult loginResult) {
                        textView.setText("successfully logged in");

                    }

                    @Override
                    public void onCancel() {
                        textView.setText("Login canceled");
                        // Function to handle cancel event
                    }

                    @Override
                    public void onError(FacebookException exception) {
                        // Function to handle Facebook errors

                    }
                });

        // Gmail login code

        btnSignIn = findViewById(R.id.btn_sign_in);
        btnSignOut = findViewById(R.id.btn_sign_out);
        btnRevokeAccess = findViewById(R.id.btn_revoke_access);
        llProfileLayout = findViewById(R.id.llProfile);
        imgProfilePic = findViewById(R.id.imgProfilePic);
        txtName = findViewById(R.id.txtName);
        txtEmail = findViewById(R.id.txtEmail);

        btnSignIn.setOnClickListener(this);
        btnSignOut.setOnClickListener(this);
        btnRevokeAccess.setOnClickListener(this);


        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this, this)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();

        // Customizing G+ button
        btnSignIn.setSize(SignInButton.SIZE_STANDARD);
        btnSignIn.setScopes(gso.getScopeArray());
    }


    private void signIn() {
        // Switching to new activity
        Intent intent = new Intent(LoginPage.this, SecondActivity.class);
        startActivity(intent);

        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }


    private void signOut() {
        Auth.GoogleSignInApi.signOut(mGoogleApiClient).setResultCallback(
                new ResultCallback<Status>() {
                    @Override
                    public void onResult(Status status) {
                        Toast.makeText(getApplicationContext(), "Logged Out", Toast.LENGTH_SHORT).show();
                    }
                });
    }


    private void revokeAccess() {
        Auth.GoogleSignInApi.revokeAccess(mGoogleApiClient).setResultCallback(
                new ResultCallback<Status>() {
                    @Override
                    public void onResult(Status status) {
                        updateUI(false);
                    }
                });
    }

    private void handleSignInResult(GoogleSignInResult result) {
        Log.d(TAG, "handleSignInResult:" + result.isSuccess());
        if (result.isSuccess()) {
            // Signed in successfully, show authenticated UI.
            GoogleSignInAccount acct = result.getSignInAccount();

            Log.e(TAG, "display name: " + acct.getDisplayName());

            String personName = acct.getDisplayName();
            String personPhotoUrl = acct.getPhotoUrl().toString();
            String email = acct.getEmail();

            Log.e(TAG, "Name: " + personName + ", email: " + email
                    + ", Image: " + personPhotoUrl);

            txtName.setText(personName);
            txtEmail.setText(email);
            Glide.with(getApplicationContext()).load(personPhotoUrl)
                    .thumbnail(0.5f)
                    .crossFade()
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(imgProfilePic);

            updateUI(true);
        } else {
            // Signed out, show unauthenticated UI.
            updateUI(false);
        }
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();

        switch (id) {
            case R.id.btn_sign_in:
                signIn();
                break;

            case R.id.btn_sign_out:
                signOut();
                break;

            case R.id.btn_revoke_access:
                revokeAccess();
                break;
        }
    }

    // The method that should be called from the Activity.
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        callbackManager.onActivityResult(requestCode, resultCode, data);
        Intent intent = new Intent(LoginPage.this, SecondActivity.class);
        startActivity(intent);

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            handleSignInResult(result);
        }
    }

    @Override
    public void onStart() {
        super.onStart();

        OptionalPendingResult<GoogleSignInResult> opr = Auth.GoogleSignInApi.silentSignIn(mGoogleApiClient);
        if (opr.isDone()) {
            // If the user's cached credentials are valid, the OptionalPendingResult will be "done"
            // and the GoogleSignInResult will be available instantly.
            Log.d(TAG, "Got cached sign-in");
            GoogleSignInResult result = opr.get();
            handleSignInResult(result);
        } else {
            // If the user has not previously signed in on this device or the sign-in has expired,
            // this asynchronous branch will attempt to sign in the user silently.  Cross-device
            // single sign-on will occur in this branch.
            showProgressDialog();
            opr.setResultCallback(new ResultCallback<GoogleSignInResult>() {
                @Override
                public void onResult(GoogleSignInResult googleSignInResult) {
                    hideProgressDialog();
                    handleSignInResult(googleSignInResult);
                }
            });
        }
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        // An unresolvable error has occurred and Google APIs (including Sign-In) will not
        // be available.
        Log.d(TAG, "onConnectionFailed:" + connectionResult);
    }

    private void showProgressDialog() {
        if (mProgressDialog == null) {
            mProgressDialog = new ProgressDialog(this);
            mProgressDialog.setMessage(getString(R.string.ld));
            mProgressDialog.setIndeterminate(true);
        }

        mProgressDialog.show();
    }

    private void hideProgressDialog() {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.hide();
        }
    }

    private void updateUI(boolean isSignedIn) {
        if (isSignedIn) {
            btnSignIn.setVisibility(View.GONE);
            btnSignOut.setVisibility(View.VISIBLE);
            btnRevokeAccess.setVisibility(View.VISIBLE);
            llProfileLayout.setVisibility(View.VISIBLE);
        } else {
            btnSignIn.setVisibility(View.VISIBLE);
            btnSignOut.setVisibility(View.GONE);
            btnRevokeAccess.setVisibility(View.GONE);
            llProfileLayout.setVisibility(View.GONE);
        }

    }

    // Newhere button click event
    public void new_activity(View view) {
        // Switching to Register screen
        Intent intent = new Intent(LoginPage.this, NewhereAcitivy.class);
        startActivity(intent);
    }
}
