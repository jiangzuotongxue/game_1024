package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.GridLayout;
import android.widget.ImageButton;
import android.widget.TextView;

public class MainActivity extends Activity {
    private FrameLayout frameLayout;
    private ImageButton imageButton;
    private  float touchDownX,touchUpX;//控制手指左右移动的两次x坐标
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        frameLayout=findViewById(R.id.frameLayout);
        imageButton=findViewById(R.id.image);
        frameLayout.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction()==MotionEvent.ACTION_DOWN){//监听到手指放下来
                    touchDownX=event.getX();
                    return true;
                }
                else if(event.getAction()==MotionEvent.ACTION_UP) {
                    touchUpX=event.getX();
                    if(touchUpX-touchDownX<-100){
                        Intent intent=new Intent(MainActivity.this,HelpActivity.class);
                        startActivityForResult(intent,0x11);
                    }
                }
                return false;
            }
        });
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,GameActivity.class);
                startActivityForResult(intent,0x13);
            }
        });
    }
}
