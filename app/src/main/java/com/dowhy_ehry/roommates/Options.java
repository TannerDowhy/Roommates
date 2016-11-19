package com.dowhy_ehry.roommates;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Options extends AppCompatActivity {

    private final String ALPHABET = "abcdefghijklmnopqrstuvwxyz";
    private String HomeCode = "999";

    private DatabaseReference root = FirebaseDatabase.getInstance().getReference().getRoot();
    private Button add_room;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_options);

        add_room = (Button) findViewById(R.id.btn_add_room);

        add_room.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String room_name = genHomeCode();
                Map<String,Object> map = new HashMap<String, Object>();
                map.put(room_name, "");
                root.updateChildren(map);
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

    public String genHomeCode() {
        String code = "";
        int i = 0;
        int alpha = 0;
        int numeric = 0;

        do {
            int decision = (Math.random() <= 0.5) ? 1 : 2;

            if(alpha >= 3) {
                Random rand = new Random();
                int n = rand.nextInt(9) + 0;
                code = code + Integer.toString(n);
            }else if(numeric >= 3){
                Random rand = new Random();
                int  n = rand.nextInt(26) + 1;
                code = code + ALPHABET.charAt(n);

            }else{
                if (decision == 1) {
                    Random rand = new Random();
                    int  n = rand.nextInt(26) + 1;
                    code = code + ALPHABET.charAt(n);
                    alpha++;
                } else if (decision == 2) {
                    Random rand = new Random();
                    int  n = rand.nextInt(9) + 0;
                    code = code + Integer.toString(n);
                    numeric++;
                } else {
                    System.out.println("DANGER WILL ROBINSON: WE HAVE AN ERROR");
                }
            }



            i++;
        }while(i != 6);

        return code;

    }



}
