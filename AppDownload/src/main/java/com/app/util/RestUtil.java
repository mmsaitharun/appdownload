package com.app.util;

import org.apache.commons.codec.binary.Base64;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

/**
 * @author INC00718
 *
 */
public class RestUtil {
	public static String callRest(String url) {
		HttpClient httpClient = HttpClientBuilder.create().build();
		String json = "";
		String userPassword = "WORKBOX" + ":" + "Incture1234567891013";
		byte[] encodeBase64 = Base64.encodeBase64(userPassword.getBytes());
		try {
			HttpGet httpGet = new HttpGet(url);
			httpGet.addHeader("content-type", "application/json; charset=UTF-8");
			httpGet.addHeader("Authorization", "BASIC " + new String(encodeBase64));
			HttpResponse response = httpClient.execute(httpGet);
			json = EntityUtils.toString(response.getEntity());
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("Exception : "+e.getMessage());
		}
		return json;
	}
	
	public static void main(String[] args) {
		System.out.println(RestUtil.callRest("https://a65dee8964f1.us2.hana.ondemand.com/WORKBOX/GetFromAPP_FILE_QA.xsjs?fileType='IPA'"));
	}
}
