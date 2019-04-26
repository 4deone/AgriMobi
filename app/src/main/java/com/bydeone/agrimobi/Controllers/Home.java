package com.bydeone.agrimobi.Controllers;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.bydeone.agrimobi.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Home extends AppCompatActivity implements View.OnClickListener {

    private FirebaseAuth firebaseAuth;
    private FirebaseUser firebaseUser;

    private TextView tvHome;
    private Button btLogOff;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        firebaseAuth = FirebaseAuth.getInstance();

        if (firebaseAuth.getCurrentUser() == null){
            //
            finish();
            startActivity(new Intent(getApplicationContext(), SignUp.class));
        }

        firebaseUser = firebaseAuth.getCurrentUser();

        //
        tvHome = (TextView) findViewById(R.id.tvHome);
        btLogOff = (Button) findViewById(R.id.btLogOff);

        btLogOff.setOnClickListener(this);
        // Set Text
        tvHome.setText("Bienvenue sur KofiSa, " + firebaseUser.getEmail());

    }

    @Override
    public void onClick(View v) {
        if (v == btLogOff){
            firebaseAuth.signOut();
            finish();
            startActivity(new Intent(this, SignUp.class));
        }
    }
}
