package com.henugao.coolweather.model;

/**
 * City表的实体类
 * @author henugao
 *
 */
public class City {

	//id字段
	private int id;
	
	//city的名字
	private String cityName;
	
	//city的代号
	private String cityCode;
	
    public int getProvinceId() {
		return provinceId;
	}

	public void setProvinceId(int provinceId) {
		this.provinceId = provinceId;
	}

	//city所对应的provinceId
	private int provinceId;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public String getCityCode() {
		return cityCode;
	}

	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}
	
	
}
