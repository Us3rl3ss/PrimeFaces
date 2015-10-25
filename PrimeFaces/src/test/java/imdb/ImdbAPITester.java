package imdb;

import hr.primefaces.imdb.ImdbAPI;
import hr.primefaces.imdb.ImdbJsonModel;
import hr.primefaces.model.Movie;
import hr.primefaces.util.Check;
import hr.primefaces.util.ObjectRemapper;

import java.util.ArrayList;
import java.util.List;


public final class ImdbAPITester {

	private ImdbAPITester(){}

	/**
	 * ImdbAPI test
	 * @param p_args
	 */
	public static void main(final String[] p_args) {

		ImdbJsonModel imdb = ImdbAPI.getMovieFromImdbById("tt1300854");
		Movie movie = ObjectRemapper.imdbToMovieObj(imdb);
		System.out.println();
	}

}
