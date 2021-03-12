package com.example.einzelphase;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void berechung(View view) {

        // 11732093 mod 7 = 2 => replace very second number with ASCII character

        TextView antwort_server = findViewById(R.id.antwort_server);
        EditText matnr_input = findViewById(R.id.matnr_input);

        int matnr = Integer.parseInt(matnr_input.getText().toString());

        String antwort = "" + matnr;
        String ascii = "ABCDEFGHIO" ;

        for(int i = 0  ; i < antwort.length()-1 ; i+= 2){

            antwort.replace(antwort.charAt(i),ascii.charAt(i));
        }

        antwort_server.setText(antwort);
        serverSide();
    }

    public void serverSide() {

        TextView antwort_server = findViewById(R.id.antwort_server);
        EditText matnr_input = findViewById(R.id.matnr_input);

       Thread thread = new Thread(new Runnable() {
           @Override
           public void run() {

           }
       });

       thread.start();

       try {
           thread.join();
       }catch (InterruptedException e){
           e.printStackTrace();
       }

    }

}


