package com.henugao.coolweather.model;

/**
 * 关于城市天气的相关信息
 * @author henugao
 *
 */
public class Weather {
	
	//城市名
	private String cityName;
	
	//城市代码
	private String weatherCode;
	
	//城市最低温度
	private String temp1;
	
	//城市最高温度
	private String temp2;
	
	//城市气候
	private String weatherDesp;
	
	//天气发布时间
	private String publishTime;

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public String getWeatherCode() {
		return weatherCode;
	}

	public void setWeatherCode(String weatherCode) {
		this.weatherCode = weatherCode;
	}

	public String getTemp1() {
		return temp1;
	}

	public void setTemp1(String temp1) {
		this.temp1 = temp1;
	}

	public String getTemp2() {
		return temp2;
	}

	public void setTemp2(String temp2) {
		this.temp2 = temp2;
	}

	public String getWeatherDesp() {
		return weatherDesp;
	}

	public void setWeatherDesp(String weatherDesp) {
		this.weatherDesp = weatherDesp;
	}

	public String getPublishTime() {
		return publishTime;
	}

	public void setPublishTime(String publishTime) {
		this.publishTime = publishTime;
	}
	
	
}
