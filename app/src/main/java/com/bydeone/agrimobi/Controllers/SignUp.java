package com.bydeone.agrimobi.Controllers;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.bydeone.agrimobi.R;

public class SignUp extends AppCompatActivity {

    private EditText signUpLogin;
    private EditText signUpPassword;
    private TextView goToSignIn;
    private Button goToHome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        configureSignUpLogin();
        configureSignUpPassword();
        configureGoToSignIn();
        configureGoToHome();
    }

    // ----

    private void configureSignUpLogin(){
        // Serialise ImageView
        this.signUpLogin = (EditText) this.findViewById(R.id.EdtLogin);
        // Set OnClick Listener on it

    }

    private void configureSignUpPassword(){
        // Serialise ImageView
        this.signUpPassword = (EditText) this.findViewById(R.id.EdtPassword);
        // Set OnClick Listener on it

    }

    private void configureGoToSignIn(){
        // Serialise ImageView
        this.goToSignIn = (TextView) this.findViewById(R.id.goToSignIn);
        // Set OnClick Listener on it
        goToSignIn.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                //Launch Detail Activity
                launchSignInActivity();
            }
        });
    }

    private void configureGoToHome(){
        // Serialise ImageView
        this.goToHome = (Button) this.findViewById(R.id.goToHome);
        // Set OnClick Listener on it
        goToHome.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                //Launch Detail Activity
                launchHomeActivity();
            }
        });
    }

    // ----

    private void launchHomeActivity(){
        Intent myIntent = new Intent(SignUp.this, Home.class);
        this.startActivity(myIntent);
    }

    private void launchSignInActivity(){
        Intent myIntent = new Intent(SignUp.this, SignIn.class);
        this.startActivity(myIntent);
    }
}
