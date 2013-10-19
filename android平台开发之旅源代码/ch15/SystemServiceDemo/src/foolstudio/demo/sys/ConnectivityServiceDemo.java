package foolstudio.demo.sys;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.NetworkInfo.DetailedState;

public class ConnectivityServiceDemo {
	//获取连接管理信息
	public static String getInfo(ConnectivityManager service) {
		// TODO Auto-generated method stub
		StringBuffer sb = new StringBuffer();
		
		NetworkInfo actInfo = service.getActiveNetworkInfo();
		sb.append(getInfo(actInfo) );
		
		NetworkInfo[] infos = service.getAllNetworkInfo();
		
		for(int i = 0; i < infos.length; ++i) {
			sb.append(getInfo(infos[i]) );
		}
		
		//boolean isBkgDataSetting = service.getBackgroundDataSetting();
		//NetworkInfo info = 
			service.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
		NetworkInfo info = 
			service.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
		sb.append(getInfo(info) );
		//int preference = service.getNetworkPreference();
		
		return (sb.toString() );
	}

	//获取网络信息
	private static String getInfo(NetworkInfo info) {
		// TODO Auto-generated method stub
		StringBuffer sb = new StringBuffer();
		
		DetailedState state = info.getDetailedState();
		sb.append(getState(state) );
		
		sb.append("; ExtInf=" + info.getExtraInfo() );
		sb.append("; Reason=" + info.getReason() );
		NetworkInfo.State state2 = info.getState();
		sb.append("; Name=" + state2.name() );
		sb.append("; SubType=" + info.getSubtype() );
		sb.append("; Name=" + info.getSubtypeName() );
		sb.append("; Type=" + info.getType() );
		sb.append("; Name=" + info.getTypeName() );
		sb.append('\n');
		/*
		info.isAvailable();
		info.isConnected();
		info.isConnectedOrConnecting();
		info.isFailover();
		info.isRoaming();
		*/
		
		return (sb.toString() );
	}

	//获取详细状态
	private static String getState(DetailedState state) {
		// TODO Auto-generated method stub
		StringBuffer sb = new StringBuffer();
		
		sb.append("Name=");
		sb.append(state.name() );
		sb.append("; Ordi=");
		sb.append(state.ordinal() );
		sb.append('\n');
		
		return (sb.toString() );
	}
};
