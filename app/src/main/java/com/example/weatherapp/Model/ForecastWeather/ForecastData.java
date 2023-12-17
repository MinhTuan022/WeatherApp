package com.example.weatherapp.Model.ForecastWeather;
import java.util.List;
public class ForecastData {
    private Main main;
    private Clouds clouds;
    private Wind wind;
    private List<Weather> weather;
    private String dt_txt;

    public ForecastData(Main main, Clouds clouds, Wind wind, List<Weather> weather, String dt_txt) {
        this.main = main;
        this.clouds = clouds;
        this.wind = wind;
        this.weather = weather;
        this.dt_txt = dt_txt;
    }

    public Main getMain() {
        return main;
    }

    public void setMain(Main main) {
        this.main = main;
    }

    public Clouds getClouds() {
        return clouds;
    }

    public void setClouds(Clouds clouds) {
        this.clouds = clouds;
    }

    public Wind getWind() {
        return wind;
    }

    public void setWind(Wind wind) {
        this.wind = wind;
    }

    public List<Weather> getWeather() {
        return weather;
    }

    public void setWeather(List<Weather> weather) {
        this.weather = weather;
    }

    public String getDt_txt() {
        return dt_txt;
    }

    public void setDt_txt(String dt_txt) {
        this.dt_txt = dt_txt;
    }
}
