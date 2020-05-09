package com.example.akroc.edversity;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Vibrator;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class AlphaAdapter extends PagerAdapter {

    Context context;
    LayoutInflater layoutInflater;


    public AlphaAdapter(Context context) {
        this.context = context;
    }

    public int[] slideImages={
            R.mipmap.one,
            R.mipmap.two,
            R.mipmap.three,
            R.mipmap.four,
            R.mipmap.five,
            R.mipmap.six,
            R.mipmap.seven,
            R.mipmap.eight,
            R.mipmap.nine,
            R.mipmap.zero,
            R.mipmap.letter_a,
            R.mipmap.letter_b,
            R.mipmap.letter_c,
            R.mipmap.letter_d,
            R.mipmap.letter_e,
            R.mipmap.letter_f,
            R.mipmap.letter_g,
            R.mipmap.letter_h,
            R.mipmap.letter_i,
            R.mipmap.letter_j,
            R.mipmap.letter_k,
            R.mipmap.letter_l
    };

    public String[] slideHead={

            "One","Two","Three","Four","Five","Six","Seven","Eight","Nine","Zero"
            ,"Letter A","Letter B","Letter C","Letter D","Letter E","Letter F","Letter G","Letter H","Letter I","Letter J","Letter K","Letter L"
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
        View view= layoutInflater.inflate(R.layout.alpha_layout,container,false);
        final ImageView slideIV= (ImageView) view.findViewById(R.id.imagearray);
        TextView slideTV= (TextView) view.findViewById(R.id.letterarray);

        slideIV.setImageResource(slideImages[position]);
        slideTV.setText(slideHead[position]);
        container.addView(view);
        slideIV.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int x = (int)event.getX();
                int y = (int)event.getY();
                slideIV.buildDrawingCache();
                Bitmap bmap = slideIV.getDrawingCache();
                if(y<bmap.getHeight()&&x<bmap.getWidth()&&x>=0&&y>=0)
                {if(bmap.getPixel(x,y)==-16777216){
                    try {
                        final Vibrator vibe = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
                        vibe.vibrate(10);
                    }
                    catch (Exception e)
                    {}
                    return true;}}
                return false;
            }
        });
        return view;

    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((RelativeLayout) object);
    }
}
