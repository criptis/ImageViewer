package com.pcc.imageviewer.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.pcc.imageviewer.R;
import com.pcc.imageviewer.models.Image;

import io.realm.Realm;


public class DetailsFragment extends Fragment {
	private static final String TAG = DetailsFragment.class.getSimpleName();

	private static final String ARG_IMAGE_ID = "imageID";

	private long imageId;
	private TextView textViewTitle;
	private ImageView imageView;


	public DetailsFragment() {
		// Required empty public constructor
	}

	// Create new instance of DetailsFragment with the imageId passed by the activity
	public static DetailsFragment newInstance(long imageId) {
		DetailsFragment fragment = new DetailsFragment();
		Bundle args = new Bundle();
		args.putLong(ARG_IMAGE_ID, imageId);
		fragment.setArguments(args);
		return fragment;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		if (getArguments() != null) {
			imageId = getArguments().getLong(ARG_IMAGE_ID);
		}
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.fragment_details, container, false);

		textViewTitle = (TextView) v.findViewById(R.id.textview_details_title);
		imageView = (ImageView) v.findViewById(R.id.imageview_details);

		return v;
	}

	@Override
	public void onResume() {
		super.onResume();

		// Get image object with specified id
		Realm realm = Realm.getDefaultInstance();
		Image image = realm.where(Image.class).equalTo("id",imageId).findFirst();

		textViewTitle.setText(image.getTitle());

		// Load image or error drawable in case there is any problem
		Glide.with(getContext())
				.load(image.getImageUrl())
				.error(R.drawable.load_error_600)
				.into(imageView);

		Log.d(TAG, "Id: " + image.getId());
	}
}
