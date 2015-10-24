package hr.primefaces.dao;

import hr.primefaces.model.User;
import hr.primefaces.model.UserFollowing;

public interface IUserFollowingDAO {

	void addUserFollowing(UserFollowing p_userFollowing);

	void updateUserFollowing(UserFollowing p_userFollowing);

	void deleteUserFollowing(UserFollowing p_userFollowing);

	UserFollowing getUserFriends(User p_user, User p_user2);

}
