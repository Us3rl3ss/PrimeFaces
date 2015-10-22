package hr.primefaces.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Set;

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
import javax.persistence.Transient;

@Entity
@Table(name = "movie")
public class Movie implements Serializable {

	private static final long serialVersionUID = 1L;

	// TODO equals and hash

	@Id
	@GeneratedValue
	private Integer id;

	@Column(name = "name", nullable = false)
	private String name;

	@Column(name = "year_of_creation", nullable = false)
	private int yearOfCreation;

	@Column(name = "director_name", nullable = false)
	private String directorName;

	@Column(name = "duration", nullable = false)
	private int duration;

	@Column(name = "info", nullable = false)
	private String info;

	@Lob
	@Basic(fetch = FetchType.EAGER)
	@Column(name = "IMAGE")
	private byte[] image;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "movie_actor", joinColumns = { @JoinColumn(name = "movie_id", referencedColumnName = "id") },
		inverseJoinColumns = { @JoinColumn(name = "actor_id", referencedColumnName = "id") })
	private List<Actor> actorList;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "movie_genre", joinColumns = { @JoinColumn(name = "movie_id", referencedColumnName = "id") },
		inverseJoinColumns = { @JoinColumn(name = "genre_id", referencedColumnName = "id") })
	private List<Genre> genreList;

	@OneToMany(targetEntity = Projection.class, mappedBy = "movie", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Set<Projection> projection;

	@OneToMany(targetEntity = UserMovieRate.class, mappedBy = "movie", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Set<UserMovieRate> userMovieRate;

	@OneToMany(targetEntity = UserMovieReview.class, mappedBy = "movie", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Set<UserMovieReview> userMovieReview;

	@OneToMany(targetEntity = UserFavoriteMovie.class, mappedBy = "movie", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Set<UserFavoriteMovie> userFavoriteMovie;

	@Transient
	private String listOfActorsText;

	@Transient
	private String listOfGenresText;

	public Movie() {
	}

	public Movie(final String p_name, final Date p_created, final int p_yearOfCreation,
			final String p_directorName, final int p_duration, final String p_info) {
		this.name = p_name;
		this.yearOfCreation = p_yearOfCreation;
		this.directorName = p_directorName;
		this.duration = p_duration;
		this.info = p_info;
	}

	@Override
	public boolean equals(final Object p_obj) {

		return this.getId() == ((Movie) p_obj).getId();
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
	 * @return the yearOfCreation
	 */
	public int getYearOfCreation() {
		return yearOfCreation;
	}

	/**
	 * @return the directorName
	 */
	public String getDirectorName() {
		return directorName;
	}

	/**
	 * @return the duration
	 */
	public int getDuration() {
		return duration;
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
	 * @return the actorList
	 */
	public List<Actor> getActorList() {
		return actorList;
	}

	/**
	 * @return the genreList
	 */
	public List<Genre> getGenreList() {
		return genreList;
	}

	/**
	 * @return the projection
	 */
	public Set<Projection> getProjection() {
		return projection;
	}

	/**
	 * @return the userMovieRate
	 */
	public Set<UserMovieRate> getUserMovieRate() {
		return userMovieRate;
	}

	/**
	 * @return the userMovieReview
	 */
	public Set<UserMovieReview> getUserMovieReview() {
		return userMovieReview;
	}

	/**
	 * @return the userFavoriteMovie
	 */
	public Set<UserFavoriteMovie> getUserFavoriteMovie() {
		return userFavoriteMovie;
	}

	/**
	 * @return the listOfActorsText
	 */
	public String getListOfActorsText() {
		return listOfActorsText;
	}

	/**
	 * @return the listOfGenresText
	 */
	public String getListOfGenresText() {
		return listOfGenresText;
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
	 * @param p_yearOfCreation the yearOfCreation to set
	 */
	public void setYearOfCreation(final int p_yearOfCreation) {
		this.yearOfCreation = p_yearOfCreation;
	}

	/**
	 * @param p_directorName the directorName to set
	 */
	public void setDirectorName(final String p_directorName) {
		this.directorName = p_directorName;
	}

	/**
	 * @param p_duration the duration to set
	 */
	public void setDuration(final int p_duration) {
		this.duration = p_duration;
	}

	/**
	 * @param p_info the info to set
	 */
	public void setInfo(final String p_info) {
		this.info = p_info;
	}

	/**
	 * @param p_image the image to set
	 */
	public void setImage(final byte[] p_image) {
		this.image = p_image;
	}

	/**
	 * @param p_actorList the actorList to set
	 */
	public void setActorList(final List<Actor> p_actorList) {
		this.actorList = p_actorList;
	}

	/**
	 * @param p_genreList the genreList to set
	 */
	public void setGenreList(final List<Genre> p_genreList) {
		this.genreList = p_genreList;
	}

	/**
	 * @param p_projection the projection to set
	 */
	public void setProjection(final Set<Projection> p_projection) {
		this.projection = p_projection;
	}

	/**
	 * @param p_userMovieRate the userMovieRate to set
	 */
	public void setUserMovieRate(final Set<UserMovieRate> p_userMovieRate) {
		this.userMovieRate = p_userMovieRate;
	}

	/**
	 * @param p_userMovieReview the userMovieReview to set
	 */
	public void setUserMovieReview(final Set<UserMovieReview> p_userMovieReview) {
		this.userMovieReview = p_userMovieReview;
	}

	/**
	 * @param p_userFavoriteMovie the userFavoriteMovie to set
	 */
	public void setUserFavoriteMovie(final Set<UserFavoriteMovie> p_userFavoriteMovie) {
		this.userFavoriteMovie = p_userFavoriteMovie;
	}

	/**
	 * @param p_listOfActorsText the listOfActorsText to set
	 */
	public void setListOfActorsText(final String p_listOfActorsText) {
		this.listOfActorsText = p_listOfActorsText;
	}

	/**
	 * @param p_listOfGenresText the listOfGenresText to set
	 */
	public void setListOfGenresText(final String p_listOfGenresText) {
		this.listOfGenresText = p_listOfGenresText;
	}

}
