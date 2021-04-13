
package com.example.android.sunshine.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.android.sunshine.data.WeatherContract.WeatherEntry;

/**
 * Manages a local database for weather data.
 */
public class WeatherDbHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "weather.db";

   
    private static final int DATABASE_VERSION = 3;

    public WeatherDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    /**
     * Called when the database is created for the first time. This is where the creation of
     * tables and the initial population of the tables should happen.
     *
     * @param sqLiteDatabase The database.
     */
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        /*
         * This String will contain a simple SQL statement that will create a table that will
         * cache our weather data.
         */
        final String SQL_CREATE_WEATHER_TABLE =

                "CREATE TABLE " + WeatherEntry.TABLE_NAME + " (" +

                /*
                 * WeatherEntry did not explicitly declare a column called "_ID". However,
                 * WeatherEntry implements the interface, "BaseColumns", which does have a field
                 * named "_ID". We use that here to designate our table's primary key.
                 */
                WeatherEntry._ID               + " INTEGER PRIMARY KEY AUTOINCREMENT, " +

                WeatherEntry.COLUMN_DATE       + " INTEGER NOT NULL, "                 +

                WeatherEntry.COLUMN_WEATHER_ID + " INTEGER NOT NULL,"                  +

                WeatherEntry.COLUMN_MIN_TEMP   + " REAL NOT NULL, "                    +
                WeatherEntry.COLUMN_MAX_TEMP   + " REAL NOT NULL, "                    +

                WeatherEntry.COLUMN_HUMIDITY   + " REAL NOT NULL, "                    +
                WeatherEntry.COLUMN_PRESSURE   + " REAL NOT NULL, "                    +

                WeatherEntry.COLUMN_WIND_SPEED + " REAL NOT NULL, "                    +
                WeatherEntry.COLUMN_DEGREES    + " REAL NOT NULL, "                    +

                " UNIQUE (" + WeatherEntry.COLUMN_DATE + ") ON CONFLICT REPLACE);";

        /*
         * After we've spelled out our SQLite table creation statement above, we actually execute
         * that SQL with the execSQL method of our SQLite database object.
         */
        sqLiteDatabase.execSQL(SQL_CREATE_WEATHER_TABLE);
    }

 
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + WeatherEntry.TABLE_NAME);
        onCreate(sqLiteDatabase);
    }
}