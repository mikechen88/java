package foolstudio.demo.net;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

public class NetMonitor extends BroadcastReceiver {
	@Override
	public void onReceive(Context ctx, Intent intent) {
		//获取附加数据
		Bundle data = intent.getExtras();
		String reason = "未知原因";
		if(data != null) {
			//获取原因
			reason = data.getString("reason");
		}
			
		Toast.makeText(ctx, "连接状态（"+reason+"）改变！", 
					   Toast.LENGTH_LONG).show();
	}
};
