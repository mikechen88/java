package foolstudio.demo.sys;

import android.app.KeyguardManager;

public class KeyguardServiceDemo {
	//��ȡ���̷�����Ϣ
	public static String getInfo(KeyguardManager service) {
		// TODO Auto-generated method stub
		StringBuffer sb = new StringBuffer();
		
		KeyguardManager.KeyguardLock locker = 
			service.newKeyguardLock("KeyguardServiceDemo");
		locker.disableKeyguard();
		locker.reenableKeyguard();
		
		return (sb.toString() );
	}
};
