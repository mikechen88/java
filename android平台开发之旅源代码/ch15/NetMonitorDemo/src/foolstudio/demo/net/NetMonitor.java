package foolstudio.demo.net;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

public class NetMonitor extends BroadcastReceiver {
	@Override
	public void onReceive(Context ctx, Intent intent) {
		//��ȡ��������
		Bundle data = intent.getExtras();
		String reason = "δ֪ԭ��";
		if(data != null) {
			//��ȡԭ��
			reason = data.getString("reason");
		}
			
		Toast.makeText(ctx, "����״̬��"+reason+"���ı䣡", 
					   Toast.LENGTH_LONG).show();
	}
};
