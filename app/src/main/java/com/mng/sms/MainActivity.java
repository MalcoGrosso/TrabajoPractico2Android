package com.mng.sms;


import android.Manifest;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;


import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

public class MainActivity extends AppCompatActivity {
    Handler handler = new Handler();
    Button comenzar, terminar;
    private final int TIEMPO = 9000;
    Boolean DDS = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        comenzar = findViewById(R.id.comenzar);
        terminar = findViewById(R.id.terminar);
        ActivityCompat.requestPermissions(MainActivity.this, new String[]{android.Manifest.permission.READ_SMS}, PackageManager.PERMISSION_GRANTED);


        comenzar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                handler.postDelayed(new Runnable() {
                    public void run() {
                        if(DDS.equals(true)){
                            handler.removeCallbacks(this);
                        } else {
                        Cursor cursor = getContentResolver().query(Uri.parse("content://sms//inbox"), null, null,null,null);
                        if (cursor.moveToFirst()) {
                            int cont = 0;
                            do {
                                String Data = "";

                                Data +=  cursor.getString(12);
                                Log.d("salida", Data.toString());
                                cursor.moveToNext();
                                cont++;


                            } while (cont<5);
                            Log.d("salida", "------------------");
                        } else {
                            Log.d("salida", "No hay mensajes");
                        }

                        handler.postDelayed(this, TIEMPO);

                    }}

                }, TIEMPO);

            }
        });

        terminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DDS = true;
            }
        });

    }






   // public void Read_SMS(View view){




        //   Log.d("salida", cursor.getString(12));
        //myTextView.setText(cursor.getString(12));

   // }



}