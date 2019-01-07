package com.dw2.craig.hellocompat;

import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private TextView mHelloTextView;
    private String[] mColorArray = {"red", "pink", "purple", "deep_purple",
            "indigo", "blue", "light_blue", "cyan", "teal", "green",
            "light_green", "lime", "yellow", "amber", "orange", "deep_orange",
            "brown", "grey", "blue_grey", "black" };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mHelloTextView =findViewById(R.id.hello_textview);
        if(savedInstanceState!=null){
            mHelloTextView.setTextColor(savedInstanceState.getInt("color"));
        }
    }
    @Override
    public void  onSaveInstanceState(Bundle outstate){
        super.onSaveInstanceState(outstate);
        // save the color of the current instance
        outstate.putInt("color",mHelloTextView.getCurrentTextColor());

    }

    public void changeColor(View view) {
        Random random = new Random();
        int colorRes;
        String colorName =mColorArray[random.nextInt(20)];
        int colorResourceName = getResources().getIdentifier(colorName,
                "color", getApplicationContext().getPackageName());
        //note getcolor not supported before api 23
       // int colorRes = getResources().getColor(colorResourceName,this.getTheme());
        // this compatibility library allows the code to work with phones running earlier versions

        colorRes = ContextCompat.getColor(this,colorResourceName);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
             colorRes = getResources().getColor(colorResourceName, this.getTheme());
        } else {
            colorRes = getResources().getColor(colorResourceName);
        }

        mHelloTextView.setTextColor(colorRes);
    }

}
