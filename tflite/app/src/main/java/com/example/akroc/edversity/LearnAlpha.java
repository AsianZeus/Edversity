package com.example.akroc.edversity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Matrix;
import android.os.Vibrator;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.lyft.android.scissors.CropView;

import java.io.ByteArrayOutputStream;

public class LearnAlpha extends AppCompatActivity {

    private ImageView imgx;
    private Button test;
    private static final String TAG = "MyActivity";
    private ViewPager mvp;
    private AlphaAdapter adapter;
    int CurrentPage;


    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_learn_alpha);
        mvp= (ViewPager) findViewById(R.id.vp4);
        adapter = new AlphaAdapter(this);
        mvp.setAdapter(adapter);
        mvp.addOnPageChangeListener(viewListener);

        test = (Button) findViewById(R.id.alphatest);
        test.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LearnAlpha.this,AlphaTest.class));
            }
        });


    }

    ViewPager.OnPageChangeListener viewListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position)
        {
            CurrentPage=position;
        }


        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };

}

