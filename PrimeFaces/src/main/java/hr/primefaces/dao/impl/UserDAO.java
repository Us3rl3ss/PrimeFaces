package hr.primefaces.dao.impl;

import hr.primefaces.dao.IUserDAO;
import hr.primefaces.model.User;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

@Repository
public class UserDAO implements IUserDAO, Serializable {

	private static final long serialVersionUID = 1L;

	private SessionFactory sessionFactory;

	private final String GET_USER_BY_ID = "from User where id = :user_id";
	private final String GET_USER_BY_DISTINCT_USERNAME = "from User where username = :username";
	private final String GET_USER_BY_USERNAME = "from User where name like :username";
	private final String GET_USERS = "from User";
	private final String GET_USER_FOLLOW = "select ufl from User u join u.userFollowList ufl where user_id = :login_user_id and follow_id = :curr_user_Id";
	private final String GET_USER_FOLLOW_BY_USER = "select ufl from User u join u.userFollowList ufl where user_id = :login_user_id";
	private final String GET_USER_FOLLOW_BY_FOLLOWER = "select ufl from User u join u.userFollowList ufl where follow_id = :curr_user_id";

	@Override
	public void addUser(final User p_user) {
		getSessionFactory().getCurrentSession().save(p_user);
	}

	@Override
	public void deleteUser(final User p_user) {
		getSessionFactory().getCurrentSession().delete(p_user);
	}

	@Override
	public void updateUser(final User p_user) {
		getSessionFactory().getCurrentSession().update(p_user);
	}

	@Override
	public User getUserById(final int p_id) {

		final Query query = getSessionFactory().getCurrentSession().createQuery(GET_USER_BY_ID);
		query.setParameter("user_id", p_id);
		return (User) query.uniqueResult();
	}

	@Override
	public User getUserByDistinctUsername(final String p_username) {

		final Query query = getSessionFactory().getCurrentSession().createQuery(GET_USER_BY_DISTINCT_USERNAME);
		query.setParameter("username", p_username);
		return (User) query.uniqueResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<User> getUserByUsername(final String p_username) {

		final Query query = getSessionFactory().getCurrentSession().createQuery(GET_USER_BY_USERNAME);
		query.setParameter("username", "%" + p_username.toLowerCase() + "%");
		return query.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<User> getUsers() {

		final Query query = getSessionFactory().getCurrentSession().createQuery(GET_USERS);
		return query.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<User> getUserFollow(final User p_loginUser, final User p_currUser) {

		final Query query = getSessionFactory().getCurrentSession().createQuery(GET_USER_FOLLOW);
		query.setParameter("login_user_id", p_loginUser.getId());
		query.setParameter("curr_user_id", p_currUser.getId());
		return query.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<User> getUserFollowByUser(final User p_loginUser) {

		final Query query = getSessionFactory().getCurrentSession().createQuery(GET_USER_FOLLOW_BY_USER);
		query.setParameter("login_user_id", p_loginUser.getId());
		return query.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<User> getUserFollowByFollower(final User p_currUser) {

		final Query query = getSessionFactory().getCurrentSession().createQuery(GET_USER_FOLLOW_BY_FOLLOWER);
		query.setParameter("curr_user_id", p_currUser.getId());
		return query.list();
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
