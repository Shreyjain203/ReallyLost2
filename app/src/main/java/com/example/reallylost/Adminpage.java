package com.example.reallylost;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;

public class Adminpage extends AppCompatActivity {


    static int row=25;
    static int column=25;
    static int [][] Path= new int[row][column];
    RelativeLayout l1;
    SharedPreferences sharedPreferences;

    String keyForSharedPreferences="0";
    int countofSharedPreference=1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adminpage);

        for(int i=0;i<row;++i){
            for(int j=0;j<column;++j){
                Path[i][j]=1;
            }
        }

        SharedPreferences sharedPreferences = getPreferences(Context.MODE_MULTI_PROCESS);


        while(sharedPreferences.contains(keyForSharedPreferences)){
            int x=sharedPreferences.getInt(keyForSharedPreferences,-1);
            countofSharedPreference++;
            keyForSharedPreferences=String.valueOf(countofSharedPreference);
            int y=sharedPreferences.getInt(keyForSharedPreferences,-1);
            countofSharedPreference++;
            keyForSharedPreferences=String.valueOf(countofSharedPreference);
            resettheview(x,y);
        }
        countofSharedPreference=1;

    }

    private void resettheview(int x,int y) {
        RelativeLayout linearview = (RelativeLayout) findViewById(R.id.touch);
        EditText editText = new EditText(this);
        editText.setLayoutParams(new android.view.ViewGroup.LayoutParams(50, 50));
        //editText.setImageResource(R.drawable.greyimage);


        editText.setBackgroundColor(Color.YELLOW);

        editText.setTextColor(Color.BLUE);
        editText.setX(x);
        editText.setY(y);
        editText.setId(x);
        linearview.addView(editText);

    }



    public void create(View v){
        touchLisner();

    }


    private void touchLisner(){
        l1=(RelativeLayout) findViewById(R.id.touch);
        l1.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                int  x=(int) motionEvent.getX();
                int y=(int)motionEvent.getY();


                display(x,y);


                return false;
            }
        });
    }

    private void display(int x,int y){
        RelativeLayout linearview=(RelativeLayout) findViewById(R.id.touch);
        EditText editText=new EditText(this);
        editText.setLayoutParams(new android.view.ViewGroup.LayoutParams(50,50));
        //editText.setImageResource(R.drawable.greyimage);


        editText.setBackgroundColor(Color.YELLOW);

        editText.setTextColor(Color.BLUE);
        int p=x%50;
        x=x-p;
        p=y%50;
        y=y-p;




        //call to change the matrix
        updateMatrix(x,y);


        editText.setX(x);
        editText.setY(y);
        editText.setId(x);
        SharedPreferences sharedPreferences = getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(keyForSharedPreferences, x);
        editor.apply();
        countofSharedPreference++;
        keyForSharedPreferences=String.valueOf(countofSharedPreference);
        editor.putInt(keyForSharedPreferences, y);
        editor.apply();
        countofSharedPreference++;
        keyForSharedPreferences=String.valueOf(countofSharedPreference);
        linearview.addView(editText);


    }
    void updateMatrix(int x,int y){
        x=x/50;
        y=y/50;
        Path[x][y]=0;

    }

    public void deleteText(View v){
        l1=(RelativeLayout) findViewById(R.id.touch);

        l1.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                int x = (int) motionEvent.getX();
                int y=(int)motionEvent.getY();

                delete(x,y);
                return false;



            }
        });


    }
    public  void delete(int x,int y){
        int p=x%50;
        x=x-p;
        p=y%50;
        y=y-p;


        EditText editText=(EditText)findViewById(x);
        if(editText!=null) {


            editText.setVisibility(View.GONE);

            x=x/50;
            y=y/50;
            Path[x][y]=1;

        }

    }









}
