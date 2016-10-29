package com.pcc.imageviewer.rest;

import com.pcc.imageviewer.models.Image;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Pablo Criado Carrera on 29/10/2016.
 */

public interface ApiInterface {
	@GET("photos")
	Call<List<Image>> getAllImages();
}
