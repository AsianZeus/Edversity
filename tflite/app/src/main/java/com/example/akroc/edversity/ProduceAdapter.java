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

public class ProduceAdapter extends PagerAdapter {


    Context context;
    LayoutInflater layoutInflater;


    public ProduceAdapter(Context context) {
        this.context = context;
    }

    public int[] slideImages={
            R.mipmap.apple,
            R.mipmap.banana,
            R.mipmap.strawberry,
            R.mipmap.grape,
            R.mipmap.lemon,
            R.mipmap.lychee,
            R.mipmap.orange,
            R.mipmap.papaya,
            R.mipmap.mango,
            R.mipmap.carrot,
            R.mipmap.cauliflower,
            R.mipmap.cabbage,
            R.mipmap.celery,
            R.mipmap.corn,
            R.mipmap.eggplant,
            R.mipmap.pea,
            R.mipmap.onion,
            R.mipmap.potato,
            R.mipmap.tomato,
            R.mipmap.mashroom
    };

    public String[] slideHead={

            "Apple","Banana","Strawberry","Grape","Lemon","Lychee","Orange","Papaya","Mango",
            "Carrot","Cauliflower","Cabbage","Celery","Corn","Eggplant","Pea","Onion","Potato","Tomato","Musroom"
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
        View view= layoutInflater.inflate(R.layout.produce_layout,container,false);
        final ImageView slideIV= (ImageView) view.findViewById(R.id.produceimage);
        TextView slideTV= (TextView) view.findViewById(R.id.producename);

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
