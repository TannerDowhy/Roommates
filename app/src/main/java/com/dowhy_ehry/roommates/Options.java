package com.dowhy_ehry.roommates;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Options extends AppCompatActivity {

    private final String ALPHABET = "abcdefghijklmnopqrstuvwxyz";
    private String HomeCode = "999";

    private DatabaseReference db_ref = FirebaseDatabase.getInstance().getReference().getRoot();
    private Button add_room;

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

        add_room.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Room room = new Room();
                Roommate roommate = new Roommate(room, mAuth.getCurrentUser().getPhotoUrl()
                        .toString(), mAuth.getCurrentUser().getDisplayName());
                Map<String,Object> map = new HashMap<String, Object>();
                Map<String,Object> map2 = new HashMap<String, Object>();
                map2.put("Occupant"+room.getOccupants(), roommate.getDisplayName());
                db_ref.child("room").child(mRoomID).updateChildren(map);
                db_ref.child("room").child(mRoomID).updateChildren(map2);

                goToCreateHome();
            }
        });
    }

    public void goToCreateHome() {
        Intent intent = new Intent(this,CreateHome.class);
        startActivity(intent);
    }

    public void goToJoinHome(View view){
        Intent intent = new Intent(this, JoinHome.class);
        startActivity(intent);
    }
}
