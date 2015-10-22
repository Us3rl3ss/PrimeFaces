package hr.primefaces.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
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
public class Projection implements Serializable {

	private static final long serialVersionUID = 1L;

	// TODO equals and hash

	@Id
	@GeneratedValue
	private Integer id;

	@Column(name = "start_time", nullable = false)
	private Date startTime;

	@Column(name = "end_time", nullable = false)
	private Date endTime;

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

	/**
	 * ################# GETTERS AND SETTERS #################
	 */

	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @return the startTime
	 */
	public Date getStartTime() {
		return startTime;
	}

	/**
	 * @return the endTime
	 */
	public Date getEndTime() {
		return endTime;
	}

	/**
	 * @return the theater
	 */
	public Theater getTheater() {
		return theater;
	}

	/**
	 * @return the cinema
	 */
	public Cinema getCinema() {
		return cinema;
	}

	/**
	 * @return the movie
	 */
	public Movie getMovie() {
		return movie;
	}

	/**
	 * @return the projectionReservedSeatsList
	 */
	public Set<ProjectionReservedSeats> getProjectionReservedSeatsList() {
		return projectionReservedSeatsList;
	}

	/**
	 * @return the numberOfFreeSeatsText
	 */
	public String getNumberOfFreeSeatsText() {
		return numberOfFreeSeatsText;
	}

	/**
	 * @param p_id the id to set
	 */
	public void setId(final Integer p_id) {
		this.id = p_id;
	}

	/**
	 * @param p_startTime the startTime to set
	 */
	public void setStartTime(final Date p_startTime) {
		this.startTime = p_startTime;
	}

	/**
	 * @param p_endTime the endTime to set
	 */
	public void setEndTime(final Date p_endTime) {
		this.endTime = p_endTime;
	}

	/**
	 * @param p_theater the theater to set
	 */
	public void setTheater(final Theater p_theater) {
		this.theater = p_theater;
	}

	/**
	 * @param p_cinema the cinema to set
	 */
	public void setCinema(final Cinema p_cinema) {
		this.cinema = p_cinema;
	}

	/**
	 * @param p_movie the movie to set
	 */
	public void setMovie(final Movie p_movie) {
		this.movie = p_movie;
	}

	/**
	 * @param p_projectionReservedSeatsList the projectionReservedSeatsList to set
	 */
	public void setProjectionReservedSeatsList(final Set<ProjectionReservedSeats> p_projectionReservedSeatsList) {
		this.projectionReservedSeatsList = p_projectionReservedSeatsList;
	}

	/**
	 * @param p_numberOfFreeSeatsText the numberOfFreeSeatsText to set
	 */
	public void setNumberOfFreeSeatsText(final String p_numberOfFreeSeatsText) {
		this.numberOfFreeSeatsText = p_numberOfFreeSeatsText;
	}

}
