package hr.primefaces.model;

import java.io.Serializable;
import java.util.ArrayList;
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
@Table(name = "cinema")
public class Cinema implements Serializable {

	private static final long serialVersionUID = 1L;

	// TODO equals and hash

	@Id
	@GeneratedValue
	private Integer id;

	@Column(name = "name", nullable = false)
	private String name;

	@Column(name = "number_of_seats", nullable = false)
	private int numberOfSeats;

	@Column(name = "number_of_rows", nullable = false)
	private int numberOfRows;

	@Column(name = "number_of_seats_in_row", nullable = false)
	private int numberOfSeatsInRow;

	@ManyToOne
	@JoinColumn(name = "theater_id")
	private Theater theater;

	@OneToMany(targetEntity = Projection.class, mappedBy = "cinema", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Projection> projectionList;

	@OneToMany(targetEntity = CinemaSeats.class, mappedBy = "cinema", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<CinemaSeats> cinemaSeatsList = new ArrayList<CinemaSeats>();

	public Cinema() {
	}

	public Cinema(final String p_name, final int p_numberOfSeats) {
		this.name = p_name;
		this.numberOfSeats = p_numberOfSeats;
	}


	@Override
	public boolean equals(final Object p_obj) {

		return this.getId() == ((Cinema) p_obj).getId();
	}

	// bidirectional association
	public void addToCinemaSeatsList(final CinemaSeats p_cs) {
		getCinemaSeatsList().add(p_cs);
		p_cs.setCinema(this);
	}

//	/* (non-Javadoc)
//	 * @see java.lang.Object#hashCode()
//	 */
//	@Override
//	public int hashCode() {
//		final int prime = 31;
//		int result = 1;
//		result = prime * result + ((cinemaSeatsList == null) ? 0 : cinemaSeatsList.hashCode());
//		result = prime * result + ((id == null) ? 0 : id.hashCode());
//		result = prime * result + ((name == null) ? 0 : name.hashCode());
//		result = prime * result + numberOfRows;
//		result = prime * result + numberOfSeats;
//		result = prime * result + numberOfSeatsInRow;
//		result = prime * result + ((projectionList == null) ? 0 : projectionList.hashCode());
//		result = prime * result + ((theater == null) ? 0 : theater.hashCode());
//		return result;
//	}
//
//	/* (non-Javadoc)
//	 * @see java.lang.Object#equals(java.lang.Object)
//	 */
//	@Override
//	public boolean equals(Object obj) {
//		if (this == obj)
//			return true;
//		if (obj == null)
//			return false;
//		if (getClass() != obj.getClass())
//			return false;
//		Cinema other = (Cinema) obj;
//		if (cinemaSeatsList == null) {
//			if (other.cinemaSeatsList != null)
//				return false;
//		} else if (!cinemaSeatsList.equals(other.cinemaSeatsList))
//			return false;
//		if (id == null) {
//			if (other.id != null)
//				return false;
//		} else if (!id.equals(other.id))
//			return false;
//		if (name == null) {
//			if (other.name != null)
//				return false;
//		} else if (!name.equals(other.name))
//			return false;
//		if (numberOfRows != other.numberOfRows)
//			return false;
//		if (numberOfSeats != other.numberOfSeats)
//			return false;
//		if (numberOfSeatsInRow != other.numberOfSeatsInRow)
//			return false;
//		if (projectionList == null) {
//			if (other.projectionList != null)
//				return false;
//		} else if (!projectionList.equals(other.projectionList))
//			return false;
//		if (theater == null) {
//			if (other.theater != null)
//				return false;
//		} else if (!theater.equals(other.theater))
//			return false;
//		return true;
//	}

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
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return the numberOfSeats
	 */
	public int getNumberOfSeats() {
		return numberOfSeats;
	}

	/**
	 * @return the numberOfRows
	 */
	public int getNumberOfRows() {
		return numberOfRows;
	}

	/**
	 * @return the numberOfSeatsInRow
	 */
	public int getNumberOfSeatsInRow() {
		return numberOfSeatsInRow;
	}

	/**
	 * @return the theater
	 */
	public Theater getTheater() {
		return theater;
	}

	/**
	 * @return the projectionList
	 */
	public List<Projection> getProjectionList() {
		return projectionList;
	}

	/**
	 * @return the cinemaSeatsList
	 */
	public List<CinemaSeats> getCinemaSeatsList() {
		return cinemaSeatsList;
	}

	/**
	 * @param p_id the id to set
	 */
	public void setId(final Integer p_id) {
		this.id = p_id;
	}

	/**
	 * @param p_name the name to set
	 */
	public void setName(final String p_name) {
		this.name = p_name;
	}

	/**
	 * @param p_numberOfSeats the numberOfSeats to set
	 */
	public void setNumberOfSeats(final int p_numberOfSeats) {
		this.numberOfSeats = p_numberOfSeats;
	}

	/**
	 * @param p_numberOfRows the numberOfRows to set
	 */
	public void setNumberOfRows(final int p_numberOfRows) {
		this.numberOfRows = p_numberOfRows;
	}

	/**
	 * @param p_numberOfSeatsInRow the numberOfSeatsInRow to set
	 */
	public void setNumberOfSeatsInRow(final int p_numberOfSeatsInRow) {
		this.numberOfSeatsInRow = p_numberOfSeatsInRow;
	}

	/**
	 * @param p_theater the theater to set
	 */
	public void setTheater(final Theater p_theater) {
		this.theater = p_theater;
	}

	/**
	 * @param p_projectionList the projectionList to set
	 */
	public void setProjectionList(final List<Projection> p_projectionList) {
		this.projectionList = p_projectionList;
	}

	/**
	 * @param p_cinemaSeatsList the cinemaSeatsList to set
	 */
	public void setCinemaSeatsList(final List<CinemaSeats> p_cinemaSeatsList) {
		this.cinemaSeatsList = p_cinemaSeatsList;
	}

}
