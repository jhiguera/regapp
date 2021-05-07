package cl.hda.regapp.web;

import javax.faces.component.UIComponent;
import javax.inject.Named;

import org.springframework.web.context.annotation.RequestScope;

@Named("bindingController")
@RequestScope
public class BindingController {
	
	UIComponent productoComponent;

	public UIComponent getProductoComponent() {
		return productoComponent;
	}

	public void setProductoComponent(UIComponent productoComponent) {
		this.productoComponent = productoComponent;
	}
	

}
