package hr.primefaces.bean;

import hr.primefaces.model.Cinema;
import hr.primefaces.model.Movie;
import hr.primefaces.model.Projection;
import hr.primefaces.model.Theater;
import hr.primefaces.service.IMovieService;
import hr.primefaces.service.IProjectionService;
import hr.primefaces.service.ITheaterService;
import hr.primefaces.util.DateConverter;
import hr.primefaces.util.MessageUtil;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.hibernate.HibernateException;
import org.primefaces.context.RequestContext;
import org.primefaces.event.ScheduleEntryMoveEvent;
import org.primefaces.event.ScheduleEntryResizeEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.DefaultScheduleEvent;
import org.primefaces.model.DefaultScheduleModel;
import org.primefaces.model.ScheduleEvent;
import org.primefaces.model.ScheduleModel;

@ManagedBean(name = "addProjectionMB")
@ViewScoped
public class AddProjectionView implements Serializable {

	private static final long serialVersionUID = 1L;

	private static final String DROPDOWN_COMPONENT = "addProjectionForm:dropdownPanel";
	private static final String SCHEDULE_COMPONENT = "addProjectionForm:schedule";

	@ManagedProperty(value = "#{ProjectionService}")
	private IProjectionService projectionService;

	@ManagedProperty(value = "#{TheaterService}")
	private ITheaterService theaterService;

	@ManagedProperty(value = "#{MovieService}")
	private IMovieService movieService;

	private Projection projection;
	private List<Theater> theaterList;
	private List<Cinema> cinemaList;
	private List<Movie> movieList;
	private ScheduleModel eventModel;
	private ScheduleEvent event;
	private Date currStartDate;
	private Date currEndDate;
	private boolean forUpdate;

	@PostConstruct
	public void init() {

		setProjection(new Projection());
		setEventModel(new DefaultScheduleModel());
		setEvent(new DefaultScheduleEvent());

		setMovieList(getMovieService().getMovies());
		setTheaterList(getTheaterService().getTheaters());

		if (getTheaterList().size() > 0) {

			getProjection().setTheater(getTheaterList().get(0));
		}
		else {

			getProjection().setTheater(new Theater());
		}

		prepareCinemaList();
	}

	/**
	 * save
	 */
	public void save() {

		try {
			getProjectionService().addProjection(getProjection());
			MessageUtil.info("Podaci uspješno spremljeni!");
			prepareProjectionList();
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
	 * edit
	 */
	public void edit() {

		try {
			getProjectionService().updateProjection(getProjection());
			MessageUtil.info("Podaci uspješno ažurirani!");
			prepareProjectionList();
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
	 * delete
	 */
	public void delete() {

		try {
			getProjectionService().deleteProjection(getProjection());
			MessageUtil.info("Podaci uspješno obrisani!");
			prepareProjectionList();
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
	 * prepareCinemaList
	 */
	public void prepareCinemaList() {

		setCinemaList(getTheaterService().getCinemaByTheater(getProjection().getTheater()));

		if (getCinemaList().size() > 0) {

			if (getCinemaList().size() > 0) {

				getProjection().setCinema(getCinemaList().get(0));
			}
			else {

				getProjection().setCinema(new Cinema());
			}

			RequestContext.getCurrentInstance().update(DROPDOWN_COMPONENT);
			prepareProjectionList();
		}
	}

	/**
	 * prepareProjectionList
	 */
	public void prepareProjectionList() {

		setEventModel(new DefaultScheduleModel());
		for (Projection p : getProjectionService().getProjectionsByCinema(getProjection().getCinema())) {
			getEventModel().addEvent(new DefaultScheduleEvent(p.getMovie().getName(), p.getStartTime(), p.getEndTime()));
		}

		RequestContext.getCurrentInstance().update(SCHEDULE_COMPONENT);
	}

	/**
	 * prepareProjectionListInDateRange
	 */
	public void prepareProjectionListInDateRange() {

		setEventModel(new DefaultScheduleModel());
		for (Projection p : getProjectionService().getProjectionByCinemaBetweenStartEnd(
				getProjection().getCinema(), getCurrStartDate(), getCurrEndDate())) {
			getEventModel().addEvent(new DefaultScheduleEvent(p.getMovie().getName(), p.getStartTime(), p.getEndTime()));
		}

		RequestContext.getCurrentInstance().update(SCHEDULE_COMPONENT);
	}

	/**
	 * onEventSelect
	 * @param p_selectEvent
	 */
	public void onEventSelect(final SelectEvent p_selectEvent) {

		setForUpdate(true);
		getProjection().setStartTime(((DefaultScheduleEvent) p_selectEvent.getObject()).getStartDate());
		getProjection().setEndTime(((DefaultScheduleEvent) p_selectEvent.getObject()).getEndDate());
		setProjection(getProjectionService().getProjectionByCinemaStartEnd(getProjection().getCinema(),
				getProjection().getStartTime(), getProjection().getEndTime()));
	}

	/**
	 * onDateSelect
	 * @param p_selectEvent
	 */
	public void onDateSelect(final SelectEvent p_selectEvent) {

		setForUpdate(false);
		getProjection().setStartTime((Date) p_selectEvent.getObject());
		getProjection().setEndTime((Date) p_selectEvent.getObject());
		setEvent(new DefaultScheduleEvent("", (Date) p_selectEvent.getObject(), (Date) p_selectEvent.getObject()));
	}

	/**
	 * onEventMove
	 * @param p_selectEvent
	 */
	public void onEventMove(final ScheduleEntryMoveEvent p_selectEvent) {

		final Date initialStartTime = DateConverter.setDateDifference(p_selectEvent.getScheduleEvent().getStartDate(),
				p_selectEvent.getDayDelta(), p_selectEvent.getMinuteDelta());
		final Date initialEndTime = DateConverter.setDateDifference(p_selectEvent.getScheduleEvent().getEndDate(),
				p_selectEvent.getDayDelta(), p_selectEvent.getMinuteDelta());
		setProjection(getProjectionService().getProjectionByCinemaStartEnd(getProjection().getCinema(), initialStartTime, initialEndTime));
		getProjection().setStartTime(p_selectEvent.getScheduleEvent().getStartDate());
		getProjection().setEndTime(p_selectEvent.getScheduleEvent().getEndDate());

		edit();
	}

	/**
	 * onEventResize
	 * @param p_selectEvent
	 */
	public void onEventResize(final ScheduleEntryResizeEvent p_selectEvent) {

		final Date initialEndTime = DateConverter.setDateDifference(p_selectEvent.getScheduleEvent().getEndDate(),
				p_selectEvent.getDayDelta(), p_selectEvent.getMinuteDelta());
		setProjection(getProjectionService().getProjectionByCinemaStartEnd(getProjection().getCinema(),
				p_selectEvent.getScheduleEvent().getStartDate(), initialEndTime));
		getProjection().setStartTime(p_selectEvent.getScheduleEvent().getStartDate());
		getProjection().setEndTime(p_selectEvent.getScheduleEvent().getEndDate());

		edit();
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
	 * @return the eventModel
	 */
	public ScheduleModel getEventModel() {
		return eventModel;
	}

	/**
	 * @return the event
	 */
	public ScheduleEvent getEvent() {
		return event;
	}

	/**
	 * @return the currStartDate
	 */
	public Date getCurrStartDate() {
		return currStartDate;
	}

	/**
	 * @return the currEndDate
	 */
	public Date getCurrEndDate() {
		return currEndDate;
	}

	/**
	 * @return the forUpdate
	 */
	public boolean isForUpdate() {
		return forUpdate;
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
	 * @param p_eventModel the eventModel to set
	 */
	public void setEventModel(final ScheduleModel p_eventModel) {
		this.eventModel = p_eventModel;
	}

	/**
	 * @param p_event the event to set
	 */
	public void setEvent(final ScheduleEvent p_event) {
		this.event = p_event;
	}

	/**
	 * @param p_currStartDate the currStartDate to set
	 */
	public void setCurrStartDate(final Date p_currStartDate) {
		this.currStartDate = p_currStartDate;
	}

	/**
	 * @param p_currEndDate the currEndDate to set
	 */
	public void setCurrEndDate(final Date p_currEndDate) {
		this.currEndDate = p_currEndDate;
	}

	/**
	 * @param p_forUpdate the forUpdate to set
	 */
	public void setForUpdate(final boolean p_forUpdate) {
		this.forUpdate = p_forUpdate;
	}

}