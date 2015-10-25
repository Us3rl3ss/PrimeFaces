package hr.primefaces.bean;

import hr.primefaces.imdb.ImdbAPI;
import hr.primefaces.imdb.ImdbJsonModel;
import hr.primefaces.omdb.OmdbAPI;
import hr.primefaces.omdb.OmdbJsonModel;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.AjaxBehaviorEvent;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

@ManagedBean(name = "homeMB")
@ViewScoped
public class HomeView implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * OMDB:
	 */
	private String input;
	private String imageUrl = "";
	private StreamedContent picture = new DefaultStreamedContent();
	private OmdbJsonModel omdb = new OmdbJsonModel();

	/**
	 * IMDB:
	 */

	private ImdbJsonModel imdbMovie = new ImdbJsonModel();

	@PostConstruct
	public void init() {
	}

	public List<ImdbJsonModel> searchImdb(String p_input){
		return ImdbAPI.getMovieFromImdbByName(p_input);
	}

	/**
	 * onItemSelect
	 * @param p_event
	 */
	public void onItemSelect(final AjaxBehaviorEvent p_event) {
		setImdbMovie(ImdbAPI.getMovieFromImdbById(getImdbMovie().getIdIMDB()));
	}

	public void searchOmdb(){

		setOmdb(OmdbAPI.getMovieFromOmdb(getInput()));
//		final String pic = movie.getPoster();

//		setImageUrl(pic);

//		URL url;
//		try {
//			url = new URL(pic);
//			final InputStream is = url.openStream();
//			final byte[] byteData = IOUtils.toByteArray(is);
//			setPicture(new DefaultStreamedContent(new ByteArrayInputStream(byteData)));
//		}
//		catch (MalformedURLException e) {
//			e.printStackTrace();
//		}
//		catch (IOException e) {
//			e.printStackTrace();
//		}
	}

	/**
	 * @return the input
	 */
	public String getInput() {
		return input;
	}

	/**
	 * @param p_input the input to set
	 */
	public void setInput(final String p_input) {
		this.input = p_input;
	}

	/**
	 * @return the picture
	 */
	public StreamedContent  getPicture() {
		return picture;
	}

	/**
	 * @param p_picture the picture to set
	 */
	public void setPicture(final StreamedContent  p_picture) {
		this.picture = p_picture;
	}

	/**
	 * @return the imageUrl
	 */
	public String getImageUrl() {
		return imageUrl;
	}

	/**
	 * @param p_imageUrl the imageUrl to set
	 */
	public void setImageUrl(final String p_imageUrl) {
		this.imageUrl = p_imageUrl;
	}

	/**
	 * @return the omdb
	 */
	public OmdbJsonModel getOmdb() {
		return omdb;
	}

	/**
	 * @param p_omdb the omdb to set
	 */
	public void setOmdb(final OmdbJsonModel p_omdb) {
		this.omdb = p_omdb;
	}

	/**
	 * @return the imdbMovie
	 */
	public ImdbJsonModel getImdbMovie() {
		return imdbMovie;
	}

	/**
	 * @param p_imdbMovie the imdbMovie to set
	 */
	public void setImdbMovie(final ImdbJsonModel p_imdbMovie) {
		this.imdbMovie = p_imdbMovie;
	}

}