package hr.primefaces.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "user_following")
public class UserFollowing implements Serializable {

	private static final long serialVersionUID = 1L;

	// TODO equals and hash

	@Id
	@GeneratedValue
	private Integer id;

	@Column(name = "place_of_birth", nullable = false)
	private Date created;

	@Column(name = "user_id", nullable = false)
	private Integer userId;

	@Column(name = "follow_id", nullable = false)
	private Integer followId;

	public UserFollowing() {
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
	 * @return the created
	 */
	public Date getCreated() {
		return created;
	}

	/**
	 * @return the userId
	 */
	public Integer getUserId() {
		return userId;
	}

	/**
	 * @return the followId
	 */
	public Integer getFollowId() {
		return followId;
	}

	/**
	 * @param p_id the id to set
	 */
	public void setId(final Integer p_id) {
		this.id = p_id;
	}

	/**
	 * @param p_created the created to set
	 */
	public void setCreated(final Date p_created) {
		this.created = p_created;
	}

	/**
	 * @param p_userId the userId to set
	 */
	public void setUserId(final Integer p_userId) {
		this.userId = p_userId;
	}

	/**
	 * @param p_followId the followId to set
	 */
	public void setFollowId(final Integer p_followId) {
		this.followId = p_followId;
	}

}
