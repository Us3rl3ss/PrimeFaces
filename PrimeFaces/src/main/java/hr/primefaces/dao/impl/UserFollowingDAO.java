package hr.primefaces.dao.impl;

import hr.primefaces.dao.IUserFollowingDAO;
import hr.primefaces.model.UserFollowing;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

@Repository
public class UserFollowingDAO implements IUserFollowingDAO {

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

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
