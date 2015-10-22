package hr.primefaces.bean;

import hr.primefaces.util.MessageUtil;

import java.io.IOException;
import java.io.Serializable;
import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.primefaces.context.RequestContext;

@ManagedBean(name = "idleMonitorMB")
@SessionScoped
public class IdleMonitorView implements Serializable {

	private static final long serialVersionUID = 1L;

	private static final Integer IDLE_TIME = 300000;
	private static final String IDLE_DIALOG_JS = "PF('idleDialog').show();";

	@ManagedProperty(value = "#{userSession}")
	private UserSession userSession;

	private Integer idleTime;

	@PostConstruct
	public void init() {

		setIdleTime(IDLE_TIME);
	}

	public void onIdle() throws IOException {

		getUserSession().logout();
		// redirectToStart();

		RequestContext.getCurrentInstance().execute(IDLE_DIALOG_JS);
		MessageUtil.warn("Zbog neaktivnosti, istekla Vam je sesija. Molimo Vas da se ponovno prijavite!");
	}

	/**
	 * redirectToStart
	 */
	public void redirectToStart() {

		InetAddress ip;
		try {
			ip = InetAddress.getLocalHost();
			FacesContext.getCurrentInstance().getExternalContext().redirect("http://" + ip.getHostAddress() + ":8080/PrimeFaces/");
		}
		catch (UnknownHostException e) {
			e.printStackTrace();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * onActive
	 */
	public void onActive() {
		MessageUtil.info("Dobrodošli natrag!");
	}

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
	 * @return the idleTime
	 */
	public Integer getIdleTime() {
		return idleTime;
	}

	/**
	 * @param p_userSession the userSession to set
	 */
	public void setUserSession(final UserSession p_userSession) {
		this.userSession = p_userSession;
	}

	/**
	 * @param p_idleTime the idleTime to set
	 */
	public void setIdleTime(final Integer p_idleTime) {
		this.idleTime = p_idleTime;
	}

}