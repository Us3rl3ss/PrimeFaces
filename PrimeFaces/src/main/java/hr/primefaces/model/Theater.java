package hr.primefaces.model;


/*
 * Imports
 */

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "theater")
public class Theater implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	
	
	/*
	 * Private Attributes
	 */

	@Id
	@GeneratedValue
	private Integer id;
	private String name;
	private String place;
	private String address;

	@OneToMany(targetEntity = Cinema.class, 
			mappedBy = "theater", 
			cascade = CascadeType.ALL, 
			fetch = FetchType.EAGER)
	private Set<Cinema> cinema;

	@OneToMany(targetEntity = Projection.class, 
			mappedBy = "theater", 
			cascade = CascadeType.ALL, 
			fetch = FetchType.EAGER)
	private Set<Projection> projection;

	
	/*
	 * Constructors
	 */
	
	public Theater() {}
	
	public Theater(Integer id, String name, String place, String address, Set<Cinema> cinema, Set<Projection> projection) {
		super();
		this.id = id;
		this.name = name;
		this.place = place;
		this.address = address;
		this.cinema = cinema;
		this.projection = projection;
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

	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Set<Cinema> getCinema() {
		return cinema;
	}

	public void setCinema(Set<Cinema> cinema) {
		this.cinema = cinema;
	}

	public Set<Projection> getProjection() {
		return projection;
	}

	public void setProjection(Set<Projection> projection) {
		this.projection = projection;
	}
	
}
