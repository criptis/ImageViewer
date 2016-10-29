package com.pcc.imageviewer;

import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.pcc.imageviewer.fragments.DetailsFragment;
import com.pcc.imageviewer.fragments.GridFragment;
import com.pcc.imageviewer.models.Image;
import com.pcc.imageviewer.rest.ApiClient;
import com.pcc.imageviewer.rest.ApiInterface;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements GridFragment.OnImageSelectedListener {
	private static final String TAG = MainActivity.class.getSimpleName();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		Realm.init(this);
		final Realm realm = Realm.getDefaultInstance();

		// Create API client
		ApiInterface apiInterface = ApiClient.getRetrofit().create(ApiInterface.class);

		// Retrieve all images from network
		Call<List<Image>> call = apiInterface.getAllImages();
		call.enqueue(new Callback<List<Image>>() {
			@Override
			public void onResponse(Call<List<Image>> call, Response<List<Image>> response) {
				ArrayList<Image> images = (ArrayList<Image>) response.body();

				// Save image objects to realm database
				realm.beginTransaction();
				realm.copyToRealmOrUpdate(images);
				realm.commitTransaction();
			}

			@Override
			public void onFailure(Call<List<Image>> call, Throwable t) {
				Toast.makeText(MainActivity.this, t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
			}
		});


		if (savedInstanceState == null) {
			FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
			GridFragment fragment = GridFragment.newInstance();
			transaction.replace(R.id.container, fragment).commit();
		}

	}

	@Override
	public void onImageSelected(long imageId) {
		FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
		DetailsFragment fragment = DetailsFragment.newInstance(imageId);
		transaction.addToBackStack(null);
		transaction.replace(R.id.container, fragment).commit();
	}
}
