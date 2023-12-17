package com.example.weatherapp.ViewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.weatherapp.Model.GeocodingResult;
import com.example.weatherapp.Repositories.GeocodingRepository;

import java.util.List;

public class CityViewModel extends ViewModel {
    private GeocodingRepository geocodingRepository;

    public CityViewModel() {
        geocodingRepository = new GeocodingRepository();
    }

    public LiveData<List<GeocodingResult>> searchCities(String query) {
        return geocodingRepository.getGeocodingData(query);
    }
}
