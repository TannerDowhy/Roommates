package com.dowhy_ehry.roommates;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Random;

public class CreateHome extends AppCompatActivity {
    private DatabaseReference root = FirebaseDatabase.getInstance().getReference().getRoot();
    private String HomeCode = "999";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_home);
        init();

    }

    public void init(){
//        HomeCode =
//        ((TextView)findViewById(R.id.HomeCodeTxt)).setText(HomeCode);
    }

    public void SmsShare(View v){

        String SMSBodyText = "Hello! You've been invited to join a Home in Roommates! Please enter the code below to join your new roommates.\n" + HomeCode;

        Intent message = new Intent( Intent.ACTION_VIEW, Uri.parse( "sms:" + "" ) );
        message.putExtra( "sms_body", SMSBodyText );
        startActivity(message);
    }

    public void goToChat(View view){
        Intent intent = new Intent(this, Chat.class);
        startActivity(intent);
    }




}

