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

public class SignUp extends AppCompatActivity implements View.OnClickListener {

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

        progressDialog = new ProgressDialog(this);
        signUpLogin = (EditText) this.findViewById(R.id.EdtLogin);
        signUpPassword = (EditText) this.findViewById(R.id.EdtPassword);
        goToSignIn = (TextView) this.findViewById(R.id.goToSignIn);
        goToHome = (Button) this.findViewById(R.id.goToHome);

        goToSignIn.setOnClickListener(this);
        goToHome.setOnClickListener(this);


    }

    // ----


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


    @Override
    public void onClick(View v) {
        if (v == goToHome){
            launchHomeActivity();
        }else if (v == goToSignIn){
            finish();
            this.startActivity(new Intent(SignUp.this, SignIn.class));
        }
    }
}
