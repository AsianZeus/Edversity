package com.example.akroc.edversity;

import android.animation.Animator;
import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.DragEvent;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;

public class ColorTestAdapter extends PagerAdapter implements View.OnTouchListener,View.OnDragListener {

    Context context;
    LayoutInflater layoutInflater;
    int randIndex;
    public int Answer;
    final String TAG="yowag";
    public int[][] av= new int[100][8];
    int score=0;
    TextView tscore;
    int scre[]=new int[1];
    int CurrentPosition=0;
    LottieAnimationView happy;

    public ColorTestAdapter(Context context) {
        this.context = context;
    }

    public int[] slideColor={
            Color.rgb(255,0,0),         //0
            Color.rgb(0,0,255),         //1
            Color.rgb(0,255,0),         //2
            Color.rgb(255,255,0),       //3
            Color.rgb(165,42,42),       //4
            Color.rgb(255,20,147),      //5
            Color.rgb(128,0,128),       //6
            Color.rgb(0,0,0)            //7
    };

    public String[] slideHead={

            "Red Colour",           //0
            "Blue Colour",          //1
            "Green Colour",         //2
            "Yellow Colour",        //3
            "Brown Colour",         //4
            "Pink Colour",          //5
            "Purple Colour",        //6
            "Black Colour"          //7
    };
    @Override
    public int getCount() {
        return slideHead.length;
    }

    public int[] getRandom()
    {
        int[] randomval= new int[5];
        ArrayList<Integer> list = new ArrayList<Integer>();
        for (int i=0; i<slideHead.length; i++) {
            list.add(new Integer(i));
        }
        Collections.shuffle(list);
        for (int i=0; i<5; i++) {
            randomval[i]=list.get(i);
        }
        return  randomval;
    }


    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view==(RelativeLayout) object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
            layoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            View view = layoutInflater.inflate(R.layout.color_layout, container, false);

            TextView rellay = (TextView) view.findViewById(R.id.justtesting);
            TextView tv1 = (TextView) view.findViewById(R.id.justtesting1);
            TextView tv2 = (TextView) view.findViewById(R.id.justtesting2);
            TextView tv3 = (TextView) view.findViewById(R.id.justtesting3);
            TextView tv4 = (TextView) view.findViewById(R.id.justtesting4);
            TextView tv5 = (TextView) view.findViewById(R.id.justtesting5);
            TextView colorname = (TextView) view.findViewById(R.id.colourname);

            tv1.setGravity(Gravity.CENTER_VERTICAL);
            tv2.setGravity(Gravity.CENTER_VERTICAL);
            tv3.setGravity(Gravity.CENTER_VERTICAL);
            tv4.setGravity(Gravity.CENTER_VERTICAL);
            tv5.setGravity(Gravity.CENTER_VERTICAL);

            CurrentPosition = position-1;

            tv1.setOnTouchListener(this);
            tv2.setOnTouchListener(this);
            tv3.setOnTouchListener(this);
            tv4.setOnTouchListener(this);
            tv5.setOnTouchListener(this);
            rellay.setOnDragListener(this);

            final int[] temp = getRandom();

            tv1.setTextColor(slideColor[temp[0]]);
            tv2.setTextColor(slideColor[temp[1]]);
            tv3.setTextColor(slideColor[temp[2]]);
            tv4.setTextColor(slideColor[temp[3]]);
            tv5.setTextColor(slideColor[temp[4]]);

            randIndex = ((int) (Math.random() * 5));
            Answer = temp[randIndex];

            av[position][0] = temp[0];
            av[position][1] = temp[1];
            av[position][2] = temp[2];
            av[position][3] = temp[3];
            av[position][4] = temp[4];
            av[position][5] = randIndex;
            av[position][6] = Answer;
            av[position][7] = 0;

            colorname.setText(slideHead[av[position][6]]);

            Log.i(TAG, "Randomvalue: " + av[position][0] + "," + av[position][1] + "," + av[position][2] + "," + av[position][3] + "," + av[position][4] + " Randindex " + av[position][5] + " Answer " + av[position][6] + " CurrentPage: " + position);
            happy = (LottieAnimationView) container.getRootView().findViewById(R.id.happy1);
            tscore= container.getRootView().findViewById(R.id.scorec);

        happy.addAnimatorListener(new Animator.AnimatorListener() {
                @Override
                public void onAnimationStart(Animator animation) {

                }

                @Override
                public void onAnimationEnd(Animator animation) {
                    happy.setVisibility(LottieAnimationView.INVISIBLE);
                }

                @Override
                public void onAnimationCancel(Animator animation) {

                }

                @Override
                public void onAnimationRepeat(Animator animation) {

                }
            });


            container.addView(view);

            return view;

    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((RelativeLayout) object);
    }


    @Override
    public boolean onTouch(View v, MotionEvent event) {
        if (event.getAction()==MotionEvent.ACTION_DOWN) {
            View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(v);
            v.startDrag(null, shadowBuilder, v, 0);
            Log.i(TAG,"Current Page"+CurrentPosition);
            return true;
        }
        else return false;
    }



    @Override
    public boolean onDrag(View v, DragEvent event) {
        int pos;
        if(av[slideHead.length-2][7]==1)
        {
                pos=slideHead.length-1;
        }
        else {
            if(CurrentPosition==0)
                pos=0;
            else
            pos = CurrentPosition;
        }

        if(av[pos][7]==0){
        if (event.getAction()==DragEvent.ACTION_DROP)
        {
            TextView dropped = (TextView)event.getLocalState();
            TextView dropTarget = (TextView) v;
            if(slideColor[av[pos][6]]==dropped.getCurrentTextColor())
            {
                dropTarget.setTextColor(dropped.getCurrentTextColor());
                score=score+10;
                Log.i(TAG,"valueee screee: "+scre[0]);
                final Integer[] scre = new Integer[1];
                FirebaseAuth mAuth= FirebaseAuth.getInstance();
                FirebaseFirestore mFirestore = FirebaseFirestore.getInstance();
                String UserID= mAuth.getCurrentUser().getUid();
                DocumentReference ref2=mFirestore.collection("Users").document(UserID).collection("Scores").document("Features");
                ref2.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        Object s = documentSnapshot.get("colors");
                        scre[0]= Integer.parseInt(s.toString());
                        Log.i(TAG,"valueee: "+scre[0].toString());
                        if(scre[0]!=null){
                            if(score>=scre[0])
                            {
                                Map<String,Object> newsc= new HashMap<>();
                                newsc.put("colors",score);
                                ref2.update(newsc).addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {
                                        Toast.makeText(v.getRootView().getContext(),"High Score Updated!",Toast.LENGTH_SHORT).show();
                                    }
                                });
                            }}
                    }
                });
                tscore.setText("Score: "+score);
                happy.setVisibility(LottieAnimationView.VISIBLE);
                happy.playAnimation();

                av[pos][7]=1;
            }
        }
        }
        return true;
    }
}
