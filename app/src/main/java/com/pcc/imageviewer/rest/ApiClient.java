package com.pcc.imageviewer.rest;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Pablo Criado Carrera on 29/10/2016.
 */

public class ApiClient {

	public static final String BASE_URL = "https://jsonplaceholder.typicode.com/";
	private static Retrofit retrofit;

	public static Retrofit getRetrofit() {
		if (retrofit == null){
			retrofit = new Retrofit.Builder()
					.baseUrl(BASE_URL)
					.addConverterFactory(GsonConverterFactory.create())
					.build();
		}
		return retrofit;
	}


}
