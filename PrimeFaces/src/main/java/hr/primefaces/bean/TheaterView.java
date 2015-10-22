package hr.primefaces.bean;

import hr.primefaces.gmap.Location;
import hr.primefaces.gmap.LocationCalculator;
import hr.primefaces.gmap.Result;
import hr.primefaces.model.Theater;
import hr.primefaces.service.ITheaterService;
import hr.primefaces.util.MessageUtil;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.hibernate.HibernateException;
import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.map.OverlaySelectEvent;
import org.primefaces.model.map.DefaultMapModel;
import org.primefaces.model.map.LatLng;
import org.primefaces.model.map.MapModel;
import org.primefaces.model.map.Marker;

@ManagedBean(name = "theaterMB")
@ViewScoped
public class TheaterView implements Serializable {

	private static final long serialVersionUID = 1L;

	private static final String THEATER_JS = "PF('theaterDialog').show();";
	private static final String THEATER_DIALOG_COMPONENT = "theaterDialog";

	@ManagedProperty(value = "#{TheaterService}")
	private ITheaterService theaterService;

	private static final String INIT_LOCATION = "45.795169, 15.907884";
	private static final String INIT_ZOOM = "7";

	private Theater theater;
	private String initCenter;
	private String initZoom;
	private MapModel mapModel;
	private String pretragaTekst;
	private Marker selectedMarker;
	private List<Theater> theaterList;
	private boolean deleteDisabled;

	@PostConstruct
	public void init() {

		setTheater(new Theater());
		setInitCenter(INIT_LOCATION);
		setInitZoom(INIT_ZOOM);
		setMapModel(new DefaultMapModel());
		setDeleteDisabled(true);

		setMarkers();
	}

	/**
	 * save
	 */
	public void save() {

		try {

			if (getTheaterService().getTheaterByLatLng(getTheater().getLat(), getTheater().getLng()) != null) {

				getTheaterService().updateTheater(getTheater());
				setTheater(new Theater());
				MessageUtil.info("Podaci su uspješno ažurirani!");
			}
			else {

				getTheaterService().addTheater(getTheater());
				setTheater(new Theater());
				MessageUtil.info("Podaci su uspješno spremljeni!");
			}
		}
		catch (HibernateException hex) {
			hex.printStackTrace();
			MessageUtil.error("Došlo je do hibernate greške!");
		}
		catch (Exception ex) {
			ex.printStackTrace();
			MessageUtil.error("Došlo je do greške!");
		}

		setMarkers();
	}

	/**
	 * delete
	 */
	public void delete() {

		try {
			getTheaterService().deleteTheater(getTheater());
			MessageUtil.info("Kino je uspješno obrisano!");
		}
		catch (HibernateException hex) {
			hex.printStackTrace();
			MessageUtil.error("Došlo je do hibernate greške!");
		}
		catch (Exception ex) {
			ex.printStackTrace();
			MessageUtil.error("Došlo je do greške!");
		}

		setMarkers();
	}

	/**
	 * search
	 */
	public void search() {

		if (getPretragaTekst() != null && !"".equals(getPretragaTekst())) {

			setMarkers();

			final Result locationResult = LocationCalculator.getLocationFromAddress(getPretragaTekst());

			if (locationResult.getGeometry() != null) {

				final Location location = locationResult.getGeometry().getLocation();
				final LatLng coord = new LatLng(location.getLat(), location.getLng());
				getMapModel().addOverlay(new Marker(coord, getPretragaTekst()));

				setInitCenter(coord.getLat() + ", " + coord.getLng());
				setInitZoom("10");
			}
			else {

				MessageUtil.info("Nije pronađeno mjesto!");
			}
		}
		else {

			MessageUtil.info("Polje za pretragu je prazno");
		}
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
	 * onMarkerSelect
	 * @param p_event
	 */
	public void onMarkerSelect(final OverlaySelectEvent p_event) {

		setSelectedMarker((Marker)p_event.getOverlay());
		final Double lat = getSelectedMarker().getLatlng().getLat();
		final Double lng = getSelectedMarker().getLatlng().getLng();
		final String title = getSelectedMarker().getTitle();

		setTheater(getTheaterService().getTheaterByLatLng(lat, lng));

		if (getTheater() != null) {

			setDeleteDisabled(false);
		}
		else {

			setTheater(new Theater());
			getTheater().setAddress(title);
			getTheater().setLat(lat);
			getTheater().setLng(lng);

			setDeleteDisabled(true);
		}

		RequestContext.getCurrentInstance().execute(THEATER_JS);
		RequestContext.getCurrentInstance().update(THEATER_DIALOG_COMPONENT);
	}

	/**
	 * onRowSelect
	 * @param p_event
	 */
	public void onRowSelect(final SelectEvent p_event) {

		setInitCenter(getTheater().getLat() + ", " + getTheater().getLng());
		setInitZoom("10");
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
	 * @return the pretragaTekst
	 */
	public String getPretragaTekst() {
		return pretragaTekst;
	}

	/**
	 * @return the selectedMarker
	 */
	public Marker getSelectedMarker() {
		return selectedMarker;
	}

	/**
	 * @return the theaterList
	 */
	public List<Theater> getTheaterList() {
		return theaterList;
	}

	/**
	 * @return the deleteDisabled
	 */
	public boolean isDeleteDisabled() {
		return deleteDisabled;
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
	 * @param p_pretragaTekst the pretragaTekst to set
	 */
	public void setPretragaTekst(final String p_pretragaTekst) {
		this.pretragaTekst = p_pretragaTekst;
	}

	/**
	 * @param p_selectedMarker the selectedMarker to set
	 */
	public void setSelectedMarker(final Marker p_selectedMarker) {
		this.selectedMarker = p_selectedMarker;
	}

	/**
	 * @param p_theaterList the theaterList to set
	 */
	public void setTheaterList(final List<Theater> p_theaterList) {
		this.theaterList = p_theaterList;
	}

	/**
	 * @param p_deleteDisabled the deleteDisabled to set
	 */
	public void setDeleteDisabled(final boolean p_deleteDisabled) {
		this.deleteDisabled = p_deleteDisabled;
	}

}