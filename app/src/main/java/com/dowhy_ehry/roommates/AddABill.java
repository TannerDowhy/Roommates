package com.dowhy_ehry.roommates;

import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class AddABill extends AppCompatActivity {

    private ImageButton close_btn;
    private ImageButton add_btn;
    private ImageButton addPic_btn;
    private Bills newBill;
    private int i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_abill);

        close_btn = (ImageButton) findViewById(R.id.delete_btn);
        add_btn =  (ImageButton) findViewById(R.id.add_btn);
        addPic_btn = (ImageButton) findViewById(R.id.add_image_btn);

        i = 0;

        close_btn.setOnClickListener(new View.OnClickListener(){
            @Override
          public void onClick(View view) {
                finish(); //End Activity
            }
        });



        add_btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
               //TODO
            }
        });





//        add_room.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Room room = new Room();
//                Roommate roommate = new Roommate(room, mAuth.getCurrentUser().getPhotoUrl()
//                        .toString(), mAuth.getCurrentUser().getDisplayName());
//                Map<String,Object> map = new HashMap<String, Object>();
//                Map<String,Object> map2 = new HashMap<String, Object>();
//                map2.put("Occupant"+room.getOccupants(), roommate.getDisplayName());
//                db_ref.child("room").child(mRoomID).updateChildren(map);
//                db_ref.child("room").child(mRoomID).updateChildren(map2);
//
//                goToDashboard();
//            }
//        });


    }
}
