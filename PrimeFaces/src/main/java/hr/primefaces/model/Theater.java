package hr.primefaces.model;


/*
 * Imports
 */

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "theater")
public class Theater implements Serializable {

	private static final long serialVersionUID = 1L;

	// TODO equals and hash

	@Id
	@GeneratedValue
	private Integer id;

	@Column(name = "name", nullable = false)
	private String name;

	@Column(name = "address", nullable = false)
	private String address;

	@Column(name = "lat", nullable = false)
	private Double lat;

	@Column(name = "lng", nullable = false)
	private Double lng;

	@OneToMany(targetEntity = Cinema.class,
			mappedBy = "theater",
			cascade = CascadeType.ALL,
			fetch = FetchType.LAZY)
	private List<Cinema> cinema;

	@OneToMany(targetEntity = Projection.class,
			mappedBy = "theater",
			cascade = CascadeType.ALL,
			fetch = FetchType.LAZY)
	private List<Projection> projection;

	public Theater() {}

	@Override
	public boolean equals(final Object p_obj) {

		return this.getId() == ((Theater) p_obj).getId();
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
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * @return the lat
	 */
	public Double getLat() {
		return lat;
	}

	/**
	 * @return the lng
	 */
	public Double getLng() {
		return lng;
	}

	/**
	 * @return the cinema
	 */
	public List<Cinema> getCinema() {
		return cinema;
	}

	/**
	 * @return the projection
	 */
	public List<Projection> getProjection() {
		return projection;
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
	 * @param p_address the address to set
	 */
	public void setAddress(final String p_address) {
		this.address = p_address;
	}

	/**
	 * @param p_lat the lat to set
	 */
	public void setLat(final Double p_lat) {
		this.lat = p_lat;
	}

	/**
	 * @param p_lng the lng to set
	 */
	public void setLng(final Double p_lng) {
		this.lng = p_lng;
	}

	/**
	 * @param p_cinema the cinema to set
	 */
	public void setCinema(final List<Cinema> p_cinema) {
		this.cinema = p_cinema;
	}

	/**
	 * @param p_projection the projection to set
	 */
	public void setProjection(final List<Projection> p_projection) {
		this.projection = p_projection;
	}

}
