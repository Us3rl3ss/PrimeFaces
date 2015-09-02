package hr.primefaces.model;


/*
 * Imports
 */

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
	
}
