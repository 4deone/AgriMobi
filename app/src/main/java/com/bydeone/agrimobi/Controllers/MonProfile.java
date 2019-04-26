package com.bydeone.agrimobi.Controllers;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.bydeone.agrimobi.Models.User;
import com.bydeone.agrimobi.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MonProfile extends AppCompatActivity {

    private EditText userName;
    private EditText userPhone;
    private EditText userQuartier;

    private Button saveProfile;
    private Button cancelProfile;

    private FirebaseAuth firebaseAuth;

    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mon_profile);

        firebaseAuth = FirebaseAuth.getInstance();

        if(firebaseAuth.getCurrentUser() == null){
            finish();
            startActivity(new Intent(this, SignUp.class));
        }

        databaseReference = FirebaseDatabase.getInstance().getReference();

        FirebaseUser user = firebaseAuth.getCurrentUser();

        this.configureAllViews();
        this.configureSaveProfile();

    }

    private void configureAllViews(){
        // Serialise ImageView
        this.userName = (EditText) this.findViewById(R.id.edtUserName);
        this.userPhone = (EditText) this.findViewById(R.id.edtPhoneNumber);
        this.userQuartier = (EditText) this.findViewById(R.id.edtQuartier);

        this.cancelProfile = (Button) this.findViewById(R.id.cancelMyProfile);
    }

    private void configureSaveProfile(){
        // Serialise ImageView
        this.saveProfile = (Button) this.findViewById(R.id.saveMyProfile);
        // Set OnClick Listener on it
        saveProfile.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                //Launch Detail Activity
                saveUserInformation();
            }
        });
    }

    private void configureCancelProfile(){
        // Serialise ImageView
        this.cancelProfile = (Button) this.findViewById(R.id.cancelMyProfile);
        // Set OnClick Listener on it
        cancelProfile.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                finish();
                startActivity(new Intent(MonProfile.this, Home.class));
            }
        });
    }

    private void saveUserInformation(){
        String name = userName.getText().toString().trim();
        String phone = userPhone.getText().toString().trim();
        String address = userQuartier.getText().toString().trim();

        User user = new User(name, phone, address);

        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();

        databaseReference.child(firebaseUser.getUid()).setValue(user);

        Toast.makeText(this, "Information Save...", Toast.LENGTH_LONG).show();

    }
}
