package com.example.akroc.edversity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Vibrator;
import android.support.annotation.NonNull;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;

public class slidewalk extends AppCompatActivity {

    private ViewPager mvp;
    private LinearLayout dotslay;
    private SlideAdapter adapter;
    private TextView[] mDots;
    LottieAnimationView butt;
    LottieAnimationView lav;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // remove title

        FirebaseApp.initializeApp(this);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_slidewalk);

        mAuth = FirebaseAuth.getInstance();
        mAuthListener= new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                if(firebaseAuth.getCurrentUser()!= null)
                {
                    finish();
                    startActivity(new Intent(slidewalk.this,Dashboard.class));
                }
            }
        };
        //finish();
        //startActivity(new Intent(slidewalk.this,Dashboard.class));
        lav= (LottieAnimationView) findViewById(R.id.lav);
        dotslay=(LinearLayout) findViewById(R.id.dotslayout);
        mvp= (ViewPager) findViewById(R.id.vp1);
        adapter = new SlideAdapter(this);
        mvp.setAdapter(adapter);
        addDotsIndicator(0);
        mvp.addOnPageChangeListener(viewListener);
        lav.setScale(0.5f);
        butt=  (LottieAnimationView) findViewById(R.id.next);
        butt.setSpeed(2);
        butt.setVisibility(View.INVISIBLE);

        final Vibrator vibe = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE) ;
        butt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vibe.vibrate(35);
                Intent intentx= new Intent(v.getContext(),MainActivity.class);
                startActivity(intentx);
                finish();
            }
        });
    }


    public void addDotsIndicator(int position)
    {
        mDots = new TextView[3];
        dotslay.removeAllViews();
        for(int i= 0; i<mDots.length; i++)
        {
            mDots[i]= new TextView(this);
            mDots[i].setText(Html.fromHtml("&#8226"));
            mDots[i].setTextSize(35);
            mDots[i].setTextColor(getResources().getColor(R.color.grey, getTheme()));

            dotslay.addView(mDots[i]);
        }

        if(mDots.length>0)
        {
            mDots[position].setTextColor(getResources().getColor(R.color.white, getTheme()));
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }



    ViewPager.OnPageChangeListener viewListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            addDotsIndicator(position);
            if(position==2)
                butt.setVisibility(View.VISIBLE);
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };
}