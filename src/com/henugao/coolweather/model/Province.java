package com.henugao.coolweather.model;

/**
 * Province表对应的实体类
 * @author henugao
 *
 */
public class Province {
	
	//id字段
	private int id;
	
	//省的名字
	private String provinceName;
	
	//省级代号
	private String provinceCode;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getProvinceName() {
		return provinceName;
	}

	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
	}

	public String getProvinceCode() {
		return provinceCode;
	}

	public void setProvinceCode(String provinceCode) {
		this.provinceCode = provinceCode;
	}
	
	
}
