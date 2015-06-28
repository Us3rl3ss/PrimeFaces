package hr.primefaces.bean;

import hr.primefaces.model.Projection;
import hr.primefaces.service.IProjectionService;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

@ManagedBean(name = "getProjectionMB")
@ViewScoped
public class GetProjectionManagedBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@ManagedProperty(value = "#{ProjectionService}")
	IProjectionService projectionService;

	private Projection projection = new Projection();

	private List<Projection> projectionList;
	
	private boolean render;
	
	@PostConstruct
	public void init() {
		projectionList = projectionService.getProjections();
	}

	public IProjectionService getProjectionService() {
		return projectionService;
	}

	public void setProjectionService(IProjectionService projectionService) {
		this.projectionService = projectionService;
	}

	public Projection getProjection() {
		return projection;
	}

	public void setProjection(Projection projection) {
		this.projection = projection;
	}

	public List<Projection> getProjectionList() {
		return projectionList;
	}

	public void setProjectionList(List<Projection> projectionList) {
		this.projectionList = projectionList;
	}

	public boolean isRender() {
		return render;
	}

	public void setRender(boolean render) {
		this.render = render;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	
}