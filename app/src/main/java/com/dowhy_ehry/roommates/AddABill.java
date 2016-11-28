package com.dowhy_ehry.roommates;

import android.content.Intent;
import android.graphics.Bitmap;
import android.media.Image;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;

import java.io.File;

public class AddABill extends AppCompatActivity {

    private ImageButton close_btn;
    private ImageButton add_btn;
    private ImageButton addPic_btn;
    private Bills newBill;
    private ImageView picTaken;
    private static final int CAM_REQUEST = 1313;
    private EditText bDesc;
    private EditText bAmount;
    private String bSplitTxt;
    private boolean bSplit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_abill);

        close_btn = (ImageButton) findViewById(R.id.delete_btn);
        add_btn =  (ImageButton) findViewById(R.id.add_btn);
        //addPic_btn = (ImageButton) findViewById(R.id.add_image_btn);

        bDesc = (EditText) findViewById(R.id.descr_txt);

        bAmount = (EditText) findViewById(R.id.amount_txt);


        final Spinner spinnerSplit = (Spinner) findViewById(R.id.bill_spinner);


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
                String bDescTxt = bDesc.getText().toString();
                String bAmountTxt = bAmount.getText().toString();
                float bAmountNum = Float.parseFloat(bAmountTxt);
                bSplitTxt = spinnerSplit.getSelectedItem().toString();


                //String nName, float nAmount, boolean b
                if (bSplitTxt.equals("All Roommates")){
                    bSplit = true;
                }else{
                    //No One
                    bSplit = false;
                }

                newBill = new Bills(bDescTxt, bAmountNum, bSplit);
                finish();
            }
        });

//        addPic_btn.setOnClickListener(new View.OnClickListener(){
//            @Override
//            public void onClick(View view) {
//                //TODO
//
//            }
//        });


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == CAM_REQUEST){
            Bitmap thumbnail = (Bitmap) data.getExtras().get("data");
            picTaken.setImageBitmap(thumbnail);
        }
    }
}
