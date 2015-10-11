package hr.primefaces.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "actor")
public class Actor implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Integer id;

	@Column(name = "firstname", nullable = false)
	private String firstname;

	@Column(name = "lastname", nullable = false)
	private String lastname;

	@Column(name = "date_of_birth", nullable = false)
	private Date date_of_birth;

	private String place_of_birth;
	private String info;
	
	@Lob
	@Basic(fetch = FetchType.EAGER)
	@Column(name = "IMAGE")
	private byte[] image;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "movie_actor", joinColumns = { @JoinColumn(name = "actor_id", referencedColumnName = "id") }, inverseJoinColumns = { @JoinColumn(name = "movie_id", referencedColumnName = "id") })
	private List<Movie> movieList;

	public Actor() {
	}

	public Actor(String firstname, String lastname, Date dateOfBirth,
			Date created) {
		this.firstname = firstname;
		this.lastname = lastname;
		this.date_of_birth = dateOfBirth;
	}

	public Actor(String firstname, String lastname, Date dateOfBirth,
			String placeOfBirth, String info, Date created) {
		this.firstname = firstname;
		this.lastname = lastname;
		this.date_of_birth = dateOfBirth;
		this.place_of_birth = placeOfBirth;
		this.info = info;
	}
	
	@Override
	public boolean equals(Object obj) {
		
		if (this.getId() == ((Actor) obj).getId())
				return true;
		else
			return false;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public Date getDate_of_birth() {
		return date_of_birth;
	}

	public void setDate_of_birth(Date date_of_birth) {
		this.date_of_birth = date_of_birth;
	}

	public String getPlace_of_birth() {
		return place_of_birth;
	}

	public void setPlace_of_birth(String place_of_birth) {
		this.place_of_birth = place_of_birth;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public List<Movie> getMovieList() {
		return movieList;
	}

	public void setMovieList(List<Movie> movieList) {
		this.movieList = movieList;
	}

	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}

}
