package hr.primefaces.bean;


/*
 * Imports
 */

import java.io.Serializable;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

@ManagedBean(name = "navigationController")
@ApplicationScoped
public class NavigationControllerBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	
	/*
	 * Private Attributes
	 */

	private String page = "../home";
	private int activeParam = 0;
	
	
	/*
	 * Top navigation
	 */
	
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
	

	public String doViewFriends() {
		this.page = "friends?faces-redirect=true";
		this.activeParam = 3;
		return page;
	}
	
	public String doViewMyMovies() {
		this.page = "myMovies?faces-redirect=true";
		this.activeParam = 4;
		return page;
	}
	
	public String doViewMyFriends() {
		this.page = "myFriends?faces-redirect=true";
		this.activeParam = 5;
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
	
	public String doViewAboutUs() {
		this.page = "aboutUs?faces-redirect=true";
		this.activeParam = 8;
		return page;
	}
	
	
	/*
	 * Side navigation
	 */
	
	public String doViewTheater() {
		this.page = "theater?faces-redirect=true";
		return page;
	}
	
	public String doViewCinema() {
		this.page = "cinema?faces-redirect=true";
		return page;
	}
	
	public String doViewGetProjection() {
		this.page = "getProjection?faces-redirect=true";
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

	
	/*
	 * Other navigation
	 */
	
	public String doViewLogin() {
		this.page = "login?faces-redirect=true";
		return page;
	}
	
	public String doViewReserveSeats() {
		this.page = "reserveSeats?faces-redirect=true";
		return page;
	}
	
	
	/*
	 * Getters and Setters
	 */
	
	public String getPage() {
		return page;
	}

	public void setPage(String page) {
		this.page = page;
	}

	public int getActiveParam() {
		return activeParam;
	}

	public void setActiveParam(int activeParam) {
		this.activeParam = activeParam;
	}
	
}