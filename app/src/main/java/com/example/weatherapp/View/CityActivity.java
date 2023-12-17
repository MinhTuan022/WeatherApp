package com.example.weatherapp.View;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.weatherapp.Model.GeocodingResult;
import com.example.weatherapp.ViewModel.CityViewModel;
import com.example.weatherapp.WeatherApiService;
import com.example.weatherapp.databinding.AddCityBinding;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CityActivity extends AppCompatActivity {
    private AutoCompleteTextView autoCompleteTextView;
    private List<GeocodingResult> geocodingResults;
    private WeatherApiService api;
    private CityViewModel viewModel;
    private AddCityBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = AddCityBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        geocodingResults = new ArrayList<>();
        // Khởi tạo và cấu hình AutoCompleteTextView
        autoCompleteTextView = binding.autoCompTV;
        autoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                String selectedSuggestion = (String) adapterView.getItemAtPosition(position);
                addCity(selectedSuggestion);
            }
        });

        autoCompleteTextView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int start, int before, int count) {
                // Not used in this example
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
                String query = charSequence.toString();
                if (query.length() >= 3) { // Perform search when at least 3 characters are entered
                    callGeocodingApi(query);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
                // Not used in this example
            }
        });
        viewModel = new ViewModelProvider(this).get(CityViewModel.class);



    }
    private void callGeocodingApi1(String query){
        viewModel.searchCities(query).observe(this, geocodingResults1 -> {
            Toast.makeText(this, geocodingResults1.get(1).getName(), Toast.LENGTH_SHORT).show();
        });
    }
    private void callGeocodingApi(String query) {
        // Gửi yêu cầu Geocoding API và cập nhật danh sách gợi ý trên AutoCompleteTextView
        Call<List<GeocodingResult>> call = api.getGeocodingData(query, 5, "53375e8be6744f3df37e79a1b7413d0b");
        call.enqueue(new Callback<List<GeocodingResult>>() {
            @Override
            public void onResponse(Call<List<GeocodingResult>> call, Response<List<GeocodingResult>> response) {
                if (response.isSuccessful()) {
                    List<GeocodingResult> results = response.body();
                    if (results != null && !results.isEmpty()) {
                        geocodingResults = results;
                        updateSuggestions(results);
                    }
                } else {
                    // Handle API error
                }
            }

            @Override
            public void onFailure(Call<List<GeocodingResult>> call, Throwable t) {
                // Handle failure, e.g., network issues
            }
        });
    }

    private void updateSuggestions(List<GeocodingResult> results) {
        // Cập nhật danh sách gợi ý trên AutoCompleteTextView
        List<String> suggestionStrings = new ArrayList<>();
        for (GeocodingResult result : results) {
            suggestionStrings.add(result.getName() + ", " + result.getCountry());
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, suggestionStrings);
        autoCompleteTextView.setAdapter(adapter);
        autoCompleteTextView.showDropDown();
    }

    private void addCity(String selectedSuggestion) {
        // Xử lý khi người dùng chọn một gợi ý, ví dụ: thêm thành phố vào danh sách
        // Bạn có thể thực hiện các bước lưu trữ thành phố, chẳng hạn lưu vào cơ sở dữ liệu, SharedPreferences, v.v.
//        Toast.makeText(this, "Added city: " + selectedSuggestion , Toast.LENGTH_SHORT).show();
//        finish();
        GeocodingResult selectedResult = findSelectedResult(selectedSuggestion);

        if (selectedResult != null) {
            double latitude = selectedResult.getLat();
            double longitude = selectedResult.getLon();

            // Thực hiện các hành động với lat và lon
            Toast.makeText(this, "Latitude: " + latitude + ", Longitude: " + longitude, Toast.LENGTH_SHORT).show();
        }

    }
    private GeocodingResult findSelectedResult(String selectedSuggestion) {
        // Tìm kiếm đối tượng GeocodingResult từ danh sách kết quả
        for (GeocodingResult result : geocodingResults) {
            String suggestion = result.getName() + ", " + result.getCountry();
            if (suggestion.equals(selectedSuggestion)) {
                return result;
            }
        }
        return null;
    }
}
