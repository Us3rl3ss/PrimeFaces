package hr.primefaces.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "cinema_movie")
public class CinemaMovie implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Integer id;
	private int cinemaId;
	private int movieId;
	private Date startRunning;
	private Date endRunning;
	private Date created;

	public CinemaMovie() {
	}

	public CinemaMovie(int cinemaId, int movieId, Date startRunning,
			Date endRunning, Date created) {
		this.cinemaId = cinemaId;
		this.movieId = movieId;
		this.startRunning = startRunning;
		this.endRunning = endRunning;
		this.created = created;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public int getCinemaId() {
		return this.cinemaId;
	}

	public void setCinemaId(int cinemaId) {
		this.cinemaId = cinemaId;
	}

	public int getMovieId() {
		return this.movieId;
	}

	public void setMovieId(int movieId) {
		this.movieId = movieId;
	}

	public Date getStartRunning() {
		return this.startRunning;
	}

	public void setStartRunning(Date startRunning) {
		this.startRunning = startRunning;
	}

	public Date getEndRunning() {
		return this.endRunning;
	}

	public void setEndRunning(Date endRunning) {
		this.endRunning = endRunning;
	}

	public Date getCreated() {
		return this.created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

}
