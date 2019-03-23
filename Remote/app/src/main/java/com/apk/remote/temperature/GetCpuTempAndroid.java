package com.apk.remote.temperature;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class GetCpuTempAndroid {
    private float temp;
    public float getCpuTemp() {
        Process p;
        try {
            p = Runtime.getRuntime().exec("cat sys/class/thermal/thermal_zone0/temp");
            p.waitFor();
            BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));

            String line = reader.readLine();
            temp = Float.parseFloat(line) / 1000.0f;

            return temp;

        } catch (Exception e) {
            e.printStackTrace();
            return 0.0f;
        }
    }

    @Override
    public String toString() {
        return "" + temp;
    }
}
