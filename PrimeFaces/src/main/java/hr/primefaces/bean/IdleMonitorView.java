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
	
	public Integer IDLE_TIME = 300000;

	@ManagedProperty(value = "#{userSession}")
	UserSession userSession;
	
	@PostConstruct
	public void init(){}

	public void onIdle() throws IOException {
		
		userSession.logout();
//		redirectToStart();

		RequestContext.getCurrentInstance().execute("PF('idleDialog').show();");
		MessageUtil.warn("Zbog neaktivnosti, istekla Vam je sesija. Molimo Vas da se ponovno prijavite!");
	}
	
	public void redirectToStart() {
		
		InetAddress ip;
		try {
			ip = InetAddress.getLocalHost();

			FacesContext.getCurrentInstance().getExternalContext().redirect("http://" + ip.getHostAddress() + ":8080/PrimeFaces/");

		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void onActive() {
		MessageUtil.info("Dobrodošli natrag!");
	}

	public UserSession getUserSession() {
		return userSession;
	}

	public void setUserSession(UserSession userSession) {
		this.userSession = userSession;
	}

	public Integer getIDLE_TIME() {
		return IDLE_TIME;
	}

	public void setIDLE_TIME(Integer iDLE_TIME) {
		IDLE_TIME = iDLE_TIME;
	}
}