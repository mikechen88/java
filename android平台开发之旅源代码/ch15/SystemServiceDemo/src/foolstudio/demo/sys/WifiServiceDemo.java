package foolstudio.demo.sys;

import java.util.List;

import android.net.DhcpInfo;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;

public class WifiServiceDemo {
	//��ȡWiFi������Ϣ
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
		
		sb.append("\n״̬��");
		switch(service.getWifiState() ) {
			case WifiManager.WIFI_STATE_DISABLED: {
				sb.append("������");
				break;
			}
			case WifiManager.WIFI_STATE_DISABLING: {
				sb.append("ͣ����");
				break;
			}
			case WifiManager.WIFI_STATE_ENABLED: {
				sb.append("����");
				break;
			}
			case WifiManager.WIFI_STATE_ENABLING: {
				sb.append("������");
				break;
			}	
			case WifiManager.WIFI_STATE_UNKNOWN: {
				sb.append("δ֪");
				break;
			}			
		}
		
		if(service.isWifiEnabled() ) {
			sb.append("\nWi-Fi���ã�");
		}
		else {
			sb.append("\nWi-Fi�����ã�");			
		}
		
		return (sb.toString() );
	}

	//��ȡ�������
	private static String getScanResult(ScanResult scanResult) {
		// TODO Auto-generated method stub
		StringBuffer sb = new StringBuffer();
		
		sb.append("\n���������");
		sb.append("BSSID:"+scanResult.BSSID);
		sb.append("\ncapabilities:"+scanResult.capabilities);
		sb.append("\nfrequency:"+scanResult.frequency);
		sb.append("\nlevel:"+scanResult.level);
		sb.append("\nSSID:"+scanResult.SSID);	
		
		return (sb.toString() );
	}

	//��ȡDHCP��Ϣ
	private static String getInfo(DhcpInfo info) {
		// TODO Auto-generated method stub
		StringBuffer sb = new StringBuffer();
		
		sb.append("\nDhcp��Ϣ��");
		sb.append("dns1:"+info.dns1);
		sb.append("\ndns2:"+info.dns2);
		sb.append("\ngateway:"+info.gateway);
		sb.append("\nipAddress:"+info.ipAddress);
		sb.append("\nserverAddress:"+info.serverAddress);
		
		return (sb.toString() );
	}

	//��ȡWi-Fi��Ϣ
	private static String getInfo(WifiInfo info) {
		// TODO Auto-generated method stub
		StringBuffer sb = new StringBuffer();
		
		sb.append("\nWi-Fi��Ϣ��");
		sb.append("BSSID:"+info.getBSSID() );
		sb.append("\nIp:"+info.getIpAddress() );
		sb.append("\nSpeed:"+info.getLinkSpeed() );
		sb.append("\nMac:"+info.getMacAddress() );
		sb.append("\nId:"+info.getNetworkId() );
		sb.append("\nRssi:"+info.getRssi() );
		sb.append("\nSSID:"+info.getSSID() );
		
		return (sb.toString() );
	}

	//��ȡ������Ϣ
	private static String getConfig(WifiConfiguration wifiConfiguration) {
		// TODO Auto-generated method stub
		StringBuffer sb = new StringBuffer();
		
		sb.append("\n������Ϣ��");		
		sb.append("BSSID:" + wifiConfiguration.BSSID );
		sb.append("\nnetworkId:" + wifiConfiguration.networkId );
		sb.append("\nnetworkId:" + wifiConfiguration.preSharedKey );
		sb.append("\nSSID:" + wifiConfiguration.SSID );
		
		return (sb.toString() );
	}
};
