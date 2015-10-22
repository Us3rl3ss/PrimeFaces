package hr.primefaces.bean;

import java.io.Serializable;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

@ManagedBean(name = "navigationController")
@ApplicationScoped
public class NavigationControllerBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private String page = "../home";
	private int activeParam = 0;

	public String doViewHome() {
		this.page = "home?faces-redirect=true";
		this.activeParam = 0;
		return page;
	}

	public String doViewProjection() {
		this.page = "projection?faces-redirect=true";
		this.activeParam = 1;
		return page;
	}

	public String doViewMovie() {
		this.page = "movie?faces-redirect=true";
		this.activeParam = 2;
		return page;
	}

	public String doViewUsers() {
		this.page = "users?faces-redirect=true";
		this.activeParam = 3;
		return page;
	}

	public String doViewMyProfile() {
		this.page = "myProfile?faces-redirect=true";
		this.activeParam = 6;
		return page;
	}

	public String doViewContact() {
		this.page = "contact?faces-redirect=true";
		this.activeParam = 7;
		return page;
	}

	public String doViewTheater() {
		this.page = "theater?faces-redirect=true";
		return page;
	}

	public String doViewCinema() {
		this.page = "cinema?faces-redirect=true";
		return page;
	}

	public String doViewAddProjection() {
		this.page = "addProjection?faces-redirect=true";
		return page;
	}

	public String doViewGetMovie() {
		this.page = "getMovie?faces-redirect=true";
		return page;
	}

	public String doViewAddMovie() {
		this.page = "addMovie?faces-redirect=true";
		return page;
	}

	public String doViewGetGenre() {
		this.page = "getGenre?faces-redirect=true";
		return page;
	}

	public String doViewAddGenre() {
		this.page = "addGenre?faces-redirect=true";
		return page;
	}

	public String doViewGetActor() {
		this.page = "getActor?faces-redirect=true";
		return page;
	}

	public String doViewAddActor() {
		this.page = "addActor?faces-redirect=true";
		return page;
	}

	public String doViewListOfUsers() {
		this.page = "getUser?faces-redirect=true";
		return page;
	}

	public String doViewLogin() {
		this.page = "login?faces-redirect=true";
		return page;
	}

	public String doViewReserveSeats() {
		this.page = "reserveSeats?faces-redirect=true";
		return page;
	}

	/**
	 * ################# GETTERS AND SETTERS #################
	 */

	/**
	 * @return the page
	 */
	public String getPage() {
		return page;
	}

	/**
	 * @return the activeParam
	 */
	public int getActiveParam() {
		return activeParam;
	}

	/**
	 * @param p_page the page to set
	 */
	public void setPage(final String p_page) {
		this.page = p_page;
	}

	/**
	 * @param p_activeParam the activeParam to set
	 */
	public void setActiveParam(final int p_activeParam) {
		this.activeParam = p_activeParam;
	}

}