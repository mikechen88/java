package com.szy.status;

import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.util.Log;

/**
 * @author coolszy
 * @blog http://blog.csdn.net/coolszy
 */
public class StatusService extends IntentService
{
	private static final String TAG = "StatusService";

	// private static final int KUKA = 0;

	public StatusService()
	{
		super("StatusService");
	}

	@Override
	protected void onHandleIntent(Intent intent)
	{
		Log.i(TAG, "开始下载....");
		showNotification(false);
		try
		{
			Thread.sleep(10000);
			showNotification(true);
		} catch (InterruptedException e)
		{
			e.printStackTrace();
		}
		Log.i(TAG, "程序下载完毕");
	}

	private void showNotification(boolean finish)
	{
		Notification notification;
		NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
		Intent intent = new Intent(this, MainActivity.class);
		PendingIntent contentIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
		if (!finish)
		{
			notification = new Notification(R.drawable.head, "开始下载", System.currentTimeMillis());
			notification.setLatestEventInfo(this, "下载", "正在下载中", contentIntent);
		}
		else
		{
			notification = new Notification(R.drawable.head, "下载完毕", System.currentTimeMillis());
			notification.setLatestEventInfo(this, "下载", "程序下载完毕", contentIntent);
		}
		notification.defaults=Notification.DEFAULT_ALL;
		manager.notify(R.layout.main, notification);
		
	}

}
