package com.example.myapplication;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

public class EndActivity extends AppCompatActivity {
    public int[] imageId=new int[]{
            R.drawable.win,R.drawable.lose,
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end);
        ImageView imageView=findViewById(R.id.imageview);
        Intent intent =getIntent();
        Bundle bundle=intent.getExtras();
        System.out.println(bundle.getString("result"));
        if(bundle.getString("result").equals("win")){
            imageView.setImageResource(imageId[0]);
        }else{
            imageView.setImageResource(imageId[1]);
        }

    }
}
