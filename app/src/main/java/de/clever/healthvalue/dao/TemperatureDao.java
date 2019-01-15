package de.clever.healthvalue.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import de.clever.healthvalue.entities.Temperature;
import de.clever.healthvalue.util.DateTimeUtils;
import de.clever.healthvalue.util.HealthValueDbHelper;

public class TemperatureDao {

    private static final String LOG_TAG = TemperatureDao.class.getSimpleName();
    private SQLiteDatabase database;

    private HealthValueDbHelper dbHelper;
    private static String dateTimePattern = "yyyy-MM-dd HH:mm:ss";


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
        values.put(HealthValueDbHelper.COLUMN_TEMPERATURE_TIMESTAMP, temperature.getDateTime(dateTimePattern));

        database.insert(HealthValueDbHelper.TABLE_TEMPERATURE, null, values);
        database.close();
    }

    public Temperature getTemperature(int id) {
        // see https://o7planning.org/de/10433/die-anleitung-zu-android-sqlite-database
        Log.d(LOG_TAG, "Getting temperature data from the database for id: " + id);

        SQLiteDatabase dbRead = dbHelper.getReadableDatabase();

        Cursor cursor = dbRead.query(HealthValueDbHelper.TABLE_TEMPERATURE, new String[]{
                        HealthValueDbHelper.COLUMN_TEMPERATURE_ID, HealthValueDbHelper.COLUMN_TEMPERATURE_BODYTEMPERATURE,
                        HealthValueDbHelper.COLUMN_TEMPERATURE_SCALE, HealthValueDbHelper.COLUMN_TEMPERATURE_TIMESTAMP},
                HealthValueDbHelper.COLUMN_TEMPERATURE_ID + "=?", new String[]{String.valueOf(id)}, null, null, null, null);

        if (cursor != null)
            cursor.moveToFirst();

        Temperature t = null;
        try {
            t = new Temperature(Long.parseLong(cursor.getString(0)), Double.parseDouble(cursor.getString(1)), cursor.getString(2), DateTimeUtils.getParsedDateTimeFromLocalizedString(cursor.getString(3), dateTimePattern));
        } catch (ParseException e) {
            // TODO Error msg to the GUI.
            Log.e(LOG_TAG, "Exception parsing the database content to a temperature object. Message: " + e.getLocalizedMessage());
        } catch (NullPointerException npe) {
            // TODO Error msg to the GUI
            Log.e(LOG_TAG, "Exception parsing the database content to a temperature object. Message: " + npe.getLocalizedMessage());
        } finally {
            if (cursor != null)
                cursor.close();
        }
        return t;


    }

    public List<Temperature> getAllTemperatures() {
        Log.d(LOG_TAG, "Getting all temperature data from the database. ");

        List<Temperature> temperatureList = new ArrayList<Temperature>();

        String selectAllQuery = "SELECT * FROM " + HealthValueDbHelper.TABLE_TEMPERATURE;

        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectAllQuery, null);

        // put all temperatures to the list
        if (cursor.moveToFirst()) {
            do {
                Long id = Long.parseLong(cursor.getString(0));
                Double bodyTemp = Double.parseDouble(cursor.getString(1));
                String scale = cursor.getString(2);
                Date timestamp = null;

                Temperature temperature = new Temperature();
                temperature.setId(id);
                temperature.setBodyTemperature(bodyTemp);
                temperature.setScale(cursor.getString(2));

                try {
                    timestamp = DateTimeUtils.getParsedDateTimeFromLocalizedString(cursor.getString(3), dateTimePattern);
                    temperature.setDate(timestamp);

                    // add it to the list.
                    temperatureList.add(temperature);
                } catch (ParseException e) {
                    // TODO Error msg to the GUI.
                    Log.e(LOG_TAG, "Exception parsing the database content to a temperature object. Message: " + e.getLocalizedMessage());
                }
            } while (cursor.moveToNext());
        }
        cursor.close();

        return temperatureList;

    }

    public List<Temperature> getTemperaturesBetweenDates(Date from, Date until) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

}
