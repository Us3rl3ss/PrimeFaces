package hr.primefaces.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "movie_genre")
public class MovieGenre implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Integer id;
	private int movieId;
	private int genreId;
	private Date created;

	public MovieGenre() {
	}

	public MovieGenre(int movieId, int genreId) {
		this.movieId = movieId;
		this.genreId = genreId;
	}

	public MovieGenre(int movieId, int genreId, Date created) {
		this.movieId = movieId;
		this.genreId = genreId;
		this.created = created;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public int getMovieId() {
		return this.movieId;
	}

	public void setMovieId(int movieId) {
		this.movieId = movieId;
	}

	public int getGenreId() {
		return this.genreId;
	}

	public void setGenreId(int genreId) {
		this.genreId = genreId;
	}

	public Date getCreated() {
		return this.created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

}
