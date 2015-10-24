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
	private IUserService userService;

	/**
	 * getImage
	 * @return
	 */
	public StreamedContent getImage() {

		final FacesContext fc = FacesContext.getCurrentInstance();
		if (fc.getCurrentPhaseId() == PhaseId.RENDER_RESPONSE) {
			return new DefaultStreamedContent();
		}

		final String userId = fc.getExternalContext().getRequestParameterMap().get("user_id");

		if ("".equals(userId)) {

			return new DefaultStreamedContent();
		}

		final User u = getUserService().getUserById(Integer.parseInt(userId));
		final byte[] photoData = u.getImage();

		return new DefaultStreamedContent(new ByteArrayInputStream(photoData));
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
	 * @param p_userService the userService to set
	 */
	public void setUserService(final IUserService p_userService) {
		this.userService = p_userService;
	}

}
