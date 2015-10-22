package hr.primefaces.bean;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean(name = "homeMB")
@ViewScoped
public class HomeView implements Serializable {

	private static final long serialVersionUID = 1L;

	@PostConstruct
	public void init() {
	}

}