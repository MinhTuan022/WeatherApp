package com.example.weatherapp.ViewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.weatherapp.Model.CurrentWeather.CurrentData;
import com.example.weatherapp.Model.ForecastWeather.ForecastData;
import com.example.weatherapp.Repositories.WeatherRepository;

import java.util.List;

public class WeatherViewModel extends ViewModel {
    private WeatherRepository weatherRepository;

    public WeatherViewModel() {
        weatherRepository = new WeatherRepository();
    }

//    public LiveData<CurrentData> getCurrentWeather(String city) {
//        return weatherRepository.getCurrentData(city);
//    }
    public LiveData<CurrentData> getCurrentWeather(double lat, double lon) {
        return weatherRepository.getCurrentData(lat, lon);
    }

    public LiveData<List<ForecastData>> getFiveDayWeather(double lat, double lon) {
        return weatherRepository.getFiveDayWeather(lat, lon);
    }
    public LiveData<List<ForecastData>> getNext24hHours(double lat, double lon) {
        return weatherRepository.getNext24Hours(lat, lon);
    }
}
