package de.clever.healthvalue.util;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class HealthValueDbHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "HealthValueDB";
    private static final int DB_VERSION = 1;
    // Values for Temperature
    public static final String TABLE_TEMPERATURE = "temperature";
    public static final String COLUMN_TEMPERATURE_ID = "_id";
    public static final String COLUMN_TEMPERATURE_BODYTEMPERATURE = "body_temperature";
    public static final String COLUMN_TEMPERATURE_SCALE = "scale";
    public static final String COLUMN_TEMPERATURE_TIMESTAMP = "timestamp";
    // SQLs for Temperature
    private static final String SQL_CREATE_TEMPERATURE_TABLE = "CREATE TABLE " + TABLE_TEMPERATURE
            + " (" + COLUMN_TEMPERATURE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + COLUMN_TEMPERATURE_BODYTEMPERATURE + " REAL NOT NULL, " + COLUMN_TEMPERATURE_SCALE
            + " TEXT NOT NULL, " + COLUMN_TEMPERATURE_TIMESTAMP + " TEXT NOT NULL);";


    private static final String LOG_TAG = HealthValueDbHelper.class.getSimpleName();

    public HealthValueDbHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        Log.d(LOG_TAG, "HealthValueDbHelper created the database: " + getDatabaseName());
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try {
            // TODO Check if this onCreate-method have to be there for every table (temperature, blood pressure etc.)
            Log.d(LOG_TAG, "Creating table using SQL commands: ");
            // Create table "temperature"
            Log.d(LOG_TAG, "Table 'temperature': " + SQL_CREATE_TEMPERATURE_TABLE);
            db.execSQL(SQL_CREATE_TEMPERATURE_TABLE);
        } catch (Exception ex) {
            Log.e(LOG_TAG, "Error creating database. Error: " + ex.getLocalizedMessage());
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Implement, if database has to be upgraded. Tutorial: https://thebhwgroup.com/blog/how-android-sqlite-onupgrade
        throw new UnsupportedOperationException("Not implemented yet");
    }


}
