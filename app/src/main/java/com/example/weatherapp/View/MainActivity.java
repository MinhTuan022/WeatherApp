package com.example.weatherapp.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.widget.Toast;

import com.example.weatherapp.Adapters.FiveDayAdapter;
import com.example.weatherapp.Adapters.HoursAdapter;

//import com.example.weatherapp.Manifest;
import com.example.weatherapp.Model.CurrentWeather.Clouds;
import com.example.weatherapp.Model.CurrentWeather.Main;
import com.example.weatherapp.Model.CurrentWeather.Weather;
import com.example.weatherapp.Model.CurrentWeather.Wind;


import com.example.weatherapp.R;
import com.example.weatherapp.ViewModel.WeatherViewModel;
import com.example.weatherapp.databinding.ActivityMainBinding;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;


import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final int REQUEST_CODE_LOCATION = 1;
    private FusedLocationProviderClient fusedLocationClient;
    private WeatherViewModel viewModel;
    private RecyclerView recyclerView;
    private HoursAdapter hoursAdapter;
    private FiveDayAdapter fiveDayAdapter;
    private ActivityMainBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        //recyclerView Hiển thị 24h
        recyclerView = binding.rcListForecast;
        hoursAdapter = new HoursAdapter(new ArrayList<>());
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.HORIZONTAL);
//        recyclerView.addItemDecoration(new DividerItemDecoration(recyclerView.getContext(), DividerItemDecoration.HORIZONTAL));
        recyclerView.setLayoutManager(llm);
        recyclerView.setAdapter(hoursAdapter);

        //recyclerView Hiển theo Ngày
        recyclerView = binding.rcFiveDay;
        fiveDayAdapter = new FiveDayAdapter(new ArrayList<>());
        LinearLayoutManager llm1 = new LinearLayoutManager(this);
        llm1.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(llm1);
        recyclerView.setAdapter(fiveDayAdapter);

        viewModel = new ViewModelProvider(this).get(WeatherViewModel.class);

//        viewModel.getCurrentWeather("hanoi").observe(this, currentData -> {
//            if (currentData != null) {
//                Main to = currentData.getMain();
//                Wind ws = currentData.getWind();
//                Clouds cl = currentData.getClouds();
//                binding.tvCity.setText(currentData.getName());
//                binding.tvTemp.setText(Double.valueOf(to.getTemp()).intValue() + "°");
//                binding.tvTempMinmax.setText("H:" + Double.valueOf(to.getTemp_min()).intValue() + " L:" + Double.valueOf(to.getTemp_max()).intValue());
//                List<Weather> dis = currentData.getWeather();
//                for (Weather data : dis) {
//                    binding.tvDescription.setText(data.getDescription().toUpperCase());
//                }
//                binding.tvCloud.setText(cl.getAll()+ "%");
//                binding.tvHumidity.setText(to.getHumidity() + "%");
//                binding.tvWindSpeed.setText(ws.getSpeed()+"m/s");
//            }
//        });

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
        getCurrentLocation();


    }

    private void getCurrentLocation() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            fusedLocationClient.getLastLocation()
                    .addOnSuccessListener(this, location -> {
                        if (location != null) {
                            double latitude = location.getLatitude();
                            double longitude = location.getLongitude();
                            // Sử dụng latitude và longitude để gửi yêu cầu dự báo thời tiết
                            viewModel.getCurrentWeather(latitude, longitude).observe(this, currentData -> {
                                if (currentData != null) {
                                    Main to = currentData.getMain();
                                    Wind ws = currentData.getWind();
                                    Clouds cl = currentData.getClouds();
                                    binding.tvCity.setText(currentData.getName());
                                    binding.tvTemp.setText(Double.valueOf(to.getTemp()).intValue() + "°");
                                    binding.tvTempMinmax.setText("H:" + Double.valueOf(to.getTemp_min()).intValue() + " L:" + Double.valueOf(to.getTemp_max()).intValue());
                                    List<Weather> dis = currentData.getWeather();
                                    for (Weather data : dis) {
                                        binding.tvDescription.setText(data.getDescription().toUpperCase());
                                    }
                                    binding.tvCloud.setText(cl.getAll() + "%");
                                    binding.tvHumidity.setText(to.getHumidity() + "%");
                                    binding.tvWindSpeed.setText(ws.getSpeed() + "m/s");
                                }
                            });

                            viewModel.getNext24hHours(latitude, longitude).observe(this, hourWeather -> {
                                hoursAdapter.setHoursDataList(hourWeather);
                            });

                            viewModel.getFiveDayWeather(latitude, longitude).observe(this, forecastWeather -> {
                                if (forecastWeather != null) {
                                    fiveDayAdapter.setFiveDayDataList(forecastWeather);
                                }
                            });

                        }
                    })
                    .addOnFailureListener(this, e -> {
                        // Xử lý khi không thể lấy được vị trí hiện tại
                    });
        }else {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_CODE_LOCATION);
        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_CODE_LOCATION) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Quyền đã được cấp, tiến hành lấy vị trí
                getCurrentLocation();
            } else {
                // Quyền bị từ chối, xử lý tùy thuộc vào trường hợp cụ thể của bạn
                // Ví dụ: thông báo cho người dùng biết rằng quyền truy cập vị trí là cần thiết
                Toast.makeText(this, "Hãy cấp quyền để dự báo theo vị trí hiện tại của bạn", Toast.LENGTH_SHORT).show();
            }
        }
    }
}