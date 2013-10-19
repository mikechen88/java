package foolstudio.demo.sys;

import android.app.KeyguardManager.OnKeyguardExitResult;
import android.util.Log;

public class KeyguardExitListener implements OnKeyguardExitResult {
	@Override
	public void onKeyguardExitResult(boolean success) {
		// TODO Auto-generated method stub
		if(success) {
			Log.d("KeyguardExitListener", "Exit successfully!");
		}
		else {
			Log.d("KeyguardExitListener", "Exit failed!");
		}
	}
};
