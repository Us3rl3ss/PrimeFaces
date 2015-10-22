package hr.primefaces.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "user_movie_review")
public class UserMovieReview implements Serializable {

	private static final long serialVersionUID = 1L;

	// TODO equals and hash

	@Id
	@GeneratedValue
	private Integer id;

	@Column(name = "review", nullable = false)
	private String review;

	@Column(name = "created", nullable = false)
	private Date created;

	@ManyToOne
	@JoinColumn(name = "movie_id")
	private Movie movie;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;

	public UserMovieReview() {
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
	 * @return the review
	 */
	public String getReview() {
		return review;
	}

	/**
	 * @return the created
	 */
	public Date getCreated() {
		return created;
	}

	/**
	 * @return the movie
	 */
	public Movie getMovie() {
		return movie;
	}

	/**
	 * @return the user
	 */
	public User getUser() {
		return user;
	}

	/**
	 * @param p_id the id to set
	 */
	public void setId(final Integer p_id) {
		this.id = p_id;
	}

	/**
	 * @param p_review the review to set
	 */
	public void setReview(final String p_review) {
		this.review = p_review;
	}

	/**
	 * @param p_created the created to set
	 */
	public void setCreated(final Date p_created) {
		this.created = p_created;
	}

	/**
	 * @param p_movie the movie to set
	 */
	public void setMovie(final Movie p_movie) {
		this.movie = p_movie;
	}

	/**
	 * @param p_user the user to set
	 */
	public void setUser(final User p_user) {
		this.user = p_user;
	}

}
