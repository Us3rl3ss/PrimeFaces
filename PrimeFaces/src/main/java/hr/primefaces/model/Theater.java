package hr.primefaces.model;


/*
 * Imports
 */

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
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
	
	
	/*
	 * Private Attributes
	 */

	@Id
	@GeneratedValue
	private Integer id;
	private String name;
	private String address;
	private Double lat;
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

	
	/*
	 * Constructors
	 */
	
	public Theater() {}
	
	@Override
	public boolean equals(Object obj) {
		
		if (this.getId() == ((Theater) obj).getId())
				return true;
		else
			return false;
	}
	
	/*
	 * Getters and Setters
	 */

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public List<Cinema> getCinema() {
		return cinema;
	}

	public void setCinema(List<Cinema> cinema) {
		this.cinema = cinema;
	}

	public List<Projection> getProjection() {
		return projection;
	}

	public void setProjection(List<Projection> projection) {
		this.projection = projection;
	}

	public Double getLat() {
		return lat;
	}

	public void setLat(Double lat) {
		this.lat = lat;
	}

	public Double getLng() {
		return lng;
	}

	public void setLng(Double lng) {
		this.lng = lng;
	}
	
}
