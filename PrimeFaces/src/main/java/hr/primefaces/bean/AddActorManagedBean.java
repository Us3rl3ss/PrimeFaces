package hr.primefaces.bean;

import hr.primefaces.model.Actor;
import hr.primefaces.model.Movie;
import hr.primefaces.service.IActorService;
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

@ManagedBean(name = "addActorMB")
@ViewScoped
public class AddActorManagedBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@ManagedProperty(value = "#{MovieService}")
	IMovieService movieService;
	
	@ManagedProperty(value = "#{ActorService}")
	IActorService actorService;

	@ManagedProperty("#{dropDownMB}")
	private DropdownMenuManagedBean dropDownMB;

	private Actor actor = new Actor();

	private List<Actor> actorList;
	
	private String uploadedFileNames = "";

	@PostConstruct
	public void init() {
	}

	public void spremi() {

		try {
			actorService.addActor(actor);
			dropDownMB.reloadActor();
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
			
			actor.setImage(byteData);
			setUploadedFileNames(getUploadedFileNames() + file.getFileName());
		}
	}
	
	public List<Movie> completeMovie(String input) {
		List<Movie> list = movieService.getMovieByName(input);
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

	public List<Actor> getActorList() {
		return actorList;
	}

	public void setActorList(List<Actor> actorList) {
		this.actorList = actorList;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public DropdownMenuManagedBean getDropDownMB() {
		return dropDownMB;
	}

	public void setDropDownMB(DropdownMenuManagedBean dropDownMB) {
		this.dropDownMB = dropDownMB;
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
	
	

}