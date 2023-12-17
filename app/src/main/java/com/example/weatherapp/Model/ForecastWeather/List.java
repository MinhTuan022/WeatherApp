package com.example.weatherapp.Model.ForecastWeather;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class List {
    @Expose
    @SerializedName("dt_txt")
    private String dt_txt;
    @Expose
    @SerializedName("sys")
    private Sys sys;
//    @Expose
//    @SerializedName("rain")
//    private Rain rain;
    @Expose
    @SerializedName("pop")
    private double pop;
    @Expose
    @SerializedName("visibility")
    private int visibility;
    @Expose
    @SerializedName("wind")
    private Wind wind;
    @Expose
    @SerializedName("clouds")
    private Clouds clouds;
    @Expose
    @SerializedName("weather")
    private java.util.List<Weather> weather;
    @Expose
    @SerializedName("main")
    private Main main;
    @Expose
    @SerializedName("dt")
    private int dt;

    public String getDt_txt() {
        return dt_txt;
    }

    public void setDt_txt(String dt_txt) {
        this.dt_txt = dt_txt;
    }

    public Sys getSys() {
        return sys;
    }

    public void setSys(Sys sys) {
        this.sys = sys;
    }

//    public Rain getRain() {
//        return rain;
//    }
//
//    public void setRain(Rain rain) {
//        this.rain = rain;
//    }

    public double getPop() {
        return pop;
    }

    public void setPop(double pop) {
        this.pop = pop;
    }

    public int getVisibility() {
        return visibility;
    }

    public void setVisibility(int visibility) {
        this.visibility = visibility;
    }

    public Wind getWind() {
        return wind;
    }

    public void setWind(Wind wind) {
        this.wind = wind;
    }

    public Clouds getClouds() {
        return clouds;
    }

    public void setClouds(Clouds clouds) {
        this.clouds = clouds;
    }

    public java.util.List<Weather> getWeather() {
        return weather;
    }

    public void setWeather(java.util.List<Weather> weather) {
        this.weather = weather;
    }

    public Main getMain() {
        return main;
    }

    public void setMain(Main main) {
        this.main = main;
    }

    public int getDt() {
        return dt;
    }

    public void setDt(int dt) {
        this.dt = dt;
    }
}
