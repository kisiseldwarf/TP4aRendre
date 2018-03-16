package fr.iut_amiens.weatherapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

import fr.iut_amiens.weatherapplication.openweathermap.WeatherManager;
import fr.iut_amiens.weatherapplication.openweathermap.WeatherResponse;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private WeatherResponse weatherResults = null;
    private String city = "";
    private Button btn;
    private EditText et;
    private TextView cityTitle;
    private TextView humidity;
    private TextView temperature;
    private TextView pressure;
    private TextView windSpeed;
    private TextView conditions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn = findViewById(R.id.button);
        et = findViewById(R.id.editText);
        cityTitle = findViewById(R.id.cityTitle);
        humidity = findViewById(R.id.humidty);
        temperature = findViewById(R.id.Temperature);
        pressure = findViewById(R.id.pressure);
        windSpeed = findViewById(R.id.windSpeed);
        conditions = findViewById(R.id.conditions);

        btn.setOnClickListener(this);



        // Récupération de la météo actuelle :

        // WeatherResponse weather = weatherManager.findWeatherByCityName("Amiens");
        // WeatherResponse weather = weatherManager.findWeatherByGeographicCoordinates(49.8942, 2.2957);

        // documentation : https://openweathermap.org/current

        // Récupération des prévisions par nom de la ville :

        // ForecastResponse forecast = weatherManager.findForecastByCityName("Amiens");
        // ForecastResponse forecast = weatherManager.findForecastByGeographicCoordinates(49.8942, 2.2957);

        // documentation : https://openweathermap.org/forecast5
    }

    public void updateWeather(WeatherResponse response) {
        weatherResults = response;
        cityTitle.setText(city);
        humidity.setText("Humidité : "+response.getMain().getHumidity());
        temperature.setText("Température : "+response.getMain().getTemp()+" (Min. : "+response.getMain().getTempMin()+", Max. : "+response.getMain().getTempMax()+")");
        pressure.setText("Pression : "+response.getMain().getPressure());
        windSpeed.setText("Vitesse du vent : "+response.getWind().getSpeed());
    }

    @Override
    public void onClick(View v) {
        NetworkCommunicator nm = new NetworkCommunicator();
        city = et.getText().toString();
        nm.SetCity(city);
        nm.setActivity(this);
        nm.execute();
    }
}
