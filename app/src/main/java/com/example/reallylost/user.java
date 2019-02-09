package com.example.reallylost;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Path;
import android.speech.RecognizerIntent;
import android.speech.tts.TextToSpeech;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;

public class user extends AppCompatActivity {

    private static final int REQ_CODE_SPEECH_INPUT=1000;
    EditText textView;
    TextView textView2;
    ImageButton button;
    Button button2;
    RelativeLayout linearview;
    TextToSpeech speech;
    String speaker;
    SharedPreferences sharedPreferences;

    String keyForSharedPreferences="0";
    int countofSharedPreference=1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);


        for(int i=0;i<25;++i){
            for(int j=0;j<25;++j){
                if(Adminpage.Path[i][j]==0)
                    resettheview(i,j);

            }
        }

        textView = findViewById(R.id.textview);
        textView2 = findViewById(R.id.textview2);

        button = (ImageButton) findViewById(R.id.button);

        speech = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {

                if (status!= TextToSpeech.ERROR){
                    speech.setLanguage(new Locale("en", "IN"));

                }
            }
        });


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                promptSpeechInput();
            }
        });



        button2 = (Button)findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                spoke();
            }
        });





    }

    private void resettheview(int x,int y) {
        linearview = (RelativeLayout) findViewById(R.id.touch);
        EditText editText = new EditText(this);
        editText.setLayoutParams(new android.view.ViewGroup.LayoutParams(50, 50));
        //editText.setImageResource(R.drawable.greyimage);


        editText.setBackgroundColor(Color.YELLOW);

        editText.setTextColor(Color.BLUE);
        editText.setX(x*50);
        editText.setY(y*50);
        editText.setId(x*50);
        linearview.addView(editText);

    }




    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        switch (requestCode) {
            case REQ_CODE_SPEECH_INPUT: {
                if (resultCode == RESULT_OK && null != data) {

                    //get array from voice intent
                    ArrayList<String> result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    //set to text view
                    textView.setText(result.get(0));


                }
            }
        }

    }

    private void promptSpeechInput(){

        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT,"hiiiiiiii");

        try{ startActivityForResult(intent, REQ_CODE_SPEECH_INPUT);

        }catch (Exception e) {

        }

    }



    private void spoke() {


        speaker= textView.getText().toString();

        String[] splited = speaker.split("\\s+");
        HashMap<String,String> map=new HashMap<String, String>();
        map.put("toothpaste","A");
        map.put("biscuit","B");
        map.put("chocolate","C");


        for (int i = 0; i < splited.length; i++) {
            if(map.containsKey(splited[i].toLowerCase())){
                speaker="The "+splited[i]+" is present at rack "+map.get(splited[i].toLowerCase());
                textView2.setText(speaker);
                break;
            }


        }

        // Convert the char array to equivalent String






        speech.speak(speaker, TextToSpeech.QUEUE_FLUSH, null);





    }
}
