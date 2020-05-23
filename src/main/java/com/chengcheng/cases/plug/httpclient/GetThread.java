package com.chengcheng.cases.plug.httpclient;

import java.io.IOException;

import io.swagger.annotations.Api;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;

@Api("线程测试类")
public class GetThread extends Thread {

	private CloseableHttpClient httpClient;
	private String url;

	public GetThread(CloseableHttpClient client, String url) {
		httpClient = client;
		this.url = url;
	}

	public void run() {
		for (int i = 0; i < 3; i++) {
			HttpGet httpGet = new HttpGet(url);
			CloseableHttpResponse response = null;

			try {
				response = httpClient.execute(httpGet);
				HttpEntity entity = response.getEntity();
				String result = EntityUtils.toString(entity, "utf-8");
				System.out.println(Thread.currentThread().getName() + "-----------------------");
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				try {
					if (response != null) {
						response.close();
					}
					if (httpGet != null) {
						httpGet.releaseConnection();
					}

				} catch (IOException e) {
					e.printStackTrace();
				}
			}

		}
	}
	
}
