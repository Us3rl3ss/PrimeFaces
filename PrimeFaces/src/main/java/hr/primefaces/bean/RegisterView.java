package hr.primefaces.bean;

import hr.primefaces.constants.Messages;
import hr.primefaces.model.Role;
import hr.primefaces.model.User;
import hr.primefaces.service.IUserService;
import hr.primefaces.util.MessageUtil;

import java.io.InputStream;
import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import org.apache.poi.util.IOUtils;
import org.primefaces.context.RequestContext;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;
import org.springframework.dao.DataIntegrityViolationException;

@ManagedBean(name = "registerMB")
@ViewScoped
public class RegisterView implements Serializable {

	private static final long serialVersionUID = 1L;

	private static final String REGISTRATION_DIALOG_COMPONENT = "PF('registrationDialog').hide();";
	private static final String PLACEHOLDER_IMG = "/resources/images/placeholder.png";

	@ManagedProperty(value = "#{UserService}")
	private IUserService userService;

	private User user;
	private String uploadedFileNames;

	@PostConstruct
	public void init() {

		setUser(new User());
		setUploadedFileNames("");
	}

	/**
	 * register
	 * @return
	 */
	public String register() {

		boolean pass = true;

		try {
			getUser().setRole(new Role(2));

			if (getUser().getImage() == null) {

				setDefaultUserPicture();
			}

			getUserService().addUser(getUser());
		}
		catch (DataIntegrityViolationException divex) {
			MessageUtil.error(Messages.USER_ALREADY_EXIST_MSG);
			pass = false;
		}

		if (pass) {
			MessageUtil.info(Messages.SUCCESSFUL_REGISTER_MSG);
			RequestContext.getCurrentInstance().execute(REGISTRATION_DIALOG_COMPONENT);
		}

		return "";
	}

	/**
	 * leave
	 * @return
	 */
	public String leave() {

		RequestContext.getCurrentInstance().execute(REGISTRATION_DIALOG_COMPONENT);
		return "";
	}

	/**
	 * setDefaultUserPicture
	 */
	public void setDefaultUserPicture() {

		final ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
		final InputStream input = externalContext.getResourceAsStream(PLACEHOLDER_IMG);

		byte[] byteData = null;

		try {
			byteData = IOUtils.toByteArray(input);
		}
		catch (Exception e) {
			e.printStackTrace();
		}

		getUser().setImage(byteData);
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

			getUser().setImage(byteData);
			setUploadedFileNames(getUploadedFileNames() + file.getFileName());
		}
	}

	/**
	 * ################# GETTERS AND SETTERS #################
	 */

	/**
	 * @return the userService
	 */
	public IUserService getUserService() {
		return userService;
	}

	/**
	 * @return the user
	 */
	public User getUser() {
		return user;
	}

	/**
	 * @return the uploadedFileNames
	 */
	public String getUploadedFileNames() {
		return uploadedFileNames;
	}

	/**
	 * @param p_userService the userService to set
	 */
	public void setUserService(final IUserService p_userService) {
		this.userService = p_userService;
	}

	/**
	 * @param p_user the user to set
	 */
	public void setUser(final User p_user) {
		this.user = p_user;
	}

	/**
	 * @param p_uploadedFileNames the uploadedFileNames to set
	 */
	public void setUploadedFileNames(final String p_uploadedFileNames) {
		this.uploadedFileNames = p_uploadedFileNames;
	}

}