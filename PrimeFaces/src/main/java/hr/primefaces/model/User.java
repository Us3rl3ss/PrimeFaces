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
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "user")
public class User implements Serializable {

	private static final long serialVersionUID = 1L;

	// TODO equals and hash

	@Id
	@GeneratedValue
	private Integer id;

	@Column(name = "username", nullable = false)
	private String username;

	@Column(name = "firstname", nullable = false)
	private String firstname;

	@Column(name = "lastname", nullable = false)
	private String lastname;

	@Column(name = "password", nullable = false)
	private String password;

	@Column(name = "email", nullable = false)
	private String email;

	@Column(name = "date_of_birth", nullable = false)
	private Date dateOfBirth;

	private Date updated;

	@Lob
	@Basic(fetch = FetchType.EAGER)
	@Column(name = "IMAGE")
	private byte[] image;

	@OneToOne(targetEntity = Role.class, fetch = FetchType.EAGER)
	private Role role;

	@OneToMany(targetEntity = ProjectionReservedSeats.class, mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Set<ProjectionReservedSeats> projectionReservedSeats;

	@OneToMany(targetEntity = UserMovieRate.class, mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Set<UserMovieRate> userMovieRate;

	@OneToMany(targetEntity = UserMovieReview.class, mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Set<UserMovieReview> userMovieReview;

	@ManyToMany(cascade = { CascadeType.ALL })
	@JoinTable(name = "user_following", joinColumns = { @JoinColumn(name = "user_id") }, inverseJoinColumns = { @JoinColumn(name = "follow_id") })
	private List<User> userFollowList;

	@ManyToMany(mappedBy = "userFollowList")
	private List<User> follow;

	public User() {
	}

	public User(final int p_id) {
		this.id = p_id;
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
	 * @return the username
	 */
	public String getUsername() {
		return username;
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
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @return the dateOfBirth
	 */
	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	/**
	 * @return the updated
	 */
	public Date getUpdated() {
		return updated;
	}

	/**
	 * @return the image
	 */
	public byte[] getImage() {
		return image;
	}

	/**
	 * @return the role
	 */
	public Role getRole() {
		return role;
	}

	/**
	 * @return the projectionReservedSeats
	 */
	public Set<ProjectionReservedSeats> getProjectionReservedSeats() {
		return projectionReservedSeats;
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
	 * @return the userFollowList
	 */
	public List<User> getUserFollowList() {
		return userFollowList;
	}

	/**
	 * @return the follow
	 */
	public List<User> getFollow() {
		return follow;
	}

	/**
	 * @param p_id the id to set
	 */
	public void setId(final Integer p_id) {
		this.id = p_id;
	}

	/**
	 * @param p_username the username to set
	 */
	public void setUsername(final String p_username) {
		this.username = p_username;
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
	 * @param p_password the password to set
	 */
	public void setPassword(final String p_password) {
		this.password = p_password;
	}

	/**
	 * @param p_email the email to set
	 */
	public void setEmail(final String p_email) {
		this.email = p_email;
	}

	/**
	 * @param p_dateOfBirth the dateOfBirth to set
	 */
	public void setDateOfBirth(final Date p_dateOfBirth) {
		this.dateOfBirth = p_dateOfBirth;
	}

	/**
	 * @param p_updated the updated to set
	 */
	public void setUpdated(final Date p_updated) {
		this.updated = p_updated;
	}

	/**
	 * @param p_image the image to set
	 */
	public void setImage(final byte[] p_image) {
		this.image = p_image;
	}

	/**
	 * @param p_role the role to set
	 */
	public void setRole(final Role p_role) {
		this.role = p_role;
	}

	/**
	 * @param p_projectionReservedSeats the projectionReservedSeats to set
	 */
	public void setProjectionReservedSeats(final Set<ProjectionReservedSeats> p_projectionReservedSeats) {
		this.projectionReservedSeats = p_projectionReservedSeats;
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
	 * @param p_userFollowList the userFollowList to set
	 */
	public void setUserFollowList(final List<User> p_userFollowList) {
		this.userFollowList = p_userFollowList;
	}

	/**
	 * @param p_follow the follow to set
	 */
	public void setFollow(final List<User> p_follow) {
		this.follow = p_follow;
	}

}
