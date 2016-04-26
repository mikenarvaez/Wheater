package site.weatherapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import java.text.DateFormat;
import android.widget.TextView;

import com.google.android.gms.common.api.GoogleApiClient;

import java.util.Date;

public class MainActivity extends AppCompatActivity {

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;
    private TextView textFecha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String currentDateTimeString = "Today, " + DateFormat.getDateInstance().format(new Date());

        TextView date = (TextView) findViewById(R.id.textView2);

        // textView is the TextView view that should display it
        date.setText(currentDateTimeString);
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
    }
}
