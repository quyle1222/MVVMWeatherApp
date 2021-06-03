package com.example.mvvmweatherappusingdatabinding.Network;


import com.example.mvvmweatherappusingdatabinding.Model.WeatherModel;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

import static com.example.mvvmweatherappusingdatabinding.AppConstant.AppConstant.BASE_URL;

public interface ApiRequest {

    Gson gson = new GsonBuilder().setDateFormat("yy:MM:dd HH:mm:ss").create();

    //http://api.openweathermap.org/data/2.5/weather?q=DaNang&appid=7bc4b75d4ab75ef75278b62add00f67c&units=metric&lang=VI
    ApiRequest apiRequest = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build().create(ApiRequest.class);

    @GET("data/2.5/weather")
    Call<WeatherModel> getWeatherData(@Query("q") String q,
                                      @Query("appid") String appid,
                                      @Query("units") String units,
                                      @Query("lang") String lang);
}
