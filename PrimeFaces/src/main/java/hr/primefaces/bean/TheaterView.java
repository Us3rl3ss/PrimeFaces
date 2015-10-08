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

	@ManagedProperty(value = "#{TheaterService}")
	ITheaterService theaterService;

	private Theater theater = new Theater();
	
	private String initCenter = "45.795169, 15.907884";
	private String initZoom = "7";
	private MapModel mapModel = new DefaultMapModel();
	private String pretragaTekst;
	private Marker selectedMarker;
	private List<Theater> theaterList;
	
	public boolean deleteDisabled = true;

	@PostConstruct
	public void init() {
		
		setMarkers();
	}
	
	/**
	 * onRowSelect
	 */
	public void onRowSelect(SelectEvent event) {
        
		initCenter = theater.getLat() + ", " + theater.getLng();
		initZoom = "10";
    }
	
	/**
	 * setMarkers
	 */
	public void setMarkers() {
		
		 mapModel = new DefaultMapModel();
		 
		 theaterList = theaterService.getTheaters();

		for (Theater t: theaterList) {
			
			LatLng tempCoord = new LatLng(t.getLat(), t.getLng());
			mapModel.addOverlay(new Marker(tempCoord, t.getAddress()));
		}
	}

	/**
	 * pretrazi
	 */
	public void pretrazi() {
		
		if(pretragaTekst != null && !"".equals(pretragaTekst)) {
			
			setMarkers();
			
			Result locationResult = LocationCalculator.getLocationFromAddress(pretragaTekst);
			
			if (locationResult.getGeometry() != null) {
				
				Location location = locationResult.getGeometry().getLocation();
				LatLng coord = new LatLng(location.getLat(), location.getLng());
				mapModel.addOverlay(new Marker(coord, pretragaTekst));
				
				initCenter = coord.getLat() + ", " + coord.getLng();
				initZoom = "10";
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
	 * save
	 */
	public void save() {

		try {
			
			if (theaterService.getTheaterByLatLng(theater.getLat(), theater.getLng()) != null) {
				
				theaterService.updateTheater(theater);
				theater = new Theater();
				MessageUtil.info("Podaci su uspješno ažurirani!");

			} else {
				
				theaterService.addTheater(theater);
				theater = new Theater();
				MessageUtil.info("Podaci su uspješno spremljeni!");
			}
			
		} catch (HibernateException hex) {
			hex.printStackTrace();
			MessageUtil.error("Došlo je do hibernate greške!");
		} catch (Exception ex) {
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
			theaterService.deleteTheater(theater);
			MessageUtil.info("Kino je uspješno obrisano!");
		} catch (HibernateException hex) {
			hex.printStackTrace();
			MessageUtil.error("Došlo je do hibernate greške!");
		} catch (Exception ex) {
			ex.printStackTrace();
			MessageUtil.error("Došlo je do greške!");
		}
		
		setMarkers();
	}
	
	/**
	 * onMarkerSelect
	 * 
	 * @param event
	 */
	public void onMarkerSelect(OverlaySelectEvent event) {

		selectedMarker = (Marker) event.getOverlay();
		Double lat = selectedMarker.getLatlng().getLat();
		Double lng = selectedMarker.getLatlng().getLng();
		String title = selectedMarker.getTitle();

		theater = theaterService.getTheaterByLatLng(lat, lng);

		if (theater != null) {
			
			deleteDisabled = false;

		} else {
			
			theater = new Theater();
			theater.setAddress(title);
			theater.setLat(lat);
			theater.setLng(lng);
			
			deleteDisabled = true;
		}
		
		RequestContext.getCurrentInstance().execute("PF('theaterDialog').show();");
		RequestContext.getCurrentInstance().update("theaterDialog");
	}

	public ITheaterService getTheaterService() {
		return theaterService;
	}

	public void setTheaterService(ITheaterService theaterService) {
		this.theaterService = theaterService;
	}

	public Theater getTheater() {
		return theater;
	}

	public void setTheater(Theater theater) {
		this.theater = theater;
	}

	public String getInitCenter() {
		return initCenter;
	}

	public void setInitCenter(String initCenter) {
		this.initCenter = initCenter;
	}

	public String getInitZoom() {
		return initZoom;
	}

	public void setInitZoom(String initZoom) {
		this.initZoom = initZoom;
	}

	public MapModel getMapModel() {
		return mapModel;
	}

	public void setMapModel(MapModel mapModel) {
		this.mapModel = mapModel;
	}

	public String getPretragaTekst() {
		return pretragaTekst;
	}

	public void setPretragaTekst(String pretragaTekst) {
		this.pretragaTekst = pretragaTekst;
	}

	public Marker getSelectedMarker() {
		return selectedMarker;
	}

	public void setSelectedMarker(Marker selectedMarker) {
		this.selectedMarker = selectedMarker;
	}

	public boolean isDeleteDisabled() {
		return deleteDisabled;
	}

	public void setDeleteDisabled(boolean deleteDisabled) {
		this.deleteDisabled = deleteDisabled;
	}

	public List<Theater> getTheaterList() {
		return theaterList;
	}

	public void setTheaterList(List<Theater> theaterList) {
		this.theaterList = theaterList;
	}

}