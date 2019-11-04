package com.Yam.ESwim;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.Yam.R;
import com.google.firebase.auth.FirebaseAuth;

public class MainMenu extends AppCompatActivity {
    private CardView profile ;
    private CardView chatbot;
    private CardView aboutus;
    private CardView booking;
    private Button signOut;
    private FirebaseAuth auth;
    Intent i ;
    LinearLayout ll;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main_menu);
        ll = (LinearLayout) findViewById(R.id.ll);
        chatbot = (CardView) findViewById(R.id.chatbot);
        aboutus = (CardView) findViewById(R.id.aboutus);
        booking = (CardView) findViewById(R.id.booking);
        signOut = (Button) findViewById(R.id.signOut);
        auth = FirebaseAuth.getInstance();
        profile = (CardView) findViewById(R.id.profile);

        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainMenu.this, Main2.class));
            }
        });
        chatbot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainMenu.this, ChatBot.class));
            }
        });

        aboutus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainMenu.this, AboutUs.class));
            }
        });

        booking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainMenu.this, MainActivity2.class));
            }
        });

        signOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(MainMenu.this, LoginActivity.class);

                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);//makesure user cant go back
                startActivity(intent);
            }
        });


    }

}
