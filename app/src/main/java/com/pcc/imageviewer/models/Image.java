package com.pcc.imageviewer.models;

import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Pablo Criado Carrera on 29/10/2016.
 */

public class Image extends RealmObject{

	@SerializedName("albumId")
	private int albumId;
	@SerializedName("id")
	@PrimaryKey
	private int id;
	@SerializedName("title")
	private String title;
	@SerializedName("url")
	private String imageUrl;
	@SerializedName("thumbnailUrl")
	private String thumbnailUrl;


	public Image(){}


	public Image(int albumId, int id, String title, String imageUrl, String thumbnailUrl) {
		this.albumId = albumId;
		this.id = id;
		this.title = title;
		this.imageUrl = imageUrl;
		this.thumbnailUrl = thumbnailUrl;
	}


	public int getAlbumId() {
		return albumId;
	}

	public void setAlbumId(int albumId) {
		this.albumId = albumId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public String getThumbnailUrl() {
		return thumbnailUrl;
	}

	public void setThumbnailUrl(String thumbnailUrl) {
		this.thumbnailUrl = thumbnailUrl;
	}
}
