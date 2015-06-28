package hr.primefaces.dao.impl;

import hr.primefaces.dao.IUserDAO;
import hr.primefaces.model.User;

import java.util.List;

import org.hibernate.SessionFactory;

public class UserDAO implements IUserDAO {

	private static final long serialVersionUID = 1L;

	private SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public void addUser(User user) {
		getSessionFactory().getCurrentSession().save(user);
	}

	@Override
	public void deleteUser(User user) {
		getSessionFactory().getCurrentSession().delete(user);
	}

	@Override
	public void updateUser(User user) {
		getSessionFactory().getCurrentSession().update(user);
	}

	@Override
	public User getUserById(int id) {
		List list = getSessionFactory().getCurrentSession()
				.createQuery("from User where id=?").setParameter(0, id).list();
		return (User) list.get(0);
	}

	@Override
	public User getUserByDistinctUsername(String username) {
		User tempUser = null;

		try {
			tempUser = (User) getSessionFactory().getCurrentSession()
					.createQuery("from User where username = :username")
					.setParameter("username", username)
					.list().get(0);
		} catch (IndexOutOfBoundsException iobex) {
			iobex.printStackTrace();
		}

		return tempUser;
	}
	
	@Override
	public List<User> getUserByUsername(String username) {
		List list = getSessionFactory()
				.getCurrentSession()
				.createQuery(
						"from User where username like lower('%"
								+ username.toLowerCase() + "%')").list();
		return list;
	}

	@Override
	public List<User> getUsers() {
		List list = getSessionFactory().getCurrentSession()
				.createQuery("from User").list();
		return list;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	@Override
	public List<User> getUserFollow(User loginUser, User currUser) {
		List list = getSessionFactory()
				.getCurrentSession()
				.createQuery(
						"select ufl from User u join u.userFollowList ufl where user_id = :loginUserId and follow_id = :currUserId")
						.setParameter("loginUserId", loginUser.getId())
						.setParameter("currUserId", currUser.getId())
						.list();
		return list;
	}
	
	@Override
	public List<User> getUserFollowByUser(User loginUser) {
		List list = getSessionFactory()
				.getCurrentSession()
				.createQuery(
						"select ufl from User u join u.userFollowList ufl where user_id = :loginUserId")
						.setParameter("loginUserId", loginUser.getId())
						.list();
		return list;
	}
	
	@Override
	public List<User> getUserFollowByFollower(User currUser) {
		List list = getSessionFactory()
				.getCurrentSession()
				.createQuery(
						"select ufl from User u join u.userFollowList ufl where follow_id = :currUserId")
						.setParameter("currUserId", currUser.getId())
						.list();
		return list;
	}

}
