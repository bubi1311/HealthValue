package de.clever.healthvalue.healthvalue;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class TemperatureActivity extends AppCompatActivity {

    public static final String LOG_TAG = TemperatureActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_temperature);
    }

    // TODO Connect and open database using TemperatureDao.
}
