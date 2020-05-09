package com.example.akroc.edversity;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class ProduceTest extends AppCompatActivity {

    private ViewPager mvp;
    private ProduceTestAdapter adapter;
    int CurrentPage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_produce_test);


        mvp= (ViewPager) findViewById(R.id.vp11);
        adapter = new ProduceTestAdapter(this);
        mvp.setAdapter(adapter);
        mvp.addOnPageChangeListener(viewListener);


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
