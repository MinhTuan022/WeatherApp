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

public class FiveDayAdapter extends RecyclerView.Adapter<FiveDayAdapter.FiveDayViewHolder> {
    private List<ForecastData> fivedayDataList;

    public FiveDayAdapter(List<ForecastData> fivedayDataList) {
        this.fivedayDataList = fivedayDataList;
    }

    public void setFiveDayDataList(List<ForecastData> fivedayDataList) {
        this.fivedayDataList = fivedayDataList;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public FiveDayViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_five_day, parent, false);
        return new FiveDayViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FiveDayViewHolder holder, int position) {
        ForecastData fiveDayWeather = fivedayDataList.get(position);
        holder.des.setText(fiveDayWeather.getWeather().get(0).getDescription());
        Picasso.get().load("https://openweathermap.org/img/wn/"+fiveDayWeather.getWeather().get(0).getIcon()+".png").into(holder.icon);
        holder.temp.setText(Double.valueOf(fiveDayWeather.getMain().getTemp()).intValue()+"°");
        SimpleDateFormat input =new SimpleDateFormat("yyyy-MM-dd hh:mm", Locale.getDefault());
        SimpleDateFormat output =new SimpleDateFormat("dd/MM",Locale.getDefault());
        try {
            Date t = input.parse(fiveDayWeather.getDt_txt());
            holder.day.setText(output.format(t));
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        if(fivedayDataList!=null && fivedayDataList.size()>0) {
            return fivedayDataList.size();
        }
        else{
            return 0;
        }
    }

    public class FiveDayViewHolder extends RecyclerView.ViewHolder{
        TextView day, temp, des;
        ImageView icon;

        public FiveDayViewHolder(@NonNull View itemView) {
            super(itemView);
            day = itemView.findViewById(R.id.tv_day);
            des = itemView.findViewById(R.id.tv_des);
            temp = itemView.findViewById(R.id.tv_tempmm);
            icon = itemView.findViewById(R.id.iv_icon);
        }
    }
}
