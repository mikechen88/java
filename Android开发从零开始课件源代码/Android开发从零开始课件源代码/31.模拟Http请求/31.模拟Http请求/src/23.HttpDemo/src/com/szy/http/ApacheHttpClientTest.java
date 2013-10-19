package com.szy.http;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import android.test.AndroidTestCase;
import android.util.Log;

/**
 * @author  coolszy
 * @blog    http://blog.csdn.net/coolszy
 */
public class ApacheHttpClientTest extends AndroidTestCase
{
	private static final String TAG = "ApacheHttpClientTest";
	
	String response = "";
	public void testHttpGet()
	{
		String url = "http://192.168.1.100:8080/project/test?id=1&name=kuka";
		ApacheHttpClient client = new ApacheHttpClient();
		try
		{
			response = client.httpGet(url);
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		Log.i(TAG,response);
	}

	public void testHttpPost()
	{
		String url = "http://192.168.1.100:8080/project/test";
		ApacheHttpClient client = new ApacheHttpClient();
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("id","1"));
		params.add(new BasicNameValuePair("name","kuka"));
		try
		{
			response = client.httpPost(url, params);
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		Log.i(TAG, "####"+response);
	}

}
