package com.dowhy_ehry.roommates;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.firebase.ui.auth.AuthUI;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Login extends AppCompatActivity implements View.OnClickListener {

    private static final int RC_SIGN_IN = 0;

    private FirebaseAuth mAuth;
//    public String email;
    private DatabaseReference db_ref = FirebaseDatabase.getInstance().getReference().getRoot();
//    private boolean r_value;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mAuth = FirebaseAuth.getInstance();
        if(mAuth.getCurrentUser() != null) {
            //user already signed in
            Log.d("AUTH", mAuth.getCurrentUser().getEmail());
        }
        else {
            startActivityForResult(AuthUI.getInstance()
                    .createSignInIntentBuilder()
                    .setProviders(
                            AuthUI.FACEBOOK_PROVIDER,
                            AuthUI.GOOGLE_PROVIDER)
                    .build(), RC_SIGN_IN);
        }


        findViewById(R.id.sign_out_button).setOnClickListener(this);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == RC_SIGN_IN) {
            if(resultCode == RESULT_OK) {
                //user logged in
                Log.d("AUTH", mAuth.getCurrentUser().getEmail());

//                email = mAuth.getCurrentUser().getEmail();
//                if (doesExist(email)) {
//                    goToOptions();
//                }
//                else {
//                    Person person = new Person(null, mAuth.getCurrentUser().getPhotoUrl()
//                            .toString(), mAuth.getCurrentUser().getDisplayName(), mAuth.getCurrentUser().getEmail());
//                    Map<String, Object> map = new HashMap<String, Object>();
//                    map.put("Person" + genID(), person.getEmail());
//                    db_ref.child("Person").updateChildren(map);
//                }
            }
            else {
                //user not authenticated
                Log.d("AUTH", "User not authenticated.");
            }

            //Code executes once logged in. Changes view to Options view.
            goToOptions();

        }
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.sign_out_button) {
            AuthUI.getInstance()
                    .signOut(this)
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            Log.d("AUTH", "USER LOGGED OUT.");
                            reLoginIn();
                        }
                    });
        }
    }

    public void reLoginIn(){
        Intent intent = new Intent(this,Login.class);
        startActivity(intent);
    }

    public void goToOptions(){
        Intent intent = new Intent(this, Options.class);
        startActivity(intent);
    }

    public int genID() {
        Random rnd = new Random();
        int n = 10000 + rnd.nextInt(90000);
        return n;
    }

//    public boolean doesExist(final String email) {
//        FirebaseDatabase.getInstance().getReference().child("Person")
//                .addListenerForSingleValueEvent(new ValueEventListener() {
//                    @Override
//                    public void onDataChange(DataSnapshot dataSnapshot) {
//                        for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
//                            String thisss = (String)snapshot.getValue();
//                            if(thisss != email) {
//                                r_value=false;
//                                continue;
//                            }
//                            else if (thisss == email){
//                                if(person.getCurrRoom() != null) {
//                                    r_value=true;
//                                    break;
//                                }
//                                else {
//                                    r_value=false;
//                                    break;
//                                }
//                            }
//                        }
//                    }
//                    @Override
//                    public void onCancelled(DatabaseError databaseError) {
//                    }
//                });
//        return r_value;
//    }
}