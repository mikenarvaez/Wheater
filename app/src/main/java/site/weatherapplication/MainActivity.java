package site.weatherapplication;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

import java.io.FileReader;
import java.text.DateFormat;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.gms.common.api.GoogleApiClient;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

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
        if (savedInstanceState == null){
            getSupportFragmentManager().beginTransaction().add(R.id.frameinfo, new PlaceHolderFragment()).commit();
        }
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    public static class PlaceHolderFragment extends Fragment {

        public PlaceHolderFragment(){
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
            ArrayAdapter<String> mForecastAdapter;
            String[] data = {
                    "Mon 6/23 - Sunny - 31/17",
                    "Tue 6/24 - Foggy - 21/8",
                    "Wed 6/25 - Cloudy - 22/17",
                    "Thu 6/26 - Rainy - 18/11",
                    "Fri 6/27 - Foggy - 21/10",
                    "Mon 6/28 - TRAPPED IN WEATHERSTATION - 23/18",
                    "Mon 6/29 - Sunny - 20/7",
            };

            List<String> weekForecast = new ArrayList<String>(Arrays.asList(data));

            mForecastAdapter = new ArrayAdapter<String>(
                    getActivity(),
                    R.layout.list_item_forecast,
                    R.id.list_item_forecast_textview,
                    weekForecast
            );

            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            ListView listView = (ListView) rootView.findViewById(R.id.list_item_forecast);
            listView.setAdapter(mForecastAdapter);
            return rootView;
        }
    }
}
