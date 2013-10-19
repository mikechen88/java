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

	//��ȡ����״̬
	private static String getStates(List<ProcessErrorStateInfo> states) {
		// TODO Auto-generated method stub
		StringBuffer sb = new StringBuffer();
		
		sb.append("====���̴���״̬��Ϣ��\n");
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

	//��ȡ����������
	private static String getRunningTasks(List<RunningTaskInfo> runningTasks) {
		// TODO Auto-generated method stub
		StringBuffer sb = new StringBuffer();
		
		sb.append("====������������Ϣ��\n");
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

	//��ȡ�����з�����Ϣ
	private static String getServices(List<RunningServiceInfo> services) {
		// TODO Auto-generated method stub
		StringBuffer sb = new StringBuffer();
		
		sb.append("====�����з�����Ϣ��\n");
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

	//��ȡ������Ϣ
	private static String getProcesses(List<RunningAppProcessInfo> processes) {
		// TODO Auto-generated method stub
		StringBuffer sb = new StringBuffer();
		
		sb.append("====�����н�����Ϣ��\n");
		for(int i = 0; i < processes.size(); ++i) {
			RunningAppProcessInfo info = processes.get(i);
			
			sb.append("PID=");
			sb.append(info.pid);		
			sb.append("; Name=");
			sb.append(info.processName);
			sb.append("; Level=");
			
			switch(info.importance) {
				case ActivityManager.RunningAppProcessInfo.IMPORTANCE_FOREGROUND: {
					sb.append("ǰ̨");
					break;
				}
				case ActivityManager.RunningAppProcessInfo.IMPORTANCE_VISIBLE: {
					sb.append("�ɼ�");
					break;
				}
				case ActivityManager.RunningAppProcessInfo.IMPORTANCE_SERVICE: {
					sb.append("����");
					break;
				}
				case ActivityManager.RunningAppProcessInfo.IMPORTANCE_BACKGROUND: {
					sb.append("��̨");
					break;
				}
				case ActivityManager.RunningAppProcessInfo.IMPORTANCE_EMPTY: {
					sb.append("����");
					break;
				}
			}
			
			sb.append('\n');
		}		
		
		return (sb.toString() );		
	}
	
	//��ȡ�½�������Ϣ
	private static String getRecentTasks(List<RecentTaskInfo> recentTasks) {
		// TODO Auto-generated method stub
		StringBuffer sb = new StringBuffer();
		
		sb.append("====�½�������Ϣ��\n");
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

	//��ȡ�ڴ���Ϣ
	private static String getInfo(MemoryInfo memInfo) {
		// TODO Auto-generated method stub
		StringBuffer sb = new StringBuffer();
		
		sb.append("====�ڴ���Ϣ��\n");
		sb.append("���ڴ棺");		
		sb.append(memInfo.availMem);
		sb.append('\n');
		sb.append("�Ƿ���ڴ棺");
		sb.append(memInfo.lowMemory);
		sb.append('\n');
		sb.append("�ڴ淧ֵ��");
		sb.append(memInfo.threshold);
		
		return (sb.toString() );
	}

	//��ȡ������Ϣ
	private static String getInfo(ConfigurationInfo cfgInfo) {
		// TODO Auto-generated method stub
		StringBuffer sb = new StringBuffer();
		
		sb.append("====������Ϣ��\n");
		sb.append("���뷽ʽ��");
		switch(cfgInfo.reqInputFeatures) {
			case ConfigurationInfo.INPUT_FEATURE_FIVE_WAY_NAV: {
				sb.append("���򵼺�������");
				break;
			}
			case ConfigurationInfo.INPUT_FEATURE_HARD_KEYBOARD: {
				sb.append("Ӳ��������");
				break;
			}
			case (ConfigurationInfo.INPUT_FEATURE_FIVE_WAY_NAV |
				ConfigurationInfo.INPUT_FEATURE_HARD_KEYBOARD): {
				sb.append("���ϼ�������");
				break;
			}
		}
		sb.append('\n');
		
		sb.append("�������ͣ�");
		switch(cfgInfo.reqKeyboardType) {
			case Configuration.KEYBOARD_UNDEFINED: {
				sb.append("δ�������");
				break; 
			}
			case Configuration.KEYBOARD_NOKEYS: {
				sb.append("�޼�����");
				break; 
			}
			case Configuration.KEYBOARD_QWERTY: {
				sb.append("���ֻ�����");
				break; 
			}
			case Configuration.KEYBOARD_12KEY: {
				sb.append("ʮ������");
				break; 
			}
		}
		sb.append('\n');
		
		sb.append("������ʽ��");		
		switch(cfgInfo.reqNavigation) {
			case Configuration.NAVIGATION_UNDEFINED: {
				sb.append("δ���嵼��");
				break; 
			}
			case Configuration.NAVIGATION_DPAD: {
				sb.append("��嵼��");
				break; 
			}
			case Configuration.NAVIGATION_TRACKBALL: {
				sb.append("��λ�򵼺�");
				break; 
			}
			case Configuration.NAVIGATION_WHEEL: {
				sb.append("���ֵ���");
				break; 
			}
		}		
		sb.append('\n');
		
		sb.append("��������ʽ��");		
		switch(cfgInfo.reqTouchScreen) {
			case Configuration.TOUCHSCREEN_NOTOUCH: {
				sb.append("��֧�ִ�����");
				break; 
			}
			case Configuration.TOUCHSCREEN_STYLUS: {
				sb.append("������");
				break; 
			}
			case Configuration.TOUCHSCREEN_FINGER: {
				sb.append("��ָ����");
				break; 
			}
		}
		
		return (sb.toString() );
	}
};
