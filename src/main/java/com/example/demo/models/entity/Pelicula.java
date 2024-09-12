package com.example.demo.models.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.example.demo.component.PeliculaComp;


@Entity
@Table(name = "peliculas")
public class Pelicula {
	
	@Id
	@Column(name = "id")
	private String imdbID;
	
	@Column(name = "titulo")
	private String Title;
	
	@Column(name = "director")
	private String Director;
	
	@Column(name = "anio")
	private String Year;
	
	@Column(name = "duracion")
	private String Runtime;
 
	@Column(name = "poster")
	private String Poster;
	
	public Pelicula() {
		super();
	}

	public String getTitle() {
		return Title;
	}

	public void setTitle(String title) {
		Title = title;
	}

	public String getDirector() {
		return Director;
	}

	public void setDirector(String director) {
		Director = director;
	}

	public String getYear() {
		return Year;
	}

	public void setYear(String year) {
		Year = year;
	}

	public String getRuntime() {
		return Runtime;
	}

	public void setRuntime(String runtime) {
		Runtime = runtime;
	}

	public String getImdbId() {
		return imdbID;
	}

	public void setImdbId(String imdbId) {
		imdbId = imdbId;
	}

	public String getPoster() {
		return Poster;
	}

	public void setPoster(String poster) {
		Poster = poster;
	}

	public Pelicula(String title, String director, String year, String runtime, String imdbId, String poster) {
		super();
		Title = title;
		Director = director;
		Year = year;
		Runtime = runtime;
		imdbID = imdbId;
		Poster = poster;
	}
	
	public void copia(PeliculaComp p2) {
		this.Director=p2.getDirector();
		this.imdbID=p2.getImdbId();
		this.Poster=p2.getPoster();
		this.Runtime=p2.getRuntime();
		this.Title=p2.getTitle();
		this.Year=p2.getYear();
	}

	@Override
	public String toString() {
		return "Pelicula [imdbID=" + imdbID + ", Title=" + Title + ", Director=" + Director + ", Year=" + Year
				+ ", Runtime=" + Runtime + ", Poster=" + Poster + "]";
	}

}
