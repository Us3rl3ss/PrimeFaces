package hr.primefaces.upload;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;

import org.primefaces.component.fileupload.FileUploadRenderer;

public class MyFileUploadRenderer extends FileUploadRenderer {

	@Override
	public void decode(final FacesContext p_context, final UIComponent p_component) {

		if (p_context.getExternalContext().getRequestContentType().toLowerCase().startsWith("multipart/")) {
			super.decode(p_context, p_component);
		}
	}

}