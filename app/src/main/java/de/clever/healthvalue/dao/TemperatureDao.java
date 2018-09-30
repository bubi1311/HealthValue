package de.clever.healthvalue.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import de.clever.healthvalue.util.HealthValueDbHelper;

public class TemperatureDao {

    private static final String LOG_TAG = TemperatureDao.class.getSimpleName();

    private HealthValueDbHelper dbHelper;


    public TemperatureDao(Context context) {
        Log.d(LOG_TAG, "Creating HealthValueDbHelper. ");
        dbHelper = new HealthValueDbHelper(context);
    }

    public void open() {
        Log.d(LOG_TAG, "Trying to get a database reference. ");
        SQLiteDatabase database = dbHelper.getWritableDatabase();
        Log.d(LOG_TAG, "Got database reference. Path to database: " + database.getPath());
    }

    public void close() {
        dbHelper.close();
        Log.d(LOG_TAG, "Closed database using HealthValueDbHelper. ");
    }
}
