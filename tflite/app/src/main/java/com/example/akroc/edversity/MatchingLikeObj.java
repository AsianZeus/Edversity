package com.example.akroc.edversity;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;

public class MatchingLikeObj extends AppCompatActivity {

    FragPageAdapter fragPageAdapter;
    private ViewPager mViewPager;
    ImageView wall;
    int CurrentPage=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_matching_like_obj);
        Toolbar toolbar = (Toolbar) findViewById(R.id.ptoolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null)
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbar.setTitle("Matching Like Objects");
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                startActivity(new Intent(getApplicationContext(),Dashboard.class));
            }
        });
        fragPageAdapter = new FragPageAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.vp12);
        mViewPager.addOnPageChangeListener(viewListener);
        setupViewPager(mViewPager);

        wall = (ImageView) findViewById(R.id.tabimage);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);
    }
    private void setupViewPager(ViewPager viewPager) {
        FragPageAdapter adapter = new FragPageAdapter(getSupportFragmentManager());
        adapter.addFragment(new Tab3_Fragment(), "Identical");
        adapter.addFragment(new Tab1_Fragment(), "Shape");
        adapter.addFragment(new Tab2_Fragment(), "Letter");
        viewPager.setAdapter(adapter);
    }


    ViewPager.OnPageChangeListener viewListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position)
        {
            CurrentPage=position;
            if(CurrentPage==0)
            {
                wall.setImageResource(R.mipmap.identicals);
            }
            else if(CurrentPage==1)
            {
                wall.setImageResource(R.mipmap.dshapes);
            }
            else if(CurrentPage==2)
            {
                wall.setImageResource(R.mipmap.letterspic);
            }
        }


        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };
}
