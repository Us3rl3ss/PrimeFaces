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
	private Date start_time;
	private Date end_time;

	@ManyToOne
	@JoinColumn(name = "theater_id")
	private Theater theater;

	@ManyToOne
	@JoinColumn(name = "cinema_id")
	private Cinema cinema;

	@OneToOne
	@JoinColumn(name = "movie_id")
	private Movie movie;

	@OneToMany(targetEntity = ProjectionReservedSeats.class, mappedBy = "projection", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Set<ProjectionReservedSeats> projectionReservedSeatsList;

	@Transient
	private String numberOfFreeSeatsText;

	public Projection() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getStart_time() {
		return start_time;
	}

	public void setStart_time(Date start_time) {
		this.start_time = start_time;
	}

	public Date getEnd_time() {
		return end_time;
	}

	public void setEnd_time(Date end_time) {
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
