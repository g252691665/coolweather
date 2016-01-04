package com.henugao.coolweather.db.dao;

import java.util.ArrayList;
import java.util.List;

import com.henugao.coolweather.db.CoolWeatherOpenHelper;
import com.henugao.coolweather.model.City;
import com.henugao.coolweather.model.County;
import com.henugao.coolweather.model.Province;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class CoolWeatherDB {
	
	/**
	 * 数据库名字
	 */
	private static final String DB_NAME = "cool_weather";
	
	/**
	 * 数据库的版本
	 */
	private static final int VERSION = 1;

	private static CoolWeatherDB coolWeatherDB;
	
	private CoolWeatherOpenHelper dbHelper;

	private SQLiteDatabase db;
	
	private CoolWeatherDB(Context context) {
		dbHelper = new CoolWeatherOpenHelper(context, DB_NAME, null, VERSION);
		db = dbHelper.getWritableDatabase();
	
	}
	
	/**
	 * 获取coolWeatherDB的实例
	 * @param context
	 * @return
	 */
	public synchronized static CoolWeatherDB getInstance(Context context) {
		if (coolWeatherDB == null) {
			coolWeatherDB = new CoolWeatherDB(context);
		}
		return coolWeatherDB;
	}
	/**
	 * 将province实例化存储到数据库
	 * @param province
	 */
	public void saveProvince(Province province) {
		if (province !=  null) {
			ContentValues values = new ContentValues();
			values.put("province_name", province.getProvinceName());
			values.put("province_code", province.getProvinceCode());
			db.insert("Province", null, values);
		}
	}
	/**
	 * 获得所有的省份
	 * @return所有省份的列表
	 */
	public List<Province> loadProvince() {
		List<Province> provinces = new ArrayList<Province>();
		Cursor cursor = db.query("Province", null, null, null, null, null, null);
		while (cursor.moveToNext()) {
			Province province = new Province();
			province.setId(cursor.getInt(cursor.
					getColumnIndex("id")));
			province.setProvinceName(cursor.getString(cursor.
					getColumnIndex("province_name")));
			province.setProvinceCode(cursor.getString(cursor.
					getColumnIndex("province_code")));
			
			provinces.add(province);
			
		}
		if (cursor !=null) {
			cursor.close();
		}
		return provinces;
	}
	
	/**
	 * 将city实例化存储到数据库
	 * @param city
	 */
	public void saveCity(City city) {
		if (city !=  null) {
			ContentValues values = new ContentValues();
			values.put("city_name", city.getCityName());
			values.put("city_code", city.getCityCode());
			db.insert("City", null, values);
		}
	}
	/**
	 * 获得某个省份的所有的city
	 * @return所有city的列表
	 */
	public List<City> loadCities(int provinceId) {
		List<City> cities = new ArrayList<City>();
		Cursor cursor = db.query("City", null,"province_id = ?", new String[]{String.valueOf(provinceId)}, null, null, null);
		while (cursor.moveToNext()) {
			City city = new City();
			city.setId(cursor.getInt(cursor.
					getColumnIndex("id")));
			city.setCityName(cursor.getString(cursor.
					getColumnIndex("city_name")));
			city.setCityCode(cursor.getString(cursor.
					getColumnIndex("city_code")));
			city.setProvinceId(cursor.getInt(cursor.
					getColumnIndex("province_id")));
			cities.add(city);
		}
		if (cursor !=null) {
			cursor.close();
		}
		return cities;
	}
	
	/**
	 * 将county实例化存储到数据库
	 * @param county
	 */
	public void saveCounty(County county) {
		if (county !=  null) {
			ContentValues values = new ContentValues();
			values.put("county_name", county.getCountyName());
			values.put("county_code", county.getCountyCode());
			db.insert("County", null, values);
		}
	}
	/**
	 * 获得某个city的所有的county
	 * @return所有county的列表
	 */
	public List<County> loadCounties(int cityId) {
		List<County> counties = new ArrayList<County>();
		Cursor cursor = db.query("County", null,"city_id = ?", new String[]{String.valueOf(cityId)}, null, null, null);
		while (cursor.moveToNext()) {
			County county = new County();
			county.setId(cursor.getInt(cursor.
					getColumnIndex("id")));
			county.setCountyName(cursor.getString(cursor.
					getColumnIndex("county_name")));
			county.setCountyCode(cursor.getString(cursor.
					getColumnIndex("county_code")));
			county.setCityId(cursor.getInt(cursor.
					getColumnIndex("city_id")));
			counties.add(county);
		}
		if (cursor !=null) {
			cursor.close();
		}
		return counties;
	}
	

}
