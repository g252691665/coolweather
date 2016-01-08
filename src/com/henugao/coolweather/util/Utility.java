package com.henugao.coolweather.util;

import android.text.TextUtils;

import com.henugao.coolweather.db.dao.CoolWeatherDB;
import com.henugao.coolweather.model.City;
import com.henugao.coolweather.model.County;
import com.henugao.coolweather.model.Province;

/**
 * 解析和处理服务器返回的数据
 * @author henugao
 *
 */
public class Utility {
	
	/**
	 * 解析和处理服务器返回的省级数据
	 * @param coolWeatherDB
	 * @param response
	 * @return
	 */
	public static boolean handleProvincesResponse(CoolWeatherDB
			coolWeatherDB
			,String response) {
		if(!TextUtils.isEmpty(response)) {
			//1、如果用“.”作为分隔的话,必须是如下写法,String.split("\\."),这样才能正确的分隔开,不能用String.split(".");
			//2、如果用“|”作为分隔的话,必须是如下写法,String.split("\\|"),这样才能正确的分隔开,不能用String.split("|");
			//“.”和“|”都是转义字符,必须得加"\\";
			//3、如果在一个字符串中有多个分隔符,可以用“|”作为连字符,比如,“acount=? and uu =? or n=?”,把三个都分隔出来,可以用String.split("and|or");
			String[] allProvinces = response.split(",");
			if (allProvinces !=null && allProvinces.length > 0) {
				for (String province : allProvinces) {
					String[] split = province.split("\\|");
					Province pro = new Province();
					pro.setProvinceCode(split[0]);
					pro.setProvinceName(split[1]);
					coolWeatherDB.saveProvince(pro);
				}
				return true;
			}
		}
		return false;
	}
	/**
	 * 
	 * 解析和处理服务器返回的city数据
	 * @param coolWeatherDB
	 * @param response
	 * @param provinceId
	 * @return
	 */
	public static boolean handleCitiesResponse(CoolWeatherDB coolWeatherDB,
			String response,int provinceId){
		if (!TextUtils.isEmpty(response)) {
			System.out.println("===>handleCitiesResponse"+response);
			String[] allCities = response.split(",");
			if (allCities !=null && allCities.length > 0 ) {
				for (String city : allCities) {
					String[] split = city.split("\\|");
					City c = new City();
					c.setCityCode(split[0]);
					System.out.println("===>handleCitiesResponse:split[0]"+split[0]);
					c.setCityName(split[1]);
					c.setProvinceId(provinceId);
					coolWeatherDB.saveCity(c);
				}
				return true;
			}
		}
		return false;
	}
	
	/**
	 * 
	 * 解析和处理服务器返回的county数据
	 * @param coolWeatherDB
	 * @param response
	 * @param cityId
	 * @return
	 */
	public static boolean handleCountiesResponse(CoolWeatherDB coolWeatherDB,
			String response,int cityId){
		if (!TextUtils.isEmpty(response)) {
			String[] allCounties = response.split(",");
			if (allCounties !=null && allCounties.length > 0 ) {
				for (String county : allCounties) {
					String[] split = county.split("\\|");
					County c = new County();
					c.setCountyCode(split[0]);
					c.setCountyName(split[1]);
					System.out.println("===>"+split[1]);
					c.setCityId(cityId);
					coolWeatherDB.saveCounty(c);
				}
				return true;
			}
		}
		return false;
	}

}
