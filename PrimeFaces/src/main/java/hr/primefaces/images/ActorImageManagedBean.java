package hr.primefaces.images;

import hr.primefaces.model.Actor;
import hr.primefaces.service.IActorService;

import java.io.ByteArrayInputStream;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseId;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

@ManagedBean(name = "actorImageMB")
@ApplicationScoped
public class ActorImageManagedBean {

	@ManagedProperty(value = "#{ActorService}")
	IActorService actorService;

	public StreamedContent getImage() {
		FacesContext fc = FacesContext.getCurrentInstance();
		if (fc.getCurrentPhaseId() == PhaseId.RENDER_RESPONSE) {
			return new DefaultStreamedContent();
		}

		String actor_id = fc.getExternalContext().getRequestParameterMap().get("actor_id");

		Actor a = actorService.getActorById(Integer.parseInt(actor_id));

		byte[] photoData = a.getImage();
		return new DefaultStreamedContent(new ByteArrayInputStream(photoData));
	}

	public IActorService getActorService() {
		return actorService;
	}

	public void setActorService(IActorService actorService) {
		this.actorService = actorService;
	}

}
