package hr.primefaces.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "projection_reserved_seats")
public class ProjectionReservedSeats implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Integer id;

	@ManyToOne
	@JoinColumn(name = "projection_id")
	private Projection projection;

	@ManyToOne
	@JoinColumn(name = "cinema_seats_id")
	private CinemaSeats cinema_seats;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;

	public ProjectionReservedSeats() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Projection getProjection() {
		return projection;
	}

	public void setProjection(Projection projection) {
		this.projection = projection;
	}

	public CinemaSeats getCinema_seats() {
		return cinema_seats;
	}

	public void setCinema_seats(CinemaSeats cinema_seats) {
		this.cinema_seats = cinema_seats;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
