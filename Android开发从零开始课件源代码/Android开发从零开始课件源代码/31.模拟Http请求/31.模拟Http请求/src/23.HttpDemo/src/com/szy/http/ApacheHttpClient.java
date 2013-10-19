package com.szy.http;

import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;


/**
 *@author coolszy
 *@date 2011-5-29
 *@blog http://blog.csdn.net/coolszy
 */

/**
 * 以同步方式发送Http请求
 */
public class ApacheHttpClient
{

	/**
	 * 以Get方式发送请求
	 * @param url 请求路径
	 * @return
	 */
	public String httpGet(String url)
	{
		String response = null;
		HttpClient httpclient = new DefaultHttpClient();
		//创建HttpGet对象
		HttpGet httpGet = new HttpGet(url);
		HttpResponse httpResponse;
		try
		{
			//使用execute方法发送HTTP GET请求，并返回HttpResponse对象
			httpResponse = httpclient.execute(httpGet);
			int statusCode = httpResponse.getStatusLine().getStatusCode();
			if(statusCode==HttpStatus.SC_OK)
			{
				//获得返回结果
				response = EntityUtils.toString(httpResponse.getEntity());
			}
			else
			{
				response = "返回码："+statusCode;
			}
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		return response;
		
	}

	/**
	 * 以Post方式发送请求
	 * @param url 请求地址
	 * @param params 参数 ，Post方式必须用NameValuePair[]阵列储存参数
	 * @return
	 * @throws Exception
	 */
	public String httpPost(String url, List<NameValuePair> params) throws Exception
	{
		String response = null;
		HttpClient httpclient = new DefaultHttpClient();
		//创建HttpPost对象
		HttpPost httppost = new HttpPost(url);
		try
		{
			//设置httpPost请求参数
			httppost.setEntity(new UrlEncodedFormEntity(params,HTTP.UTF_8));
			//使用execute方法发送HTTP Post请求，并返回HttpResponse对象
			HttpResponse httpResponse = httpclient.execute(httppost);
			int statusCode = httpResponse.getStatusLine().getStatusCode();
			if(statusCode==HttpStatus.SC_OK)
			{
				//获得返回结果
				response = EntityUtils.toString(httpResponse.getEntity());
			}
			else
			{
				response = "返回码："+statusCode;
			}
		}catch (Exception e)
		{
			e.printStackTrace();
		}
		return response;
	}
}
