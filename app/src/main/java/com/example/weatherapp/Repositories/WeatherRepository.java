package com.example.weatherapp.Repositories;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.weatherapp.Model.CurrentWeather.CurrentData;
import com.example.weatherapp.Model.ForecastWeather.ForecastData;
import com.example.weatherapp.Utils.RetrofitClient;
import com.example.weatherapp.WeatherApiService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WeatherRepository {
    private String Api_Key = "53375e8be6744f3df37e79a1b7413d0b";
    private WeatherApiService apiService;

    public WeatherRepository(){
        apiService = RetrofitClient.getRetrofit().create(WeatherApiService.class);
    }
//    public LiveData<CurrentData> getCurrentData(String city){
//        MutableLiveData<CurrentData> weatherData = new MutableLiveData<>();
//        Call<CurrentData> call = apiService.getCurrentWeather(city, Api_Key, "metric");
//        call.enqueue(new Callback<CurrentData>() {
//            @Override
//            public void onResponse(Call<CurrentData> call, Response<CurrentData> response) {
//                if(response.isSuccessful()){
//                    CurrentData currentData = response.body();
//                    weatherData.setValue(currentData);
//                }
//            }
//
//            @Override
//            public void onFailure(Call<CurrentData> call, Throwable t) {
//
//            }
//        });
//        return weatherData;
//    }
    public LiveData<CurrentData> getCurrentData(double lat, double lon){
        MutableLiveData<CurrentData> weatherData = new MutableLiveData<>();
        Call<CurrentData> call = apiService.getCurrentWeather(lat, lon, Api_Key, "metric");
        call.enqueue(new Callback<CurrentData>() {
            @Override
            public void onResponse(Call<CurrentData> call, Response<CurrentData> response) {
                if(response.isSuccessful()){
                    CurrentData currentData = response.body();
                    weatherData.setValue(currentData);
                }
            }

            @Override
            public void onFailure(Call<CurrentData> call, Throwable t) {

            }
        });
        return weatherData;
    }

//    public LiveData<List<ForecastData>> getNext24Hours(String city){
//        MutableLiveData<List<ForecastData>> hourWeather = new MutableLiveData<>();
//        Call<ForecastResponse> call1 = apiService.getForecastWeather(city, Api_Key, "metric");
//        // Lấy thời gian hiện tại
//        long currentTimeMillis = System.currentTimeMillis() / 1000;
//        call1.enqueue(new Callback<ForecastResponse>() {
//            @Override
//            public void onResponse(Call<ForecastResponse> call, Response<ForecastResponse> response) {
//                if(response.isSuccessful()) {
////                    hourWeather.setValue(response.body().getList());
//                    List<ForecastData> fullData = response.body().getList();
//                    List<ForecastData> next24Hours = new ArrayList<>();
//
//                    for (ForecastData forecastData : fullData){
//                        long dateTime = convertStringToTimestamp(forecastData.getDt_txt());
//                        if (dateTime >= currentTimeMillis && dateTime <= currentTimeMillis+86400){
//                            next24Hours.add(forecastData);
//                        }
//                    }
//                    hourWeather.setValue(next24Hours);
//                }
//            }
//            @Override
//            public void onFailure(Call<ForecastResponse> call, Throwable t) {
//
//            }
//        });
//        return hourWeather;
//    }

    public LiveData<List<ForecastData>> getNext24Hours(double lat, double lon){
        MutableLiveData<List<ForecastData>> hourWeather = new MutableLiveData<>();
        Call<ForecastRepository> call1 = apiService.getForecastWeather(lat, lon, Api_Key, "metric");
        // Lấy thời gian hiện tại
        long currentTimeMillis = System.currentTimeMillis() / 1000;
        call1.enqueue(new Callback<ForecastRepository>() {
            @Override
            public void onResponse(@NonNull Call<ForecastRepository> call, @NonNull Response<ForecastRepository> response) {
                if(response.isSuccessful()) {
//                    hourWeather.setValue(response.body().getList());
                    List<ForecastData> fullData = response.body().getList();
                    List<ForecastData> next24Hours = new ArrayList<>();

                    for (ForecastData forecastData : fullData){
                        long dateTime = convertStringToTimestamp(forecastData.getDt_txt());
                        if (dateTime >= currentTimeMillis && dateTime <= currentTimeMillis+86400){
                            next24Hours.add(forecastData);
                        }
                    }
                    hourWeather.setValue(next24Hours);
                }
            }
            @Override
            public void onFailure(Call<ForecastRepository> call, Throwable t) {

            }
        });
        return hourWeather;
    }
//    public LiveData<List<ForecastData>> getFiveDayWeather(String city){
//        MutableLiveData<List<ForecastData>> forecastWeather = new MutableLiveData<>();
//        Call<ForecastResponse> call1 = apiService.getForecastWeather(city, Api_Key, "metric");
//        // Lấy thời gian hiện tại
////        long currentTimeMillis = System.currentTimeMillis() / 1000;
//        call1.enqueue(new Callback<ForecastResponse>() {
//            @Override
//            public void onResponse(Call<ForecastResponse> call, Response<ForecastResponse> response) {
//                if(response.isSuccessful()) {
//                    response.body().getList();
//                    forecastWeather.setValue(response.body().getList());
////                    List<ForecastData> fullData = response.body().getList();
////                    List<ForecastData> next24Hours = new ArrayList<>();
////
////                    for (ForecastData forecastData : fullData){
////                        long dateTime = convertStringToTimestamp(forecastData.getDt_txt());
////                        if (dateTime >= currentTimeMillis && dateTime <= currentTimeMillis+86400){
////                            next24Hours.add(forecastData);
////                        }
////                    }
////                    forecastWeather.setValue(next24Hours);
//                }
//            }
//
//            @Override
//            public void onFailure(Call<ForecastResponse> call, Throwable t) {
//
//            }
//        });
//        return forecastWeather;
//    }
    public LiveData<List<ForecastData>> getFiveDayWeather(double lat, double lon){
        MutableLiveData<List<ForecastData>> forecastWeather = new MutableLiveData<>();
        Call<ForecastRepository> call = apiService.getForecastWeather(lat, lon, Api_Key, "metric");
        call.enqueue(new Callback<ForecastRepository>() {
            @Override
            public void onResponse(@NonNull Call<ForecastRepository> call, @NonNull Response<ForecastRepository> response) {
                if(response.isSuccessful()) {
                    response.body().getList();
                    forecastWeather.setValue(response.body().getList());

                }
            }

            @Override
            public void onFailure(Call<ForecastRepository> call, Throwable t) {

            }
        });
        return forecastWeather;
    }
    // Hàm chuyển đổi chuỗi thời gian sang số nguyên (long)
    private long convertStringToTimestamp(String dateString) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        try {
            Date date = dateFormat.parse(dateString);
            if (date != null) {
                return date.getTime() / 1000; // Chuyển đổi sang giây
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
