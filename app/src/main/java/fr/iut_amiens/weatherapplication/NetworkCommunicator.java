package fr.iut_amiens.weatherapplication;

import android.os.AsyncTask;

import java.io.IOException;

import fr.iut_amiens.weatherapplication.openweathermap.WeatherManager;
import fr.iut_amiens.weatherapplication.openweathermap.WeatherResponse;

/**
 * Created by hadikk on 16/03/18.
 */

public class NetworkCommunicator extends AsyncTask<Object, Object, WeatherResponse> {


    private WeatherManager weatherManager = new WeatherManager();
    private String city = "";
    private MainActivity activity;

    @Override
    protected WeatherResponse doInBackground(Object[] objects) {
        WeatherResponse weather = null;
        try {
            weather = weatherManager.findWeatherByCityName(city);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return weather;
    }

    @Override
    protected void onPostExecute(WeatherResponse o) {
        activity.updateWeather(o);
        super.onPostExecute(o);
    }

    //City must be set before task executing
    public void SetCity(String newCity)
    {
        city = newCity;
    }

    public void setActivity(MainActivity activity) {
        this.activity = activity;
    }

}
