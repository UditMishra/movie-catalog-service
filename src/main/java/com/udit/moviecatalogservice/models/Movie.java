package com.udit.moviecatalogservice.models;

public class Movie {

	private String movieId;
	private String name;

	public Movie() {
		
	}
	
	public Movie(String id, String name) {
		super();
		this.movieId = id;
		this.name = name;
	}

	public String getMovieId() {
		return movieId;
	}

	public void setMovieId(String id) {
		this.movieId = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
