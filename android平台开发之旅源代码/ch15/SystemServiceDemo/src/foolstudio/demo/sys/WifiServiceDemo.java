package foolstudio.demo.sys;

import java.util.List;

import android.net.DhcpInfo;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;

public class WifiServiceDemo {
	//获取WiFi服务信息
	public static String getInfo(WifiManager service) {
		// TODO Auto-generated method stub
		StringBuffer sb = new StringBuffer();
		
		List<WifiConfiguration> networks = service.getConfiguredNetworks();
		for(int i = 0; i < networks.size(); ++i) {
			sb.append(getConfig(networks.get(i)));
		}
		
		WifiInfo info = service.getConnectionInfo();
		if(info != null) {
			sb.append(getInfo(info) );
		}
		
		DhcpInfo info2 = service.getDhcpInfo();
		if(info2 != null) {
			sb.append(getInfo(info2) );
		}
		
		List<ScanResult> results = service.getScanResults();
		if(results != null) {
			for(int j = 0; j < results.size(); ++j) {
				sb.append(getScanResult(results.get(j)));
			}
		}
		
		sb.append("\n状态：");
		switch(service.getWifiState() ) {
			case WifiManager.WIFI_STATE_DISABLED: {
				sb.append("不可用");
				break;
			}
			case WifiManager.WIFI_STATE_DISABLING: {
				sb.append("停用中");
				break;
			}
			case WifiManager.WIFI_STATE_ENABLED: {
				sb.append("可用");
				break;
			}
			case WifiManager.WIFI_STATE_ENABLING: {
				sb.append("启用中");
				break;
			}	
			case WifiManager.WIFI_STATE_UNKNOWN: {
				sb.append("未知");
				break;
			}			
		}
		
		if(service.isWifiEnabled() ) {
			sb.append("\nWi-Fi可用！");
		}
		else {
			sb.append("\nWi-Fi不可用！");			
		}
		
		return (sb.toString() );
	}

	//获取搜索结果
	private static String getScanResult(ScanResult scanResult) {
		// TODO Auto-generated method stub
		StringBuffer sb = new StringBuffer();
		
		sb.append("\n搜索结果：");
		sb.append("BSSID:"+scanResult.BSSID);
		sb.append("\ncapabilities:"+scanResult.capabilities);
		sb.append("\nfrequency:"+scanResult.frequency);
		sb.append("\nlevel:"+scanResult.level);
		sb.append("\nSSID:"+scanResult.SSID);	
		
		return (sb.toString() );
	}

	//获取DHCP信息
	private static String getInfo(DhcpInfo info) {
		// TODO Auto-generated method stub
		StringBuffer sb = new StringBuffer();
		
		sb.append("\nDhcp信息：");
		sb.append("dns1:"+info.dns1);
		sb.append("\ndns2:"+info.dns2);
		sb.append("\ngateway:"+info.gateway);
		sb.append("\nipAddress:"+info.ipAddress);
		sb.append("\nserverAddress:"+info.serverAddress);
		
		return (sb.toString() );
	}

	//获取Wi-Fi信息
	private static String getInfo(WifiInfo info) {
		// TODO Auto-generated method stub
		StringBuffer sb = new StringBuffer();
		
		sb.append("\nWi-Fi信息：");
		sb.append("BSSID:"+info.getBSSID() );
		sb.append("\nIp:"+info.getIpAddress() );
		sb.append("\nSpeed:"+info.getLinkSpeed() );
		sb.append("\nMac:"+info.getMacAddress() );
		sb.append("\nId:"+info.getNetworkId() );
		sb.append("\nRssi:"+info.getRssi() );
		sb.append("\nSSID:"+info.getSSID() );
		
		return (sb.toString() );
	}

	//获取配置信息
	private static String getConfig(WifiConfiguration wifiConfiguration) {
		// TODO Auto-generated method stub
		StringBuffer sb = new StringBuffer();
		
		sb.append("\n配置信息：");		
		sb.append("BSSID:" + wifiConfiguration.BSSID );
		sb.append("\nnetworkId:" + wifiConfiguration.networkId );
		sb.append("\nnetworkId:" + wifiConfiguration.preSharedKey );
		sb.append("\nSSID:" + wifiConfiguration.SSID );
		
		return (sb.toString() );
	}
};
