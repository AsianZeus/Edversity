package com.example.akroc.edversity;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Vibrator;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class ShapeAdapter extends PagerAdapter {

    Context context;
    LayoutInflater layoutInflater;


    public ShapeAdapter(Context context) {
        this.context = context;
    }

    public int[] slideImages={
            R.mipmap.triangle,
            R.mipmap.square,
            R.mipmap.rectangle,
            R.mipmap.rhombus,
            R.mipmap.trapezoid,
            R.mipmap.parallelogram,
            R.mipmap.pentagon,
            R.mipmap.star,
            R.mipmap.hexagon,
            R.mipmap.octagon,
            R.mipmap.circle,
            R.mipmap.oval,
            R.mipmap.heart
    };

    public String[] slideHead={

            "Triangle","Square","Rectangle","Rhombus","Trapezoid","Parallelogram","Pentagon","Star","Hexagon","Octagon","Circle","Oval","Heart"
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
        View view= layoutInflater.inflate(R.layout.shapes_layout,container,false);
        final ImageView slideIV= (ImageView) view.findViewById(R.id.shapesimage);
        TextView slideTV= (TextView) view.findViewById(R.id.shapename);

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
