package com.dowhy_ehry.roommates;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;

import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;


public class Dashboard extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {


    private Button continue_btn;
    //Signing Options
    private GoogleSignInOptions gso;

    //google api client
    private GoogleApiClient mGoogleApiClient;
    private static final int RC_SIGN_IN = 0;
    private Button move_out_btn;
    private Button log_out_btn;
    private FirebaseAuth mAuth;
    private FirebaseAuth mAuthListener;
    private FirebaseUser mFirebaseUser;

    private String userTxt = "test";
    private String userEmailTxt = "Test";
    private String picURLTxt;
    private ImageView profilePic;
    private Bitmap bitmap;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        mAuth = FirebaseAuth.getInstance();
        mFirebaseUser = mAuth.getCurrentUser();


        log_out_btn = (Button) findViewById(R.id.action_logOut);
        move_out_btn = (Button) findViewById(R.id.action_moveOut);

    }

    public void changeUserInfo(){

        TextView header = (TextView) findViewById(R.id.headerTxt);
        TextView subHeader = (TextView) findViewById(R.id.subHeaderTxt);
        profilePic = (ImageView) findViewById(R.id.user_pic);


        userTxt = mAuth.getCurrentUser().getDisplayName();
        userEmailTxt = mAuth.getCurrentUser().getEmail();
        picURLTxt = mAuth.getCurrentUser().getPhotoUrl().toString();

        bitmap = getBitmapFromURL(mAuth.getCurrentUser().getPhotoUrl().toString());

        profilePic.setImageBitmap(bitmap);

        System.out.println("THIS IS STUPID..... " + mAuth.getCurrentUser().getPhotoUrl() );


        header.setText(userEmailTxt);
        subHeader.setText(userTxt);


    }

    public Bitmap getBitmapFromURL(String src){

        try {
            URL url = new URL(src);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);
            connection.connect();
            InputStream input = connection.getInputStream();
            Bitmap myBitMap = BitmapFactory.decodeStream(input);
            return myBitMap;

        }catch (Exception e){

            e.printStackTrace();
            return null;

        }

    }



    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.dashboard, menu);
        changeUserInfo();
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.

        Intent intent;

        int id = item.getItemId();

        if (id == R.id.nav_dashboard) {
            // Handle the camera action
        } else if (id == R.id.nav_chat) {
            Bundle bundle = getIntent().getExtras();
            intent = new Intent(this,Chat.class);
            String newString = bundle.getString("room_name");
            intent.putExtra("room_name", newString);
            startActivity(intent);

        } else if (id == R.id.nav_addabill) {
            intent = new Intent(this,AddABill.class);
            startActivity(intent);

        } else if (id == R.id.nav_outstbalances) {
            intent = new Intent(this, OutstandingBills.class);
            startActivity(intent);

        } else if (id == R.id.nav_roommateagreement) {

        } else if (id == R.id.nav_roommates) {

        }



        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);



        return true;
    }
}
