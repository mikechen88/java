package foolstudio.demo.bt;

import java.util.UUID;

public interface BtConfig {
	//
	public static final String BT_SDP = "FooBt";
	//UUID(8-4-4-4-12)
	public static final UUID BT_UUID =
        UUID.fromString("98862500-A4ED-154B-EE4B-104BA9ADC2F5");
	
	public static final int REQUEST_ENABLE_BT = 100;
};