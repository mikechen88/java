package foolstudio.demo.service;

import foolstudio.demo.service.IEchoService;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;

public class EchoService extends Service {
	
    private final IEchoService.Stub mBinder = new IEchoService.Stub() {
		@Override
		public String getEcho(String call) throws RemoteException {
			// TODO Auto-generated method stub
			return ("Hi, " + call + "!");
		}
    };  

	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		if(EchoService.class.getName().equals(intent.getAction())) {
			return (mBinder);
		}	
		
		return null;
	}
};
