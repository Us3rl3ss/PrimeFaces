package hr.primefaces.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "cinema_seats")
public class CinemaSeats implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Integer id;
	private String seats_row;
	private int seats_number;

	@ManyToOne
	@JoinColumn(name = "cinema_id")
	private Cinema cinema;

	@OneToOne(targetEntity = ProjectionReservedSeats.class, mappedBy = "cinema_seats", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private ProjectionReservedSeats projectionReservedSeats;

	public CinemaSeats() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getSeats_row() {
		return seats_row;
	}

	public void setSeats_row(String seats_row) {
		this.seats_row = seats_row;
	}

	public int getSeats_number() {
		return seats_number;
	}

	public void setSeats_number(int seats_number) {
		this.seats_number = seats_number;
	}

	public Cinema getCinema() {
		return cinema;
	}

	public void setCinema(Cinema cinema) {
		this.cinema = cinema;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public ProjectionReservedSeats getProjectionReservedSeats() {
		return projectionReservedSeats;
	}

	public void setProjectionReservedSeats(
			ProjectionReservedSeats projectionReservedSeats) {
		this.projectionReservedSeats = projectionReservedSeats;
	}

}
