package hr.primefaces.model;

import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "user")
public class User implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Integer id;
	private String username;
	private String firstname;
	private String lastname;
	private String password;
	private String email;
	private Date date_of_birth;
	private Date updated;

	@OneToOne(targetEntity = Role.class, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
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

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getDate_of_birth() {
		return date_of_birth;
	}

	public void setDate_of_birth(Date date_of_birth) {
		this.date_of_birth = date_of_birth;
	}

	public Date getUpdated() {
		return updated;
	}

	public void setUpdated(Date updated) {
		this.updated = updated;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public Set<ProjectionReservedSeats> getProjectionReservedSeats() {
		return projectionReservedSeats;
	}

	public void setProjectionReservedSeats(Set<ProjectionReservedSeats> projectionReservedSeats) {
		this.projectionReservedSeats = projectionReservedSeats;
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

	public List<User> getUserFollowList() {
		return userFollowList;
	}

	public void setUserFollowList(List<User> userFollowList) {
		this.userFollowList = userFollowList;
	}

	public List<User> getFollow() {
		return follow;
	}

	public void setFollow(List<User> follow) {
		this.follow = follow;
	}

}
