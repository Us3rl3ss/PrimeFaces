package hr.primefaces.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "cinema")
public class Cinema implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Integer id;
	private String name;
	private int number_of_seats;
	private int number_of_rows;
	private int number_of_seats_in_row;

	@ManyToOne
	@JoinColumn(name = "theater_id")
	private Theater theater;

	public void setProjectionList(List<Projection> projectionList) {
		this.projectionList = projectionList;
	}

	@OneToMany(targetEntity = Projection.class, mappedBy = "cinema", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Projection> projectionList;

	@OneToMany(targetEntity = CinemaSeats.class, mappedBy = "cinema", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<CinemaSeats> cinemaSeatsList = new ArrayList<CinemaSeats>();
	
	public Cinema() {
	}

	public Cinema(String name, int number_of_seats) {
		this.name = name;
		this.number_of_seats = number_of_seats;
	}
	
	@Override
	public boolean equals(Object obj) {
		
		if (this.getId() == ((Cinema) obj).getId())
				return true;
		else
			return false;
	}
	
	// bidirectional association
	public void addToCinemaSeatsList(CinemaSeats cs) {
		this.cinemaSeatsList.add(cs);
		cs.setCinema(this);
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getNumber_of_seats() {
		return number_of_seats;
	}

	public void setNumber_of_seats(int number_of_seats) {
		this.number_of_seats = number_of_seats;
	}

	public Theater getTheater() {
		return theater;
	}

	public void setTheater(Theater theater) {
		this.theater = theater;
	}

	public List<CinemaSeats> getCinemaSeatsList() {
		return cinemaSeatsList;
	}

	public void setCinemaSeatsList(List<CinemaSeats> cinemaSeatsList) {
		this.cinemaSeatsList = cinemaSeatsList;
	}

	public int getNumber_of_rows() {
		return number_of_rows;
	}

	public void setNumber_of_rows(int number_of_rows) {
		this.number_of_rows = number_of_rows;
	}

	public int getNumber_of_seats_in_row() {
		return number_of_seats_in_row;
	}

	public List<Projection> getProjectionList() {
		return projectionList;
	}

	public void setNumber_of_seats_in_row(int number_of_seats_in_row) {
		this.number_of_seats_in_row = number_of_seats_in_row;
	}

}
