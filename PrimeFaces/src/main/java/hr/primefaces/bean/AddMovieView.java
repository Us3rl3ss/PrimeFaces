package hr.primefaces.bean;

import hr.primefaces.model.Genre;
import hr.primefaces.model.Movie;
import hr.primefaces.service.IMovieService;
import hr.primefaces.util.MessageUtil;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.apache.poi.util.IOUtils;
import org.hibernate.HibernateException;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

@ManagedBean(name = "addMovieMB")
@ViewScoped
public class AddMovieView implements Serializable {

	private static final long serialVersionUID = 1L;

	@ManagedProperty(value = "#{MovieService}")
	private IMovieService movieService;

	private Movie movie;
	private List<Movie> movieList;
	private List<Genre> genreList;
	private String uploadedFileNames;

	@PostConstruct
	public void init() {

		setMovie(new Movie());
		setUploadedFileNames("");
	}

	/**
	 * save
	 */
	public void save() {

		try {
			getMovieService().addMovie(getMovie());
			setMovie(new Movie());
			MessageUtil.info("Podaci uspješno spremljeni!");
		}
		catch (HibernateException hex) {
			hex.printStackTrace();
			MessageUtil.error("Došlo je do hibernate greške!");
		}
		catch (Exception ex) {
			ex.printStackTrace();
			MessageUtil.error("Došlo je do greške!");
		}
	}

	/**
	 * handleFileUpload
	 */
	public void handleFileUpload(final FileUploadEvent p_event) {

		UploadedFile file;
		byte[] byteData = null;

		file = p_event.getFile();
		try {
			byteData = IOUtils.toByteArray(file.getInputstream());
		}
		catch (Exception e) {
			e.printStackTrace();
		}

		if (byteData != null) {

			getMovie().setImage(byteData);
			setUploadedFileNames(getUploadedFileNames() + file.getFileName());
		}
	}

	/**
	 * ################# GETTERS AND SETTERS #################
	 */

	/**
	 * @return the movieService
	 */
	public IMovieService getMovieService() {
		return movieService;
	}

	/**
	 * @return the movie
	 */
	public Movie getMovie() {
		return movie;
	}

	/**
	 * @return the movieList
	 */
	public List<Movie> getMovieList() {
		return movieList;
	}

	/**
	 * @return the genreList
	 */
	public List<Genre> getGenreList() {
		return genreList;
	}

	/**
	 * @return the uploadedFileNames
	 */
	public String getUploadedFileNames() {
		return uploadedFileNames;
	}

	/**
	 * @param p_movieService the movieService to set
	 */
	public void setMovieService(final IMovieService p_movieService) {
		this.movieService = p_movieService;
	}

	/**
	 * @param p_movie the movie to set
	 */
	public void setMovie(final Movie p_movie) {
		this.movie = p_movie;
	}

	/**
	 * @param p_movieList the movieList to set
	 */
	public void setMovieList(final List<Movie> p_movieList) {
		this.movieList = p_movieList;
	}

	/**
	 * @param p_genreList the genreList to set
	 */
	public void setGenreList(final List<Genre> p_genreList) {
		this.genreList = p_genreList;
	}

	/**
	 * @param p_uploadedFileNames the uploadedFileNames to set
	 */
	public void setUploadedFileNames(final String p_uploadedFileNames) {
		this.uploadedFileNames = p_uploadedFileNames;
	}

}