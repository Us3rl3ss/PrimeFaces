package hr.primefaces.bean;


/*
 * Imports
 */

import hr.primefaces.model.Actor;
import hr.primefaces.model.Genre;
import hr.primefaces.model.Movie;
import hr.primefaces.service.IActorService;
import hr.primefaces.service.IGenreService;
import hr.primefaces.service.IMovieService;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.apache.poi.util.IOUtils;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.UploadedFile;

@ManagedBean(name = "addMovieMB")
@ViewScoped
public class AddMovieManagedBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	
	/*
	 * Managed Properties
	 */

	@ManagedProperty(value = "#{MovieService}")
	IMovieService movieService;
	
	@ManagedProperty(value = "#{ActorService}")
	IActorService actorService;
	
	@ManagedProperty(value = "#{GenreService}")
	IGenreService genreService;

	@ManagedProperty("#{dropDownMB}")
	private DropdownMenuManagedBean dropDownMB;
	
	
	/*
	 * Private Attributes
	 */

	private Movie movie = new Movie();

	private List<Movie> movieList;
	private List<Genre> genreList;
	
	private String uploadedFileNames = "";

	@PostConstruct
	public void init() {
	}

	public void spremi() {

		try {
			movieService.addMovie(movie);
			dropDownMB.reloadMovie();
		} catch (Exception ex) { // TODO ERROR HANDLING
			ex.printStackTrace();
		}
	}
	
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
	
	public List<Actor> completeActor(String input) {
		List<Actor> list = (List<Actor>) actorService.getActorByName(input);
		return list;
	}
	
	public List<Genre> completeGenre(String input) {
		List<Genre> list = (List<Genre>) genreService.getGenreByName(input);
		return list;
	}
	
	public void handleSelect() {
		System.out.print("test");
	}

	public void onItemSelect(SelectEvent event) {
		FacesContext.getCurrentInstance()
				.addMessage(
						null,
						new FacesMessage("Item Selected", event.getObject()
								.toString()));
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

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public List<Movie> getMovieList() {
		return movieList;
	}

	public void setMovieList(List<Movie> movieList) {
		this.movieList = movieList;
	}

	public DropdownMenuManagedBean getDropDownMB() {
		return dropDownMB;
	}

	public void setDropDownMB(DropdownMenuManagedBean dropDownMB) {
		this.dropDownMB = dropDownMB;
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