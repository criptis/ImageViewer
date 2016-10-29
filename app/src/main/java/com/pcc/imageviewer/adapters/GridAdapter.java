package com.pcc.imageviewer.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;


import com.bumptech.glide.Glide;
import com.pcc.imageviewer.R;
import com.pcc.imageviewer.models.Image;

import java.util.List;

/**
 * Created by Pablo Criado Carrera on 29/10/2016.
 */

public class GridAdapter extends BaseAdapter {
	private static final String TAG = GridAdapter.class.getSimpleName();

	private Context context;
	private List<Image> images;

	public GridAdapter(Context context, List<Image> data){
		this.context = context;
		this.images = data;
	}


	@Override
	public int getCount() {
		return images.size();
	}

	@Override
	public Object getItem(int position) {
		return images.get(position);
	}

	@Override
	public long getItemId(int position) {
		return images.get(position).getId();
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ImageView imageView;
		if (convertView == null){
			imageView = new ImageView(context);
			imageView.setLayoutParams(new GridView.LayoutParams(context.getResources().getDimensionPixelSize(R.dimen.thumbnail),context.getResources().getDimensionPixelSize(R.dimen.thumbnail)));
			imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
		}
		else {
			imageView = (ImageView) convertView;
		}

		// Load image Thumbnails or error drawable in case there is any problem
		Glide.with(context)
				.load(images.get(position).getThumbnailUrl())
				.error(R.drawable.load_error_150)
				.into(imageView);


		return imageView;
	}
}
