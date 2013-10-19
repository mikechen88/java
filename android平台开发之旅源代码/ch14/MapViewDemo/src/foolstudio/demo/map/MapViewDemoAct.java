package foolstudio.demo.map;

import java.util.List;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;
import com.google.android.maps.OverlayItem;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ContextMenu.ContextMenuInfo;
import android.widget.Toast;

public class MapViewDemoAct extends MapActivity {

	private MapView mMapView = null;
	//叠加列表
	private List<Overlay> mOverlays = null;
	private Drawable mDrawable = null;
	private FooItemizedOverlay mItemizedOverlay = null;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        //设置地图视图
        mMapView = (MapView) findViewById(R.id.MV_MAIN);
        //设置缩放控件
        mMapView.setBuiltInZoomControls(true);
        //mMapView.setSatellite(true);
        //mMapView.setTraffic(true);
        mMapView.setStreetView(true);
        
        //获取叠加接口
        mOverlays = mMapView.getOverlays();
        mDrawable = this.getResources().getDrawable(R.drawable.flag_red);
        mItemizedOverlay = new FooItemizedOverlay(mDrawable);
        //设置叠加点（人民英雄纪念碑）
        GeoPoint point = new GeoPoint(39904600,116397500);
        OverlayItem overlayitem = new OverlayItem(point, 
        										  "人民英雄纪念碑", 
        										  "人民英雄纪念碑");
        //添加叠加项
        mItemizedOverlay.addOverlay(overlayitem);
        mOverlays.add(mItemizedOverlay);
        //移动到中心点
        mMapView.getController().animateTo(point);
        //放大等级为【1，21】
        mMapView.getController().setZoom(21);
    }

	@Override
	protected boolean isRouteDisplayed() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		this.getMenuInflater().inflate(R.menu.opt_menu, menu);
		
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		switch(item.getItemId() ) {
			case R.id.MI_GET_POINT: {
				getPoint();
				break;
			}
			case R.id.MI_ADD_POINT: {
				addPoint();
				break;
			}
		}
		
		return super.onOptionsItemSelected(item);
	}
	
	//获取当前地图中心位置
	private void getPoint() {
		// TODO Auto-generated method stub
		GeoPoint point = mMapView.getMapCenter();
		Toast.makeText(this, "当前位置：纬度："+point.getLatitudeE6()+
				"\n　　　　　经度："+ point.getLongitudeE6(), 
				Toast.LENGTH_LONG).show();
	}

	//添加叠加点
	private void addPoint() {
		// TODO Auto-generated method stub
		GeoPoint point = mMapView.getMapCenter();

		mItemizedOverlay = new FooItemizedOverlay(mDrawable);
        OverlayItem overlayitem = new OverlayItem(point, "填写抬头", "填写简介");
        //添加叠加项
        mItemizedOverlay.addOverlay(overlayitem);
        mOverlays.add(mItemizedOverlay);		
	}	
};