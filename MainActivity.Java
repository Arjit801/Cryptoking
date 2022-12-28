package com.example.android.cryptoking;

import android.content.Intent;
import android.os.Bundle;
//import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ViewFlipper;

import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity {

    Button enc, dec;
    ViewFlipper vf1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

//        setContentView(R.layout.activity_main);
        enc = findViewById(R.id.encd);
        dec = findViewById(R.id.decd);

        enc.setOnClickListener(view -> {
            Intent temp = new Intent(MainActivity.this, Encoder.class);
            startActivity(temp);
        });



        dec.setOnClickListener(view -> {
            Intent temp = new Intent(MainActivity.this, Decoder.class);
            startActivity(temp);
        });



        vf1 = findViewById(R.id.vf);
        int[] images = {R.drawable.bellaso_cipher, R.drawable.caesar_cipher_encryption, R.drawable.dorabella_cipher};

        for (int image : images) {
            flipper(image);
        }
    }

    public void flipper(int images){
        ImageView test = new ImageView(this);
        test.setBackgroundResource(images);
        vf1.addView(test);
        vf1.setFlipInterval(3000);
        vf1.setAutoStart(true);
        vf1.setInAnimation(this, android.R.anim.slide_in_left);
        vf1.setOutAnimation(this, android.R.anim.slide_out_right);
    }

}
