package com.henugao.coolweather.model;
/**
 * Count表对应的实体类
 * @author henugao
 *
 */
public class County {
	
	//id字段
	private int id;
	
	//county名字
	private String countyName;
	
	//county的代号
	private String countyCode;

	//county对应的cityId
	private int cityId;
	
	public int getCityId() {
		return cityId;
	}

	public void setCityId(int cityId) {
		this.cityId = cityId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCountyName() {
		return countyName;
	}

	public void setCountyName(String countyName) {
		this.countyName = countyName;
	}

	public String getCountyCode() {
		return countyCode;
	}

	public void setCountyCode(String countyCode) {
		this.countyCode = countyCode;
	}
}
