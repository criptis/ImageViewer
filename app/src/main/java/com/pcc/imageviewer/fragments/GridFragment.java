package com.pcc.imageviewer.fragments;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import com.pcc.imageviewer.R;
import com.pcc.imageviewer.adapters.GridAdapter;
import com.pcc.imageviewer.models.Image;

import io.realm.Realm;
import io.realm.RealmChangeListener;
import io.realm.RealmResults;


public class GridFragment extends Fragment {
	private static final String TAG = GridFragment.class.getSimpleName();

	// Interface to communicate with MainActivity
	private OnImageSelectedListener onImageSelectedListener;

	public interface OnImageSelectedListener {
		public void onImageSelected(long imageId);
	}

	public GridFragment() {
		// Required empty public constructor
	}

	public static GridFragment newInstance() {
		return new GridFragment();
	}



	@Override
	public void onAttach(Context context) {
		super.onAttach(context);
		if (context instanceof  OnImageSelectedListener){
			onImageSelectedListener = (OnImageSelectedListener) context;
		}
		else {
			throw new RuntimeException(context.toString() + " must implement OnImageSelectedListener");
		}
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.fragment_grid, container, false);

		// Get all images from database
		Realm realm = Realm.getDefaultInstance();
		RealmResults<Image> results = realm.where(Image.class).findAll();

		// Setup the gridView
		final GridAdapter adapter = new GridAdapter(getContext(), results);
		GridView gridView = (GridView) v.findViewById(R.id.gridview);
		gridView.setAdapter(adapter);

		// Listen for changes in the database to update the gridView
		results.addChangeListener(new RealmChangeListener<RealmResults<Image>>() {
			@Override
			public void onChange(RealmResults<Image> element) {
				adapter.notifyDataSetChanged();
			}
		});


		gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				Log.d(TAG, "Pos: " + position + ", Id: " + id);
				onImageSelectedListener.onImageSelected(id);
			}
		});




		return v;
	}

}
