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

public class AnimalAdapter extends PagerAdapter {
    Context context;
    LayoutInflater layoutInflater;


    public AnimalAdapter(Context context) {
        this.context = context;
    }

    public int[] slideImages={
            R.mipmap.idog,
            R.mipmap.icow,
            R.mipmap.ipeacock,
            R.mipmap.icat,
            R.mipmap.itiger,
            R.mipmap.ielephant,
            R.mipmap.iseal,
            R.mipmap.ihen,
            R.mipmap.isnake,
            R.mipmap.iowl,
            R.mipmap.ihorse,
            R.mipmap.ifrog,
            R.mipmap.imonkey,
            R.mipmap.isheep
    };
    public int[] AnimalSound={
            R.raw.dog,
            R.raw.cow,
            R.raw.peacock,
            R.raw.cat,
            R.raw.tiger,
            R.raw.elephant,
            R.raw.seal,
            R.raw.hen,
            R.raw.snake,
            R.raw.owl,
            R.raw.horse,
            R.raw.frog,
            R.raw.monkey,
            R.raw.sheep
    };

    public String[] slideHead={

            "Dog","Cow","Peacock","Cat","Tiger","Elephant","Seal","Hen","Snake","Owl","Horse","Frog","Monkey","Sheep"
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
        View view= layoutInflater.inflate(R.layout.animal_layout,container,false);
        final ImageView slideIV= (ImageView) view.findViewById(R.id.imageanimal);
        TextView slideTV= (TextView) view.findViewById(R.id.animalarray);

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
