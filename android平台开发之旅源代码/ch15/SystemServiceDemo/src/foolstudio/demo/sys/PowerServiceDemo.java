package foolstudio.demo.sys;

import android.os.PowerManager;
import android.os.PowerManager.WakeLock;
import android.util.Log;

public class PowerServiceDemo {
	//获取能源服务管理信息
	public static String getInfo(PowerManager service) {
		// TODO Auto-generated method stub
		StringBuffer sb = new StringBuffer();
		
		WakeLock locker = service.newWakeLock(PowerManager.ACQUIRE_CAUSES_WAKEUP | 
											  PowerManager.SCREEN_DIM_WAKE_LOCK, 
											  "PowerServiceDemo");
		locker.acquire();
		
		Log.d("PowerServiceDemo", "Lock acquire.");
		for(int i = 0; i < 10000000; ++i);
		
		locker.release();
		
		Log.d("PowerServiceDemo", "Lock release.");
		
		return (sb.toString() );
	}
};
