package sg.edu.rp.c346.id21023395.demoweatherdemoweather;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class MainActivity extends AppCompatActivity {

    ListView lvWeather;
    AsyncHttpClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lvWeather = findViewById(R.id.lvWeather);
        client = new AsyncHttpClient();
    }

    @Override
    protected void onResume() {
        super.onResume();
        // Create an arrayList of Weather Objects
        ArrayList<Weather> alWeather = new ArrayList<Weather>();
        // Connect to the API and wait for a response
        client.get("https://api.data.gov.sg/v1/environment/2-hour-weather-forecast", new JsonHttpResponseHandler() {
            String area;
            String forecast;

            // Triggered when response is a success
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                try {
                    // Get the Array named "items" to access the JSON Array "forecasts"
                    JSONArray jsonArrItems = response.getJSONArray("items");
                    // "forecasts" is found in the "items" JSON obj (index 0)
                    JSONObject firstObj = jsonArrItems.getJSONObject(0);
                    // Get the forecasts array
                    JSONArray jsonArrForecasts = firstObj.getJSONArray("forecasts");
                    // GO thru entire array, retrieve weather forecast object
                    for(int i = 0; i < jsonArrForecasts.length(); i++) {
                        //For each weather forecast object, retrieve "area" and "forecast",
                        // create a new Weather object, add to arrayList
                        JSONObject jsonObjForecast = jsonArrForecasts.getJSONObject(i);
                        area = jsonObjForecast.getString("area");
                        forecast = jsonObjForecast.getString("forecast");
                        Weather weather = new Weather(area, forecast);
                        alWeather.add(weather);
                    }
                }
                catch(JSONException e){
                }
                //POINT X – Code to display List View
                ArrayAdapter aa = new ArrayAdapter<>(MainActivity.this,
                        android.R.layout.simple_list_item_1, alWeather);
                lvWeather.setAdapter(aa);
            }//end onSuccess
        });
    }//end onResume

}