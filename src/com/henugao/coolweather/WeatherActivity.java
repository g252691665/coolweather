package com.henugao.coolweather;

import com.henugao.coolweather.util.HttpCallbackListener;
import com.henugao.coolweather.util.HttpUtil;
import com.henugao.coolweather.util.Utility;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.TextView;

public class WeatherActivity extends Activity {
	
	private LinearLayout weatherInfoLayout;
	
	private TextView cityNameText; // 显示城市名
	
	private TextView publishText;//显示发布时间
	
	private  TextView weatherDespText ; //显示天气描述信息
	
	private TextView temp1Text; //显示气温1
	
	private TextView temp2Text;//显示气温2
	
	private TextView currentDateText; //用于显示当前日期
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.weather_layout);

		
		weatherInfoLayout = (LinearLayout) findViewById(R.id.weather_info_layout);
		cityNameText = (TextView) findViewById(R.id.city_name);
		publishText = (TextView) findViewById(R.id.publish_text);
		weatherDespText = (TextView) findViewById(R.id.weather_desp);
		temp1Text = (TextView) findViewById(R.id.temp1);
		temp2Text = (TextView) findViewById(R.id.temp2);
		currentDateText = (TextView) findViewById(R.id.current_date);
		
		String countyCode = getIntent().getStringExtra("county_code");
		if (!TextUtils.isEmpty(countyCode)) {
			//有县级代号就去查询天气
			publishText.setText("同步中...");
			weatherInfoLayout.setVisibility(View.INVISIBLE);
			cityNameText.setVisibility(View.INVISIBLE);
			queryWeatherCode(countyCode);
		} else {
			showWeather();
		}
		
		
	}


	/**
	 * 查询县级代号对应的天气代号
	 * @param countyCode
	 */
	private void queryWeatherCode(String countyCode) {
		String address = "http://www.weather.com.cn/data/list3/city" + 
		countyCode + ".xml";
		queryFromServer(address,"countyCode");
	}


	/**
	 * 查询天气代号对应的天气
	 * @param weatherCode
	 */
	private void queryWeatherIno(String weatherCode){
		String address = "http://www.weather.com.cn/data/cityinfo/" + 
				weatherCode + ".html";
				queryFromServer(address,"weatherCode");
	}
	
	private void queryFromServer(final String address, final String type) {
		HttpUtil.sendHttpRequest(address, new HttpCallbackListener() {
			@Override
			public void onFinish(String response) {
				if ("countyCode".equals(type)) {
					if (!TextUtils.isEmpty(response)) {
						//从服务器返回的数据中解析出天气代号
						String[] arr = response.split("\\|");
						if (arr != null && arr.length ==2) {
							String weatherCode = arr[1];
							queryWeatherIno(weatherCode);
						}
					}
				}else if("weatherCode".equals(type)) {
					//处理服务器返回的天气信息
					Utility.handleWeatherResponse(WeatherActivity.this, response);
					runOnUiThread(new Runnable() {
						@Override
						public void run() {
							// TODO Auto-generated method stub
							showWeather();
						}
					});
				}
				
			}
			
			@Override
			public void onError(Exception e) {
				// TODO Auto-generated method stub
				runOnUiThread(new  Runnable() {
					public void run() {
						publishText.setText("同步失败");
					}
				});
			}
		});
	}
	/**
	 * 从sharedPreference中读取存储的天气信息,并显示到界面上
	 */
	private void showWeather() {
		SharedPreferences sp = getSharedPreferences("weather", 0);
		cityNameText.setText(sp.getString("city_name", ""));
		publishText.setText("今天" + sp.getString("publish_time", "") + "发布");
		currentDateText.setText(sp.getString("current_date", ""));
		temp1Text.setText(sp.getString("temp1", ""));
		temp2Text.setText(sp.getString("temp2", ""));
		weatherDespText.setText(sp.getString("weather_desp", ""));
		weatherInfoLayout.setVisibility(View.VISIBLE);
		cityNameText.setVisibility(View.VISIBLE);
	}
}
