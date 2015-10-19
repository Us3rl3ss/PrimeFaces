package hr.primefaces.bean;

import hr.primefaces.model.Cinema;
import hr.primefaces.model.Movie;
import hr.primefaces.model.Projection;
import hr.primefaces.model.Theater;
import hr.primefaces.service.IMovieService;
import hr.primefaces.service.IProjectionService;
import hr.primefaces.service.ITheaterService;
import hr.primefaces.util.MessageUtil;

import java.io.Serializable;
import java.util.Calendar;
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

	@ManagedProperty(value = "#{ProjectionService}")
	IProjectionService projectionService;

	@ManagedProperty(value = "#{TheaterService}")
	ITheaterService theaterService;

	@ManagedProperty(value = "#{MovieService}")
	IMovieService movieService;

	private Projection projection = new Projection();

	private List<Theater> theaterList;
	private List<Cinema> cinemaList;
	private List<Movie> movieList;

	private ScheduleModel eventModel = new DefaultScheduleModel();
	private ScheduleEvent event = new DefaultScheduleEvent();

	private Date currStartDate;
	private Date currEndDate;

	private boolean forUpdate;

	@PostConstruct
	public void init() {
		movieList = movieService.getMovies();
		theaterList = theaterService.getTheaters();
		projection.setTheater(theaterList.get(0));
		postaviCinemaList();

		// lazy fetch - projectionService - dohvat čiji je početak između dvaju
		// datuma
		// eventModel = new LazyScheduleModel() {
		//
		// @Override
		// public void loadEvents(Date start, Date end) {
		//
		// currStartDate = start;
		// currEndDate = end;
		// postaviProjectionListInDateRange();
		// }
		// };
	}

	/**
	 * spremi
	 */
	public void spremi() {

		try {
			projectionService.addProjection(projection);
			MessageUtil.info("Podaci uspješno spremljeni!");
			postaviProjectionList();
		} catch (HibernateException hex) {
			hex.printStackTrace();
			MessageUtil.error("Došlo je do hibernate greške!");
		} catch (Exception ex) {
			ex.printStackTrace();
			MessageUtil.error("Došlo je do greške!");
		}
	}

	/**
	 * uredi
	 */
	public void uredi() {

		try {
			projectionService.updateProjection(projection);
			MessageUtil.info("Podaci uspješno ažurirani!");
			postaviProjectionList();
		} catch (HibernateException hex) {
			hex.printStackTrace();
			MessageUtil.error("Došlo je do hibernate greške!");
		} catch (Exception ex) {
			ex.printStackTrace();
			MessageUtil.error("Došlo je do greške!");
		}
	}

	/**
	 * obrisi
	 */
	public void obrisi() {

		try {
			projectionService.deleteProjection(projection);
			MessageUtil.info("Podaci uspješno obrisani!");
			postaviProjectionList();
		} catch (HibernateException hex) {
			hex.printStackTrace();
			MessageUtil.error("Došlo je do hibernate greške!");
		} catch (Exception ex) {
			ex.printStackTrace();
			MessageUtil.error("Došlo je do greške!");
		}
	}

	/**
	 * postaviCinemaList
	 */
	public void postaviCinemaList() {

		cinemaList = theaterService.getCinemaByTheater(projection.getTheater());

		if (cinemaList.size() > 0) {

			projection.setCinema(cinemaList.get(0));
			RequestContext.getCurrentInstance().update("addProjectionForm:dropdownPanel");
			postaviProjectionList();
		}
	}

	/**
	 * postaviProjectionList
	 */
	public void postaviProjectionList() {

		eventModel = new DefaultScheduleModel();
		for (Projection p : projectionService.getProjectionsByCinema(projection.getCinema())) {
			eventModel.addEvent(new DefaultScheduleEvent(p.getMovie().getName(), p.getStart_time(), p.getEnd_time()));
		}
		RequestContext.getCurrentInstance().update("addProjectionForm:schedule");
	}

	/**
	 * postaviProjectionListInDateRange
	 */
	public void postaviProjectionListInDateRange() {

		eventModel = new DefaultScheduleModel();
		for (Projection p : projectionService.getProjectionByCinemaBetweenStartEnd(projection.getCinema(), currStartDate, currEndDate)) {
			eventModel.addEvent(new DefaultScheduleEvent(p.getMovie().getName(), p.getStart_time(), p.getEnd_time()));
		}
		RequestContext.getCurrentInstance().update("addProjectionForm:schedule");
	}

	/**
	 * onEventSelect
	 */
	public void onEventSelect(SelectEvent selectEvent) {
		forUpdate = true;
		projection.setStart_time(((DefaultScheduleEvent) selectEvent.getObject()).getStartDate());
		projection.setEnd_time(((DefaultScheduleEvent) selectEvent.getObject()).getEndDate());
		projection = projectionService.getProjectionByCinemaStartEnd(projection.getCinema(), projection.getStart_time(), projection.getEnd_time());
	}

	/**
	 * onDateSelect
	 */
	public void onDateSelect(SelectEvent selectEvent) {
		forUpdate = false;
		projection.setStart_time((Date) selectEvent.getObject());
		projection.setEnd_time((Date) selectEvent.getObject());
		event = new DefaultScheduleEvent("", (Date) selectEvent.getObject(), (Date) selectEvent.getObject());
	}

	/**
	 * onEventMove
	 */
	public void onEventMove(ScheduleEntryMoveEvent selectEvent) {
		Date initialStartTime = setDateDifference(selectEvent.getScheduleEvent().getStartDate(), selectEvent.getDayDelta(), selectEvent.getMinuteDelta());
		Date initialEndTime = setDateDifference(selectEvent.getScheduleEvent().getEndDate(), selectEvent.getDayDelta(), selectEvent.getMinuteDelta());
		projection = projectionService.getProjectionByCinemaStartEnd(projection.getCinema(), initialStartTime, initialEndTime);
		projection.setStart_time(selectEvent.getScheduleEvent().getStartDate());
		projection.setEnd_time(selectEvent.getScheduleEvent().getEndDate());
		uredi();
	}

	/**
	 * onEventResize
	 */
	public void onEventResize(ScheduleEntryResizeEvent selectEvent) {
		Date initialEndTime = setDateDifference(selectEvent.getScheduleEvent().getEndDate(), selectEvent.getDayDelta(), selectEvent.getMinuteDelta());
		projection = projectionService.getProjectionByCinemaStartEnd(projection.getCinema(), selectEvent.getScheduleEvent().getStartDate(), initialEndTime);
		projection.setStart_time(selectEvent.getScheduleEvent().getStartDate());
		projection.setEnd_time(selectEvent.getScheduleEvent().getEndDate());
		uredi();
	}

	/**
	 * setDateDifference
	 */
	public Date setDateDifference(Date initialDate, int dayDiff, int minDiff) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(initialDate);
		cal.add(Calendar.DAY_OF_MONTH, dayDiff * (-1));
		cal.add(Calendar.MINUTE, minDiff * (-1));
		return cal.getTime();
	}

	public IProjectionService getProjectionService() {
		return projectionService;
	}

	public void setProjectionService(IProjectionService projectionService) {
		this.projectionService = projectionService;
	}

	public ITheaterService getTheaterService() {
		return theaterService;
	}

	public void setTheaterService(ITheaterService theaterService) {
		this.theaterService = theaterService;
	}

	public IMovieService getMovieService() {
		return movieService;
	}

	public void setMovieService(IMovieService movieService) {
		this.movieService = movieService;
	}

	public Projection getProjection() {
		return projection;
	}

	public void setProjection(Projection projection) {
		this.projection = projection;
	}

	public List<Theater> getTheaterList() {
		return theaterList;
	}

	public void setTheaterList(List<Theater> theaterList) {
		this.theaterList = theaterList;
	}

	public List<Cinema> getCinemaList() {
		return cinemaList;
	}

	public void setCinemaList(List<Cinema> cinemaList) {
		this.cinemaList = cinemaList;
	}

	public List<Movie> getMovieList() {
		return movieList;
	}

	public void setMovieList(List<Movie> movieList) {
		this.movieList = movieList;
	}

	public ScheduleEvent getEvent() {
		return event;
	}

	public void setEvent(ScheduleEvent event) {
		this.event = event;
	}

	public ScheduleModel getEventModel() {
		return eventModel;
	}

	public void setEventModel(ScheduleModel eventModel) {
		this.eventModel = eventModel;
	}

	public boolean isForUpdate() {
		return forUpdate;
	}

	public void setForUpdate(boolean forUpdate) {
		this.forUpdate = forUpdate;
	}

	public Date getCurrStartDate() {
		return currStartDate;
	}

	public void setCurrStartDate(Date currStartDate) {
		this.currStartDate = currStartDate;
	}

	public Date getCurrEndDate() {
		return currEndDate;
	}

	public void setCurrEndDate(Date currEndDate) {
		this.currEndDate = currEndDate;
	}

}