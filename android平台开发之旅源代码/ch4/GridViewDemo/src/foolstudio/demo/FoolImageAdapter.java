package foolstudio.demo;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListAdapter;

public class FoolImageAdapter extends BaseAdapter implements ListAdapter {
	
	private Context mContext = null;
	
	private int mPicutureIDs[] = {
		R.drawable.hulu,
		R.drawable.light,
		R.drawable.scene,
		R.drawable.sky,
		R.drawable.tiger,
		R.drawable.woman
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
			iv.setLayoutParams(new GridView.LayoutParams(80,80) );
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
