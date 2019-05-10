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

    private final HealthValueDbHelper dbHelper;
    private static final String DATE_TIME_PATTERN = "yyyy-MM-dd HH:mm:ss";
    private static final String EXCEPTION_PARSING_DB_CONTENT = "Exception parsing the database content to a temperature object. Message: ";


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
        values.put(HealthValueDbHelper.COLUMN_TEMPERATURE_TIMESTAMP, temperature.getDateTime(DATE_TIME_PATTERN));

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


        Temperature t = null;

        if (cursor != null)
            cursor.moveToFirst();
        else return null;


        try {
            String idAsString = cursor.getString(0);
            String bodyTemperatureAsString = cursor.getString(1);

            if (!idAsString.isEmpty() || !bodyTemperatureAsString.isEmpty())
                t = new Temperature(Long.parseLong(idAsString), Double.parseDouble(bodyTemperatureAsString), cursor.getString(2), DateTimeUtils.getParsedDateTimeFromLocalizedString(cursor.getString(3), DATE_TIME_PATTERN));
        } catch (ParseException e) {
            // TODO Error msg to the GUI.
            Log.e(LOG_TAG, EXCEPTION_PARSING_DB_CONTENT + e.getLocalizedMessage());
        } catch (NullPointerException npe) {
            // TODO Error msg to the GUI
            Log.e(LOG_TAG, EXCEPTION_PARSING_DB_CONTENT + npe.getLocalizedMessage());
        } finally {
            cursor.close();
        }
        return t;


    }

    public List<Temperature> getAllTemperatures() {
        Log.d(LOG_TAG, "Getting all temperature data from the database. ");

        List<Temperature> temperatureList = new ArrayList<>();

        String selectAllQuery = "SELECT * FROM " + HealthValueDbHelper.TABLE_TEMPERATURE;

        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectAllQuery, null);

        // put all temperatures to the list
        if (cursor.moveToFirst()) {
            do {
                long id = Long.parseLong(cursor.getString(0));
                double bodyTemp = Double.parseDouble(cursor.getString(1));
                String scale = cursor.getString(2);
                Date timestamp = null;

                Temperature temperature = new Temperature();
                temperature.setId(id);
                temperature.setBodyTemperature(bodyTemp);
                temperature.setScale(cursor.getString(2));

                try {
                    timestamp = DateTimeUtils.getParsedDateTimeFromLocalizedString(cursor.getString(3), DATE_TIME_PATTERN);
                    temperature.setDate(timestamp);

                    // add it to the list.
                    temperatureList.add(temperature);
                } catch (ParseException e) {
                    // TODO Error msg to the GUI.
                    Log.e(LOG_TAG, EXCEPTION_PARSING_DB_CONTENT + e.getLocalizedMessage());
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
