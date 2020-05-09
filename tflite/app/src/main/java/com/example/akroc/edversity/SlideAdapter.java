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

public class SlideAdapter extends PagerAdapter {

    Context context;
    LayoutInflater layoutInflater;


    public SlideAdapter(Context context) {
        this.context = context;
    }

    public int[] slideImages={
            R.mipmap.speak,
            R.mipmap.shape,
            R.mipmap.objectdetection
    };

    public String[] slideHead={

            "Learning Pronunciation","Learning Contents","Visualizing Objects"

    };
    public String[] slideText={

            "Learn how to pronounce every alphabet and digit and test your skills by verbally speaking the same.",
            "Learn to draw shapes, alphabets and digits with vibrating feedback and text your skill with actually drawing the letters.",
            "Recognizing Objects around your surrounding and responding with a label of which object it is."
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
        View view= layoutInflater.inflate(R.layout.slide_layout,container,false);
        ImageView slideIV= (ImageView) view.findViewById(R.id.imageView2);
        TextView slideTV= (TextView) view.findViewById(R.id.textView);
        TextView slideTV1= (TextView) view.findViewById(R.id.textView2);

        slideIV.setImageResource(slideImages[position]);
        slideTV.setText(slideHead[position]);
        slideTV1.setText(slideText[position]);

        container.addView(view);

        return view;

    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((RelativeLayout) object);
    }
}

