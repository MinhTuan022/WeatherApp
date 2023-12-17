package com.example.weatherapp;

import com.example.weatherapp.Model.CurrentWeather.CurrentData;
import com.example.weatherapp.Model.GeocodingResult;
import com.example.weatherapp.Repositories.ForecastResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface WeatherApiService {
//    @GET("data/2.5/weather")
//    Call<CurrentData> getCurrentWeather(
//            @Query("q") String city,
//            @Query("appid") String apiKey,
//            @Query("units") String unit
//    );
    @GET("data/2.5/weather")
    Call<CurrentData> getCurrentWeather(
            @Query("lat") double lat,
            @Query("lon") double lon,
            @Query("appid") String apiKey,
            @Query("units") String unit
    );

//    @GET("data/2.5/forecast")
//    Call<ForecastResponse> getForecastWeather(
//            @Query("q") String city,
//            @Query("appid") String apiKey,
//            @Query("units") String units
//    );
    @GET("data/2.5/forecast")
    Call<ForecastResponse> getForecastWeather(
            @Query("lat") double lat,
            @Query("lon") double lon,
            @Query("appid") String apiKey,
            @Query("units") String units
    );

    @GET("geo/1.0/direct")
    Call<List<GeocodingResult>> getGeocodingData(
            @Query("q") String location,
            @Query("limit") int limit,
            @Query("appid") String apiKey
    );

}
