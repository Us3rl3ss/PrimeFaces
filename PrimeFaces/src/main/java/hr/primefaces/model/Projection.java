package hr.primefaces.model;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "projection")
public class Projection implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Integer id;
	private Date date;
	private String start_time;
	private String end_time;

	@ManyToOne
	@JoinColumn(name = "theater_id")
	private Theater theater;

	@ManyToOne
	@JoinColumn(name = "cinema_id")
	private Cinema cinema;

	@OneToOne
	@JoinColumn(name = "movie_id")
	private Movie movie;

	@OneToMany(targetEntity = ProjectionReservedSeats.class, mappedBy = "projection", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Set<ProjectionReservedSeats> projectionReservedSeatsList;

	@Transient
	private String numberOfFreeSeatsText;

	public Projection() {
	}

	public Projection(Date created, int cinemaId, int movieId, Date date, String startTime, String endTime) {
		this.date = date;
		this.start_time = startTime;
		this.end_time = endTime;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getStart_time() {
		return start_time;
	}

	public void setStart_time(String start_time) {
		this.start_time = start_time;
	}

	public String getEnd_time() {
		return end_time;
	}

	public void setEnd_time(String end_time) {
		this.end_time = end_time;
	}

	public Cinema getCinema() {
		return cinema;
	}

	public void setCinema(Cinema cinema) {
		this.cinema = cinema;
	}

	public Movie getMovie() {
		return movie;
	}

	public void setMovie(Movie movie) {
		this.movie = movie;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Set<ProjectionReservedSeats> getProjectionReservedSeatsList() {
		return projectionReservedSeatsList;
	}

	public void setProjectionReservedSeatsList(Set<ProjectionReservedSeats> projectionReservedSeatsList) {
		this.projectionReservedSeatsList = projectionReservedSeatsList;
	}

	public Theater getTheater() {
		return theater;
	}

	public void setTheater(Theater theater) {
		this.theater = theater;
	}

	public String getNumberOfFreeSeatsText() {
		return numberOfFreeSeatsText;
	}

	public void setNumberOfFreeSeatsText(String numberOfFreeSeatsText) {
		this.numberOfFreeSeatsText = numberOfFreeSeatsText;
	}

}
