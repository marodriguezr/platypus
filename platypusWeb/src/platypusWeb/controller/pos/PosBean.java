package platypusWeb.controller.pos;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import org.primefaces.model.DualListModel;

import platypusEJB.model.core.entities.InvProducto;
import platypusEJB.model.inventarioproductos.managers.ManagerInventarioProductos;

@SuppressWarnings("serial")
@Named
@SessionScoped
public class PosBean implements Serializable {

	@EJB
	private ManagerInventarioProductos managerProducto;

	private List<InvProducto> productosDisponibles;
	private List<InvProducto> productosSeleccionados;
	
	private DualListModel<InvProducto> productos;

	public PosBean() {
		// TODO Auto-generated constructor stub
	}

	public void initProductosDisponibles() {
		productosDisponibles = managerProducto.findAllInvProductos();
	}
	
	public void initProductosSeleccionados() {
		productosSeleccionados = new ArrayList<>();
	}
	
	public void initProductos() {
		productos = new DualListModel<>(productosDisponibles, productosSeleccionados);
	}

	public String actionOpenRegistrarVentaSeleccionProductosView() {
		initProductosDisponibles();
		initProductosSeleccionados();
		initProductos();
		return "registrarVentaSeleccionProductos?faces-redirect=true";
	}
	
	public String actionOpenRegistrarVentaInformacionView() {
		return "registrarVentaInformacion?faces-redirect=true";
	}
	
	public String actionOpenRegistrarVentaPagoView() {
		return "registrarVentaPago?faces-redirect=true";
	}
	
	public String actionOpenRegistrarVentaConfirmacionView() {
		return "registrarVentaConfirmacion?faces-redirect=true";
	}

	public List<InvProducto> getProductosDisponibles() {
		return productosDisponibles;
	}

	public void setProductosDisponibles(List<InvProducto> productosDisponibles) {
		this.productosDisponibles = productosDisponibles;
	}

	public List<InvProducto> getProductosSeleccionados() {
		return productosSeleccionados;
	}

	public void setProductosSeleccionados(List<InvProducto> productosSeleccionados) {
		this.productosSeleccionados = productosSeleccionados;
	}

	public DualListModel<InvProducto> getProductos() {
		return productos;
	}

	public void setProductos(DualListModel<InvProducto> productos) {
		this.productos = productos;
	}
}
