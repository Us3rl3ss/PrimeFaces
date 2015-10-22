package hr.primefaces.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "projection_reserved_seats")
public class ProjectionReservedSeats implements Serializable {

	private static final long serialVersionUID = 1L;

	// TODO equals and hash

	@Id
	@GeneratedValue
	private Integer id;

	@ManyToOne
	@JoinColumn(name = "projection_id")
	private Projection projection;

	@ManyToOne
	@JoinColumn(name = "cinema_seats_id")
	private CinemaSeats cinemaSeats;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;

	public ProjectionReservedSeats() {
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
	 * @return the projection
	 */
	public Projection getProjection() {
		return projection;
	}

	/**
	 * @return the cinemaSeats
	 */
	public CinemaSeats getCinemaSeats() {
		return cinemaSeats;
	}

	/**
	 * @return the user
	 */
	public User getUser() {
		return user;
	}

	/**
	 * @param p_id the id to set
	 */
	public void setId(final Integer p_id) {
		this.id = p_id;
	}

	/**
	 * @param p_projection the projection to set
	 */
	public void setProjection(final Projection p_projection) {
		this.projection = p_projection;
	}

	/**
	 * @param p_cinemaSeats the cinemaSeats to set
	 */
	public void setCinemaSeats(final CinemaSeats p_cinemaSeats) {
		this.cinemaSeats = p_cinemaSeats;
	}

	/**
	 * @param p_user the user to set
	 */
	public void setUser(final User p_user) {
		this.user = p_user;
	}

}
