package platypusWeb.controller.inventarioproducto;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import platypusEJB.model.core.entities.InvTipoProducto;
import platypusEJB.model.inventarioproductos.managers.ManagerInvTipoProducto;
import platypusWeb.controller.utilities.JSFUtil;

@Named
@SessionScoped
public class BeanInvTipoProductos implements Serializable {
	@EJB
	private ManagerInvTipoProducto managerInvTipoProducto;
	
	
	private List<InvTipoProducto> listaInvTipoProducto;
	private InvTipoProducto nuevoInvTipoProducto;
	private InvTipoProducto edicionInvTipoProducto;

	public BeanInvTipoProductos() {
		// TODO Auto-generated constructor stub
	}
	
	public String actionMenuInvTipoProducto() {
		listaInvTipoProducto=managerInvTipoProducto.findAllInvTipoProductos();
		return "tipo_producto";
	}
	
	public String actionMenuNuevoInvTipoProducto() {
		nuevoInvTipoProducto=new InvTipoProducto();
		return "tipo_producto_nuevo";
	}
	
	public void actionListenerInsertarNuevoInvTipoProducto() {
		try {
			managerInvTipoProducto.insertarInvTipoProducto(nuevoInvTipoProducto);
			listaInvTipoProducto=managerInvTipoProducto.findAllInvTipoProductos();
			nuevoInvTipoProducto=new InvTipoProducto();
			JSFUtil.crearMensajeINFO("Tipo de producto insertado.");
		} catch (Exception e) {
			JSFUtil.crearMensajeERROR(e.getMessage());
			e.printStackTrace();
		}
	}
	public String actionSeleccionarEdicionInvTipoProducto( InvTipoProducto invTipoProductos) {
		edicionInvTipoProducto=invTipoProductos;
		return "tipo_producto_edicion";
	}
	
	public void actionListenerActualizarEdicionInvTipoProductos() {
		try {
			managerInvTipoProducto.actualizarInvTipoProducto(edicionInvTipoProducto);
			listaInvTipoProducto=managerInvTipoProducto.findAllInvTipoProductos();
			JSFUtil.crearMensajeINFO("Tipo de producto actualizado.");
		} catch (Exception e) {
			JSFUtil.crearMensajeERROR(e.getMessage());
			e.printStackTrace();
		}
	}
	public void actionListenerEliminarInvTipoProducto(int idInvTipoProducto) {
		try {
			managerInvTipoProducto.eliminarInvTipoProducto(idInvTipoProducto);
			listaInvTipoProducto=managerInvTipoProducto.findAllInvTipoProductos();
			JSFUtil.crearMensajeINFO("Tipo de producto eliminado.");
		} catch (Exception e) {
			JSFUtil.crearMensajeERROR(e.getMessage());
			e.printStackTrace();
		}
	}

	public ManagerInvTipoProducto getManagerInvTipoProducto() {
		return managerInvTipoProducto;
	}

	public void setManagerInvTipoProducto(ManagerInvTipoProducto managerInvTipoProducto) {
		this.managerInvTipoProducto = managerInvTipoProducto;
	}

	public List<InvTipoProducto> getListaInvTipoProducto() {
		return listaInvTipoProducto;
	}

	public void setListaInvTipoProducto(List<InvTipoProducto> listaInvTipoProducto) {
		this.listaInvTipoProducto = listaInvTipoProducto;
	}

	public InvTipoProducto getNuevoInvTipoProducto() {
		return nuevoInvTipoProducto;
	}

	public void setNuevoInvTipoProducto(InvTipoProducto nuevoInvTipoProducto) {
		this.nuevoInvTipoProducto = nuevoInvTipoProducto;
	}

	public InvTipoProducto getEdicionInvTipoProducto() {
		return edicionInvTipoProducto;
	}

	public void setEdicionInvTipoProducto(InvTipoProducto edicionInvTipoProducto) {
		this.edicionInvTipoProducto = edicionInvTipoProducto;
	}

	
	

}
