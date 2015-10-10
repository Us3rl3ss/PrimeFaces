package hr.primefaces.bean;

import hr.primefaces.model.Cinema;
import hr.primefaces.model.Theater;
import hr.primefaces.service.ICinemaService;
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
	ITheaterService theaterService;

	@ManagedProperty(value = "#{CinemaService}")
	ICinemaService cinemaService;

	private Theater theater = new Theater();
	
	private String initCenter = "45.795169, 15.907884";
	private String initZoom = "7";
	private MapModel mapModel = new DefaultMapModel();
	private List<Theater> theaterList;
	
	@PostConstruct
	public void init() {
		
		setMarkers();
	}
	
	/**
	 * onRowSelect
	 */
	public void onRowSelect(SelectEvent event) {
        
		initCenter = theater.getLat() + ", " + theater.getLng();
		initZoom = "14";
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
	
	public String calcBrojDvorana(Theater theater) {
		
		List<Cinema> cinemaList = cinemaService.getCinemaByTheater(theater);
		return cinemaList.size() + "";
	}
	
	public String calcBrojSjedećihMjesta(Theater theater) {
		
		int brojSjedecihMjesta = 0;
		
		List<Cinema> cinemaList = cinemaService.getCinemaByTheater(theater);
		
		for(Cinema c : cinemaList) {
			
			brojSjedecihMjesta += c.getNumber_of_rows() * c.getNumber_of_seats_in_row();
		}
		
		return brojSjedecihMjesta + "";
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

	public List<Theater> getTheaterList() {
		return theaterList;
	}

	public void setTheaterList(List<Theater> theaterList) {
		this.theaterList = theaterList;
	}

	public ICinemaService getCinemaService() {
		return cinemaService;
	}

	public void setCinemaService(ICinemaService cinemaService) {
		this.cinemaService = cinemaService;
	}

}