package hr.primefaces.service.impl;

import hr.primefaces.dao.IProjectionDAO;
import hr.primefaces.model.Cinema;
import hr.primefaces.model.Projection;
import hr.primefaces.model.Theater;
import hr.primefaces.service.IProjectionService;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

import org.springframework.transaction.annotation.Transactional;

@ManagedBean(name = "ProjectionService")
@ApplicationScoped
@Transactional(readOnly = true)
public class ProjectionService implements IProjectionService, Serializable {

	private static final long serialVersionUID = 1L;

	IProjectionDAO projectionDAO;
	List<Projection> projectionList;

	public ProjectionService() {
	}

	public List<Projection> getProjectionList() {
		return projectionList;
	}

	public void setProjectionList(List<Projection> projectionList) {
		this.projectionList = projectionList;
	}

	@Transactional(readOnly = false)
	@Override
	public void addProjection(Projection projection) {
		getProjectionDAO().addProjection(projection);
	}

	@Transactional(readOnly = false)
	@Override
	public void deleteProjection(Projection projection) {
		getProjectionDAO().deleteProjection(projection);
	}

	@Transactional(readOnly = false)
	@Override
	public void updateProjection(Projection projection) {
		getProjectionDAO().updateProjection(projection);
	}

	@Override
	public Projection getProjectionById(int id) {
		return getProjectionDAO().getProjectionById(id);
	}

	@Override
	public List<Projection> getProjections() {
		return getProjectionDAO().getProjections();
	}
	
	@Override
	public Projection getProjectionByCinemaStartEnd(Cinema cinema, Date start, Date end) {
		return getProjectionDAO().getProjectionByCinemaStartEnd(cinema, start, end);
	}
	
	@Override
	public List<Projection> getProjectionByCinemaBetweenStartEnd(Cinema cinema, Date start, Date end) {
		return getProjectionDAO().getProjectionByCinemaBetweenStartEnd(cinema, start, end);
	}

	public IProjectionDAO getProjectionDAO() {
		return projectionDAO;
	}

	public void setProjectionDAO(IProjectionDAO projectionDAO) {
		this.projectionDAO = projectionDAO;
	}
	
	public List<Projection> getProjectionsByTheater(Theater theater) {
		return getProjectionDAO().getProjectionsByTheater(theater);
	}
	
	public List<Projection> getProjectionsByCinema(Cinema cinema) {
		return getProjectionDAO().getProjectionsByCinema(cinema);
	}
	
	public List<Projection> getProjectionsForReservation(Theater theater, Date datumProjekcije) {
		return getProjectionDAO().getProjectionsForReservation(theater, datumProjekcije);
	}

	public List<Projection> getDistinctMovieProjections(Projection projection) {
		return getProjectionDAO().getDistinctMovieProjections(projection);
	}

}
