package com.example.akroc.edversity;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class PronounceAdapter extends PagerAdapter {

    Context context;
    LayoutInflater layoutInflater;


    public PronounceAdapter(Context context) {
        this.context = context;
    }

    public int[] slideImages={
            R.mipmap.a_for_apple,
            R.mipmap.b_for_ball,
            R.mipmap.c_for_cat
    };

    public String[] slideHead={

            "A for Apple","B for Ball","C for Cat"
    };
    @Override
    public int getCount() {
        return slideHead.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view==(RelativeLayout) object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        layoutInflater=(LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view= layoutInflater.inflate(R.layout.pronounce_layout,container,false);
        ImageView slideIV= (ImageView) view.findViewById(R.id.imageView3);
        TextView slideTV= (TextView) view.findViewById(R.id.alphaarray);

        slideIV.setImageResource(slideImages[position]);
        slideTV.setText(slideHead[position]);
        container.addView(view);

        return view;

    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((RelativeLayout) object);
    }
}
