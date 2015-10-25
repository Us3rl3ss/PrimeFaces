package hr.primefaces.dao;

import hr.primefaces.model.Movie;
import hr.primefaces.model.User;
import hr.primefaces.model.UserMovieWishlist;

import java.util.List;

public interface IUserMovieWishlistDAO {

	void addUserMovieWishlist(UserMovieWishlist p_userMovieWishlist);

	void updateUserMovieWishlist(UserMovieWishlist p_userMovieWishlist);

	void deleteUserMovieWishlist(UserMovieWishlist p_userMovieWishlist);

	UserMovieWishlist getMovieInUserWishlist(User p_user, Movie p_movie);

	List<UserMovieWishlist> getUserMovieWishlistByUser(User p_user);

}
