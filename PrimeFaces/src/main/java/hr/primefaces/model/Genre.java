package hr.primefaces.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "genre")
public class Genre implements Serializable {

	private static final long serialVersionUID = 1L;

	// TODO equals and hash

	@Id
	@GeneratedValue
	private Integer id;

	@Column(name = "name", nullable = false)
	private String name;

	@Column(name = "created", nullable = false)
	private Date created;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "movie_genre", joinColumns = { @JoinColumn(name = "genre_id", referencedColumnName = "id") },
		inverseJoinColumns = { @JoinColumn(name = "movie_id", referencedColumnName = "id") })
	private List<Genre> genreList;

	public Genre() {
	}

	public Genre(final String p_name, final Date p_created) {
		this.name = p_name;
	}

	@Override
	public boolean equals(final Object p_obj) {

		return this.getId() == ((Genre) p_obj).getId();
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
	 * @return the created
	 */
	public Date getCreated() {
		return created;
	}

	/**
	 * @return the genreList
	 */
	public List<Genre> getGenreList() {
		return genreList;
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
	 * @param p_created the created to set
	 */
	public void setCreated(final Date p_created) {
		this.created = p_created;
	}

	/**
	 * @param p_genreList the genreList to set
	 */
	public void setGenreList(final List<Genre> p_genreList) {
		this.genreList = p_genreList;
	}

}
