package hr.primefaces.bean;

import hr.primefaces.constants.Messages;
import hr.primefaces.model.Role;
import hr.primefaces.model.User;
import hr.primefaces.service.IUserService;
import hr.primefaces.util.MessageUtil;

import java.io.InputStream;
import java.io.Serializable;

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

	@ManagedProperty(value = "#{UserService}")
	IUserService userService;

	private User user = new User();
	private String uploadedFileNames = "";

	public String register() {

		boolean pass = true;

		try {
			user.setRole(new Role(2));
			
			if (user.getImage() == null) {
				
				setDefaultUserPicture();
			}
			
			userService.addUser(user);
		} catch (DataIntegrityViolationException divex) {
			MessageUtil.error(Messages.USER_ALREADY_EXIST_MSG);
			pass = false;
		}

		if (pass) {
			MessageUtil.info(Messages.SUCCESSFUL_REGISTER_MSG);
			RequestContext.getCurrentInstance().execute("PF('registrationDialog').hide();");
		}

		return "";
	}

	private void setDefaultUserPicture() {

		ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
		InputStream input = externalContext.getResourceAsStream("/resources/images/placeholder.png");
		
		byte[] byteData = null;
		
		try {
			byteData = IOUtils.toByteArray(input);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		user.setImage(byteData);
	}

	public String odustani() {
		RequestContext.getCurrentInstance().execute("PF('registrationDialog').hide();");
		return "";
	}
	
	/**
	 * handleFileUpload
	 */
	public void handleFileUpload(FileUploadEvent event) {
		
		UploadedFile file;
		byte[] byteData = null;
		
		file = event.getFile();
		try {
			byteData = IOUtils.toByteArray(file.getInputstream());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if (byteData != null) {
			
			user.setImage(byteData);
			setUploadedFileNames(getUploadedFileNames() + file.getFileName());
		}
	}

	public IUserService getUserService() {
		return userService;
	}

	public void setUserService(IUserService userService) {
		this.userService = userService;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getUploadedFileNames() {
		return uploadedFileNames;
	}

	public void setUploadedFileNames(String uploadedFileNames) {
		this.uploadedFileNames = uploadedFileNames;
	}

}