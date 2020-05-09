package com.example.akroc.edversity;
import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.support.v7.widget.Toolbar;
import android.widget.GridLayout;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.core.Tag;
import com.google.firebase.firestore.FirebaseFirestore;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class Dashboard extends AppCompatActivity {

    List<Feature> lstFeature ;
    private static final int REQUEST_RECORD_AUDIO_PERMISSION = 200;
    private FirebaseAuth mAuth;
    private FirebaseUser user;
    private CircleImageView civ;
    private final String TAG= "photourl";
    private boolean permissionToRecordAccepted = false;
    private String [] permissions = {Manifest.permission.RECORD_AUDIO};



    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode){
            case REQUEST_RECORD_AUDIO_PERMISSION:
                permissionToRecordAccepted  = grantResults[0] == PackageManager.PERMISSION_GRANTED;
                break;
        }
        if (!permissionToRecordAccepted ) finish();

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        ActivityCompat.requestPermissions(this, permissions, REQUEST_RECORD_AUDIO_PERMISSION);
        mAuth = FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();
        civ= (CircleImageView) findViewById(R.id.cProfileview);
        Toolbar mToolbar= (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        RequestOptions placeholderOption= new RequestOptions();
        placeholderOption.placeholder(R.mipmap.usericon);
        if(user.getPhotoUrl()!=null) {
            Glide.with(this.getApplicationContext()).setDefaultRequestOptions(placeholderOption).load(user.getPhotoUrl().toString()).into(civ);
        }
        else
        {        civ.setImageResource(R.mipmap.usericon);}

        lstFeature = new ArrayList<>();
        lstFeature.add(new Feature("Learn Numbers and Alphabet",R.mipmap.abcico));
        lstFeature.add(new Feature("Learn Pronunciation",R.mipmap.pronico));
        lstFeature.add(new Feature("Learn Shapes",R.mipmap.shapeico));
        lstFeature.add(new Feature("Learn Colours",R.mipmap.colorico));
        lstFeature.add(new Feature("Learn Rhymes",R.mipmap.rhymesico));
        lstFeature.add(new Feature("Learn Animal Sounds",R.mipmap.animalico));
        lstFeature.add(new Feature("Learn Fruits and Vegetables",R.mipmap.fruitsico));
        lstFeature.add(new Feature("Matching Like Objects",R.mipmap.matchico));
        lstFeature.add(new Feature("Recognizing Live Objects",R.mipmap.objregico));

        RecyclerView myrv = (RecyclerView) findViewById(R.id.frecyclerid);
        RecyclerViewAdapter myAdapter = new RecyclerViewAdapter(this,lstFeature);
        GridLayoutManager gdm=new GridLayoutManager(this,2);
        myrv.setLayoutManager(gdm);
        myrv.setAdapter(myAdapter);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.dash_menu,menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==R.id.settings)
        {
            startActivity(new Intent(Dashboard.this,Settings.class));
        }
        if(item.getItemId()==R.id.logout)
        {
            FirebaseAuth.getInstance().signOut();
            finish();
            startActivity(new Intent(Dashboard.this,MainActivity.class));
        }

        return super.onOptionsItemSelected(item);
    }
}
