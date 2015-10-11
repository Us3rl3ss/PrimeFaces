package hr.primefaces.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "user_following")
public class UserFollowing implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Integer id;
	private Date created;
	private Integer user_id;
	private Integer follow_id;

	// @ManyToMany
	// @JoinColumn(name = "user_id")
	// Set<User> user_follower;
	//
	// @ManyToMany
	// @JoinColumn(name = "user_id")
	// Set<User> user_followed;

	public UserFollowing() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Integer getUser_id() {
		return user_id;
	}

	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}

	public Integer getFollow_id() {
		return follow_id;
	}

	public void setFollow_id(Integer follow_id) {
		this.follow_id = follow_id;
	}

	// public Set<User> getUser_follower() {
	// return user_follower;
	// }
	//
	// public void setUser_follower(Set<User> user_follower) {
	// this.user_follower = user_follower;
	// }
	//
	// public Set<User> getUser_followed() {
	// return user_followed;
	// }
	//
	// public void setUser_followed(Set<User> user_followed) {
	// this.user_followed = user_followed;
	// }

}
