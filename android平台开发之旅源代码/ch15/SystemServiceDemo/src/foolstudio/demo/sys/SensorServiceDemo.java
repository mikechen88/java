package foolstudio.demo.sys;

import java.util.List;

import android.hardware.Sensor;
import android.hardware.SensorManager;

public class SensorServiceDemo {
	//获取传感器服务信息
	public static String getInfo(SensorManager service) {
		// TODO Auto-generated method stub
		StringBuffer sb = new StringBuffer();
		
		/*		
		Sensor sensor = service.getDefaultSensor(Sensor.TYPE_ALL);
		if(sensor != null) {
			sb.append(getInfo(sensor) );
		}
		*/
		
		List<Sensor> sensors = service.getSensorList(Sensor.TYPE_ALL);
		for(int i = 0; i < sensors.size(); ++i) {
			sb.append(getInfo(sensors.get(i)) );
		}
		
		return (sb.toString() );
	}

	//获取传感器信息
	private static String getInfo(Sensor sensor) {
		// TODO Auto-generated method stub
		StringBuffer sb = new StringBuffer();
		
		sb.append("MaximumRange:" + sensor.getMaximumRange() );
		sb.append("\nName:" + sensor.getName() );
		sb.append("\nPower:" + sensor.getPower() );
		sb.append("\nResolution:" + sensor.getResolution() );
		sb.append("\nType:" + sensor.getType() );
		sb.append("\nVendor:" + sensor.getVendor() );
		sb.append("\nVersion:" + sensor.getVersion() );		
		
		return (sb.toString() );
	}
}
