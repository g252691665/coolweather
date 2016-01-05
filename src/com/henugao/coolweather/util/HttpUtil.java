package com.henugao.coolweather.util;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpUtil {
	
	public static void sendHttpRequest(final String address,final HttpCallbackListener listener) {
		new Thread(){
			public void run() {
				HttpURLConnection connection = null;
				try {
					URL url = new URL(address);
					connection = (HttpURLConnection) url.openConnection();
					connection.setRequestMethod("GET");
					connection.setReadTimeout(5000);
					connection.setConnectTimeout(5000);
					InputStream is = connection.getInputStream();
					BufferedReader br = new BufferedReader(new InputStreamReader(is));
					StringBuilder response = new StringBuilder();
					String line ;
					while((line = br.readLine()) != null) {
						response.append(line);
					}
					if (listener != null) {
						//回调onFinish方法
						listener.onFinish(response.toString());
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					if (listener != null) {
						//回调onError方法
						listener.onError(e);
					}
				} finally {
					if (connection !=null) {
						connection.disconnect();
					}
				}
				
			};
		}.start();
	}

}
