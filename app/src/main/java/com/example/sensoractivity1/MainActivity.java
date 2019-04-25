package com.example.sensoractivity1;
import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

    public class MainActivity extends AppCompatActivity {
        TextView ProximitySensor, data;
        SensorManager sm;
        Sensor mySensor;
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
            ProximitySensor = (TextView) findViewById(R.id.proximitySensor);
            data = (TextView) findViewById(R.id.data);
            sm = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
            mySensor = sm.getDefaultSensor(
                    Sensor.TYPE_PROXIMITY);
            if (mySensor == null) {
                ProximitySensor.setText("No Proximity Sensor!");
            } else {
                sm.registerListener(proximitySensorEventListener,mySensor, SensorManager.SENSOR_DELAY_NORMAL);
            }
        }
        SensorEventListener proximitySensorEventListener
                = new SensorEventListener() {
            @Override
            public void onAccuracyChanged(Sensor sensor, int accuracy) {
                // TODO Auto-generated method stub
            }
            @Override
            public void onSensorChanged(SensorEvent event) {
                // TODO Auto-generated method stub
                if (event.sensor.getType() == Sensor.TYPE_PROXIMITY) {
                    if (event.values[0] == 0
                    ) {
                        data.setText("The object is at short distance");
                    } else {
                        data.setText("The object is at a  long distance");
                    }
                    if(event.values[0] < mySensor.getMaximumRange()) {
                        // Detected something nearby
                        getWindow().getDecorView().setBackgroundColor(Color.BLUE);
                    } else {
                        // Nothing is nearby
                        getWindow().getDecorView().setBackgroundColor(Color.RED);
                    }
                }
            }
        };
    }