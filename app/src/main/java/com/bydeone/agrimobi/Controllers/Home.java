package com.bydeone.agrimobi.Controllers;

import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.bydeone.agrimobi.Models.AllFragmentAdapter;
import com.bydeone.agrimobi.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Home extends AppCompatActivity {


    private TextView userName;
    private ViewPager viewPager;
    private AllFragmentAdapter allFragmentAdapter;

    private FirebaseAuth firebaseAuth;
    private FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        this.configureToolbar();

        firebaseAuth = FirebaseAuth.getInstance();

        if (firebaseAuth.getCurrentUser() == null){
            //
            finish();
            startActivity(new Intent(getApplicationContext(), SignUp.class));
        }

        user = firebaseAuth.getCurrentUser();

        this.configureUserName();
        this.configureViewPager();
        this.configureAllFragmentAdapter();


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //2 - Inflate the menu and add it to the Toolbar
        getMenuInflater().inflate(R.menu.menu_activity_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //3 - Handle actions on menu items
        switch (item.getItemId()) {
            case R.id.menu_activity_main_params:
                Toast.makeText(this, "Il n'y a rien à paramétrer ici, passez votre chemin...", Toast.LENGTH_LONG).show();
                return true;
            case R.id.menu_activity_main_search:
                Toast.makeText(this, "Recherche indisponible, demandez plutôt l'avis de Google, c'est mieux et plus rapide.", Toast.LENGTH_LONG).show();
                return true;
            case R.id.menu_activity_main_profile:
                finish();
                startActivity(new Intent(this, MonProfile.class));
                return true;
            case R.id.menu_activity_main_logOut:
                firebaseAuth.signOut();
                finish();
                startActivity(new Intent(Home.this, SignUp.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void configureToolbar(){
        // Get the toolbar view inside the activity layout
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        // Set the Toolbar
        setSupportActionBar(toolbar);
    }

    private void configureUserName(){
        // Serialise ImageView
        this.userName = (TextView) this.findViewById(R.id.goToSignIn);
        // Set Text
        userName.setText("Bienvenue sur KofiSa, " + user.getEmail());
    }

    private void configureViewPager(){
        // Serialise ImageView
        this.viewPager = this.findViewById(R.id.homeViewPager);
    }

    private void configureAllFragmentAdapter(){
        // Serialise ImageView
        this.allFragmentAdapter = new AllFragmentAdapter(getSupportFragmentManager());
        viewPager.setAdapter(allFragmentAdapter);
    }
}
