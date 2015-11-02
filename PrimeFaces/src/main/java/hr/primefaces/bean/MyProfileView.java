package hr.primefaces.bean;

import hr.primefaces.model.User;
import hr.primefaces.model.UserFavoriteMovie;
import hr.primefaces.model.UserMovieRate;
import hr.primefaces.model.UserMovieReview;
import hr.primefaces.service.IMovieService;
import hr.primefaces.service.IUserService;
import hr.primefaces.util.MessageUtil;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.apache.poi.util.IOUtils;
import org.hibernate.HibernateException;
import org.primefaces.context.RequestContext;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

@ManagedBean(name = "myProfileMB")
@ViewScoped
public class MyProfileView implements Serializable {

	private static final long serialVersionUID = 1L;

	private static final String REFRESH_PAGE_JS = "$(window).off('beforeunload'); location.reload();";
//	private static final String PLACEHOLDER_IMG = "images/placeholder.png";

	@ManagedProperty(value = "#{userSession}")
	private UserSession userSession;

	@ManagedProperty(value = "#{UserService}")
	private IUserService userService;

	@ManagedProperty(value = "#{MovieService}")
	private IMovieService movieService;

	private User user;
	private List<UserMovieRate> userMovieRateList;
	private List<UserMovieReview> userMovieReviewList;
	private List<UserFavoriteMovie> userFavoriteMovieList;
	private UserMovieRate selectedRate;
	private UserMovieReview selectedReview;
	private UserFavoriteMovie selectedFavorite;
	private Integer averageRate;
	private String uploadedFileNames;
//	private String filename;

	@PostConstruct
	public void init() {

		setAverageRate(0);
		setUploadedFileNames("");
		setUser(getUserSession().getUser());
		setUserMovieRateList(getUserService().getUserMovieRateByUser(getUser()));
		setUserMovieReviewList(getUserService().getUserMovieReviewByUser(getUser()));
		setUserFavoriteMovieList(getUserService().getUserFavoriteMovieByUser(getUser()));

//		final ServletContext servletContext = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
//		setTempCamPicture(PLACEHOLDER_IMG);
	}

	/**
	 * save
	 */
	public void save() {

		try {
			getUser().setUpdated(new Date());
			getUserService().updateUser(getUser());
			MessageUtil.info("Podaci uspješno spremljeni!");
			refreshUserData();
		}
		catch (HibernateException hex) {
			hex.printStackTrace();
			MessageUtil.error("Došlo je do hibernate greške!");
		}
		catch (Exception ex) {
			ex.printStackTrace();
			MessageUtil.error("Došlo je do greške!");
		}
	}

	/**
	 * refreshUserData
	 */
	private void refreshUserData() {

		setUser(getUserService().getUserById(getUser().getId()));
		RequestContext.getCurrentInstance().execute(REFRESH_PAGE_JS);
	}

	/**
	 * isInFollowList
	 * @param p_loggedUser
	 * @param p_currUser
	 * @return
	 */
	public boolean isInFollowList(final User p_loggedUser, final User p_currUser) {

		boolean result = false;

		if (getUserService().getUserFollow(p_loggedUser, p_currUser).size() > 0) {

			result = true;
		}

		return result;
	}

	/**
	 * calculateAverageRate
	 */
	public void calculateAverageRate() {

		int avgRate = 0;

		final Double avg = getUserService().getAverageRateByMovie(getSelectedRate().getMovie());

		try {

			if (avg != null) {
				avgRate = avg.intValue();
			}
		}
		catch (NumberFormatException nex) {
			nex.printStackTrace();
		}
		catch (Exception ex) {
			ex.printStackTrace();
		}

		setAverageRate(avgRate);
	}

	/**
	 * handleFileUpload
	 */
	public void handleFileUpload(final FileUploadEvent p_event) {

		UploadedFile file;
		byte[] byteData = null;

		file = p_event.getFile();
		try {
			byteData = IOUtils.toByteArray(file.getInputstream());
		}
		catch (Exception e) {
			e.printStackTrace();
		}

		if (byteData != null) {

			getUser().setImage(byteData);
			setUploadedFileNames(getUploadedFileNames() + file.getFileName());
		}
	}

//	/**
//	 * oncapture
//	 * @param p_captureEvent
//	 */
//	public void oncapture(final CaptureEvent p_captureEvent) {
//
//		setFilename(getRandomImageName());
//
//        final byte[] data = p_captureEvent.getData();
//
//        final ServletContext servletContext = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
//        final String newFileName = servletContext.getRealPath("") + File.separator + "resources" +
//                                    File.separator + "images" + File.separator + filename + ".jpeg";
//
//        FileImageOutputStream imageOutput;
//        try {
//            imageOutput = new FileImageOutputStream(new File(newFileName));
//            imageOutput.write(data, 0, data.length);
//            imageOutput.close();
//
//        }
//        catch(IOException e) {
//            throw new FacesException("Error in writing captured image.", e);
//        }
//
//        RequestContext.getCurrentInstance().update(":camForm:photo");
//    }
//
//	/**
//	 * getRandomImageName
//	 * @return
//	 */
//	private String getRandomImageName() {
//
//		final int i = (int) (Math.random() * 10000000);
//        return String.valueOf(i);
//    }

	/**
	 * ################# GETTERS AND SETTERS #################
	 */

	/**
	 * @return the userSession
	 */
	public UserSession getUserSession() {
		return userSession;
	}

	/**
	 * @return the userService
	 */
	public IUserService getUserService() {
		return userService;
	}

	/**
	 * @return the movieService
	 */
	public IMovieService getMovieService() {
		return movieService;
	}

	/**
	 * @return the user
	 */
	public User getUser() {
		return user;
	}

	/**
	 * @return the userMovieRateList
	 */
	public List<UserMovieRate> getUserMovieRateList() {
		return userMovieRateList;
	}

	/**
	 * @return the userMovieReviewList
	 */
	public List<UserMovieReview> getUserMovieReviewList() {
		return userMovieReviewList;
	}

	/**
	 * @return the userFavoriteMovieList
	 */
	public List<UserFavoriteMovie> getUserFavoriteMovieList() {
		return userFavoriteMovieList;
	}

	/**
	 * @return the selectedRate
	 */
	public UserMovieRate getSelectedRate() {
		return selectedRate;
	}

	/**
	 * @return the selectedReview
	 */
	public UserMovieReview getSelectedReview() {
		return selectedReview;
	}

	/**
	 * @return the selectedFavorite
	 */
	public UserFavoriteMovie getSelectedFavorite() {
		return selectedFavorite;
	}

	/**
	 * @return the averageRate
	 */
	public Integer getAverageRate() {
		return averageRate;
	}

	/**
	 * @return the uploadedFileNames
	 */
	public String getUploadedFileNames() {
		return uploadedFileNames;
	}

//	/**
//	 * @return the filename
//	 */
//	public String getFilename() {
//		return filename;
//	}

	/**
	 * @param p_userSession the userSession to set
	 */
	public void setUserSession(final UserSession p_userSession) {
		this.userSession = p_userSession;
	}

	/**
	 * @param p_userService the userService to set
	 */
	public void setUserService(final IUserService p_userService) {
		this.userService = p_userService;
	}

	/**
	 * @param p_movieService the movieService to set
	 */
	public void setMovieService(final IMovieService p_movieService) {
		this.movieService = p_movieService;
	}

	/**
	 * @param p_user the user to set
	 */
	public void setUser(final User p_user) {
		this.user = p_user;
	}

	/**
	 * @param p_userMovieRateList the userMovieRateList to set
	 */
	public void setUserMovieRateList(final List<UserMovieRate> p_userMovieRateList) {
		this.userMovieRateList = p_userMovieRateList;
	}

	/**
	 * @param p_userMovieReviewList the userMovieReviewList to set
	 */
	public void setUserMovieReviewList(final List<UserMovieReview> p_userMovieReviewList) {
		this.userMovieReviewList = p_userMovieReviewList;
	}

	/**
	 * @param p_userFavoriteMovieList the userFavoriteMovieList to set
	 */
	public void setUserFavoriteMovieList(final List<UserFavoriteMovie> p_userFavoriteMovieList) {
		this.userFavoriteMovieList = p_userFavoriteMovieList;
	}

	/**
	 * @param p_selectedRate the selectedRate to set
	 */
	public void setSelectedRate(final UserMovieRate p_selectedRate) {
		this.selectedRate = p_selectedRate;
	}

	/**
	 * @param p_selectedReview the selectedReview to set
	 */
	public void setSelectedReview(final UserMovieReview p_selectedReview) {
		this.selectedReview = p_selectedReview;
	}

	/**
	 * @param p_selectedFavorite the selectedFavorite to set
	 */
	public void setSelectedFavorite(final UserFavoriteMovie p_selectedFavorite) {
		this.selectedFavorite = p_selectedFavorite;
	}

	/**
	 * @param p_averageRate the averageRate to set
	 */
	public void setAverageRate(final Integer p_averageRate) {
		this.averageRate = p_averageRate;
	}

	/**
	 * @param p_uploadedFileNames the uploadedFileNames to set
	 */
	public void setUploadedFileNames(final String p_uploadedFileNames) {
		this.uploadedFileNames = p_uploadedFileNames;
	}

//	/**
//	 * @param p_filename the filename to set
//	 */
//	public void setFilename(final String p_filename) {
//		this.filename = p_filename;
//	}

}