package hr.primefaces.bean;

import hr.primefaces.model.Cinema;
import hr.primefaces.model.Theater;
import hr.primefaces.service.ITheaterService;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.primefaces.event.SelectEvent;
import org.primefaces.model.map.DefaultMapModel;
import org.primefaces.model.map.LatLng;
import org.primefaces.model.map.MapModel;
import org.primefaces.model.map.Marker;

@ManagedBean(name = "contactMB")
@ViewScoped
public class ContactView implements Serializable {

	private static final long serialVersionUID = 1L;

	@ManagedProperty(value = "#{TheaterService}")
	private ITheaterService theaterService;

	private static final String INIT_LOCATION = "45.795169, 15.907884";
	private static final String INIT_ZOOM = "7";

	private Theater theater;
	private String initCenter;
	private String initZoom;
	private MapModel mapModel;
	private List<Theater> theaterList;

	@PostConstruct
	public void init() {

		setTheater(new Theater());
		setInitCenter(INIT_LOCATION);
		setInitZoom(INIT_ZOOM);
		setMapModel(new DefaultMapModel());

		setMarkers();
	}

	/**
	 * setMarkers
	 */
	public void setMarkers() {

		setMapModel(new DefaultMapModel());

		setTheaterList(getTheaterService().getTheaters());

		for (Theater t : getTheaterList()) {

			final LatLng tempCoord = new LatLng(t.getLat(), t.getLng());
			getMapModel().addOverlay(new Marker(tempCoord, t.getAddress()));
		}
	}

	/**
	 * calcNoCinema
	 * @param p_theater
	 * @return
	 */
	public String calcNoCinema(final Theater p_theater) {

		return getTheaterService().getCinemaByTheater(p_theater) + "";
	}

	/**
	 * calcNoSeatPlaces
	 * @param p_theater
	 * @return
	 */
	public String calcNoSeatPlaces(final Theater p_theater) {

		int brojSjedecihMjesta = 0;

		for(Cinema c : getTheaterService().getCinemaByTheater(p_theater)) {

			brojSjedecihMjesta += c.getNumberOfRows() * c.getNumberOfSeatsInRow();
		}

		return brojSjedecihMjesta + "";
	}

	/**
	 * onRowSelect
	 * @param p_event
	 */
	public void onRowSelect(final SelectEvent p_event) {

		setInitCenter(getTheater().getLat() + ", " + getTheater().getLng());
		setInitZoom("14");
	}

	/**
	 * ################# GETTERS AND SETTERS #################
	 */

	/**
	 * @return the theaterService
	 */
	public ITheaterService getTheaterService() {
		return theaterService;
	}

	/**
	 * @return the theater
	 */
	public Theater getTheater() {
		return theater;
	}

	/**
	 * @return the initCenter
	 */
	public String getInitCenter() {
		return initCenter;
	}

	/**
	 * @return the initZoom
	 */
	public String getInitZoom() {
		return initZoom;
	}

	/**
	 * @return the mapModel
	 */
	public MapModel getMapModel() {
		return mapModel;
	}

	/**
	 * @return the theaterList
	 */
	public List<Theater> getTheaterList() {
		return theaterList;
	}

	/**
	 * @param p_theaterService the theaterService to set
	 */
	public void setTheaterService(final ITheaterService p_theaterService) {
		this.theaterService = p_theaterService;
	}

	/**
	 * @param p_theater the theater to set
	 */
	public void setTheater(final Theater p_theater) {
		this.theater = p_theater;
	}

	/**
	 * @param p_initCenter the initCenter to set
	 */
	public void setInitCenter(final String p_initCenter) {
		this.initCenter = p_initCenter;
	}

	/**
	 * @param p_initZoom the initZoom to set
	 */
	public void setInitZoom(final String p_initZoom) {
		this.initZoom = p_initZoom;
	}

	/**
	 * @param p_mapModel the mapModel to set
	 */
	public void setMapModel(final MapModel p_mapModel) {
		this.mapModel = p_mapModel;
	}

	/**
	 * @param p_theaterList the theaterList to set
	 */
	public void setTheaterList(final List<Theater> p_theaterList) {
		this.theaterList = p_theaterList;
	}

}