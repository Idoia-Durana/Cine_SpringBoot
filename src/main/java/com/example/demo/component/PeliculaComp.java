package com.example.demo.component;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import com.example.demo.models.entity.Pelicula;

	@Component
	@SessionScope
	public class PeliculaComp {
		
		private String imdbID;
		
		private String Title;

		private String Director;
		
		private String Year;
		
		private String Runtime;
	
		private String Poster;
		
		public PeliculaComp() {
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

		public PeliculaComp(String title, String director, String year, String runtime, String imdbId, String poster) {
			super();
			Title = title;
			Director = director;
			Year = year;
			Runtime = runtime;
			imdbID = imdbId;
			Poster = poster;
		}
		
		public void copia(Pelicula p2) {
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
