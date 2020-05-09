package com.example.akroc.edversity;

import android.animation.Animator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.DragEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
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

public class Tab2_Fragment extends Fragment  implements View.OnLongClickListener,View.OnDragListener{
    TextView textView;
    int temp[];
    Button button;
    int scre[]=new int[1];
    int temp2[]=new int[4];
    final String TAG="yowag";
    public int[][] av= new int[100][7];
    int score=0;
    LottieAnimationView lav;

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
    public int[] slideObjects={
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

    public int[] getRandom()
    {
        int[] randomval= new int[4];
        ArrayList<Integer> list = new ArrayList<Integer>();
        for (int i=0; i<slideImages.length; i++) {
            list.add(new Integer(i));
        }
        Collections.shuffle(list);
        for (int i=0; i<4; i++) {
            randomval[i]=list.get(i);
        }
        return  randomval;
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull final LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view=inflater.inflate(R.layout.tab2_fragment,container,false);
        final ImageView img1 = (ImageView) view.findViewById(R.id.aid1);
        final ImageView img2 = (ImageView) view.findViewById(R.id.aid2);
        final ImageView img3 = (ImageView) view.findViewById(R.id.aid3);
        final ImageView img4 = (ImageView) view.findViewById(R.id.aid4);
        final ImageView img5 = (ImageView) view.findViewById(R.id.aid5);
        final ImageView img6 = (ImageView) view.findViewById(R.id.aid6);
        final ImageView img7 = (ImageView) view.findViewById(R.id.aid7);
        final ImageView img8 = (ImageView) view.findViewById(R.id.aid8);
        lav= (LottieAnimationView) view.findViewById(R.id.tab2happy);

        textView= (TextView) view.findViewById(R.id.amatchscore);
        button =(Button) view.findViewById(R.id.alphbuton);
        temp=getRandom();
        img1.setImageResource(slideImages[temp[0]]);
        img2.setImageResource(slideImages[temp[1]]);
        img3.setImageResource(slideImages[temp[2]]);
        img4.setImageResource(slideImages[temp[3]]);

        ArrayList<Integer> list2 = new ArrayList<Integer>();
        list2.add(0,temp[0]);
        list2.add(1,temp[1]);
        list2.add(2,temp[2]);
        list2.add(3,temp[3]);
        Collections.shuffle(list2);
        temp2[0]=list2.get(0);
        temp2[1]=list2.get(1);temp2[2]=list2.get(2);temp2[3]=list2.get(3);

        img5.setImageResource(slideObjects[temp2[0]]);
        img6.setImageResource(slideObjects[temp2[1]]);
        img7.setImageResource(slideObjects[temp2[2]]);
        img8.setImageResource(slideObjects[temp2[3]]);

        img1.setOnLongClickListener(this);
        img2.setOnLongClickListener(this);
        img3.setOnLongClickListener(this);
        img4.setOnLongClickListener(this);

        img5.setOnDragListener(this);
        img6.setOnDragListener(this);
        img7.setOnDragListener(this);
        img8.setOnDragListener(this);

        lav.addAnimatorListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                lav.setVisibility(LottieAnimationView.INVISIBLE);
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                temp=getRandom();
                img1.setImageResource(slideImages[temp[0]]);
                img2.setImageResource(slideImages[temp[1]]);
                img3.setImageResource(slideImages[temp[2]]);
                img4.setImageResource(slideImages[temp[3]]);

                ArrayList<Integer> list2 = new ArrayList<Integer>();
                list2.add(0,temp[0]);
                list2.add(1,temp[1]);
                list2.add(2,temp[2]);
                list2.add(3,temp[3]);
                Collections.shuffle(list2);
                temp2[0]=list2.get(0);
                temp2[1]=list2.get(1);temp2[2]=list2.get(2);temp2[3]=list2.get(3);

                img5.setImageResource(slideObjects[temp2[0]]);
                img6.setImageResource(slideObjects[temp2[1]]);
                img7.setImageResource(slideObjects[temp2[2]]);
                img8.setImageResource(slideObjects[temp2[3]]);

                img1.setEnabled(true);
                img2.setEnabled(true);
                img3.setEnabled(true);
                img4.setEnabled(true);
            }
        });



        return view;

    }

    @Override
    public boolean onDrag(View v, DragEvent event) {
        if (event.getAction()==DragEvent.ACTION_DROP)
        {
            ImageView dropped = (ImageView)event.getLocalState();
            ImageView dropTarget = (ImageView) v;
            Bitmap bitmap = ((BitmapDrawable)dropped.getDrawable()).getBitmap();
            Bitmap bitmap2 = ((BitmapDrawable)dropTarget.getDrawable()).getBitmap();

            if(bitmap==bitmap2)
            {
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
                        Object s = documentSnapshot.get("matchtab2");
                        scre[0]= Integer.parseInt(s.toString());
                        Log.i(TAG,"valueee: "+scre[0].toString());
                        if(scre[0]!=null){
                            if(score>=scre[0])
                            {
                                Map<String,Object> newsc= new HashMap<>();
                                newsc.put("matchtab2",score);
                                ref2.update(newsc).addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {
                                        Toast.makeText(v.getRootView().getContext(),"High Score Updated!",Toast.LENGTH_SHORT).show();
                                    }
                                });
                            }}
                    }
                });
                textView.setText("Score: "+score);
                dropped.setEnabled(false);
                final Vibrator vibe = (Vibrator) v.getContext().getSystemService(Context.VIBRATOR_SERVICE);
                vibe.vibrate(50);
                lav.setVisibility(LottieAnimationView.VISIBLE);
                lav.playAnimation();
            }

        }
        return true;
    }

    @Override
    public boolean onLongClick(View v) {
        final Vibrator vibe = (Vibrator) v.getContext().getSystemService(Context.VIBRATOR_SERVICE);
        vibe.vibrate(50);
        View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(v);
        v.startDrag(null, shadowBuilder, v, 0);
        return true;
    }
}
