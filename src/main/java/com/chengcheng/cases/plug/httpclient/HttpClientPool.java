package com.chengcheng.cases.plug.httpclient;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.http.HttpEntity;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContextBuilder;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpRequestRetryHandler;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.util.EntityUtils;

@Api("HttpClient连接池")
@SuppressWarnings("deprecation")
public class HttpClientPool {

	// 池化管理
	private static PoolingHttpClientConnectionManager poolConnManager = null;
	private static CloseableHttpClient httpClient;
	// 请求器配置
	private static RequestConfig requestConfig;

	static {
		System.out.println("=====   HttpClent连接池初始化    =====");
		SSLContextBuilder builder = new SSLContextBuilder();
		try {
			builder.loadTrustMaterial(null, new TrustSelfSignedStrategy());
			SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(builder.build());

			// 配置同时支持HTTP和HTTPS协议
			Registry<ConnectionSocketFactory> socketFactoryRegistry = RegistryBuilder.<ConnectionSocketFactory>create()
					.register("http", PlainConnectionSocketFactory.getSocketFactory()).register("https", sslsf).build();
			// 初始化连接管理器
			poolConnManager = new PoolingHttpClientConnectionManager(socketFactoryRegistry);
			// 设置最大连接数
			poolConnManager.setMaxTotal(200);
			// 设置路由
			poolConnManager.setDefaultMaxPerRoute(50);
			// 设置默认超时限制初始化requestConfig
			int socketTimeout = 60000;
			int connectTimeout = 60000;
			int connectionRequestTimeout = 60000;
			requestConfig = RequestConfig.custom().setConnectionRequestTimeout(connectionRequestTimeout)
					.setSocketTimeout(socketTimeout).setConnectTimeout(connectTimeout).build();
			// 初始化HttpClient
			httpClient = getConnection();
			System.out.println("===== HttpClent连接池初始化结束  =====");
			System.out.println("---------------------------------");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (KeyStoreException e) {
			e.printStackTrace();
		} catch (KeyManagementException e) {
			e.printStackTrace();
		}

	}

	@ApiOperation("创建HttpClient对象")
	public static CloseableHttpClient getConnection() {

		CloseableHttpClient httpClient = HttpClients.custom().setConnectionManager(poolConnManager) // 连接池管理
				.setDefaultRequestConfig(requestConfig) // 设置请求配置
				.setRetryHandler(new DefaultHttpRequestRetryHandler(0, false)) // 设置重试次数
				.build();

		return httpClient;
	}

	@ApiOperation("HttpGet请求方法")
	public static String httpGet(String url) {

		HttpGet httpGet = new HttpGet(url);
		CloseableHttpResponse response = null;
		String result = "";
		try {
			response = httpClient.execute(httpGet);
			HttpEntity entity = response.getEntity();
			result = EntityUtils.toString(entity, "utf-8");
			EntityUtils.consume(entity);
//			System.out.println("数据集结果" + result);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (response != null) {
				try {
					response.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		return result;
	}

/*	@ApiOperation("测试主函数")
	public static void main(String[] args) {
//		HttpClientPool.httpGet("http://10.48.146.246/superset/explore_json/table/71/?viz_type=pivot_table&granularity_sqla=%E5%87%BA%E7%94%9F%E6%97%A5%E6%9C%9F&since=1900-01-01&until=now&where=%28UPPER%28hyzk%29+like+%27%25%E5%B7%B2%E5%A9%9A%25%27%29&groupby=%E5%A9%9A%E5%A7%BB%E7%8A%B6%E5%86%B5&metrics=%E6%95%B0%E9%87%8F&columns=%E5%B9%B4%E9%BE%84&row_limit=200&y_axis_format=0r&slice_name=%E5%B1%B1%E4%B8%9C%E5%B8%B8%E4%BD%8F%E4%BA%BA%E5%8F%A3%E4%BF%A1%E6%81%AF&AvoidLogin=True");
		String[] urisToGet = {
				"http://10.48.146.246/superset/explore_json/table/71/?viz_type=pivot_table&granularity_sqla=%E5%87%BA%E7%94%9F%E6%97%A5%E6%9C%9F&since=1900-01-01&until=now&where=%28UPPER%28hyzk%29+like+%27%25%E5%B7%B2%E5%A9%9A%25%27%29&groupby=%E5%A9%9A%E5%A7%BB%E7%8A%B6%E5%86%B5&metrics=%E6%95%B0%E9%87%8F&columns=%E5%B9%B4%E9%BE%84&row_limit=200&y_axis_format=0r&slice_name=%E5%B1%B1%E4%B8%9C%E5%B8%B8%E4%BD%8F%E4%BA%BA%E5%8F%A3%E4%BF%A1%E6%81%AF&AvoidLogin=True",
				"http://10.48.146.246/superset/explore_json/table/71/?viz_type=pivot_table&granularity_sqla=%E5%87%BA%E7%94%9F%E6%97%A5%E6%9C%9F&since=1900-01-01&until=now&where=%28UPPER%28hyzk%29+like+%27%25%E5%B7%B2%E5%A9%9A%25%27%29&groupby=%E5%A9%9A%E5%A7%BB%E7%8A%B6%E5%86%B5&metrics=%E6%95%B0%E9%87%8F&columns=%E5%B9%B4%E9%BE%84&row_limit=200&y_axis_format=0r&slice_name=%E5%B1%B1%E4%B8%9C%E5%B8%B8%E4%BD%8F%E4%BA%BA%E5%8F%A3%E4%BF%A1%E6%81%AF&AvoidLogin=True",
				"http://10.48.146.246/superset/explore_json/table/71/?viz_type=pivot_table&granularity_sqla=%E5%87%BA%E7%94%9F%E6%97%A5%E6%9C%9F&since=1900-01-01&until=now&where=%28UPPER%28hyzk%29+like+%27%25%E5%B7%B2%E5%A9%9A%25%27%29&groupby=%E5%A9%9A%E5%A7%BB%E7%8A%B6%E5%86%B5&metrics=%E6%95%B0%E9%87%8F&columns=%E5%B9%B4%E9%BE%84&row_limit=200&y_axis_format=0r&slice_name=%E5%B1%B1%E4%B8%9C%E5%B8%B8%E4%BD%8F%E4%BA%BA%E5%8F%A3%E4%BF%A1%E6%81%AF&AvoidLogin=True",
				"http://10.48.146.246/superset/explore_json/table/71/?viz_type=pivot_table&granularity_sqla=%E5%87%BA%E7%94%9F%E6%97%A5%E6%9C%9F&since=1900-01-01&until=now&where=%28UPPER%28hyzk%29+like+%27%25%E5%B7%B2%E5%A9%9A%25%27%29&groupby=%E5%A9%9A%E5%A7%BB%E7%8A%B6%E5%86%B5&metrics=%E6%95%B0%E9%87%8F&columns=%E5%B9%B4%E9%BE%84&row_limit=200&y_axis_format=0r&slice_name=%E5%B1%B1%E4%B8%9C%E5%B8%B8%E4%BD%8F%E4%BA%BA%E5%8F%A3%E4%BF%A1%E6%81%AF&AvoidLogin=True",
				"http://10.48.146.246/superset/explore_json/table/71/?viz_type=pivot_table&granularity_sqla=%E5%87%BA%E7%94%9F%E6%97%A5%E6%9C%9F&since=1900-01-01&until=now&where=%28UPPER%28hyzk%29+like+%27%25%E5%B7%B2%E5%A9%9A%25%27%29&groupby=%E5%A9%9A%E5%A7%BB%E7%8A%B6%E5%86%B5&metrics=%E6%95%B0%E9%87%8F&columns=%E5%B9%B4%E9%BE%84&row_limit=200&y_axis_format=0r&slice_name=%E5%B1%B1%E4%B8%9C%E5%B8%B8%E4%BD%8F%E4%BA%BA%E5%8F%A3%E4%BF%A1%E6%81%AF&AvoidLogin=True"
		};

		GetThread[] threads = new GetThread[urisToGet.length];
		for (int i = 0; i < threads.length; i++) {
			threads[i] = new GetThread(httpClient, urisToGet[i]);
		}

		for (Thread tmp : threads) {
			tmp.start();
		}
	}*/


}
