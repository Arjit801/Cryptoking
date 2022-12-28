package com.example.android.cryptoking;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import javax.crypto.SecretKey;

public class Encoder extends AppCompatActivity {

    EditText etenc;
    TextView enctv;
    ClipboardManager cpb;
    SecretKey key;
    DES des;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_encoder2);

        try {
            key = DES.generateKey();

        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            des = new DES(key);
        } catch (Exception e) {
            e.printStackTrace();
        }
        etenc = findViewById(R.id.etenc);
        enctv = findViewById(R.id.enctv);
        cpb = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void enc(View view) throws Exception {
        String temp = etenc.getText().toString();
        String rv = DES.enc(temp);
        enctv.setText(rv);
    }

    public void cp2(View view){
        String data = enctv.getText().toString().trim();
        if(!data.isEmpty()){
            ClipData temp = ClipData.newPlainText("text", data);
            cpb.setPrimaryClip(temp);
            Toast.makeText(this, "Copied", Toast.LENGTH_LONG).show();
        }
    }
}
