package hr.primefaces.dao.impl;

import hr.primefaces.dao.IUserFollowingDAO;
import hr.primefaces.model.User;
import hr.primefaces.model.UserFollowing;

import java.io.Serializable;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

@Repository
public class UserFollowingDAO implements IUserFollowingDAO, Serializable {

	private static final long serialVersionUID = 1L;

	private SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public void addUserFollowing(UserFollowing userFollowing) {
		getSessionFactory().getCurrentSession().save(userFollowing);
	}

	@Override
	public void deleteUserFollowing(UserFollowing userFollowing) {
		getSessionFactory().getCurrentSession().delete(userFollowing);
	}

	@Override
	public void updateUserFollowing(UserFollowing userFollowing) {
		getSessionFactory().getCurrentSession().update(userFollowing);
	}

	@Override
	public UserFollowing getUserFriends(User user, User user2) {

		return (UserFollowing) getSessionFactory().getCurrentSession().createQuery("from UserFollowing where user_id = :user_id and follow_id = :follow_id")
				.setParameter("user_id", user.getId()).setParameter("follow_id", user2.getId()).uniqueResult();
	}

}
