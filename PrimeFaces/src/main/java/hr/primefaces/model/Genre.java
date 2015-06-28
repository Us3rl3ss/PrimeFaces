package hr.primefaces.model;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "genre")
public class Genre implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Integer id;
	private String name;
	private Date created;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "movie_genre", joinColumns = { @JoinColumn(name = "genre_id", referencedColumnName = "id") }, inverseJoinColumns = { @JoinColumn(name = "movie_id", referencedColumnName = "id") })
	private List<Genre> genreList;

	public Genre() {
	}

	public Genre(String name, Date created) {
		this.name = name;
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

	public List<Genre> getGenreList() {
		return genreList;
	}

	public void setGenreList(List<Genre> genreList) {
		this.genreList = genreList;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

}
