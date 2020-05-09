package com.example.akroc.edversity;

import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class LearnFruandVeg extends AppCompatActivity {

    private Button test;
    private static final String TAG = "MyActivity";
    private ViewPager mvp;
    private ProduceAdapter adapter;
    int CurrentPage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_learn_fruand_veg);
        mvp= (ViewPager) findViewById(R.id.vp10);
        adapter = new ProduceAdapter(this);
        mvp.setAdapter(adapter);
        mvp.addOnPageChangeListener(viewListener);

        test = (Button) findViewById(R.id.producetest);
        test.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LearnFruandVeg.this,ProduceTest.class));
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
