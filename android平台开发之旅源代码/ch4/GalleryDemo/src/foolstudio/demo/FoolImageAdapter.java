package foolstudio.demo;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.ListAdapter;

public class FoolImageAdapter extends BaseAdapter implements ListAdapter {
	
	private Context mContext = null;
	
	private int mPicutureIDs[] = {
		R.drawable.box,
		R.drawable.bus,
		R.drawable.flower,
		R.drawable.grass,
		R.drawable.house,
		R.drawable.machine,
		R.drawable.root,
		R.drawable.scale,
		R.drawable.snow
	};
	
	public FoolImageAdapter(Context c) {
        this.mContext = c;
    }

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return (mPicutureIDs.length);
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	public int getResId(int position) {
		return (mPicutureIDs[position]);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		ImageView iv = null;
		
		if(convertView == null) {
			iv =  new ImageView(mContext);
			
			iv.setAdjustViewBounds(true);			
			iv.setLayoutParams(new Gallery.LayoutParams(80,80) );
			iv.setScaleType(ImageView.ScaleType.CENTER_CROP);
			iv.setPadding(8, 8, 8, 8);
		}
		else {
			iv = (ImageView)convertView;
		}
		
		iv.setImageResource(mPicutureIDs[position]);
		
		return (iv);
	}
};
