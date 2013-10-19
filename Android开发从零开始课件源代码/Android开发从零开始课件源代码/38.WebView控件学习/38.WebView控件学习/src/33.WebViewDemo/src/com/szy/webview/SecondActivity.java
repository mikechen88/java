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
	private final static int DRIVEROUTE=0; //驾驶导航
	private final static int BUSROUTE=1; //公交成导航
	
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
	 * 初始化地图
	 */
	private void initViews()
	{
		webView = (WebView) findViewById(R.id.webview);
		WebSettings webSettings = webView.getSettings();
		// js生效
		webSettings.setJavaScriptEnabled(true);
		webSettings.setSupportZoom(true);
		// 将js页面与本类的一个对象桥接起来
		webView.addJavascriptInterface(this, "MainActivity");
		// 加载页面
		webView.loadUrl("file:///android_asset/map.html");
	}
	
	/**
	 * 显示一个EditText对话框
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
	 * 显示两个EditText对话框
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
	 * 查找地点
	 * 
	 * @param str
	 */
	private void findPlace(String str)
	{
		//调用html页面js中findPlace(place)方法
		String url = "javascript:findPlace('" + str + "')";
		webView.loadUrl(url);
	}
	
	/**
	 * 查询驾车导航路线
	 * 
	 * @param from 起点
	 * @param to 终点
	 */
	private void findDriveRoute(String from, String to)
	{
		//调用html页面js中findDriveRoute(from,to)方法
		String url = "javascript:findDriveRoute('" + from + "','" + to + "')";
		webView.loadUrl(url);
	}

	/**
	 * 查询公交车路线
	 * 
	 * @param from 起点
	 * @param to 终点
	 */
	private void findBusRoute(String from, String to)
	{
		//调用html页面js中findBusRoute(from,to)方法
		String url = "javascript:findBusRoute('" + from + "','" + to + "')";
		webView.loadUrl(url);
	}
	
	/**
	 * 关闭对话框
	 */
	private void closeDialog()
	{
		if (null!=alertDialog)
		{
			alertDialog.dismiss();
		}
	}
	
	/**
	 * 显示查询结果对话框
	 * 注：本方法有map.html中的js代码调用
	 * @param result 查询结果，从html中传递进来
	 */
	public void showResult(String result)
	{
		LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(LAYOUT_INFLATER_SERVICE);
		View layout = inflater.inflate(R.layout.result_dialog, (ViewGroup) findViewById(R.id.result_dialog));
		TextView txtResult = (TextView) layout.findViewById(R.id.txtResult);
		//把result的<b></b>中的内容改变颜色
		result=result.replace("<b>", "<font color=#BCEE68>");
		result=result.replace("</b>", "</font>");
		txtResult.setText(Html.fromHtml(result));
		//设置TextView出现滚动条
		txtResult.setMovementMethod(ScrollingMovementMethod.getInstance());  
		builder = new AlertDialog.Builder(mContext);
		builder.setView(layout);
		builder.setPositiveButton("关闭",  new DialogInterface.OnClickListener() 
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
	 * 点击"BACK"按钮返回上一页网页，而并非直接退出程序
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