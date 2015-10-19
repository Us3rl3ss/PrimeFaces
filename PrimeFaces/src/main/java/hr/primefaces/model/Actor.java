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
	private Date dateOfBirth;

	@Column(name = "place_of_birth", nullable = false)
	private String placeOfBirth;
	private String info;

	@Lob
	@Basic(fetch = FetchType.EAGER)
	@Column(name = "IMAGE")
	private byte[] image;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "movie_actor", joinColumns = { @JoinColumn(name = "actor_id", referencedColumnName = "id") },
		inverseJoinColumns = { @JoinColumn(name = "movie_id", referencedColumnName = "id") })
	private List<Movie> movieList;

	public Actor() {
	}

	public Actor(final String p_firstname, final String p_lastname, final Date p_dateOfBirth, final Date p_created) {
		this.firstname = p_firstname;
		this.lastname = p_lastname;
		this.dateOfBirth = p_dateOfBirth;
	}

	public Actor(final String p_firstname, final String p_lastname, final Date p_dateOfBirth, final String p_placeOfBirth, final String p_info,
			final Date p_created) {
		this.firstname = p_firstname;
		this.lastname = p_lastname;
		this.dateOfBirth = p_dateOfBirth;
		this.placeOfBirth = p_placeOfBirth;
		this.info = p_info;
	}

	@Override
	public boolean equals(final Object p_obj){

		return this.getId() == ((Actor) p_obj).getId();
	}

//	/* (non-Javadoc)
//	 * @see java.lang.Object#hashCode()
//	 */
//	@Override
//	public int hashCode() {
//		final int prime = 31;
//		int result = 1;
//		result = prime * result + ((date_of_birth == null) ? 0 : date_of_birth.hashCode());
//		result = prime * result + ((firstname == null) ? 0 : firstname.hashCode());
//		result = prime * result + ((id == null) ? 0 : id.hashCode());
//		result = prime * result + Arrays.hashCode(image);
//		result = prime * result + ((info == null) ? 0 : info.hashCode());
//		result = prime * result + ((lastname == null) ? 0 : lastname.hashCode());
//		result = prime * result + ((movieList == null) ? 0 : movieList.hashCode());
//		result = prime * result + ((place_of_birth == null) ? 0 : place_of_birth.hashCode());
//		return result;
//	}
//
//	/* (non-Javadoc)
//	 * @see java.lang.Object#equals(java.lang.Object)
//	 */
//	@Override
//	public boolean equals(final Object p_obj) {
//		if (this == p_obj) {
//			return true;
//		}
//		if (p_obj == null) {
//			return false;
//		}
//		if (getClass() != p_obj.getClass()) {
//			return false;
//		}
//		final Actor other = (Actor) p_obj;
//		if (date_of_birth == null) {
//			if (other.date_of_birth != null) {
//				return false;
//			}
//		}
//		else if (!date_of_birth.equals(other.date_of_birth)) {
//			return false;
//		}
//		if (firstname == null) {
//			if (other.firstname != null) {
//				return false;
//			}
//		}
//		else if (!firstname.equals(other.firstname)) {
//			return false;
//		}
//		if (id == null) {
//			if (other.id != null) {
//				return false;
//			}
//		}
//		else if (!id.equals(other.id)) {
//			return false;
//		}
//		if (!Arrays.equals(image, other.image)) {
//			return false;
//		}
//		if (info == null) {
//			if (other.info != null) {
//				return false;
//			}
//		}
//		else if (!info.equals(other.info)) {
//			return false;
//		}
//		if (lastname == null) {
//			if (other.lastname != null) {
//				return false;
//			}
//		}
//		else if (!lastname.equals(other.lastname)) {
//			return false;
//		}
//		if (movieList == null) {
//			if (other.movieList != null) {
//				return false;
//			}
//		}
//		else if (!movieList.equals(other.movieList)) {
//			return false;
//		}
//		if (place_of_birth == null) {
//			if (other.place_of_birth != null) {
//				return false;
//			}
//		}
//		else if (!place_of_birth.equals(other.place_of_birth)) {
//			return false;
//		}
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
	 * @return the firstname
	 */
	public String getFirstname() {
		return firstname;
	}

	/**
	 * @return the lastname
	 */
	public String getLastname() {
		return lastname;
	}

	/**
	 * @return the info
	 */
	public String getInfo() {
		return info;
	}

	/**
	 * @return the image
	 */
	public byte[] getImage() {
		return image;
	}

	/**
	 * @return the movieList
	 */
	public List<Movie> getMovieList() {
		return movieList;
	}

	/**
	 * @param p_id the id to set
	 */
	public void setId(final Integer p_id) {
		this.id = p_id;
	}

	/**
	 * @param p_firstname the firstname to set
	 */
	public void setFirstname(final String p_firstname) {
		this.firstname = p_firstname;
	}

	/**
	 * @param p_lastname the lastname to set
	 */
	public void setLastname(final String p_lastname) {
		this.lastname = p_lastname;
	}

	/**
	 * @param p_info the info to set
	 */
	public void setInfo(final String p_info) {
		this.info = p_info;
	}

	/**
	 * @return the dateOfBirth
	 */
	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	/**
	 * @return the placeOfBirth
	 */
	public String getPlaceOfBirth() {
		return placeOfBirth;
	}

	/**
	 * @param p_dateOfBirth the dateOfBirth to set
	 */
	public void setDateOfBirth(final Date p_dateOfBirth) {
		this.dateOfBirth = p_dateOfBirth;
	}

	/**
	 * @param p_placeOfBirth the placeOfBirth to set
	 */
	public void setPlaceOfBirth(final String p_placeOfBirth) {
		this.placeOfBirth = p_placeOfBirth;
	}

	/**
	 * @param p_image the image to set
	 */
	public void setImage(final byte[] p_image) {
		this.image = p_image;
	}

	/**
	 * @param p_movieList the movieList to set
	 */
	public void setMovieList(final List<Movie> p_movieList) {
		this.movieList = p_movieList;
	}

}
