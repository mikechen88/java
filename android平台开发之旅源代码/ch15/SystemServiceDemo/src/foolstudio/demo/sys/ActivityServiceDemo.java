package foolstudio.demo.sys;

import java.util.List;

import android.app.ActivityManager;
import android.app.ActivityManager.MemoryInfo;
import android.app.ActivityManager.ProcessErrorStateInfo;
import android.app.ActivityManager.RecentTaskInfo;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.app.ActivityManager.RunningServiceInfo;
import android.app.ActivityManager.RunningTaskInfo;
import android.content.pm.ConfigurationInfo;
import android.content.res.Configuration;

public class ActivityServiceDemo {

	public static String getInfo(ActivityManager service) {
		// TODO Auto-generated method stub
		StringBuffer sb = new StringBuffer();
		
		ConfigurationInfo cfgInfo = service.getDeviceConfigurationInfo();
		sb.append(getInfo(cfgInfo) );
		sb.append('\n');
		//
		MemoryInfo memInfo = new MemoryInfo();
		service.getMemoryInfo(memInfo);
		sb.append(getInfo(memInfo) );
		sb.append('\n');
		//
		List<ProcessErrorStateInfo> states = service.getProcessesInErrorState();
		if(states != null) {
			sb.append(getStates(states) );
		}
		//
		List<ActivityManager.RecentTaskInfo> recentTasks = 
			service.getRecentTasks(Integer.MAX_VALUE, 
								   ActivityManager.RECENT_WITH_EXCLUDED);
		if(recentTasks != null) {
			sb.append(getRecentTasks(recentTasks) );
		}		
		//
		List<ActivityManager.RunningAppProcessInfo> processes = 
			service.getRunningAppProcesses();
		if(recentTasks != null) {
			sb.append(getProcesses(processes) );
		}		
		//
		List<ActivityManager.RunningServiceInfo> services = 
			service.getRunningServices(Integer.MAX_VALUE);
		if(services != null) {
			sb.append(getServices(services) );
		}
		//
		List<ActivityManager.RunningTaskInfo> runningTasks = 
			service.getRunningTasks(Integer.MAX_VALUE);
		if(runningTasks != null) {
			sb.append(getRunningTasks(runningTasks) );
		}
		
		return (sb.toString() );
	}

	//获取错误状态
	private static String getStates(List<ProcessErrorStateInfo> states) {
		// TODO Auto-generated method stub
		StringBuffer sb = new StringBuffer();
		
		sb.append("====进程错误状态信息：\n");
		for(int i = 0; i < states.size(); ++i) {
			ProcessErrorStateInfo info = states.get(i);
			
			sb.append("PID=");
			sb.append(info.pid);
			sb.append("; LMSG=");			
			sb.append(info.longMsg);
			sb.append("; SMSG=");			
			sb.append(info.shortMsg);	
			sb.append("; TAG=");			
			sb.append(info.tag);			
			sb.append('\n');			
		}
		
		return (sb.toString() );
	}

	//获取运行中任务
	private static String getRunningTasks(List<RunningTaskInfo> runningTasks) {
		// TODO Auto-generated method stub
		StringBuffer sb = new StringBuffer();
		
		sb.append("====在运行任务信息：\n");
		for(int i = 0; i < runningTasks.size(); ++i) {
			RunningTaskInfo info = runningTasks.get(i);
			
			sb.append("TID=");
			sb.append(info.id);		
			sb.append("; Act=");
			sb.append(info.baseActivity.getShortClassName() );
			sb.append('\n');
		}		
		
		return (sb.toString() );		
	}

	//获取运行中服务信息
	private static String getServices(List<RunningServiceInfo> services) {
		// TODO Auto-generated method stub
		StringBuffer sb = new StringBuffer();
		
		sb.append("====在运行服务信息：\n");
		for(int i = 0; i < services.size(); ++i) {
			RunningServiceInfo info = services.get(i);
			
			sb.append("Service=");
			sb.append(info.service.getShortClassName() );				
			sb.append("; PID=");
			sb.append(info.pid);	
			sb.append("; Proc=");
			sb.append(info.process);			
			sb.append('\n');
		}
		
		return (sb.toString() );		
	}

	//获取进程信息
	private static String getProcesses(List<RunningAppProcessInfo> processes) {
		// TODO Auto-generated method stub
		StringBuffer sb = new StringBuffer();
		
		sb.append("====正运行进程信息：\n");
		for(int i = 0; i < processes.size(); ++i) {
			RunningAppProcessInfo info = processes.get(i);
			
			sb.append("PID=");
			sb.append(info.pid);		
			sb.append("; Name=");
			sb.append(info.processName);
			sb.append("; Level=");
			
			switch(info.importance) {
				case ActivityManager.RunningAppProcessInfo.IMPORTANCE_FOREGROUND: {
					sb.append("前台");
					break;
				}
				case ActivityManager.RunningAppProcessInfo.IMPORTANCE_VISIBLE: {
					sb.append("可见");
					break;
				}
				case ActivityManager.RunningAppProcessInfo.IMPORTANCE_SERVICE: {
					sb.append("服务");
					break;
				}
				case ActivityManager.RunningAppProcessInfo.IMPORTANCE_BACKGROUND: {
					sb.append("后台");
					break;
				}
				case ActivityManager.RunningAppProcessInfo.IMPORTANCE_EMPTY: {
					sb.append("空置");
					break;
				}
			}
			
			sb.append('\n');
		}		
		
		return (sb.toString() );		
	}
	
	//获取新近任务信息
	private static String getRecentTasks(List<RecentTaskInfo> recentTasks) {
		// TODO Auto-generated method stub
		StringBuffer sb = new StringBuffer();
		
		sb.append("====新近任务信息：\n");
		for(int i = 0; i < recentTasks.size(); ++i) {
			RecentTaskInfo info = recentTasks.get(i);
			
			sb.append("TID=");
			sb.append(info.id);		
			sb.append("; Act=");
			sb.append(info.baseIntent.getAction() );
			sb.append('\n');
		}
		
		return (sb.toString() );		
	}	

	//获取内存信息
	private static String getInfo(MemoryInfo memInfo) {
		// TODO Auto-generated method stub
		StringBuffer sb = new StringBuffer();
		
		sb.append("====内存信息：\n");
		sb.append("总内存：");		
		sb.append(memInfo.availMem);
		sb.append('\n');
		sb.append("是否低内存：");
		sb.append(memInfo.lowMemory);
		sb.append('\n');
		sb.append("内存阀值：");
		sb.append(memInfo.threshold);
		
		return (sb.toString() );
	}

	//获取配置信息
	private static String getInfo(ConfigurationInfo cfgInfo) {
		// TODO Auto-generated method stub
		StringBuffer sb = new StringBuffer();
		
		sb.append("====配置信息：\n");
		sb.append("输入方式：");
		switch(cfgInfo.reqInputFeatures) {
			case ConfigurationInfo.INPUT_FEATURE_FIVE_WAY_NAV: {
				sb.append("五向导航键输入");
				break;
			}
			case ConfigurationInfo.INPUT_FEATURE_HARD_KEYBOARD: {
				sb.append("硬键盘输入");
				break;
			}
			case (ConfigurationInfo.INPUT_FEATURE_FIVE_WAY_NAV |
				ConfigurationInfo.INPUT_FEATURE_HARD_KEYBOARD): {
				sb.append("复合键盘输入");
				break;
			}
		}
		sb.append('\n');
		
		sb.append("键盘类型：");
		switch(cfgInfo.reqKeyboardType) {
			case Configuration.KEYBOARD_UNDEFINED: {
				sb.append("未定义键盘");
				break; 
			}
			case Configuration.KEYBOARD_NOKEYS: {
				sb.append("无键键盘");
				break; 
			}
			case Configuration.KEYBOARD_QWERTY: {
				sb.append("打字机键盘");
				break; 
			}
			case Configuration.KEYBOARD_12KEY: {
				sb.append("十二键盘");
				break; 
			}
		}
		sb.append('\n');
		
		sb.append("导航方式：");		
		switch(cfgInfo.reqNavigation) {
			case Configuration.NAVIGATION_UNDEFINED: {
				sb.append("未定义导航");
				break; 
			}
			case Configuration.NAVIGATION_DPAD: {
				sb.append("面板导航");
				break; 
			}
			case Configuration.NAVIGATION_TRACKBALL: {
				sb.append("定位球导航");
				break; 
			}
			case Configuration.NAVIGATION_WHEEL: {
				sb.append("滚轮导航");
				break; 
			}
		}		
		sb.append('\n');
		
		sb.append("触摸屏方式：");		
		switch(cfgInfo.reqTouchScreen) {
			case Configuration.TOUCHSCREEN_NOTOUCH: {
				sb.append("不支持触摸屏");
				break; 
			}
			case Configuration.TOUCHSCREEN_STYLUS: {
				sb.append("触摸笔");
				break; 
			}
			case Configuration.TOUCHSCREEN_FINGER: {
				sb.append("手指触摸");
				break; 
			}
		}
		
		return (sb.toString() );
	}
};
