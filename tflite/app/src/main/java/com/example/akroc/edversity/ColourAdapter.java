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

public class ColourAdapter extends PagerAdapter {
    Context context;
    LayoutInflater layoutInflater;


    public ColourAdapter(Context context) {
        this.context = context;
    }

    public int[] slideImages={
            R.mipmap.red_color,
            R.mipmap.blue_color,
            R.mipmap.green_color,
            R.mipmap.yellow_color,
            R.mipmap.brown_color,
            R.mipmap.pink_color,
            R.mipmap.purple_color,
            R.mipmap.white_color,
            R.mipmap.black_color
    };

    public String[] slideHead={

            "Red Colour",
            "Blue Colour",
            "Green Colour",
            "Yellow Colour",
            "Brown Colour",
            "Pink Colour",
            "Purple Colour",
            "White Colour",
            "Black Colour"
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
        View view= layoutInflater.inflate(R.layout.colours_layout,container,false);
        ImageView slideIV= (ImageView) view.findViewById(R.id.colourimage);
        TextView slideTV= (TextView) view.findViewById(R.id.colourtext);

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
