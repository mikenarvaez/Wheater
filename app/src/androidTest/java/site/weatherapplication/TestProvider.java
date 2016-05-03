package site.weatherapplication;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.test.AndroidTestCase;

import site.weatherapplication.data.WeatherContract;
import site.weatherapplication.data.WeatherDbHelper;

/**
 * Created by mnarvaez on 5/3/16.
 */
public class TestProvider extends AndroidTestCase {

    public static final  String LOG_TAG = TestProvider.class.getSimpleName();

    public void deteleAllRecordsFromProvider(){
       mContext.getContentResolver().delete(
               WeatherContract.WeatherEntry.CONTENT_URI,
               null,
               null
       );

        mContext.getContentResolver().delete(
                WeatherContract.LocationEntry.CONTENT_URI,
                null,
                null
        );

        Cursor cursor = mContext.getContentResolver().query(
                WeatherContract.WeatherEntry.CONTENT_URI,
                null,
                null,
                null,
                null
        );

        assertEquals("Error: Records not deleted from Weather table during delete", 0, cursor.getCount());
        cursor.close();

        cursor = mContext.getContentResolver().query(
                WeatherContract.LocationEntry.CONTENT_URI,
                null,
                null,
                null,
                null
        );

        assertEquals("Error: Records not deleted from Location table durign delete", 0, cursor.getCount());
        cursor.close();
    }

    public void deleteAllRecordsFromDB(){
        WeatherDbHelper dbHelper = new WeatherDbHelper(mContext);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        db.delete(WeatherContract.WeatherEntry.TABLE_NAME, null, null);
        db.delete(WeatherContract.LocationEntry.TABLE_NAME, null, null);

        db.close();
    }

    public void deleteAllRecords() { deleteAllRecordsFromDB();}

    @Override
    protected  void setUp() throws Exception{
        super.setUp();
        deleteAllRecords();
    }
}
