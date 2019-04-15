package com.apk.remote;

import android.app.Activity;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.apk.remote.temperature.GetCpuTempAndroid;

import java.text.DateFormat;
import java.util.Date;

public class MainActivity extends Activity implements SensorEventListener {
    private SensorManager sensorManager;
    private Sensor pressure;
    private TextView pressureView;
    private Button submitButton;
    private SeekBar customSeekBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        pressureView = (TextView) findViewById(R.id.pressure);

        final LinearLayout linearLayout = (LinearLayout) findViewById(R.id.right);



        final TextView time = (TextView) findViewById(R.id.time);
        final TextView temperature = (TextView) findViewById(R.id.temperature);

        final ImageButton brightness = (ImageButton)findViewById(R.id.brightness);

        final ImageButton settings = (ImageButton)findViewById(R.id.settings);

        final ImageButton more = (ImageButton)findViewById(R.id.more);




       // customSeekBar =(SeekBar)findViewById(R.id.customSeekBar);
        // perform seek bar change listener event used for getting the progress value
       /* customSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int progressChangedValue = 0;

            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                progressChangedValue = progress;
            }

            public void onStartTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub
            }

            public void onStopTrackingTouch(SeekBar seekBar) {
                Toast.makeText(MainActivity.this, "Seek bar progress is :" + progressChangedValue,
                        Toast.LENGTH_SHORT).show();
            }
        });
*/



        brightness.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Works ! ", Toast.LENGTH_LONG).show();
            }
        });

        final String[] currentDateTimeString = {DateFormat.getDateTimeInstance().format(new Date())};
        time.setText(currentDateTimeString[0]);

        final GetCpuTempAndroid[] tempAndroid = {new GetCpuTempAndroid()};
        Thread t = new Thread() {
            @Override
            public void run() {
                while (!isInterrupted()) {
                    try {

                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                currentDateTimeString[0] = DateFormat.getDateTimeInstance().format(new Date());
                                time.setText(currentDateTimeString[0]);
                                tempAndroid[0] = new GetCpuTempAndroid();
                                Integer temp = ((int) tempAndroid[0].getCpuTemp());
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
