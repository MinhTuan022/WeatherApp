package com.example.weatherapp.Model.ForecastWeather;//package com.example.weatherapp.Model.ForecastWeather;
//
//import com.google.gson.annotations.Expose;
//import com.google.gson.annotations.SerializedName;
//
//public abstract class Forecast {
//
//    @Expose
//    @SerializedName("city")
//    private City city;
//    @Expose
//    @SerializedName("list")
//    private java.util.List<List> list;
//    @Expose
//    @SerializedName("cnt")
//    private int cnt;
//    @Expose
//    @SerializedName("message")
//    private int message;
//    @Expose
//    @SerializedName("cod")
//    private String cod;
//
//    public Forecast(City city, java.util.List<List> list, int cnt, int message, String cod) {
//        this.city = city;
//        this.list = list;
//        this.cnt = cnt;
//        this.message = message;
//        this.cod = cod;
//    }
//
//    public City getCity() {
//        return city;
//    }
//
//    public void setCity(City city) {
//        this.city = city;
//    }
//
//    public java.util.List<List> getList() {
//        return list;
//    }
//
//    public void setList(java.util.List<List> list) {
//        this.list = list;
//    }
//
//    public int getCnt() {
//        return cnt;
//    }
//
//    public void setCnt(int cnt) {
//        this.cnt = cnt;
//    }
//
//    public int getMessage() {
//        return message;
//    }
//
//    public void setMessage(int message) {
//        this.message = message;
//    }
//
//    public String getCod() {
//        return cod;
//    }
//
//    public void setCod(String cod) {
//        this.cod = cod;
//    }
//}
