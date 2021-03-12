package com.example.einzelphase;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void berechung(View view) {

        // 11732093 mod 7 = 2 => replace every second number with ASCII character

        TextView antwort_server = findViewById(R.id.antwort_server);
        EditText matnr_input = findViewById(R.id.matnr_input);

        int matnr = Integer.parseInt(matnr_input.getText().toString());

        String antwort = matnr + "" ;
        char [] aux = new char[antwort.length()];

        for (int i  = 0 ; i < aux.length ; i ++ ) {

            if (i % 2 != 0) {
                aux[i] = (char)(64 + Integer.parseInt(String.valueOf(antwort.charAt(i))));
            } else {
                aux[i] = antwort.charAt(i);
            }
        }
        antwort = "" ;
        for (int i = 0; i < aux.length ; i++) {
            antwort = antwort + aux[i] ;
        }

        antwort_server.setText(antwort);
        //serverSide();
    }

    String nachricht ;

    public void serverSide() {

        TextView antwort_server = findViewById(R.id.antwort_server);
        EditText matnr_input = findViewById(R.id.matnr_input);

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Socket socket = new Socket("se2-isys.aau.at",53212);
                    PrintWriter printWriter = new PrintWriter(socket.getOutputStream());
                    printWriter.println(matnr_input.getText().toString());
                    printWriter.flush();
                    BufferedReader bufferedReader = new BufferedReader(
                            new InputStreamReader(socket.getInputStream()));

                    nachricht = bufferedReader.readLine();

                    socket.close();
                    printWriter.close();

                } catch (UnknownHostException e) {
                    nachricht = "! Unbekannter Host !" ;
                    e.printStackTrace();
                }catch (IOException e){
                    nachricht = "! Serverfehler !" ;
                    e.printStackTrace();
                }



            }
        });

        thread.start();

        try {
            thread.join();
        }catch (InterruptedException e){
            e.printStackTrace();
        }

        antwort_server.setText(nachricht);

    }

}


