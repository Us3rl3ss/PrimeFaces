package hr.primefaces.bean;

import hr.primefaces.model.CinemaSeats;
import hr.primefaces.model.Projection;
import hr.primefaces.model.ProjectionReservedSeats;
import hr.primefaces.service.ICinemaSeatsService;
import hr.primefaces.service.IProjectionReservedSeatsService;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.faces.event.ComponentSystemEvent;

import org.primefaces.context.RequestContext;

import com.google.gson.Gson;

@ManagedBean(name = "reserveSeatsMB")
@ViewScoped
public class ReserveSeatsManagedBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@ManagedProperty(value = "#{loginMB}")
	LoginManagedBean loginMB;

	@ManagedProperty(value = "#{CinemaSeatsService}")
	ICinemaSeatsService cinemaSeatsService;

	@ManagedProperty(value = "#{ProjectionReservedSeatsService}")
	IProjectionReservedSeatsService projectionReservedSeatsService;

	private Projection projection;
	private List<CinemaSeats> cinemaSeatsList;
	private List<String> selectedCinemaSeatsList;
	private List<String> savedCinemaSeatsList;
	
	private String test = "testiranje";
	
	private List<String> savedCinemaSeatsByCurrUserList;

	private boolean disabled = true;
	
	public void test() {
		List<String> a = new ArrayList<String>();
		a.add("aa");
		a.add("bb");
		
		Gson gson = new Gson();
		String res = gson.toJson(a);
		
		String call = "handleResize(" + res + ")";
		
		System.out.println(call);
		
		RequestContext.getCurrentInstance().execute(call);
	}

	public void reserveSeats() {

		List<ProjectionReservedSeats> seatsToSaveList = new ArrayList<ProjectionReservedSeats>();

		List<String> cinemaSeatIdToSaveList = getNewReservations(
				this.selectedCinemaSeatsList, this.savedCinemaSeatsList);

		Iterator<String> iter = cinemaSeatIdToSaveList.iterator();

		while (iter.hasNext()) {

			CinemaSeats cinemaSeats = new CinemaSeats();
			cinemaSeats.setId(Integer.parseInt(iter.next()));

			ProjectionReservedSeats prs = new ProjectionReservedSeats();
			prs.setProjection(this.projection);
			prs.setCinema_seats(cinemaSeats);
			prs.setUser(loginMB.getUser());

			seatsToSaveList.add(prs);
		}

		if (seatsToSaveList.size() > 0) {

			Iterator<ProjectionReservedSeats> saveIter = seatsToSaveList
					.iterator();

			while (saveIter.hasNext()) {

				ProjectionReservedSeats prs = saveIter.next();
				projectionReservedSeatsService.addProjectionReservedSeats(prs);
			}
		}
	}

	private List<String> getNewReservations(
			List<String> selectedCinemaSeatsList,
			List<String> savedCinemaSeatsList) {

		List<String> result = new ArrayList<String>();

		Iterator<String> iterSelected = selectedCinemaSeatsList.iterator();

		while (iterSelected.hasNext()) {

			String tempSelected = iterSelected.next();

			boolean inList = false;
			for (int i = 0; i < savedCinemaSeatsList.size(); i++) {

				String tempSaved = savedCinemaSeatsList.get(i);

				if (tempSelected.equals(tempSaved))
					inList = true;
			}

			if (!inList)
				result.add(tempSelected);
		}

		return result;
	}

	public void pullValuesFromFlash(ComponentSystemEvent e) {
		Flash flash = FacesContext.getCurrentInstance().getExternalContext()
				.getFlash();

		Projection p = (Projection) flash.get("projection");

		this.projection = p;

		System.out.println(projection.getId());

		setSeats();
		setSelectedSeats();
	}

	public void setSeats() {

		this.setCinemaSeatsList(cinemaSeatsService
				.getCinemaSeatsByCinemaId(projection.getCinema().getId()));
	}

	public void showSelected() {

		Iterator<String> iter = selectedCinemaSeatsList.iterator();
		while (iter.hasNext()) {

			System.out.println(iter.next());
		}
	}

	public void setSelectedSeats() {

		List<ProjectionReservedSeats> prsList = projectionReservedSeatsService
				.getProjectionReservedSeatsByProjection(this.projection);

		Iterator<ProjectionReservedSeats> iter = prsList.iterator();

		selectedCinemaSeatsList = new ArrayList<String>();
		savedCinemaSeatsList = new ArrayList<String>();

		while (iter.hasNext()) {

			ProjectionReservedSeats prs = iter.next();

			String cinemaSeatId = prs.getCinema_seats().getId() + "";

			selectedCinemaSeatsList.add(cinemaSeatId);
			savedCinemaSeatsList.add(cinemaSeatId);
		}
	}
	
	public void setSavedCinemaSeatsByCurrUserList() {

		List<ProjectionReservedSeats> prsList = projectionReservedSeatsService
				.getProjectionReservedSeatsByProjectionAndUser(this.projection, loginMB.getUser());

		Iterator<ProjectionReservedSeats> iter = prsList.iterator();

		while (iter.hasNext()) {

			ProjectionReservedSeats prs = iter.next();

			String cinemaSeatId = prs.getCinema_seats().getId() + "";

			savedCinemaSeatsByCurrUserList.add(cinemaSeatId);
		}
	}

	@PostConstruct
	public void init() {
		// setSelected();

		System.out.println("INIT");
	}

	public void spremi() {
	}

	public void pretrazi() {
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public ICinemaSeatsService getCinemaSeatsService() {
		return cinemaSeatsService;
	}

	public void setCinemaSeatsService(ICinemaSeatsService cinemaSeatsService) {
		this.cinemaSeatsService = cinemaSeatsService;
	}

	public Projection getProjection() {
		return projection;
	}

	public void setProjection(Projection projection) {
		this.projection = projection;
	}

	public List<CinemaSeats> getCinemaSeatsList() {
		return cinemaSeatsList;
	}

	public void setCinemaSeatsList(List<CinemaSeats> cinemaSeatsList) {
		this.cinemaSeatsList = cinemaSeatsList;
	}

	public List<String> getSelectedCinemaSeatsList() {
		return selectedCinemaSeatsList;
	}

	public void setSelectedCinemaSeatsList(List<String> selectedCinemaSeatsList) {
		this.selectedCinemaSeatsList = selectedCinemaSeatsList;
	}

	public IProjectionReservedSeatsService getProjectionReservedSeatsService() {
		return projectionReservedSeatsService;
	}

	public void setProjectionReservedSeatsService(
			IProjectionReservedSeatsService projectionReservedSeatsService) {
		this.projectionReservedSeatsService = projectionReservedSeatsService;
	}

	public boolean isDisabled() {
		return disabled;
	}

	public void setDisabled(boolean disabled) {
		this.disabled = disabled;
	}

	public List<String> getSavedCinemaSeatsList() {
		return savedCinemaSeatsList;
	}

	public void setSavedCinemaSeatsList(List<String> savedCinemaSeatsList) {
		this.savedCinemaSeatsList = savedCinemaSeatsList;
	}

	public LoginManagedBean getLoginMB() {
		return loginMB;
	}

	public void setLoginMB(LoginManagedBean loginMB) {
		this.loginMB = loginMB;
	}

	public List<String> getSavedCinemaSeatsByCurrUserList() {
		return savedCinemaSeatsByCurrUserList;
	}

	public void setSavedCinemaSeatsByCurrUserList(List<String> savedCinemaSeatsByCurrUserList) {
		this.savedCinemaSeatsByCurrUserList = savedCinemaSeatsByCurrUserList;
	}

	public String getTest() {
		return test;
	}

	public void setTest(String test) {
		this.test = test;
	}

}