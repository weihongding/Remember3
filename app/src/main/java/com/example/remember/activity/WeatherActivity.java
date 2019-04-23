package com.example.remember.activity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.remember.R;
import com.example.remember.gson.Forecast;
import com.example.remember.gson.Weather;
import com.example.remember.util.HttpUtil;
import com.example.remember.util.JsonUtil;

import java.io.IOException;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class WeatherActivity extends AppCompatActivity {

    private ScrollView weatherLayout;
    private TextView titleCity;
    private TextView titleUpdateTime;
    private TextView degreeText;
    private TextView weatherInfoText;
    private LinearLayout forecastLayout;
    private TextView aqiText;
    private TextView pm25Text;
    private TextView comfortText;
    private TextView carWashText;
    private TextView sportText;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tq_weather);
        //初始化控件
        weatherLayout = (ScrollView)findViewById(R.id.layout_tq_weather);
        titleCity = (TextView)findViewById(R.id.text_tq_title_city);
        titleUpdateTime = (TextView)findViewById(R.id.text_tq_title_update_time);
        degreeText = (TextView)findViewById(R.id.text_tq_degree);
        weatherInfoText = (TextView)findViewById(R.id.text_tq_weather_info);
        forecastLayout = (LinearLayout)findViewById(R.id.layout_tq_forecast);
        aqiText = (TextView)findViewById(R.id.text_tq_aqi);
        pm25Text = (TextView)findViewById(R.id.text_tq_pm25);
        comfortText = (TextView)findViewById(R.id.text_tq_comfort);
        carWashText = (TextView)findViewById(R.id.text_tq_car_wash);
        sportText = (TextView)findViewById(R.id.text_tq_sport);
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        String weatherString = prefs.getString("weather",null);
        if (weatherString != null){
            //有缓存时直接解析天气数据
            Weather weather = JsonUtil.handleWeatherResponse(weatherString);
            showWeatherInfo(weather);
        }else{
            //无缓存时去服务器查询天气
            String weatherId = getIntent().getStringExtra("weather_id");
            weatherLayout.setVisibility(View.INVISIBLE);
            requestWeather(weatherId);
        }

    }

    //根据天气id请求城市天气信息
    public void requestWeather(final String weatherId){

        String weatherUrl = "http://guolin.tech/api/weather?cityid=" + weatherId + "&key=23c78578233349de8dc8d5285f3c9cb5";
        HttpUtil.sendOkHttpRequest(weatherUrl, new Callback() {

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String responseText = response.body().string();
                final Weather weather = JsonUtil.handleWeatherResponse(responseText);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (weather != null && "ok".equals(weather.status)){
                            SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(WeatherActivity.this).edit();
                            editor.putString("weather",responseText);
                            editor.apply();
                            showWeatherInfo(weather);
                        }else {
                            Toast.makeText(WeatherActivity.this, "获取天气信息失败", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }


            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(WeatherActivity.this, "获取天气信息失败", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

    }

    //处理并展示Weather实体类中的数据
    private void showWeatherInfo(Weather weather){
        String cityName = weather.basic.cityName;
        String updateTime = weather.basic.update.updateTime.split(" ")[1];
        String degree = weather.now.temperature + "℃";
        String weatherInfo = weather.now.more.info;
        titleCity.setText(cityName);
        titleUpdateTime.setText(updateTime);
        degreeText.setText(degree);
        weatherInfoText.setText(weatherInfo);
        forecastLayout.removeAllViews();
        for (Forecast forecast:weather.forecastList) {
            View view = LayoutInflater.from(this).inflate(R.layout.tq_forecast_item,forecastLayout,false);
            TextView dateText = (TextView)view.findViewById(R.id.text_tq_date);
            TextView infoText = (TextView)view.findViewById(R.id.text_tq_info);
            TextView maxText = (TextView)view.findViewById(R.id.text_tq_max);
            TextView minText = (TextView)view.findViewById(R.id.text_tq_min);
            dateText.setText(forecast.date);
            infoText.setText(forecast.more.info);
            maxText.setText(forecast.temperature.max);
            maxText.setText(forecast.temperature.min);
            forecastLayout.addView(view);
        }

        if (weather.aqi != null){
            aqiText.setText(weather.aqi.city.aqi);
            pm25Text.setText(weather.aqi.city.pm25);
        }

        String comfort = "舒适度：" + weather.suggestion.comfort.info;
        String carWash = "洗车指数：" + weather.suggestion.carWash.info;
        String sport = "运动建议：" + weather.suggestion.sport.info;
        comfortText.setText(comfort);
        carWashText.setText(carWash);
        sportText.setText(sport);
        weatherLayout.setVisibility(View.VISIBLE);

    }

}
