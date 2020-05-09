package com.example.akroc.edversity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.speech.RecognitionListener;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.speech.tts.TextToSpeech;
import android.speech.tts.Voice;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;

import java.util.List;
import java.util.Locale;

public class PronunTest extends AppCompatActivity {

    ImageView mic;
    LottieAnimationView synthe;
    private ViewPager mvp;
    private PronounceAdapter adapter;
    private SpeechRecognizer speechRecognizer;
    private TextToSpeech myTTS;
    View viewx;
    private int CurrentPage;

    @Override
    protected void onRestart() {
        super.onRestart();
        initializeTextToSpeech();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pronun_test);
        synthe =(LottieAnimationView) findViewById(R.id.synthesis);
        mic=(ImageView) findViewById(R.id.voicemic);
        viewx= (View) findViewById(R.id.relativetestid);
        mic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                synthe.setVisibility(synthe.VISIBLE);
                synthe.playAnimation();
                Intent intent= new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
                intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
                intent.putExtra(RecognizerIntent.EXTRA_MAX_RESULTS,1);
                speechRecognizer.startListening(intent);
            }
        });
        initializeTextToSpeech();
        initializeSpeechRecognizer();
        mvp= (ViewPager) findViewById(R.id.vp2);
        adapter = new PronounceAdapter(this);
        mvp.setAdapter(adapter);
        mvp.addOnPageChangeListener(viewListener);

    }

    private void initializeSpeechRecognizer() {
        if(SpeechRecognizer.isRecognitionAvailable(this)){
            speechRecognizer= SpeechRecognizer.createSpeechRecognizer(this);
            speechRecognizer.setRecognitionListener(new RecognitionListener() {
                @Override
                public void onReadyForSpeech(Bundle params) {

                }

                @Override
                public void onBeginningOfSpeech() {

                }

                @Override
                public void onRmsChanged(float rmsdB) {

                }

                @Override
                public void onBufferReceived(byte[] buffer) {

                }

                @Override
                public void onEndOfSpeech() {

                }

                @Override
                public void onError(int error) {

                }

                @Override
                public void onResults(Bundle bundle) {
                    synthe.setVisibility(synthe.INVISIBLE);
                    synthe.clearAnimation();
                    List<String> results = bundle.getStringArrayList(speechRecognizer.RESULTS_RECOGNITION);
                    processResult(results.get(0));
                }

                @Override
                public void onPartialResults(Bundle partialResults) {

                }

                @Override
                public void onEvent(int eventType, Bundle params) {

                }
            });
        }
    }

    private void processResult(String command) {
        command=command.toLowerCase();
        String temp= adapter.slideHead[CurrentPage];
        String letter= ""+temp.toLowerCase().charAt(0);
        //Toast.makeText(getApplicationContext(),"You Spoke: "+command+" Answer: "+letter,Toast.LENGTH_SHORT).show();
        Snackbar.make(viewx,"You Spoke: '"+command.toUpperCase()+"' Answer: '"+letter.toUpperCase()+"'",Snackbar.LENGTH_SHORT).show();
        if(command.equals(letter))
        {
            speak("Correct Answer!");
        }
        else
            {
                speak("Oops, the correct answer is "+letter+".");
            }
    }

    private void initializeTextToSpeech() {
        myTTS= new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if(myTTS.getEngines().size()==0)
                {
                    Toast.makeText(getApplicationContext(),"There is no TTS Engine in your Device!",Toast.LENGTH_SHORT).show();
                }
                else
                    {
                        myTTS.setLanguage(Locale.US);
                        myTTS.setSpeechRate(0.9f);
                        myTTS.setPitch(1.2f);
                    }
            }
        });
    }

    private void speak(String s) {
        myTTS.speak(s,TextToSpeech.QUEUE_FLUSH,null,null);
    }

    @Override
    protected void onPause() {
        super.onPause();
        myTTS.shutdown();
    }

    ViewPager.OnPageChangeListener viewListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            CurrentPage=position;
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };
}
