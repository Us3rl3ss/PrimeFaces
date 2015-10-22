package hr.primefaces.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "cinema_seats")
public class CinemaSeats implements Serializable {

	private static final long serialVersionUID = 1L;

	// TODO equals and hash

	@Id
	@GeneratedValue
	private Integer id;

	@Column(name = "seats_row", nullable = false)
	private String seatsRow;

	@Column(name = "seats_number", nullable = false)
	private int seatsNumber;

	@ManyToOne
	@JoinColumn(name = "cinema_id")
	private Cinema cinema;

	@OneToMany(targetEntity = ProjectionReservedSeats.class, mappedBy = "cinemaSeats", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<ProjectionReservedSeats> projectionReservedSeatsList;

	public CinemaSeats() {
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
	 * @return the seatsRow
	 */
	public String getSeatsRow() {
		return seatsRow;
	}

	/**
	 * @return the seatsNumber
	 */
	public int getSeatsNumber() {
		return seatsNumber;
	}

	/**
	 * @return the cinema
	 */
	public Cinema getCinema() {
		return cinema;
	}

	/**
	 * @return the projectionReservedSeatsList
	 */
	public List<ProjectionReservedSeats> getProjectionReservedSeatsList() {
		return projectionReservedSeatsList;
	}

	/**
	 * @param p_id the id to set
	 */
	public void setId(final Integer p_id) {
		this.id = p_id;
	}

	/**
	 * @param p_seatsRow the seatsRow to set
	 */
	public void setSeatsRow(final String p_seatsRow) {
		this.seatsRow = p_seatsRow;
	}

	/**
	 * @param p_seatsNumber the seatsNumber to set
	 */
	public void setSeatsNumber(final int p_seatsNumber) {
		this.seatsNumber = p_seatsNumber;
	}

	/**
	 * @param p_cinema the cinema to set
	 */
	public void setCinema(final Cinema p_cinema) {
		this.cinema = p_cinema;
	}

	/**
	 * @param p_projectionReservedSeatsList the projectionReservedSeatsList to set
	 */
	public void setProjectionReservedSeatsList(final List<ProjectionReservedSeats> p_projectionReservedSeatsList) {
		this.projectionReservedSeatsList = p_projectionReservedSeatsList;
	}

}
