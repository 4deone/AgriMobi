package com.bydeone.agrimobi.Controllers;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.bydeone.agrimobi.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignUp extends AppCompatActivity {

    private EditText signUpLogin;
    private EditText signUpPassword;
    private TextView goToSignIn;
    private Button goToHome;

    private ProgressDialog progressDialog;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        firebaseAuth = FirebaseAuth.getInstance();

        if (firebaseAuth.getCurrentUser() != null){
            //
            finish();
            startActivity(new Intent(getApplicationContext(), Home.class));
        }

        this.configureSignUpLogin();
        this.configureSignUpPassword();
        this.configureGoToSignIn();
        this.configureGoToHome();
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

    private void configureProgressBar(){
        // Serialise ImageView
        this.progressDialog = new ProgressDialog(this);
    }

    private void launchHomeActivity(){
        String email = signUpLogin.getText().toString().trim();
        String password = signUpPassword.getText().toString().trim();

        if (TextUtils.isEmpty(email)){
            Toast.makeText(SignUp.this, "Please enter your login", Toast.LENGTH_SHORT).show();
            return;
        }

        if (TextUtils.isEmpty(password)){
            Toast.makeText(SignUp.this, "Please enter your password", Toast.LENGTH_SHORT).show();
            return;
        }

        progressDialog.setMessage("Login please wait ...");
        progressDialog.show();

        firebaseAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(SignUp.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            finish();
                            startActivity(new Intent(getApplicationContext(), Home.class));
                        }
                        progressDialog.dismiss();
                    }
                });
    }

    private void launchSignInActivity(){
        finish();
        this.startActivity(new Intent(SignUp.this, SignIn.class));
    }
}
