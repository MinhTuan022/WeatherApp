package com.example.weatherapp.Model.ForecastWeather;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Wind {
    @Expose
    @SerializedName("gust")
    private double gust;
    @Expose
    @SerializedName("deg")
    private int deg;
    @Expose
    @SerializedName("speed")
    private double speed;

    public double getGust() {
        return gust;
    }

    public void setGust(double gust) {
        this.gust = gust;
    }

    public int getDeg() {
        return deg;
    }

    public void setDeg(int deg) {
        this.deg = deg;
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }
}
