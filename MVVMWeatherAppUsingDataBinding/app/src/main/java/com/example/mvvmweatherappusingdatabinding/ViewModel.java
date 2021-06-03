package com.example.mvvmweatherappusingdatabinding;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import com.example.mvvmweatherappusingdatabinding.Model.WeatherModel;
import com.example.mvvmweatherappusingdatabinding.Network.ApiRequest;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.mvvmweatherappusingdatabinding.AppConstant.AppConstant.KEY;
import static com.example.mvvmweatherappusingdatabinding.AppConstant.AppConstant.LANG;
import static com.example.mvvmweatherappusingdatabinding.AppConstant.AppConstant.UNITS;

public class ViewModel extends BaseObservable {
    String temp, tempMin, tempMax, speed, description, feel_like, city;
    @Bindable
    public String getTemp() {
        return temp;

    }

    public void setTemp(String temp) {
        this.temp = temp;
        notifyPropertyChanged(BR.temp);
    }
    @Bindable
    public String getTempMin() {
        return tempMin;
    }

    public void setTempMin(String tempMin) {
        this.tempMin = tempMin;
        notifyPropertyChanged(BR.tempMin);
    }
    @Bindable
    public String getTempMax() {
        return tempMax;
    }

    public void setTempMax(String tempMax) {
        this.tempMax = tempMax;
        notifyPropertyChanged(BR.tempMax);
    }
    @Bindable
    public String getSpeed() {
        return speed;
    }

    public void setSpeed(String speed) {
        this.speed = speed;
        notifyPropertyChanged(BR.speed);
    }
    @Bindable
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
        notifyPropertyChanged(BR.description);
    }
    @Bindable
    public String getFeel_like() {
        return feel_like;
    }

    public void setFeel_like(String feel_like) {
        this.feel_like = feel_like;
        notifyPropertyChanged(BR.feel_like);
    }
    @Bindable
    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
        notifyPropertyChanged(BR.city);
    }

    public void onClickGetData() {
        ApiRequest.apiRequest.getWeatherData("DaNang", KEY, UNITS, LANG).enqueue(new Callback<WeatherModel>() {
            @Override
            public void onResponse(Call<WeatherModel> call, Response<WeatherModel> response) {
                WeatherModel model = response.body();
                if (response != null) {
                    city = String.valueOf(model.getName());
                    description = String.valueOf(model.getWeather().get(0).getDescription());
                    temp = String.valueOf(model.getMain().getTemp());
                    tempMin = String.valueOf(model.getMain().getTemp_min());
                    tempMax = String.valueOf(model.getMain().getTemp_max());
                    feel_like = String.valueOf(model.getMain().getFeels_like());
                    speed = String.valueOf(model.getWind().getSpeed());
                    notifyChange();
                }
            }

            @Override
            public void onFailure(Call<WeatherModel> call, Throwable t) {
            }
        });
    }
}
