package com.example.weatherapp.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.weatherapp.Model.ForecastWeather.ForecastData;
import com.example.weatherapp.R;
import com.squareup.picasso.Picasso;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

public class HoursAdapter extends RecyclerView.Adapter<HoursAdapter.HoursViewHolder> {
    private List<ForecastData> hoursDataList;

    public HoursAdapter(List<ForecastData> hoursDataList) {
        this.hoursDataList = hoursDataList;
    }

    public void setHoursDataList(List<ForecastData> hoursDataList) {
        this.hoursDataList = hoursDataList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public HoursViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_forecast, parent, false);
        return new HoursViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HoursViewHolder holder, int position) {
        ForecastData forecastWeather = hoursDataList.get(position);
        Picasso.get().load("https://openweathermap.org/img/wn/"+forecastWeather.getWeather().get(0).getIcon()+".png").into(holder.icon);
        holder.temp.setText(Double.valueOf(forecastWeather.getMain().getTemp()).intValue()+"°");
        SimpleDateFormat input =new SimpleDateFormat("yyyy-MM-dd hh:mm",Locale.getDefault());
        SimpleDateFormat output =new SimpleDateFormat("HH:mm",Locale.getDefault());

        input.setTimeZone(TimeZone.getTimeZone("GMT"));
        output.setTimeZone(TimeZone.getTimeZone("GMT"));

        try {
            Date t = input.parse(forecastWeather.getDt_txt());
            holder.hour.setText(output.format(t));
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        if(hoursDataList!=null && hoursDataList.size()>0) {
            return hoursDataList.size();
        }
        else{
            return 0;
        }
    }

    public class HoursViewHolder extends RecyclerView.ViewHolder{
        TextView hour, temp;
        ImageView icon;
        public HoursViewHolder(@NonNull View itemView) {
            super(itemView);
            hour = itemView.findViewById(R.id.tv_hour);
            temp = itemView.findViewById(R.id.tv_temph);
            icon = itemView.findViewById(R.id.img_icon);

        }

    }

}
