package hr.primefaces.images;

import hr.primefaces.model.User;
import hr.primefaces.service.IUserService;

import java.io.ByteArrayInputStream;
import java.io.Serializable;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseId;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

@ManagedBean(name = "userImageMB")
@ApplicationScoped
public class UserImageManagedBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@ManagedProperty(value = "#{UserService}")
	IUserService userService;

	public StreamedContent getImage() {
		FacesContext fc = FacesContext.getCurrentInstance();
		if (fc.getCurrentPhaseId() == PhaseId.RENDER_RESPONSE) {
			return new DefaultStreamedContent();
		}

		String user_id = fc.getExternalContext().getRequestParameterMap().get("user_id");

		if ("".equals(user_id))
			return new DefaultStreamedContent();

		User u = userService.getUserById(Integer.parseInt(user_id));

		byte[] photoData = u.getImage();
		return new DefaultStreamedContent(new ByteArrayInputStream(photoData));
	}

	public IUserService getUserService() {
		return userService;
	}

	public void setUserService(IUserService userService) {
		this.userService = userService;
	}

}
