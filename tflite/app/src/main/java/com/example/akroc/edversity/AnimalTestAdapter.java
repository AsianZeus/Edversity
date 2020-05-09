package com.example.akroc.edversity;

import android.animation.Animator;
import android.content.Context;
import android.os.Bundle;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class AnimalTestAdapter extends PagerAdapter {


    Context context;
    LayoutInflater layoutInflater;
    int randIndex;
    public int Answer;
    final String TAG="yowag";
    public int[][] av= new int[100][7];
    int score=0;
    int scre[]=new int[1];
    LottieAnimationView happy;
    LottieAnimationView sad;


    public AnimalTestAdapter(Context context) {
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
    public int[] animalSound={
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

    public int[] getRandom()
    {
        int[] randomval= new int[4];
        ArrayList<Integer> list = new ArrayList<Integer>();
        for (int i=0; i<slideHead.length; i++) {
            list.add(new Integer(i));
        }
        Collections.shuffle(list);
        for (int i=0; i<4; i++) {
            randomval[i]=list.get(i);
        }
        return  randomval;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == (RelativeLayout) object;
    }


    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, final int position) {

        layoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        final View view = layoutInflater.inflate(R.layout.anitest_layout, container, false);
        final ImageView img1 = (ImageView) view.findViewById(R.id.imageanimaa);
        final ImageView img2 = (ImageView) view.findViewById(R.id.imageanimab);
        final ImageView img3 = (ImageView) view.findViewById(R.id.imageanimac);
        final ImageView img4 = (ImageView) view.findViewById(R.id.imageanimad);
        final int[]temp=getRandom();
        img1.setImageResource(slideImages[temp[0]]);
        img2.setImageResource(slideImages[temp[1]]);
        img3.setImageResource(slideImages[temp[2]]);
        img4.setImageResource(slideImages[temp[3]]);
        randIndex=((int)(Math.random() * 4));
        Answer=temp[randIndex];
        av[position][0]=temp[0];
        av[position][1]=temp[1];
        av[position][2]=temp[2];
        av[position][3]=temp[3];
        av[position][4]=randIndex;
        av[position][5]=Answer;
        av[position][6]=0;

        Log.i(TAG,"Randomvalue: "+av[position][0]+","+av[position][1]+","+av[position][2]+","+av[position][3]+" Randindex "+av[position][4]+" Answer "+ av[position][5]+" CurrentPage: "+position);
        final TextView tscore= container.getRootView().findViewById(R.id.score);
        happy =(LottieAnimationView) container.getRootView().findViewById(R.id.happy);
        sad =(LottieAnimationView) container.getRootView().findViewById(R.id.sad);
        img1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(av[position][0]==av[position][5]&&av[position][6]==0)
                {
                    sad.setVisibility(LottieAnimationView.INVISIBLE);
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
                            Object s = documentSnapshot.get("animals");
                            scre[0]= Integer.parseInt(s.toString());
                            Log.i(TAG,"valueee: "+scre[0].toString());
                            if(scre[0]!=null){
                                if(score>=scre[0])
                                {
                                    Map<String,Object> newsc= new HashMap<>();
                                    newsc.put("animals",score);
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
                    av[position][6]=1;
                }
                else if(!(av[position][0]==av[position][5])){
                    sad.setVisibility(LottieAnimationView.VISIBLE);
                    sad.playAnimation();}
            }
        });
        img2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(av[position][1]==av[position][5]&&av[position][6]==0){
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
                            Object s = documentSnapshot.get("animals");
                            scre[0]= Integer.parseInt(s.toString());
                            Log.i(TAG,"valueee: "+scre[0].toString());
                            if(scre[0]!=null){
                                if(score>=scre[0])
                                {
                                    Map<String,Object> newsc= new HashMap<>();
                                    newsc.put("animals",score);
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
                    sad.setVisibility(LottieAnimationView.INVISIBLE);
                    happy.setVisibility(LottieAnimationView.VISIBLE);
                    happy.playAnimation();
                    av[position][6]=1;
            }
            else  if(!(av[position][1]==av[position][5])){
                sad.setVisibility(LottieAnimationView.VISIBLE);
                sad.playAnimation();
            }
            }
        });
        img3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (av[position][2] == av[position][5]&&av[position][6]==0) {
                    score = score + 10;
                    Log.i(TAG,"valueee screee: "+scre[0]);
                    final Integer[] scre = new Integer[1];
                    FirebaseAuth mAuth= FirebaseAuth.getInstance();
                    FirebaseFirestore mFirestore = FirebaseFirestore.getInstance();
                    String UserID= mAuth.getCurrentUser().getUid();
                    DocumentReference ref2=mFirestore.collection("Users").document(UserID).collection("Scores").document("Features");
                    ref2.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                        @Override
                        public void onSuccess(DocumentSnapshot documentSnapshot) {
                            Object s = documentSnapshot.get("animals");
                            scre[0]= Integer.parseInt(s.toString());
                            Log.i(TAG,"valueee: "+scre[0].toString());
                            if(scre[0]!=null){
                                if(score>=scre[0])
                                {
                                    Map<String,Object> newsc= new HashMap<>();
                                    newsc.put("animals",score);
                                    ref2.update(newsc).addOnSuccessListener(new OnSuccessListener<Void>() {
                                        @Override
                                        public void onSuccess(Void aVoid) {
                                            Toast.makeText(v.getRootView().getContext(),"High Score Updated!",Toast.LENGTH_SHORT).show();
                                        }
                                    });
                                }}
                        }
                    });
                    tscore.setText("Score: " + score);
                    sad.setVisibility(LottieAnimationView.INVISIBLE);
                    happy.setVisibility(LottieAnimationView.VISIBLE);
                    happy.playAnimation();
                    av[position][6]=1;
                } else if(!(av[position][2]==av[position][5])) {
                    sad.setVisibility(LottieAnimationView.VISIBLE);
                    sad.playAnimation();
                }
            }
        });
        img4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(av[position][3]==av[position][5]&&av[position][6]==0){
                    score=score+10;
                    sad.setVisibility(LottieAnimationView.INVISIBLE);
                    Log.i(TAG,"valueee screee: "+scre[0]);
                    final Integer[] scre = new Integer[1];
                    FirebaseAuth mAuth= FirebaseAuth.getInstance();
                    FirebaseFirestore mFirestore = FirebaseFirestore.getInstance();
                    String UserID= mAuth.getCurrentUser().getUid();
                    DocumentReference ref2=mFirestore.collection("Users").document(UserID).collection("Scores").document("Features");
                    ref2.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                        @Override
                        public void onSuccess(DocumentSnapshot documentSnapshot) {
                            Object s = documentSnapshot.get("animals");
                            scre[0]= Integer.parseInt(s.toString());
                            Log.i(TAG,"valueee: "+scre[0].toString());
                            if(scre[0]!=null){
                                if(score>=scre[0])
                                {
                                    Map<String,Object> newsc= new HashMap<>();
                                    newsc.put("animals",score);
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
                    av[position][6]=1;
        }
        else if(!(av[position][3]==av[position][5])) {
        sad.setVisibility(LottieAnimationView.VISIBLE);
        sad.playAnimation();
            }
            }
        });

        sad.addAnimatorListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                sad.setVisibility(LottieAnimationView.INVISIBLE);
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
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
}