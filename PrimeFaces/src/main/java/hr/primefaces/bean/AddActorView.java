package hr.primefaces.bean;

import hr.primefaces.model.Actor;
import hr.primefaces.service.IActorService;
import hr.primefaces.util.MessageUtil;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.apache.poi.util.IOUtils;
import org.hibernate.HibernateException;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

@ManagedBean(name = "addActorMB")
@ViewScoped
public class AddActorView implements Serializable {

	private static final long serialVersionUID = 1L;

	@ManagedProperty(value = "#{ActorService}")
	private IActorService actorService;

	private Actor actor;
	private String uploadedFileNames;

	@PostConstruct
	public void init() {

		setActor(new Actor());
		setUploadedFileNames("");
	}

	/**
	 * save -funkcija za dodavanje objekta Actor
	 */
	public void save() {

		try {
			getActorService().addActor(getActor());
			setActor(new Actor());
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
	 * TODO izbaciti u zaseban file
	 */
	public void handleFileUpload(final FileUploadEvent p_event) {

		UploadedFile file;
		byte[] byteData = null;

		file = p_event.getFile();
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
	 * ################# GETTERS AND SETTERS #################
	 */

	/**
	 * @return the actorService
	 */
	public IActorService getActorService() {
		return actorService;
	}

	/**
	 * @return the actor
	 */
	public Actor getActor() {
		return actor;
	}

	/**
	 * @return the uploadedFileNames
	 */
	public String getUploadedFileNames() {
		return uploadedFileNames;
	}

	/**
	 * @param actorService
	 *            the actorService to set
	 */
	public void setActorService(final IActorService p_actorService) {
		this.actorService = p_actorService;
	}

	/**
	 * @param actor
	 *            the actor to set
	 */
	public void setActor(final Actor p_actor) {
		this.actor = p_actor;
	}

	/**
	 * @param uploadedFileNames
	 *            the uploadedFileNames to set
	 */
	public void setUploadedFileNames(final String p_uploadedFileNames) {
		this.uploadedFileNames = p_uploadedFileNames;
	}

}