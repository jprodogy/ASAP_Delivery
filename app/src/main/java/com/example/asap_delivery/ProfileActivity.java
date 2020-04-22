package com.example.asap_delivery;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ProfileActivity extends AppCompatActivity implements View.OnClickListener {
    private static FirebaseUser currentUser;
    private static final String TAG = "RealtimeDB";
    private FirebaseDatabase database;
    private DatabaseReference dbRef;

    private TextView userText;
    private ImageView userPicture;

    private EditText nameText;
    private EditText phoneText;
    private EditText emailText;
    private EditText seatText;
    private EditText cardText;
    private Button profileBtn;
    private int switcher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        currentUser = FirebaseAuth.getInstance().getCurrentUser();
        database = FirebaseDatabase.getInstance();
        dbRef = database.getReference("/Data3");
        dbRef.addValueEventListener(changeListener);

        userText = findViewById(R.id.username_text);
        userPicture = findViewById(R.id.user_picture);
        nameText = findViewById(R.id.name_text);
        phoneText = findViewById(R.id.phone_text);
        emailText = findViewById(R.id.email_text);
        seatText = findViewById(R.id.seat_num_text);
        cardText = findViewById(R.id.card_text);
        profileBtn = findViewById(R.id.edit_profile_btn);
        profileBtn.setOnClickListener(this);
        switcher = 0;

    }

    @Override
    protected void onStart() {
        super.onStart();
        userText.setText(currentUser.getEmail());
    }

    public void SelectFood(View v){
        Intent intent = new Intent(this, FoodMenuActivity.class);
        startActivity(intent);
    }


    public void EditProfile(){

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

    public void EditUser(){


    }

    ValueEventListener changeListener = new ValueEventListener() {

        @Override
        public void onDataChange(DataSnapshot dataSnapshot) {

            String name = dataSnapshot.child(currentUser.getUid()).child("Name").getValue(String.class);
            String phoneNum = dataSnapshot.child(currentUser.getUid()).child("Phone Number").getValue(String.class);
            String email = dataSnapshot.child(currentUser.getUid()).child("Email").getValue(String.class);
            String seatNum = dataSnapshot.child(currentUser.getUid()).child("Seat Number").getValue(String.class);
            String cardInfo = dataSnapshot.child(currentUser.getUid()).child("Card Info").getValue(String.class);

            nameText.setText(name);
            phoneText.setText(phoneNum);
            emailText.setText(email);
            seatText.setText(seatNum);
            cardText.setText(cardInfo);
        }

        @Override
        public void onCancelled(DatabaseError databaseError) {
            notifyUser("Database error: " + databaseError.toException());
        }



    };

    public void saveData() {
        dbRef.child(currentUser.getUid()).child("Name").setValue(nameText.getText().toString(), completionListener);
        dbRef.child(currentUser.getUid()).child("Phone Number").setValue(phoneText.getText().toString(), completionListener);
        dbRef.child(currentUser.getUid()).child("Email").setValue(emailText.getText().toString(), completionListener);
        dbRef.child(currentUser.getUid()).child("Seat Number").setValue(seatText.getText().toString(), completionListener);
        dbRef.child(currentUser.getUid()).child("Card Info").setValue(cardText.getText().toString(), completionListener);


        nameText.setEnabled(false);
        nameText.setClickable(false);
        phoneText.setEnabled(false);
        phoneText.setClickable(false);
        emailText.setEnabled(false);
        emailText.setClickable(false);
        seatText.setEnabled(false);
        seatText.setClickable(false);
        cardText.setEnabled(false);
        cardText.setClickable(false);
    }

    DatabaseReference.CompletionListener completionListener =
            new DatabaseReference.CompletionListener() {
                @Override
                public void onComplete(DatabaseError databaseError,
                                       DatabaseReference databaseReference) {

                    if (databaseError != null) {
                        notifyUser(databaseError.getMessage());
                    }
                }
            };

    private void notifyUser(String message) {
        Toast.makeText(ProfileActivity.this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View view) {
        if (switcher == 0){
            switcher = 1;
        }else{
            switcher = 0;
        }
        switch (switcher) {
            case 0:
                profileBtn.setText("Edit Profile");
                saveData();
                break;
            case 1:
                profileBtn.setText("Save");
                EditProfile();
                break;
        }



    }
}
