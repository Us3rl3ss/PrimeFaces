package hr.primefaces.bean;

import hr.primefaces.model.Theater;
import hr.primefaces.service.ITheaterService;
import hr.primefaces.util.MessageUtil;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.hibernate.HibernateException;

@ManagedBean(name = "addTheaterMB")
@ViewScoped
public class AddTheaterView implements Serializable {

	private static final long serialVersionUID = 1L;

	@ManagedProperty(value = "#{TheaterService}")
	ITheaterService theaterService;

	private Theater theater = new Theater();

	@PostConstruct
	public void init() {
	}

	/**
	 * spremi
	 */
	public void spremi() {

		try {
			theaterService.addTheater(theater);
			theater = new Theater();
			MessageUtil.info("Podaci uspješno spremljeni!");
		} catch (HibernateException hex) {
			hex.printStackTrace();
			MessageUtil.error("Došlo je do hibernate greške!");
		} catch (Exception ex) {
			ex.printStackTrace();
			MessageUtil.error("Došlo je do greške!");
		}
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

}