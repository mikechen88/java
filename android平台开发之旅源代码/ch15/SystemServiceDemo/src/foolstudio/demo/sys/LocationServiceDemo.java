package foolstudio.demo.sys;

import java.util.List;

import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.location.LocationProvider;

public class LocationServiceDemo {
	//获取位置管理信息
	public static String getInfo(LocationManager service) {
		// TODO Auto-generated method stub
		StringBuffer sb = new StringBuffer();
		
		List<String> providers = service.getAllProviders();
		//List<String> providers = service.getProviders(true);
		int count = providers.size();
		
		//列举所有服务提供者
		for(int i = 0; i < count; ++i) {
			sb.append("Provider #" + i + '/' + count +':');
			sb.append(providers.get(i) );
			sb.append('\n');
		}
		
		//判断服务提供者是否可用
		if(service.isProviderEnabled(providers.get(0)) == true) {
			sb.append(providers.get(0) + " enabled\n");
		}
		else {
			sb.append(providers.get(0) + " disabled\n");
		}
		
		LocationProvider provider = service.getProvider(providers.get(0));
		
		switch(provider.getAccuracy() ) {
			case Criteria.ACCURACY_COARSE: {
				sb.append("Accuracy:COARSE");
				break;
			}
			case Criteria.ACCURACY_FINE: {
				sb.append("Accuracy:FINE");
				break;
			}			
		}
		
		sb.append("\nName:" + provider.getName() );
		
		switch(provider.getPowerRequirement() ) {
			case Criteria.POWER_HIGH: {
				sb.append("\nPowerRequirement:HIGH");
				break;
			}
			case Criteria.POWER_MEDIUM: {
				sb.append("\nPowerRequirement:MEDIUM");
				break;
			}			
			case Criteria.POWER_LOW: {
				sb.append("\nPowerRequirement:LOW");
				break;
			}			
		}
		
		sb.append("\nhasMonetaryCost:" + provider.hasMonetaryCost() );
		sb.append("\nrequiresCell:" + provider.requiresCell() );
		sb.append("\nrequiresNetwork:" + provider.requiresNetwork() );
		sb.append("\nequiresSatellite:" + provider.requiresSatellite() );
		sb.append("\nsupportsAltitude:" + provider.supportsAltitude() );
		sb.append("\nsupportsBearing:" + provider.supportsBearing() );
		sb.append("\nsupportsSpeed:" + provider.supportsSpeed() );
		sb.append('\n');
		
		//获取定位信息
		Location loc = new Location(providers.get(0) );
		if(loc != null) {
			sb.append("Lat:" + loc.getLatitude() );
			sb.append(", Lon:" + loc.getLongitude() );
			sb.append(", Alt:" + loc.getAltitude() );
		}
		
		return (sb.toString() );
	}
};
