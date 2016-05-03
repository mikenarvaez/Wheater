package site.weatherapplication;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.test.AndroidTestCase;

import java.util.HashSet;

import site.weatherapplication.data.WeatherContract;
import site.weatherapplication.data.WeatherDbHelper;

/**
 * Created by mnarvaez on 4/28/16.
 */
public class TestDb extends AndroidTestCase {
    public static final String LOG_TAG = TestDb.class.getSimpleName();

    void deleteTheDatabase(){
        mContext.deleteDatabase(WeatherDbHelper.DATABASE_NAME);
    }

    public void setUp(){
        deleteTheDatabase();
    }

    public void testCreateDb() throws Throwable{
        final HashSet<String> tableNameHashSet = new HashSet<>();
        tableNameHashSet.add(WeatherContract.LocationEntry.TABLE_NAME);
        tableNameHashSet.add(WeatherContract.WeatherEntry.TABLE_NAME);

        mContext.deleteDatabase(WeatherDbHelper.DATABASE_NAME);

        SQLiteDatabase db = new WeatherDbHelper(
                this.mContext).getWritableDatabase();

        assertEquals(true, db.isOpen());

        Cursor c = db.rawQuery("SELECT name FROM sqlite_master WHERE type='table'",null);
        assertTrue("Error: This means that the database has not been created correctly", c.moveToFirst());

        do{
            tableNameHashSet.remove(c.getString(0));
        }while(c.moveToNext());

        //if this fails, it means thar your database doesn't contain both the location entry

        assertTrue("Error: Your database was created without both the" +
                    "location entry and weather entry tables", tableNameHashSet.isEmpty());

        c = db.rawQuery("PRAGMA table_info(" + WeatherContract.LocationEntry.TABLE_NAME + ")",null);

        assertTrue("Error: This means that we were unable to query the database for table information",
                c.moveToFirst());

        //Build a HashSet of all of the columns names we want to look for
        final HashSet<String> locationColumnHashSet = new HashSet<String>();
        locationColumnHashSet.add(WeatherContract.LocationEntry._ID);

        locationColumnHashSet.add(WeatherContract.LocationEntry.COLUMN_CITY_NAME);
        locationColumnHashSet.add(WeatherContract.LocationEntry.COLUMN_COORD_LAT);
        locationColumnHashSet.add(WeatherContract.LocationEntry.COLUMN_COORD_LONG);
        locationColumnHashSet.add(WeatherContract.LocationEntry.COLUMN_LOCATION_SETTING);

        int columnNameIndex = c.getColumnIndex("name");
        do{
            String columnName = c.getString(columnNameIndex);
            locationColumnHashSet.remove(columnName);

        }while (c.moveToNext());

        assertTrue("Error: The database doesn't contain all of the required " +
                    "location entry columns", locationColumnHashSet.isEmpty());

        c = db.rawQuery("PRAGMA table_info(" + WeatherContract.WeatherEntry.TABLE_NAME + ")",null);

        assertTrue("Error: This means that we were unable to query the database for table information",
                c.moveToFirst());

        final HashSet<String> weatherColumnHashSet = new HashSet<String>();
        weatherColumnHashSet.add(WeatherContract.WeatherEntry._ID);
        weatherColumnHashSet.add(WeatherContract.WeatherEntry.COLUMN_DATE);
        weatherColumnHashSet.add(WeatherContract.WeatherEntry.COLUMN_DEGREES);
        weatherColumnHashSet.add(WeatherContract.WeatherEntry.COLUMN_HUMIDITY);
        weatherColumnHashSet.add(WeatherContract.WeatherEntry.COLUMN_LOC_KEY);
        weatherColumnHashSet.add(WeatherContract.WeatherEntry.COLUMN_MAX_TEMP);
        weatherColumnHashSet.add(WeatherContract.WeatherEntry.COLUMN_MIN_TEMP);
        weatherColumnHashSet.add(WeatherContract.WeatherEntry.COLUMN_PRESSURE);
        weatherColumnHashSet.add(WeatherContract.WeatherEntry.COLUMN_SHOR_DESC);
        weatherColumnHashSet.add(WeatherContract.WeatherEntry.COLUMN_WEATHER_ID);
        weatherColumnHashSet.add(WeatherContract.WeatherEntry.COLUMN_WIND_SPEED);

        int columnNameIndex2 = c.getColumnIndex("name");
        do{
            String columnName = c.getString(columnNameIndex2);
            weatherColumnHashSet.remove(columnName);

        }while (c.moveToNext());

        assertTrue("Error: The database doesn't contain all of the required " +
                "location entry columns", weatherColumnHashSet.isEmpty());
        db.close();
    }

}
