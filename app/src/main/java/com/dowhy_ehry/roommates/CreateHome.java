package com.dowhy_ehry.roommates;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Random;

public class CreateHome extends AppCompatActivity {
    private DatabaseReference db_ref = FirebaseDatabase.getInstance().getReference();
    private FirebaseAuth mAuth;
    private String mRoomID;
    private String homeCode = "999";
    DataSnapshot ds;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_home);
        mAuth = FirebaseAuth.getInstance();
        init();
    }

    public void init(){
        mRoomID = mAuth.getCurrentUser().getUid();
        db_ref.child(mRoomID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                homeCode = (String)dataSnapshot.getKey();
                ((TextView)findViewById(R.id.HomeCodeTxt)).setText(homeCode);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public void SmsShare(View v){

        String SMSBodyText = "Hello! You've been invited to join a Home in Roommates! Please enter the code below to join your new roommates.\n" + homeCode;

        Intent message = new Intent( Intent.ACTION_VIEW, Uri.parse( "sms:" + "" ) );
        message.putExtra( "sms_body", SMSBodyText );
        startActivity(message);
    }

    public void goToChat(View view){
        Intent intent = new Intent(this, Chat.class);
        startActivity(intent);
    }




}

