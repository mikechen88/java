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
		Log.i(TAG, "��ʼ����....");
		showNotification(false);
		try
		{
			Thread.sleep(10000);
			showNotification(true);
		} catch (InterruptedException e)
		{
			e.printStackTrace();
		}
		Log.i(TAG, "�����������");
	}

	private void showNotification(boolean finish)
	{
		Notification notification;
		NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
		Intent intent = new Intent(this, MainActivity.class);
		PendingIntent contentIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
		if (!finish)
		{
			notification = new Notification(R.drawable.head, "��ʼ����", System.currentTimeMillis());
			notification.setLatestEventInfo(this, "����", "����������", contentIntent);
		}
		else
		{
			notification = new Notification(R.drawable.head, "�������", System.currentTimeMillis());
			notification.setLatestEventInfo(this, "����", "�����������", contentIntent);
		}
		notification.defaults=Notification.DEFAULT_ALL;
		manager.notify(R.layout.main, notification);
		
	}

}
