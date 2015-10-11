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

	@Id
	@GeneratedValue
	private Integer id;
	private String name;
	private int year_of_creation;
	private String director_name;
	private int duration;
	private String info;

	@Lob
	@Basic(fetch = FetchType.EAGER)
	@Column(name = "IMAGE")
	private byte[] image;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "movie_actor", joinColumns = { @JoinColumn(name = "movie_id", referencedColumnName = "id") }, inverseJoinColumns = { @JoinColumn(name = "actor_id", referencedColumnName = "id") })
	private List<Actor> actorList;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "movie_genre", joinColumns = { @JoinColumn(name = "movie_id", referencedColumnName = "id") }, inverseJoinColumns = { @JoinColumn(name = "genre_id", referencedColumnName = "id") })
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

	public Movie(String name, Date created, int yearOfCreation, String directorName, int duration, String info) {
		this.name = name;
		this.year_of_creation = yearOfCreation;
		this.director_name = directorName;
		this.duration = duration;
		this.info = info;
	}
	
	@Override
	public boolean equals(Object obj) {
		
		if (this.getId() == ((Movie) obj).getId())
				return true;
		else
			return false;
	}
	
	public Movie (String name) {
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

	public int getYearOfCreation() {
		return this.year_of_creation;
	}

	public void setYearOfCreation(int yearOfCreation) {
		this.year_of_creation = yearOfCreation;
	}

	public String getDirectorName() {
		return this.director_name;
	}

	public void setDirectorName(String directorName) {
		this.director_name = directorName;
	}

	public int getDuration() {
		return this.duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public String getInfo() {
		return this.info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public int getYear_of_creation() {
		return year_of_creation;
	}

	public void setYear_of_creation(int year_of_creation) {
		this.year_of_creation = year_of_creation;
	}

	public String getDirector_name() {
		return director_name;
	}

	public void setDirector_name(String director_name) {
		this.director_name = director_name;
	}

	public Set<Projection> getProjection() {
		return projection;
	}

	public void setProjection(Set<Projection> projection) {
		this.projection = projection;
	}

	public List<Genre> getGenreList() {
		return genreList;
	}

	public void setGenreList(List<Genre> genreList) {
		this.genreList = genreList;
	}

	public List<Actor> getActorList() {
		return actorList;
	}

	public void setActorList(List<Actor> actorList) {
		this.actorList = actorList;
	}

	public Set<UserMovieRate> getUserMovieRate() {
		return userMovieRate;
	}

	public void setUserMovieRate(Set<UserMovieRate> userMovieRate) {
		this.userMovieRate = userMovieRate;
	}

	public Set<UserMovieReview> getUserMovieReview() {
		return userMovieReview;
	}

	public void setUserMovieReview(Set<UserMovieReview> userMovieReview) {
		this.userMovieReview = userMovieReview;
	}

	public Set<UserFavoriteMovie> getUserFavoriteMovie() {
		return userFavoriteMovie;
	}

	public void setUserFavoriteMovie(Set<UserFavoriteMovie> userFavoriteMovie) {
		this.userFavoriteMovie = userFavoriteMovie;
	}

	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}

	public String getListOfActorsText() {
		return listOfActorsText;
	}

	public void setListOfActorsText(String listOfActorsText) {
		this.listOfActorsText = listOfActorsText;
	}

	public String getListOfGenresText() {
		return listOfGenresText;
	}

	public void setListOfGenresText(String listOfGenresText) {
		this.listOfGenresText = listOfGenresText;
	}

}
