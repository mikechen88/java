package com.szy.webview;


import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.Html;
import android.text.method.ScrollingMovementMethod;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class SecondActivity extends Activity
{
	private final static int DRIVEROUTE=0; //��ʻ����
	private final static int BUSROUTE=1; //�����ɵ���
	
	private WebView webView;
	
	private Button btnPlace = null;
	private Button btnDrive = null;
	private Button btnBus =null;
	
	private AlertDialog.Builder builder;
	private AlertDialog alertDialog;
	
	private Context mContext;
	
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.second);
		btnPlace=(Button)findViewById(R.id.btnPlace);
		btnDrive=(Button)findViewById(R.id.btnDrive);
		btnBus=(Button)findViewById(R.id.btnBus);
		btnPlace.setOnClickListener(listener);
		btnDrive.setOnClickListener(listener);
		btnBus.setOnClickListener(listener);
		initViews();
		mContext = SecondActivity.this;
	}
	
	private OnClickListener listener= new OnClickListener()
	{
		@Override
		public void onClick(View v)
		{
			switch (v.getId())
			{
			case R.id.btnPlace:
				showOneDialog();
				break;
			case R.id.btnDrive:
				showTwoDialog(DRIVEROUTE);	
				break;
			case R.id.btnBus:
				showTwoDialog(BUSROUTE);
				break;
			default:
				Toast.makeText(mContext, R.string.defaultId, Toast.LENGTH_LONG);
				break;
			}
		}
	};
	
	/**
	 * ��ʼ����ͼ
	 */
	private void initViews()
	{
		webView = (WebView) findViewById(R.id.webview);
		WebSettings webSettings = webView.getSettings();
		// js��Ч
		webSettings.setJavaScriptEnabled(true);
		webSettings.setSupportZoom(true);
		// ��jsҳ���뱾���һ�������Ž�����
		webView.addJavascriptInterface(this, "MainActivity");
		// ����ҳ��
		webView.loadUrl("file:///android_asset/map.html");
	}
	
	/**
	 * ��ʾһ��EditText�Ի���
	 */
	private void showOneDialog()
	{
		LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(LAYOUT_INFLATER_SERVICE);
		View layout = inflater.inflate(R.layout.one_dialog,(ViewGroup) findViewById(R.id.one_layout_root));
		final EditText editPlace = (EditText) layout.findViewById(R.id.editPlace);
		Button btnPlaceFind=(Button) layout.findViewById(R.id.btnPlaceFind);
		btnPlaceFind.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View arg0)
			{
				findPlace(editPlace.getText().toString());
				closeDialog();
			}
		});
		builder = new AlertDialog.Builder(mContext);
		builder.setView(layout);
		alertDialog = builder.create();
		alertDialog.show();
	}
	
	/**
	 * ��ʾ����EditText�Ի���
	 */
	private void showTwoDialog(final int type)
	{
		LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(LAYOUT_INFLATER_SERVICE);
		View layout = inflater.inflate(R.layout.two_dialog,(ViewGroup) findViewById(R.id.two_layout_root));
		final EditText editFrom = (EditText) layout.findViewById(R.id.editFrom);
		final EditText editTo = (EditText) layout.findViewById(R.id.editTo);
		Button btnRouteFind=(Button) layout.findViewById(R.id.btnRouteFind);
		btnRouteFind.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View arg0)
			{
				if (type==DRIVEROUTE)
				{
					findDriveRoute(editFrom.getText().toString(), editTo.getText().toString());
					closeDialog();
				}
				else
				{
					findBusRoute(editFrom.getText().toString(), editTo.getText().toString());
					closeDialog();
				}
			}
		});
		builder = new AlertDialog.Builder(mContext);
		builder.setView(layout);
		alertDialog = builder.create();
		alertDialog.show();
	}
	
	/**
	 * ���ҵص�
	 * 
	 * @param str
	 */
	private void findPlace(String str)
	{
		//����htmlҳ��js��findPlace(place)����
		String url = "javascript:findPlace('" + str + "')";
		webView.loadUrl(url);
	}
	
	/**
	 * ��ѯ�ݳ�����·��
	 * 
	 * @param from ���
	 * @param to �յ�
	 */
	private void findDriveRoute(String from, String to)
	{
		//����htmlҳ��js��findDriveRoute(from,to)����
		String url = "javascript:findDriveRoute('" + from + "','" + to + "')";
		webView.loadUrl(url);
	}

	/**
	 * ��ѯ������·��
	 * 
	 * @param from ���
	 * @param to �յ�
	 */
	private void findBusRoute(String from, String to)
	{
		//����htmlҳ��js��findBusRoute(from,to)����
		String url = "javascript:findBusRoute('" + from + "','" + to + "')";
		webView.loadUrl(url);
	}
	
	/**
	 * �رնԻ���
	 */
	private void closeDialog()
	{
		if (null!=alertDialog)
		{
			alertDialog.dismiss();
		}
	}
	
	/**
	 * ��ʾ��ѯ����Ի���
	 * ע����������map.html�е�js�������
	 * @param result ��ѯ�������html�д��ݽ���
	 */
	public void showResult(String result)
	{
		LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(LAYOUT_INFLATER_SERVICE);
		View layout = inflater.inflate(R.layout.result_dialog, (ViewGroup) findViewById(R.id.result_dialog));
		TextView txtResult = (TextView) layout.findViewById(R.id.txtResult);
		//��result��<b></b>�е����ݸı���ɫ
		result=result.replace("<b>", "<font color=#BCEE68>");
		result=result.replace("</b>", "</font>");
		txtResult.setText(Html.fromHtml(result));
		//����TextView���ֹ�����
		txtResult.setMovementMethod(ScrollingMovementMethod.getInstance());  
		builder = new AlertDialog.Builder(mContext);
		builder.setView(layout);
		builder.setPositiveButton("�ر�",  new DialogInterface.OnClickListener() 
		{          
			public void onClick(DialogInterface dialog, int id) 
			{                
				dialog.dismiss();           
			}       
		});
		alertDialog = builder.create();
		alertDialog.show();
	}
	
	/**
	 * ���"BACK"��ť������һҳ��ҳ��������ֱ���˳�����
	 */
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event)
	{
		if ((keyCode == KeyEvent.KEYCODE_BACK) && webView.canGoBack())
		{
			webView.goBack();
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}
}