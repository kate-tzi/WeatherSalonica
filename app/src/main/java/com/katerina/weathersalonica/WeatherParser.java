package com.katerina.weathersalonica;

import android.icu.text.SimpleDateFormat;
import android.text.format.Time;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/*** Created by cathr on 26/6/2017. ***/

public class WeatherParser {

    private static final String LOG_TAG = WeatherParser.class.getName();

    public static String[] parseWeatherFromJSON(int numDays, String jsonWeather) throws JSONException {
        String[] results = new String[numDays];

        JSONObject forecastObject = new JSONObject(jsonWeather);
        JSONArray weatherArray = forecastObject.getJSONArray("list");

        Time dayTime = new Time();  //android.text.format.Time
        dayTime.setToNow();
        int julianStartDay = Time.getJulianDay(System.currentTimeMillis(), dayTime.gmtoff);
        dayTime = new Time();

        for(int i =0; i<numDays; i++){
            String day, description, minmax;

            JSONObject dayForecast = weatherArray.getJSONObject(i);

            long dateTime;
            dateTime = dayTime.setJulianDay(julianStartDay + i);
            SimpleDateFormat sdf = new SimpleDateFormat("EEE, dd/M");
            day = sdf.format(dateTime);

            JSONObject jsonDescription = dayForecast.getJSONArray("weather").getJSONObject(0);
            description = jsonDescription.getString("main");

            JSONObject jsonTemp = dayForecast.getJSONObject("temp");
            double mintemp = jsonTemp.getDouble("min");
            double maxtemp = jsonTemp.getDouble("max");
            minmax = Math.round(mintemp)+" - "+Math.round(maxtemp);

            results[i] = day + ":  " + description + "  | Temperature: " + minmax;
        }

        for(int i=0; i<numDays; i++){
            Log.i(LOG_TAG, "Forecast Entry: " + results[i]);
        }

        return results;
    }


}