package com.bydeone.agrimobi.Controllers;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bydeone.agrimobi.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignIn extends AppCompatActivity implements View.OnClickListener {

    private EditText signUpLogin;
    private EditText signUpPassword;
    private EditText signUpConfirmPassword;
    private CheckBox acceptCondition;
    private Button goToSignUp;

    private ProgressDialog progressDialog;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        firebaseAuth = FirebaseAuth.getInstance();

        signUpLogin = (EditText) this.findViewById(R.id.EdtLogin);
        signUpPassword = (EditText) this.findViewById(R.id.EdtPassword);
        signUpConfirmPassword = (EditText) this.findViewById(R.id.EdtConfirmPassword);
        acceptCondition = (CheckBox) this.findViewById(R.id.acceptConditions);
        goToSignUp = (Button) this.findViewById(R.id.goToSignUp);
        progressDialog = new ProgressDialog(this);
    }

    // ----

    private void configureAcceptCondition(){
        // Serialise ImageView
        this.
        // Set OnClick Listener on it
        acceptCondition.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                //Launch Detail Activity
                //launchSignInActivity();
            }
        });
    }

    // ----

    private void registreUser(){
        String email = signUpLogin.getText().toString().trim();
        String password = signUpPassword.getText().toString().trim();
        String confirmCassword = signUpConfirmPassword.getText().toString().trim();

        if (TextUtils.isEmpty(email)){
            Toast.makeText(SignIn.this, "Please enter your login", Toast.LENGTH_SHORT).show();
            return;
        }

        if (TextUtils.isEmpty(password)){
            Toast.makeText(SignIn.this, "Please enter your password", Toast.LENGTH_SHORT).show();
            return;
        }

        if (TextUtils.isEmpty(confirmCassword)){
            Toast.makeText(SignIn.this, "Please enter your Confirmation password", Toast.LENGTH_SHORT).show();
            return;
        }

        if (confirmCassword != password){
            Toast.makeText(SignIn.this, "Please pas les password ne sont pas identiques", Toast.LENGTH_SHORT).show();
            return;
        }

        progressDialog.setMessage("Register please wait ...");
        progressDialog.show();

        firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    finish();
                    startActivity(new Intent(getApplicationContext(), SignUp.class));
                }else {
                    Toast.makeText(SignIn.this, "Registered error", Toast.LENGTH_SHORT).show();
                }
                progressDialog.dismiss();
            }
        });
    }


    @Override
    public void onClick(View v) {
        if (v == goToSignUp){
            registreUser();
        }
    }
}
