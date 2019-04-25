package com.bydeone.agrimobi.Controllers;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.bydeone.agrimobi.R;

public class MainActivity extends AppCompatActivity {

    private Button connexionKofisa;
    private Button connexionGoogle;
    private Button connexionFacebook;
    private ImageView detailKofisa;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.configureDetailKofisa();
        this.configureConnexionKofisa();
        this.configureConnexionGoogle();
        this.configureConnexionFacebook();
    }

    // ----

    private void configureConnexionKofisa(){
        // Serialise ImageView
        this.connexionKofisa = (Button) this.findViewById(R.id.KofisaConnexion);
        // Set OnClick Listener on it
        connexionKofisa.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                //Launch Detail Activity
                launchSignUpActivity();
            }
        });
    }

    private void configureConnexionGoogle(){
        // Serialise ImageView
        this.connexionGoogle = (Button) this.findViewById(R.id.GoogleConnexion);
        // Set OnClick Listener on it
        connexionGoogle.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                //Launch Detail Activity
                //launchDetailActivity();
            }
        });
    }

    private void configureConnexionFacebook(){
        // Serialise ImageView
        this.connexionFacebook = (Button) this.findViewById(R.id.FacebookConnexion);
        // Set OnClick Listener on it
        connexionFacebook.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                //Launch Detail Activity
                //launchDetailActivity();
            }
        });
    }

    private void configureDetailKofisa(){
        // Serialise ImageView
        this.detailKofisa = (ImageView) this.findViewById(R.id.imageView);
        // Set OnClick Listener on it
        detailKofisa.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                //Launch Detail Activity
                launchDetailActivity();
            }
        });
    }

    // ----

    private void launchDetailActivity(){
        Intent myIntent = new Intent(MainActivity.this, Detail.class);
        this.startActivity(myIntent);
    }

    private void launchSignUpActivity(){
        Intent myIntent = new Intent(MainActivity.this, SignUp.class);
        this.startActivity(myIntent);
    }

}
