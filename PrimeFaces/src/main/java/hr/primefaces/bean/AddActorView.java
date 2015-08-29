package hr.primefaces.bean;

import hr.primefaces.helper.MovieFavorite;
import hr.primefaces.model.Actor;
import hr.primefaces.model.Movie;
import hr.primefaces.service.IActorService;
import hr.primefaces.service.IMovieService;
import hr.primefaces.util.MessageUtil;

import java.io.InputStream;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;

import org.apache.poi.util.IOUtils;
import org.hibernate.HibernateException;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.DefaultScheduleEvent;
import org.primefaces.model.DefaultScheduleModel;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.ScheduleModel;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;
import org.primefaces.model.chart.PieChartModel;

@ManagedBean(name = "addActorMB")
@ViewScoped
public class AddActorView implements Serializable {

	private static final long serialVersionUID = 1L;

	@ManagedProperty(value = "#{MovieService}")
	IMovieService movieService;

	@ManagedProperty(value = "#{ActorService}")
	IActorService actorService;

	private Actor actor = new Actor();
	private String uploadedFileNames = "";

	private PieChartModel chart;
	private ScheduleModel scheduleModel;
	private StreamedContent downloadFile;

	@PostConstruct
	public void init() {

		setPieChartValue();
		setScheduleModelValue();
		setFileForDownload();
	}

	/**
	 * setFileForDownload
	 */
	private void setFileForDownload() {

		InputStream stream = ((ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext()).getResourceAsStream("images/ironMan3.jpg");
		downloadFile = new DefaultStreamedContent(stream, "image/jpg", "downloaded_ironMan3.jpg");
	}

	/**
	 * setScheduleModelValue
	 */
	private void setScheduleModelValue() {

		scheduleModel = new DefaultScheduleModel();
			
		scheduleModel.addEvent(new DefaultScheduleEvent(" Iron Man 2", stringToDate("08.08.2015 10:00"), stringToDate("08.08.2015 12:00")));
		scheduleModel.addEvent(new DefaultScheduleEvent(" Batman: Arkham Knight", stringToDate("08.08.2015 10:00"), stringToDate("08.08.2015 12:00")));
		scheduleModel.addEvent(new DefaultScheduleEvent(" Terminator", stringToDate("08.08.2015 12:00"), stringToDate("08.08.2015 15:00")));
		scheduleModel.addEvent(new DefaultScheduleEvent(" The Avengers", stringToDate("10.08.2015 10:00"), stringToDate("10.08.2015 16:00")));
		scheduleModel.addEvent(new DefaultScheduleEvent(" Ant-Man", stringToDate("09.08.2015 10:00"), stringToDate("09.08.2015 19:00")));
	}
	
	/**
	 * stringToDate
	 */
	public Date stringToDate(String date) {
		
		Date result = new Date();
		
		SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy HH:mm");
	 
		try {
	 
			result = formatter.parse(date);
	 
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		return result;
	}

	/**
	 * setPieChartValue
	 */
	public void setPieChartValue() {

		chart = new PieChartModel();
		List<MovieFavorite> mfList = getMovieFavoriteList();

		for (MovieFavorite tempMf : mfList) {

			chart.set(tempMf.getMovie().getName(), tempMf.getFavoriteNumber());
		}

		chart.setTitle("Top 5");
		chart.setLegendPosition("e");
		chart.setFill(false);
		chart.setShowDataLabels(true);
	}
	
	/**
	 * getMovieFavoriteList
	 */
	private List<MovieFavorite> getMovieFavoriteList() {

		List<MovieFavorite> result = new ArrayList<MovieFavorite>();

		result.add(new MovieFavorite(new Movie("Iron Man"), 267));
		result.add(new MovieFavorite(new Movie("Terminator"), 120));
		result.add(new MovieFavorite(new Movie("The Avengers"), 243));
		result.add(new MovieFavorite(new Movie("Ant-Man"), 210));
		result.add(new MovieFavorite(new Movie("Batman: Arkham Knight"), 312));

		return result;
	}

	
	/**
	 * spremi
	 */
	public void spremi() {

		try {
			actorService.addActor(actor);
			actor = new Actor();
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

			actor.setImage(byteData);
			setUploadedFileNames(getUploadedFileNames() + file.getFileName());
		}
	}

	/**
	 * completeMovie
	 */
	public List<Movie> completeMovie(String input) {
		List<Movie> list = movieService.getMovieByName(input);
		return list;
	}

	public IActorService getActorService() {
		return actorService;
	}

	public void setActorService(IActorService actorService) {
		this.actorService = actorService;
	}

	public Actor getActor() {
		return actor;
	}

	public void setActor(Actor actor) {
		this.actor = actor;
	}

	public String getUploadedFileNames() {
		return uploadedFileNames;
	}

	public void setUploadedFileNames(String uploadedFileNames) {
		this.uploadedFileNames = uploadedFileNames;
	}

	public IMovieService getMovieService() {
		return movieService;
	}

	public void setMovieService(IMovieService movieService) {
		this.movieService = movieService;
	}

	public PieChartModel getChart() {
		return chart;
	}

	public void setChart(PieChartModel chart) {
		this.chart = chart;
	}

	public ScheduleModel getScheduleModel() {
		return scheduleModel;
	}

	public void setScheduleModel(ScheduleModel scheduleModel) {
		this.scheduleModel = scheduleModel;
	}

	public StreamedContent getDownloadFile() {
		return downloadFile;
	}

	public void setDownloadFile(StreamedContent downloadFile) {
		this.downloadFile = downloadFile;
	}

}