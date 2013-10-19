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
	//�����б�
	private List<Overlay> mOverlays = null;
	private Drawable mDrawable = null;
	private FooItemizedOverlay mItemizedOverlay = null;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        //���õ�ͼ��ͼ
        mMapView = (MapView) findViewById(R.id.MV_MAIN);
        //�������ſؼ�
        mMapView.setBuiltInZoomControls(true);
        //mMapView.setSatellite(true);
        //mMapView.setTraffic(true);
        mMapView.setStreetView(true);
        
        //��ȡ���ӽӿ�
        mOverlays = mMapView.getOverlays();
        mDrawable = this.getResources().getDrawable(R.drawable.flag_red);
        mItemizedOverlay = new FooItemizedOverlay(mDrawable);
        //���õ��ӵ㣨����Ӣ�ۼ����
        GeoPoint point = new GeoPoint(39904600,116397500);
        OverlayItem overlayitem = new OverlayItem(point, 
        										  "����Ӣ�ۼ��", 
        										  "����Ӣ�ۼ��");
        //��ӵ�����
        mItemizedOverlay.addOverlay(overlayitem);
        mOverlays.add(mItemizedOverlay);
        //�ƶ������ĵ�
        mMapView.getController().animateTo(point);
        //�Ŵ�ȼ�Ϊ��1��21��
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
	
	//��ȡ��ǰ��ͼ����λ��
	private void getPoint() {
		// TODO Auto-generated method stub
		GeoPoint point = mMapView.getMapCenter();
		Toast.makeText(this, "��ǰλ�ã�γ�ȣ�"+point.getLatitudeE6()+
				"\n�������������ȣ�"+ point.getLongitudeE6(), 
				Toast.LENGTH_LONG).show();
	}

	//��ӵ��ӵ�
	private void addPoint() {
		// TODO Auto-generated method stub
		GeoPoint point = mMapView.getMapCenter();

		mItemizedOverlay = new FooItemizedOverlay(mDrawable);
        OverlayItem overlayitem = new OverlayItem(point, "��д̧ͷ", "��д���");
        //��ӵ�����
        mItemizedOverlay.addOverlay(overlayitem);
        mOverlays.add(mItemizedOverlay);		
	}	
};