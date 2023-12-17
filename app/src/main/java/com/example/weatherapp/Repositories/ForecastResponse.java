package com.example.weatherapp.Repositories;

import com.example.weatherapp.Model.ForecastWeather.ForecastData;

import java.util.List;

public class ForecastResponse {
    private List<ForecastData> list;

    public ForecastResponse(List<ForecastData> list) {
        this.list = list;
    }

    public List<ForecastData> getList() {
        return list;
    }

    public void setList(List<ForecastData> list) {
        this.list = list;
    }
}
