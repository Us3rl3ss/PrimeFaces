package hr.primefaces.util;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

public final class MessageUtil {

	private MessageUtil() {}

	/**
	 * info
	 * @param p_message
	 */
	public static void info(final String p_message) {
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", p_message));
	}

	/**
	 * warn
	 * @param p_message
	 */
	public static void warn(final String p_message) {
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Warning!", p_message));
	}

	/**
	 * error
	 * @param p_message
	 */
	public static void error(final String p_message) {
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", p_message));
	}

	/**
	 * fatal
	 * @param p_message
	 */
	public static void fatal(final String p_message) {
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Fatal!", p_message));
	}

}
