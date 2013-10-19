package foolstudio.demo.map;

import java.util.ArrayList;

import android.graphics.drawable.Drawable;

import com.google.android.maps.ItemizedOverlay;
import com.google.android.maps.OverlayItem;

public class FooItemizedOverlay extends ItemizedOverlay<OverlayItem> {
	
	private ArrayList<OverlayItem> mOverlays = new ArrayList<OverlayItem>();	

	public FooItemizedOverlay(Drawable defaultMarker) {
		//super(defaultMarker);
		super(boundCenterBottom(defaultMarker) );		
		// TODO Auto-generated constructor stub
	}

	@Override
	protected OverlayItem createItem(int i) {
		// TODO Auto-generated method stub
		return (mOverlays.get(i) );
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return (mOverlays.size() );
	}
	
	public void addOverlay(OverlayItem overlay) {
	    mOverlays.add(overlay);
	    populate();
	}
};
