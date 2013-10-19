package com.szy.http;

import android.test.AndroidTestCase;
import android.util.Log;

/**
 * @author  coolszy
 * @blog    http://blog.csdn.net/coolszy
 */
public class BasicHttpClientTest extends AndroidTestCase
{
	private static final String TAG = "HttpClientTest";
	//10.0.2.2
	String urlStr = "http://192.168.1.100:8080/project/test";
	String params = "id=1&name=kuka";
	public void testHttpGet()
	{
		BasicHttpClient client = new BasicHttpClient();
		String response = client.httpGet(urlStr+"?"+params);
		Log.i(TAG, response);
	}

	public void testHttpPost()
	{
		BasicHttpClient client = new BasicHttpClient();
		String response = client.httpPost(urlStr, params);
		Log.i(TAG, response);
	}

}
