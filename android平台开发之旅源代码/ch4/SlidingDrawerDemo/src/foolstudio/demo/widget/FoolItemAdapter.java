package foolstudio.demo.widget;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;

public class FoolItemAdapter extends BaseAdapter implements ListAdapter {
	
	private Activity mContext = null;
	
	public static final int[] DRAWABLE_IDs = {
		R.drawable.audio,
		R.drawable.basketball,
		R.drawable.kid,
		R.drawable.nemo,
		R.drawable.scissors,
		R.drawable.search,
		R.drawable.setting
	};
	
	public static final String[] FUNCTIONs = {
		"Musics", 
		"Sports",
		"Children",
		"Cartoon",
		"Family",
		"Search",
		"Setting"
	};
	
	public FoolItemAdapter(Activity c, int resource, int textViewResourceId) {
        this.mContext = c;
    }

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return (DRAWABLE_IDs.length);
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
		return (DRAWABLE_IDs[position]);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		LayoutInflater inflater = mContext.getLayoutInflater();
		//获取项目View（每一行）
		View itemView = inflater.inflate(R.layout.item_view, null, false);
		//获取每一行子项
		ImageView imageView = (ImageView)itemView.findViewById(R.id.IMG_ICON);
		TextView textView = (TextView)itemView.findViewById(R.id.LAB_TITLE);
		//设置资源
		imageView.setImageResource(DRAWABLE_IDs[position]);
		textView.setText(FUNCTIONs[position]);
		
		return(itemView);
	}
};
