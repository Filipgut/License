package com.apk.remote;

import android.app.Activity;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;

import com.apk.remote.temperature.GetCpuTempAndroid;

import java.text.DateFormat;
import java.util.Date;

public class SecondActivity extends Activity implements SensorEventListener {
    private SensorManager sensorManager;
    private Sensor pressure;
    TextView pressureView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        pressureView = (TextView) findViewById(R.id.pressure);
        final TextView time = (TextView) findViewById(R.id.time);
        final TextView temperature = (TextView) findViewById(R.id.temperature);


        String currentDateTimeString = DateFormat.getDateTimeInstance().format(new Date());
        time.setText(currentDateTimeString);

        final GetCpuTempAndroid[] tempAndroid = {new GetCpuTempAndroid()};
        Thread t = new Thread() {
            @Override
            public void run() {
                while (!isInterrupted()) {
                    try {

                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                tempAndroid[0] = new GetCpuTempAndroid();
                                Float temp = tempAndroid[0].getCpuTemp();
                                temperature.setText(temp.toString() + " \u00B0" + "C");
                            }
                        });
                        Thread.sleep(1000);  //1000ms = 1 sec
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };

        t.start();

        sensorManager = (SensorManager)

                getSystemService(Context.SENSOR_SERVICE);

        pressure = sensorManager.getDefaultSensor(Sensor.TYPE_PRESSURE);
        pressureView.setText(String.format("%.3f mbar", pressure.getPower()));

    }

    @Override
    public final void onSensorChanged(SensorEvent event) {
        float[] values = event.values;
        pressureView.setText(String.format("%.3f mbar", values[0]));

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
