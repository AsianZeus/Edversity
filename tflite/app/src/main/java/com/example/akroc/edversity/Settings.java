package com.example.akroc.edversity;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.RatingBar.OnRatingBarChangeListener;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Settings extends AppCompatActivity {


    private FirebaseAuth mAuth;
    private FirebaseUser user;
    private Toolbar toolbar;
    private CoordinatorLayout clayout;
    private CardView crd1;
    private CardView crd2;
    private CardView crd3;
    private CardView crd4;
    private FloatingActionButton floatb;
    private CollapsingToolbarLayout ctl;
    private AppBarLayout appBarLayout;
    private final String TAG= "photourl";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        mAuth = FirebaseAuth.getInstance();
       // rootLayout = (RelativeLayout) findViewById(R.id.rootLayout);
        user = mAuth.getCurrentUser();
        toolbar= (Toolbar) findViewById(R.id.stoolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null)
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                startActivity(new Intent(getApplicationContext(),Dashboard.class));
            }
        });


        appBarLayout = (AppBarLayout) findViewById(R.id.sapp_bar);
        ctl = (CollapsingToolbarLayout) findViewById(R.id.stoolbar_layout);
        crd1=(CardView) findViewById(R.id.crdv1);
        crd2=(CardView) findViewById(R.id.crdv2);
        crd3=(CardView) findViewById(R.id.crdv3);
        crd4=(CardView) findViewById(R.id.crd4);
        floatb= (FloatingActionButton) findViewById(R.id.floatc);
        clayout =(CoordinatorLayout) findViewById(R.id.coordilay);
        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (verticalOffset == -300) {
                    ctl.setTitle("\nSettings");
                    ctl.setCollapsedTitleGravity(Gravity.TOP);
                }if(verticalOffset ==0)
                {ctl.setTitle("");}
            }
        });

        floatb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showEditDailog();
            }
        });

        crd1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showEditDailog();
            }
        });


        crd4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showScoreDailog();
            }
        });

        crd3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDeleteDailog();
            }
        });
        crd2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showFeedbackDailog();
            }
        });

    }

    private void showScoreDailog() {

        AlertDialog.Builder dialog= new AlertDialog.Builder(this,R.style.AlertDialogTheme);
        dialog.setTitle("High Scores");
        dialog.setMessage("Listing various Activities.");
        LayoutInflater inflater = LayoutInflater.from(this);
        View login_layout = inflater.inflate(R.layout.layout_highscores,null);
        FirebaseAuth mAuth= FirebaseAuth.getInstance();
        FirebaseFirestore mFirestore = FirebaseFirestore.getInstance();
        String UserID= mAuth.getCurrentUser().getUid();
        DocumentReference ref1=mFirestore.collection("Users").document(UserID).collection("Scores").document("Features");
        final TextView color= (TextView) login_layout.findViewById(R.id.closcr);
        final TextView animal= (TextView) login_layout.findViewById(R.id.aniscr);
        final TextView fruit= (TextView) login_layout.findViewById(R.id.fruscr);
        final TextView tab1= (TextView) login_layout.findViewById(R.id.IdenticalMatch);
        final TextView tab2= (TextView) login_layout.findViewById(R.id.ShapeMatch);
        final TextView tab3= (TextView) login_layout.findViewById(R.id.LetterMatch);
        dialog.setView(login_layout);

        ref1.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                color.setText(task.getResult().get("colors").toString());
                animal.setText(task.getResult().get("animals").toString());
                fruit.setText(task.getResult().get("fruits").toString());
                tab1.setText(task.getResult().get("matchtab1").toString());
                tab2.setText(task.getResult().get("matchtab2").toString());
                tab3.setText(task.getResult().get("matchtab3").toString());
            }
        });

        dialog.setPositiveButton("OKAY", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }

        });

        dialog.show();

    }

    private void showFeedbackDailog() {
        AlertDialog.Builder dialog= new AlertDialog.Builder(this,R.style.AlertDialogTheme);
        dialog.setTitle("Feedback");
        dialog.setMessage("Give us the Rating we deserve.");
        LayoutInflater inflater = LayoutInflater.from(this);
        View login_layout = inflater.inflate(R.layout.layout_feedback,null);
        FirebaseAuth mAuth= FirebaseAuth.getInstance();
        FirebaseFirestore mFirestore = FirebaseFirestore.getInstance();
        String UserID= mAuth.getCurrentUser().getUid();
        DocumentReference ref1=mFirestore.collection("Users").document(UserID).collection("Feedback").document("List");
        final EditText feed= (EditText) login_layout.findViewById(R.id.feedbacktxt);

        dialog.setView(login_layout);


        //if rating value is changed,
        //display the current rating value in the result (textview) automatically

        dialog.setPositiveButton("SUBMIT", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                final RatingBar ratingBar = (RatingBar) login_layout.findViewById(R.id.ratingBarx);
                Map<String,String> mv= new HashMap();
                mv.put(new Date().toString(),feed.getText().toString());
                ref1.set(mv);
                Map<String,Object> mx= new HashMap();
                mx.put("Rating",String.valueOf(ratingBar.getRating()));
                mFirestore.collection("Users").document(UserID).update(mx).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Toast.makeText(getApplicationContext(),"Feedback Submitted!", Toast.LENGTH_SHORT).show();
                    }
                });

            }

        });

        dialog.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        dialog.show();

    }

    private void showDeleteDailog() {
        FirebaseAuth mAuth= FirebaseAuth.getInstance();
        FirebaseFirestore mFirestore = FirebaseFirestore.getInstance();
        String UserID= mAuth.getCurrentUser().getUid();
        DocumentReference ref1=mFirestore.collection("Users").document(UserID);

        AlertDialog.Builder dialog= new AlertDialog.Builder(this,R.style.AlertDialogTheme);
        dialog.setTitle("Delete Account");
        LayoutInflater inflater = LayoutInflater.from(this);
        View login_layout = inflater.inflate(R.layout.layout_deleteacc,null);


        dialog.setView(login_layout);

        dialog.setPositiveButton("YES", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                ref1.delete().addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        user.delete().addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                            }
                        });
                        Snackbar.make(clayout,"Account Deleted!",Toast.LENGTH_SHORT).show();
                        finish();
                        startActivity(new Intent(getApplicationContext(),MainActivity.class));
                    }
                });
            }

        });

        dialog.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        dialog.show();

    }





    private void showEditDailog() {

        FirebaseAuth mAuth= FirebaseAuth.getInstance();
        FirebaseFirestore mFirestore = FirebaseFirestore.getInstance();
        String UserID= mAuth.getCurrentUser().getUid();
        DocumentReference ref1=mFirestore.collection("Users").document(UserID);

        AlertDialog.Builder dialog= new AlertDialog.Builder(this,R.style.AlertDialogTheme);
        dialog.setTitle("Edit Profile");
        dialog.setMessage("Please fill your valid info.");
        LayoutInflater inflater = LayoutInflater.from(this);
        View login_layout = inflater.inflate(R.layout.layout_editprofile,null);
        final EditText edtName = login_layout.findViewById(R.id.edtName);
        final EditText edtEmail = login_layout.findViewById(R.id.edtEmail);
        final EditText edtMobile = login_layout.findViewById(R.id.edtMobile);

        final String[] data = {"","",""};
        ref1.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                data[0] = (String) documentSnapshot.get("Name");
                data[1] = (String) documentSnapshot.get("MobileNo");
                data[2] = (String) documentSnapshot.get("Email");

                Log.i(TAG, "DATA: " + data[0] + data[1] + data[2]);
                edtEmail.setText(data[2]);
                edtMobile.setText(data[1]);
                edtName.setText(data[0]);
            }});

        dialog.setView(login_layout);

        dialog.setPositiveButton("SAVE", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                        if(data[0]!=null)
                        {
                            if (TextUtils.isEmpty(edtName.getText().toString())) {
                                Snackbar.make(clayout, "Please Enter Your Name", Snackbar.LENGTH_SHORT).show();
                                return;
                            }
                        }

                        if(data[1]!=null)
                        {
                            if (TextUtils.isEmpty(edtMobile.getText().toString())) {
                                Snackbar.make(clayout, "Please Enter Your Mobile Number", Snackbar.LENGTH_SHORT).show();
                                return;
                            }
                        }
                        if(data[2]!=null)
                        {
                            if (TextUtils.isEmpty(edtEmail.getText().toString())) {
                                Snackbar.make(clayout, "Please Enter Your Email Address", Snackbar.LENGTH_SHORT).show();
                                return;
                            }
                        }

                        Map<String,Object> newsc= new HashMap<>();
                        newsc.put("Name",edtName.getText().toString());
                        newsc.put("MobileNo",edtMobile.getText().toString());
                        newsc.put("Email",edtEmail.getText().toString());
                        ref1.update(newsc).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Toast.makeText(getApplicationContext(),"Profile Updated!",Toast.LENGTH_SHORT).show();
                            }
                        });

                    }

            });

        dialog.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        dialog.show();
}
}
