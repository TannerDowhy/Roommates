package com.dowhy_ehry.roommates;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class OutstandingBills extends AppCompatActivity {

    private ImageButton close_btn;
    private ImageButton add_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_outstanding__bills);

        close_btn = (ImageButton) findViewById(R.id.delete_btn);
        add_btn =  (ImageButton) findViewById(R.id.add_btn);

        close_btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                finish(); //End Activity
            }
        });
    }
}
