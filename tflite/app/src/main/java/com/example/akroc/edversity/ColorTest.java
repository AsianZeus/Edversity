package com.example.akroc.edversity;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;


public class ColorTest extends AppCompatActivity {
    private ViewPager mvp;
    private ColorTestAdapter adapter;
    int CurrentPage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_color_test);

        mvp= (ViewPager) findViewById(R.id.vp8);
        adapter = new ColorTestAdapter(this);
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
            if(position<CurrentPage)
            {}
            else
            CurrentPage=position;

            mvp.setCurrentItem(CurrentPage);
        }


        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };


}
