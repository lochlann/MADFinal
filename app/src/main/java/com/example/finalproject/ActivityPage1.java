
package com.example.finalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;

public class ActivityPage1 extends AppCompatActivity implements SensorEventListener{
        private SensorManager mSensorManager;
        private Sensor mSensor;
        boolean isFlat;


    private final double NORTH_MOVE_FORWARD = 9.0;     // upper mag limit
    private final double NORTH_MOVE_BACKWARD = 6.0;      // lower mag limit
    boolean highLimit = false;
    public int[] arrayAct1 = new int[120];
    public int[] arrayMainAct = new int[120];
    int sequenceCount, btnClicked = 0;
    int score = 0;
    int arrayIndex = 0;
    private final int YELLOW = 1;
    private final int ORANGE = 2;
    private final int PURPLE = 3;
    private final int GREEN = 4;
    //final MediaPlayer sneeze = MediaPlayer.create(this,
        //      R.raw.sneezing);
    Button btnOrange, btnPurple, btnYellow, btnGreen;
    TextView tvTest;
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_page1);

            btnYellow = findViewById(R.id.btnLeft);
            btnOrange = findViewById(R.id.btnTop);
            btnPurple = findViewById(R.id.btnRight);
            btnGreen = findViewById(R.id.btnBottom);

            // choose the sensor you want
            mSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
            mSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
            tvTest = findViewById(R.id.tvTest);
            Bundle extras = getIntent().getExtras();
            sequenceCount = extras.getInt("sequenceCount");
            arrayMainAct = extras.getIntArray("numbers");

        }
        @Override
        public void onSensorChanged(SensorEvent event) {
            // called byt the system every x ms
            float x, y, z;
            x = Math.abs(event.values[0]); // get x value
            y = Math.abs(event.values[1]); //if y - that means left
            z = Math.abs(event.values[2]);

            //top
            if ((x > NORTH_MOVE_FORWARD) && (z > 0) && (highLimit == false)) {
                highLimit = true;
                arrayAct1[arrayIndex++] = ORANGE;

            }
            //bottom
            if ((x < NORTH_MOVE_BACKWARD) && (z < 0) && (highLimit == true)) {
                // we have a tilt to the north
                arrayAct1[arrayIndex++] = GREEN;
                highLimit = false;
            }
            //right
            if ((y > NORTH_MOVE_FORWARD) && (y > 0) && (highLimit == false)) {
                highLimit = true;
                arrayAct1[arrayIndex++] = PURPLE;
            }
            //left
            if ((y > NORTH_MOVE_FORWARD) && (y < 0) && (highLimit == true)) {
                // we have a tilt to the north
                arrayAct1[arrayIndex++] = YELLOW;
                highLimit = false;
            }

            if(arrayAct1 == arrayMainAct){
                Intent i = new Intent(ActivityPage1.this, MainActivity.class);
                //i.putExtra("numbers", gameSequence);
                //startActivity(i);
            }
        }
        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {
        }

    public static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        long factor = (long) Math.pow(10, places);
        value = value * factor;
        long tmp = Math.round(value);
        return (double) tmp / factor;
    }

    public void doOrange(View view) {
        arrayAct1[arrayIndex] = ORANGE;
        arrayIndex++;
        btnClicked++;
        Intent toPrevious = new Intent(ActivityPage1.this, MainActivity.class);
        Intent toNext = new Intent(ActivityPage1.this, ActivityPage2.class);

            Log.d("User sequence", String.valueOf(arrayAct1));


        if(sequenceCount == btnClicked ){
                if(Arrays.equals(arrayAct1, arrayMainAct)){
                    toPrevious.putExtra("sequenceCount", sequenceCount+2);
                    setResult(ActivityPage1.RESULT_OK, toPrevious);

                    finish();
                }
                else{
                    score = score + sequenceCount*2;
                    toNext.putExtra("score", score);
                    startActivity(toNext);
                }

        }

    }

    public void doGreen(View view) {
        arrayAct1[arrayIndex] = GREEN;
        arrayIndex++;
        btnClicked++;
        Intent toPrevious = new Intent(ActivityPage1.this, MainActivity.class);
        Intent toNext = new Intent(ActivityPage1.this, ActivityPage2.class);

            Log.d("User sequence", String.valueOf(arrayAct1));
            Log.d("game sequence", String.valueOf(arrayMainAct));



        if(sequenceCount == btnClicked ){
                if(Arrays.equals(arrayAct1, arrayMainAct)){
                    toPrevious.putExtra("sequenceCount", sequenceCount+2);
                    finish();
                }
                else{
                    score = score + sequenceCount*2;
                    toNext.putExtra("score", score);
                    startActivity(toNext);
                }

        }
    }

    public void doYellow(View view) {
        arrayAct1[arrayIndex] = YELLOW;
        arrayIndex++;
        btnClicked++;
        Intent toPrevious = new Intent(ActivityPage1.this, MainActivity.class);
        Intent toNext = new Intent(ActivityPage1.this, ActivityPage2.class);

            Log.d("User sequence", String.valueOf(arrayAct1));
            Log.d("game sequence", String.valueOf(arrayMainAct));


        if(sequenceCount == btnClicked ){
                if(Arrays.equals(arrayAct1, arrayMainAct)){
                    toPrevious.putExtra("sequenceCount", sequenceCount+2);
                    finish();
                }
                else{score = score + sequenceCount*2;
                    toNext.putExtra("score", score);
                    startActivity(toNext);
                }

        }
    }

    public void doPurple(View view) {
        arrayAct1[arrayIndex] = PURPLE;
        arrayIndex++;
        btnClicked++;
        Intent toPrevious = new Intent(ActivityPage1.this, MainActivity.class);
        Intent toNext = new Intent(ActivityPage1.this, ActivityPage2.class);

            Log.d("User sequence", String.valueOf(arrayAct1));
            Log.d("game sequence", String.valueOf(arrayMainAct));

        if(sequenceCount == btnClicked ){
            if(Arrays.equals(arrayAct1, arrayMainAct)){
                toPrevious.putExtra("sequenceCount", sequenceCount+2);
                finish();
            }
            else{
                score = score + sequenceCount*2;
                toNext.putExtra("score", score);
                startActivity(toNext);
            }

        }
    }
}