package com.example.asap_delivery;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

public class ProfileActivity extends AppCompatActivity {
    private EditText nameText;
    private EditText phoneText;
    private EditText emailText;
    private EditText seatText;
    private EditText cardText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        nameText = findViewById(R.id.name_text);
        phoneText = findViewById(R.id.phone_text);
        emailText = findViewById(R.id.email_text);
        seatText = findViewById(R.id.seat_num_text);
        cardText = findViewById(R.id.card_text);


    }

    public void SelectFood(View v){
        Intent intent = new Intent(this, MenuActivity.class);
        startActivity(intent);
    }

    public void EditProfile(View v){

        nameText.setEnabled(true);
        nameText.setClickable(true);
        phoneText.setEnabled(true);
        phoneText.setClickable(true);
        emailText.setEnabled(true);
        emailText.setClickable(true);
        seatText.setEnabled(true);
        seatText.setClickable(true);
        cardText.setEnabled(true);
        cardText.setClickable(true);


    }

}
