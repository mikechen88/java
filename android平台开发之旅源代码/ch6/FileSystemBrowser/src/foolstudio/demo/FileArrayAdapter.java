package foolstudio.demo;

import java.util.List;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class FileArrayAdapter extends ArrayAdapter<FileInfo> {

	private Activity mContext = null;
	
	public FileArrayAdapter(Activity context, int resource, 
			int textViewResourceId, List<FileInfo> objects) {
		super(context, resource, textViewResourceId, objects);
		// TODO Auto-generated constructor stub
		this.mContext = context;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		LayoutInflater inflater = mContext.getLayoutInflater();
		//获取项目View（每一行）
		View itemView = inflater.inflate(R.layout.item_view, null, false);
		//获取每一行子项
		ImageView imageView = (ImageView)itemView.findViewById(R.id.IMG_ICON);
		TextView textView = (TextView)itemView.findViewById(R.id.LAB_FILE_NAME);
		FileInfo fileInfo = this.getItem(position);
		
		if(fileInfo.isFolder() ) {
			imageView.setImageResource(R.drawable.folder);
		}
		else {
			imageView.setImageResource(R.drawable.file);			
		}
		
		textView.setText(fileInfo.getName() );
		
		return (itemView);
		
		//return super.getView(position, convertView, parent);
	}
};
