package hr.primefaces.dao.impl;

import hr.primefaces.dao.IUserFollowingDAO;
import hr.primefaces.model.User;
import hr.primefaces.model.UserFollowing;

import java.io.Serializable;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

@Repository
public class UserFollowingDAO implements IUserFollowingDAO, Serializable {

	private static final long serialVersionUID = 1L;

	private SessionFactory sessionFactory;

	private final String GET_USER_FRIENDS = "from UserFollowing where user_id = :user_id and follow_id = :follow_id";

	@Override
	public void addUserFollowing(final UserFollowing p_userFollowing) {
		getSessionFactory().getCurrentSession().save(p_userFollowing);
	}

	@Override
	public void deleteUserFollowing(final UserFollowing p_userFollowing) {
		getSessionFactory().getCurrentSession().delete(p_userFollowing);
	}

	@Override
	public void updateUserFollowing(final UserFollowing p_userFollowing) {
		getSessionFactory().getCurrentSession().update(p_userFollowing);
	}

	@Override
	public UserFollowing getUserFriends(final User p_user, final User p_user2) {

		final Query query = getSessionFactory().getCurrentSession().createQuery(GET_USER_FRIENDS);
		query.setParameter("user_id", p_user.getId());
		query.setParameter("follow_id", p_user2.getId());
		return (UserFollowing) query.uniqueResult();
	}

	/**
	 * ################# GETTERS AND SETTERS #################
	 */

	/**
	 * @return the sessionFactory
	 */
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	/**
	 * @param p_sessionFactory the sessionFactory to set
	 */
	public void setSessionFactory(final SessionFactory p_sessionFactory) {
		this.sessionFactory = p_sessionFactory;
	}

}
