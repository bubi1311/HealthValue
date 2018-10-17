package de.clever.healthvalue.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.Date;
import java.util.List;

import de.clever.healthvalue.entities.Temperature;
import de.clever.healthvalue.util.HealthValueDbHelper;

public class TemperatureDao {

    private static final String LOG_TAG = TemperatureDao.class.getSimpleName();
    private SQLiteDatabase database;

    private HealthValueDbHelper dbHelper;


    public TemperatureDao(Context context) {
        Log.d(LOG_TAG, "Creating HealthValueDbHelper. ");
        dbHelper = new HealthValueDbHelper(context);
    }

    public void open() {
        Log.d(LOG_TAG, "Trying to get a database reference. ");
        database = dbHelper.getWritableDatabase();
        Log.d(LOG_TAG, "Got database reference. Path to database: " + database.getPath());
    }

    public void close() {
        dbHelper.close();
        Log.d(LOG_TAG, "Closed database using HealthValueDbHelper. ");
    }

    public void addData(Temperature temperature) {
        Log.d(LOG_TAG, "Adding temperature data to database. ");
        ContentValues values = new ContentValues();
        values.put(HealthValueDbHelper.COLUMN_TEMPERATURE_BODYTEMPERATURE, temperature.getBodyTemperature());
        values.put(HealthValueDbHelper.COLUMN_TEMPERATURE_SCALE, temperature.getScale());
        values.put(HealthValueDbHelper.COLUMN_TEMPERATURE_TIMESTAMP, temperature.getDateTime());

        database.insert(HealthValueDbHelper.TABLE_TEMPERATURE,null,values);
        database.close();
    }

    public Temperature getTemperature(int id){
        throw new UnsupportedOperationException("Not implemented yet");
    }

    public List<Temperature> getAllTemperatures(){
        throw new UnsupportedOperationException("Not implemented yet");
    }

    public List<Temperature> getTemperaturesBetweenDates(Date from, Date until){
        throw new UnsupportedOperationException("Not implemented yet");
    }

}
