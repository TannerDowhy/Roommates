package com.dowhy_ehry.roommates;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.math.BigInteger;
import java.security.SecureRandom;

public class Options extends AppCompatActivity {

    private DatabaseReference db_ref = FirebaseDatabase.getInstance().getReference().getRoot();
    private Button add_room;
    private Button submit_room;
    private EditText input_msg;

    private FirebaseAuth mAuth;
    private FirebaseUser mFirebaseUser;
    private String mRoomID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_options);

        //mFirebaseUser = mAuth.getCurrentUser();
        mAuth = FirebaseAuth.getInstance();
        mRoomID = mAuth.getCurrentUser().getUid();
        add_room = (Button) findViewById(R.id.btn_add_room);
        submit_room = (Button) findViewById(R.id.submit_room);
        input_msg = (EditText) findViewById(R.id.in_join_room);

        add_room.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SecureRandom random = new SecureRandom();
                Room room = new Room();
                String n = new BigInteger(24, random).toString(32);
                Roommate roommate = new Roommate(n, mAuth.getCurrentUser().getPhotoUrl()
                        .toString(), mAuth.getCurrentUser().getDisplayName());
                Map<String,Object> map = new HashMap<String, Object>();
                Map<String,Object> map2 = new HashMap<String, Object>();
                map2.put("Occupant"+genID(), roommate.getDisplayName());
                db_ref.child("room").child(n).updateChildren(map);
                db_ref.child("room").child(n).updateChildren(map2);

                goToDashboard();
            }
        });

        submit_room.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String s = input_msg.getText().toString();
                Roommate roommate = new Roommate(s, mAuth.getCurrentUser().getPhotoUrl()
                        .toString(), mAuth.getCurrentUser().getDisplayName());
                Map<String,Object> map = new HashMap<String, Object>();
                map.put("Occupant"+genID(), roommate.getDisplayName());
                db_ref.child("room").child(s).updateChildren(map);
            }
        });
    }

    public void goToDashboard() {
        Intent intent = new Intent(this,Dashboard.class);
        startActivity(intent);
    }

    public void goToJoinHome(View view){
        Intent intent = new Intent(this, JoinHome.class);
        startActivity(intent);
    }

    public int genID() {
        Random rnd = new Random();
        int n = 10000 + rnd.nextInt(90000);
        return n;
    }
}
