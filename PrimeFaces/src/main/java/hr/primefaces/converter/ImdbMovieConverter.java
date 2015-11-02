package hr.primefaces.converter;

import hr.primefaces.imdb.ImdbAPI;
import hr.primefaces.imdb.ImdbJsonModel;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;

@ManagedBean
@SessionScoped
public class ImdbMovieConverter implements Converter {

	private List<ImdbJsonModel> imdbMovieList;

	/**
	 * getAsObject
	 */
	public Object getAsObject(final FacesContext p_fc, final UIComponent p_uic, final String p_value) {

		ImdbJsonModel result = null;
		if (p_value != null && p_value.trim().length() > 0) {

			try {

				if (getImdbMovieList().size() > 0) {

					for (ImdbJsonModel im : getImdbMovieList()) {

						if (p_value.equals(im.getIdIMDB())) {
							result = im;
						}
					}
				}

				if (result == null) {
					result = ImdbAPI.getMovieFromImdbById(p_value);
				}

				setImdbMovieList(new ArrayList<ImdbJsonModel>());

				return result;
			}
			catch (NumberFormatException e) {
				throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Conversion Error", "Not a valid object."));
			}
		}
		else {
			return null;
		}
	}

	/**
	 * getAsString
	 */
	public String getAsString(final FacesContext p_fc, final UIComponent p_uic, final Object p_object) {

		if (getImdbMovieList() == null) {
			setImdbMovieList(new ArrayList<ImdbJsonModel>());
		}

		if (p_object != null) {

			getImdbMovieList().add((ImdbJsonModel) p_object);
			return String.valueOf(((ImdbJsonModel) p_object).getIdIMDB());
		}
		else {
			return null;
		}
	}

	/**
	 * @return the imdbMovieList
	 */
	public List<ImdbJsonModel> getImdbMovieList() {
		return imdbMovieList;
	}

	/**
	 * @param p_imdbMovieList the imdbMovieList to set
	 */
	public void setImdbMovieList(final List<ImdbJsonModel> p_imdbMovieList) {
		this.imdbMovieList = p_imdbMovieList;
	}

}
