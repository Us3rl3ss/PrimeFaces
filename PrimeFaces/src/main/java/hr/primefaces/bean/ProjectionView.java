package hr.primefaces.bean;

import hr.primefaces.model.Cinema;
import hr.primefaces.model.Genre;
import hr.primefaces.model.Movie;
import hr.primefaces.model.Projection;
import hr.primefaces.model.Theater;
import hr.primefaces.service.IMovieService;
import hr.primefaces.service.IProjectionService;
import hr.primefaces.service.ITheaterService;

import java.io.Serializable;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;

import org.primefaces.context.RequestContext;

@ManagedBean(name = "projectionMB")
@ViewScoped
public class ProjectionView implements Serializable {

	private static final long serialVersionUID = 1L;

	private static final String PROJECTION_COMPONENT = "projection";

	@ManagedProperty(value = "#{ProjectionService}")
	private IProjectionService projectionService;

	@ManagedProperty(value = "#{TheaterService}")
	private ITheaterService theaterService;

	@ManagedProperty(value = "#{MovieService}")
	private IMovieService movieService;

	private Projection projection;
	private Theater theater;
	private Cinema cinema;
	private Movie movie;
	private List<Projection> projectionList;
	private List<Theater> theaterList;
	private List<Cinema> cinemaList;
	private List<Movie> movieList;
	private Projection selectedProjection;
	private List<Projection> distinctMovieProjectionList;
	private boolean render;
	private Date datumProjekcije;

	@PostConstruct
	public void init() {

		setProjection(new Projection());
		setTheater(new Theater());
		setCinema(new Cinema());
		setMovie(new Movie());
		setDatumProjekcije(new Date());
		setTheaterList(getTheaterService().getTheaters());
	}

	/**
	 * search
	 */
	public void search() {

		setProjectionList(getProjectionService().getProjectionsForReservation(getTheater(), getDatumProjekcije()));

		for (int i = 0; i < getProjectionList().size(); i++) {

			final Projection tempProjection = getProjectionList().get(i);
			final Movie tempMovie = tempProjection.getMovie();
			final List<Genre> genreList = getMovieService().getAllMovieGenres(tempMovie);
			tempProjection.getMovie().setGenreList(genreList);
			tempProjection.getMovie().setListOfGenresText(getListOfGenresText(genreList));
		}

		RequestContext.getCurrentInstance().update(PROJECTION_COMPONENT);
	}

	/**
	 * doViewReserveSeats
	 */
	public String doViewReserveSeats() {

		final Flash flash = FacesContext.getCurrentInstance().getExternalContext().getFlash();
		flash.setKeepMessages(true);

		flash.put("projection", projection);

		return "reserveSeats?faces-redirect=true";
	}

	/**
	 * doViewDistinctMovieProjections
	 * @param p_currentProjection
	 * @return
	 */
	public List<Projection> doViewDistinctMovieProjections(final Projection p_currentProjection) {

		final List<Projection> tempProjectionList = getProjectionService().getDistinctMovieProjections(p_currentProjection);

		for (int i = 0; i < tempProjectionList.size(); i++) {

			final Projection tempProjection = tempProjectionList.get(i);

			final int numberOfSeats = tempProjection.getCinema().getNumberOfSeats();
			final int numberOfFreeSeats = getProjectionService().getProjectionReservedSeatsByProjection(tempProjection).size();

			final String numberOfFreeSeatsText = numberOfFreeSeats + "/" + numberOfSeats;

			tempProjectionList.get(i).setNumberOfFreeSeatsText(numberOfFreeSeatsText);
		}

		return tempProjectionList;
	}

	/**
	 * getListOfGenresText
	 * @param p_genreList
	 * @return
	 */
	public String getListOfGenresText(final List<Genre> p_genreList) {

		String result = "";

		if (p_genreList.size() > 0) {

			final Iterator<Genre> iter = p_genreList.iterator();

			while (iter.hasNext()) {

				final Genre g = iter.next();

				result += g.getName();
				result += ", ";
			}

			result = result.substring(0, result.length() - 2);
		}

		if (result.length() == 0) {

			result = "-";
		}

		return result;
	}

	/**
	 * ################# GETTERS AND SETTERS #################
	 */

	/**
	 * @return the projectionService
	 */
	public IProjectionService getProjectionService() {
		return projectionService;
	}

	/**
	 * @return the theaterService
	 */
	public ITheaterService getTheaterService() {
		return theaterService;
	}

	/**
	 * @return the movieService
	 */
	public IMovieService getMovieService() {
		return movieService;
	}

	/**
	 * @return the projection
	 */
	public Projection getProjection() {
		return projection;
	}

	/**
	 * @return the theater
	 */
	public Theater getTheater() {
		return theater;
	}

	/**
	 * @return the cinema
	 */
	public Cinema getCinema() {
		return cinema;
	}

	/**
	 * @return the movie
	 */
	public Movie getMovie() {
		return movie;
	}

	/**
	 * @return the projectionList
	 */
	public List<Projection> getProjectionList() {
		return projectionList;
	}

	/**
	 * @return the theaterList
	 */
	public List<Theater> getTheaterList() {
		return theaterList;
	}

	/**
	 * @return the cinemaList
	 */
	public List<Cinema> getCinemaList() {
		return cinemaList;
	}

	/**
	 * @return the movieList
	 */
	public List<Movie> getMovieList() {
		return movieList;
	}

	/**
	 * @return the selectedProjection
	 */
	public Projection getSelectedProjection() {
		return selectedProjection;
	}

	/**
	 * @return the distinctMovieProjectionList
	 */
	public List<Projection> getDistinctMovieProjectionList() {
		return distinctMovieProjectionList;
	}

	/**
	 * @return the render
	 */
	public boolean isRender() {
		return render;
	}

	/**
	 * @return the datumProjekcije
	 */
	public Date getDatumProjekcije() {
		return datumProjekcije;
	}

	/**
	 * @param p_projectionService the projectionService to set
	 */
	public void setProjectionService(final IProjectionService p_projectionService) {
		this.projectionService = p_projectionService;
	}

	/**
	 * @param p_theaterService the theaterService to set
	 */
	public void setTheaterService(final ITheaterService p_theaterService) {
		this.theaterService = p_theaterService;
	}

	/**
	 * @param p_movieService the movieService to set
	 */
	public void setMovieService(final IMovieService p_movieService) {
		this.movieService = p_movieService;
	}

	/**
	 * @param p_projection the projection to set
	 */
	public void setProjection(final Projection p_projection) {
		this.projection = p_projection;
	}

	/**
	 * @param p_theater the theater to set
	 */
	public void setTheater(final Theater p_theater) {
		this.theater = p_theater;
	}

	/**
	 * @param p_cinema the cinema to set
	 */
	public void setCinema(final Cinema p_cinema) {
		this.cinema = p_cinema;
	}

	/**
	 * @param p_movie the movie to set
	 */
	public void setMovie(final Movie p_movie) {
		this.movie = p_movie;
	}

	/**
	 * @param p_projectionList the projectionList to set
	 */
	public void setProjectionList(final List<Projection> p_projectionList) {
		this.projectionList = p_projectionList;
	}

	/**
	 * @param p_theaterList the theaterList to set
	 */
	public void setTheaterList(final List<Theater> p_theaterList) {
		this.theaterList = p_theaterList;
	}

	/**
	 * @param p_cinemaList the cinemaList to set
	 */
	public void setCinemaList(final List<Cinema> p_cinemaList) {
		this.cinemaList = p_cinemaList;
	}

	/**
	 * @param p_movieList the movieList to set
	 */
	public void setMovieList(final List<Movie> p_movieList) {
		this.movieList = p_movieList;
	}

	/**
	 * @param p_selectedProjection the selectedProjection to set
	 */
	public void setSelectedProjection(final Projection p_selectedProjection) {
		this.selectedProjection = p_selectedProjection;
	}

	/**
	 * @param p_distinctMovieProjectionList the distinctMovieProjectionList to set
	 */
	public void setDistinctMovieProjectionList(final List<Projection> p_distinctMovieProjectionList) {
		this.distinctMovieProjectionList = p_distinctMovieProjectionList;
	}

	/**
	 * @param p_render the render to set
	 */
	public void setRender(final boolean p_render) {
		this.render = p_render;
	}

	/**
	 * @param p_datumProjekcije the datumProjekcije to set
	 */
	public void setDatumProjekcije(final Date p_datumProjekcije) {
		this.datumProjekcije = p_datumProjekcije;
	}

}