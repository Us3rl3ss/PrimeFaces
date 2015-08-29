package hr.primefaces.bean;

import hr.primefaces.model.Actor;
import hr.primefaces.model.Genre;
import hr.primefaces.model.Movie;
import hr.primefaces.service.IActorService;
import hr.primefaces.service.IGenreService;
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
	IMovieService movieService;
	
	@ManagedProperty(value = "#{ActorService}")
	IActorService actorService;
	
	@ManagedProperty(value = "#{GenreService}")
	IGenreService genreService;

	private Movie movie = new Movie();
	private List<Movie> movieList;
	private List<Genre> genreList;
	private String uploadedFileNames = "";

	@PostConstruct
	public void init() {
	}

	/**
	 * spremi
	 */
	public void spremi() {

		try {
			movieService.addMovie(movie);
			movie = new Movie();
			MessageUtil.info("Podaci uspješno spremljeni!");
		} catch (HibernateException hex) {
			hex.printStackTrace();
			MessageUtil.error("Došlo je do hibernate greške!");
		} catch (Exception ex) {
			ex.printStackTrace();
			MessageUtil.error("Došlo je do greške!");
		}
	}
	
	/**
	 * handleFileUpload
	 */
	public void handleFileUpload(FileUploadEvent event) {
		System.out.println(event);
		
		UploadedFile file;
		byte[] byteData = null;
		
		file = event.getFile();
		try {
			byteData = IOUtils.toByteArray(file.getInputstream());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if (byteData != null) {
			
			movie.setImage(byteData);
			setUploadedFileNames(getUploadedFileNames() + file.getFileName());
		}
	}
	
	/**
	 * completeActor
	 */
	public List<Actor> completeActor(String input) {
		return actorService.getActorByName(input);
	}
	
	/**
	 * completeGenre
	 */
	public List<Genre> completeGenre(String input) {
		return genreService.getGenreByName(input);
	}

	public IMovieService getMovieService() {
		return movieService;
	}

	public void setMovieService(IMovieService movieService) {
		this.movieService = movieService;
	}

	public Movie getMovie() {
		return movie;
	}

	public void setMovie(Movie movie) {
		this.movie = movie;
	}

	public List<Movie> getMovieList() {
		return movieList;
	}

	public void setMovieList(List<Movie> movieList) {
		this.movieList = movieList;
	}

	public List<Genre> getGenreList() {
		return genreList;
	}

	public void setGenreList(List<Genre> genreList) {
		this.genreList = genreList;
	}

	public String getUploadedFileNames() {
		return uploadedFileNames;
	}

	public void setUploadedFileNames(String uploadedFileNames) {
		this.uploadedFileNames = uploadedFileNames;
	}

	public IActorService getActorService() {
		return actorService;
	}

	public void setActorService(IActorService actorService) {
		this.actorService = actorService;
	}

	public IGenreService getGenreService() {
		return genreService;
	}

	public void setGenreService(IGenreService genreService) {
		this.genreService = genreService;
	}
	
	
	
}