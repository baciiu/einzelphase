package com.example.einzelphase;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void berechung(View view) {

        // 11732093 mod 7 = 2 => replace very second number with ASCII character

        TextView gib_matnr = findViewById(R.id.gib_matnr);
        EditText matnr_input = findViewById(R.id.matnr_input);

        int matnr = Integer.parseInt(matnr_input.getText().toString());

        String solution = "" + matnr;
        String ascii = "ABCDEFGHIO" ;

        for(int i = 0  ; i < solution.length()-1 ; i+= 2){

            solution.replace(solution.charAt(i),ascii.charAt(i));
        }

        gib_matnr.setText(solution);
        // server
    }

}


