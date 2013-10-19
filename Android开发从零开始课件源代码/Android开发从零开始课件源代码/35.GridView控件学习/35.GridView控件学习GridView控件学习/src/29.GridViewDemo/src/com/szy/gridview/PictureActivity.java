package com.szy.gridview;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

/**
 * @author coolszy
 * @blog http://blog.csdn.net/coolszy
 */
public class PictureActivity extends Activity
{
	private GridView gridView;
	private String[] titles = new String[]
	{ "MM1", "MM2", "MM3", "MM4", "MM5", "MM6", "MM7", "MM8", };
	private int[] images = new int[]
	{ R.drawable.p1, R.drawable.p2, R.drawable.p3, R.drawable.p4, R.drawable.p5, R.drawable.p6, R.drawable.p7, R.drawable.p8 };

	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		gridView = (GridView) findViewById(R.id.gridview);
		PictureAdapter adapter = new PictureAdapter(titles, images, this);
		gridView.setAdapter(adapter);

		gridView.setOnItemClickListener(new OnItemClickListener()
		{
			public void onItemClick(AdapterView<?> parent, View v, int position, long id)
			{
				Log.i("MainActivity", "Position:" + position);
				Intent intent = new Intent(PictureActivity.this, ImageViewActivity.class);
				intent.putExtra("id", images[position]);
				startActivity(intent);
			}
		});
	}
}

class PictureAdapter extends BaseAdapter
{
	private LayoutInflater inflater;
	private List<Picture> pictures;

	public PictureAdapter(String[] titles, int[] images, Context context)
	{
		super();
		pictures = new ArrayList<Picture>();
		inflater = LayoutInflater.from(context);
		for (int i = 0; i < images.length; i++)
		{
			Picture picture = new Picture(titles[i], images[i]);
			pictures.add(picture);
		}
	}

	@Override
	public int getCount()
	{
		if (null != pictures)
		{
			return pictures.size();
		} else
		{
			return 0;
		}
	}

	@Override
	public Object getItem(int position)
	{
		return pictures.get(position);
	}

	@Override
	public long getItemId(int position)
	{
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent)
	{
		ViewHolder viewHolder;
		if (convertView == null)
		{
			convertView = inflater.inflate(R.layout.picture_item, null);
			viewHolder = new ViewHolder();
			viewHolder.title = (TextView) convertView.findViewById(R.id.title);
			viewHolder.image = (ImageView) convertView.findViewById(R.id.image);
			convertView.setTag(viewHolder);
		} else
		{
			viewHolder = (ViewHolder) convertView.getTag();
		}
		viewHolder.title.setText(pictures.get(position).getTitle());
		viewHolder.image.setImageResource(pictures.get(position).getImageId());
		Log.i("MainActivity", "<<<<<<<" + position);
		return convertView;
	}

}

class ViewHolder
{
	public TextView title;
	public ImageView image;
}

class Picture
{
	private String title;
	private int imageId;

	public Picture()
	{
		super();
	}

	public Picture(String title, int imageId)
	{
		super();
		this.title = title;
		this.imageId = imageId;
	}

	public String getTitle()
	{
		return title;
	}

	public void setTitle(String title)
	{
		this.title = title;
	}

	public int getImageId()
	{
		return imageId;
	}

	public void setImageId(int imageId)
	{
		this.imageId = imageId;
	}
}
