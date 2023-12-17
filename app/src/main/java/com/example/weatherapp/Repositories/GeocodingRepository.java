package com.example.weatherapp.Repositories;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.weatherapp.Model.GeocodingResult;
import com.example.weatherapp.Utils.RetrofitClient;
import com.example.weatherapp.WeatherApiService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GeocodingRepository {
    private WeatherApiService apiService;
    private String Api_Key = "53375e8be6744f3df37e79a1b7413d0b";
    public GeocodingRepository() {
        apiService = RetrofitClient.getRetrofit().create(WeatherApiService.class);
    }
    public LiveData<List<GeocodingResult>> getGeocodingData(String location) {
        MutableLiveData<List<GeocodingResult>> geocodingData = new MutableLiveData<>();
        Call<List<GeocodingResult>> call = apiService.getGeocodingData(location, 5, Api_Key);
        call.enqueue(new Callback<List<GeocodingResult>>() {
            @Override
            public void onResponse(Call<List<GeocodingResult>> call, Response<List<GeocodingResult>> response) {
                if (response.isSuccessful()) {
                    List<GeocodingResult> results = response.body();
                    if (results != null && !results.isEmpty()) {
                        geocodingData.setValue(results);
                    }
                } else {
                    // Handle API error
                }
            }

            @Override
            public void onFailure(Call<List<GeocodingResult>> call, Throwable t) {

            }
        });
        return geocodingData;
    }
//    public LiveData<List<GeocodingResult>> getGeocodingData(String location, int limit, String apiKey) {
//        MutableLiveData<List<GeocodingResult>> geocodingData = new MutableLiveData<>();
//
//        apiService.getGeocodingData(location, 5, Api_Key).enqueue(new Callback<List<GeocodingResult>>() {
//            @Override
//            public void onResponse(Call<List<GeocodingResult>> call, Response<List<GeocodingResult>> response) {
//                if (response.isSuccessful()) {
//                    geocodingData.setValue(response.body());
//                } else {
//                    // Xử lý lỗi khi nhận được phản hồi không thành công từ API
//                }
//            }
//
//
//            @Override
//            public void onFailure(Call<List<GeocodingResult>> call, Throwable t) {
//                // Xử lý khi gặp lỗi kết nối
//            }
//        });
//
//        return geocodingData;
//    }
}
