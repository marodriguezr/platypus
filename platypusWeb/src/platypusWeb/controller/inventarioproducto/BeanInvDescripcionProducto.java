package platypusWeb.controller.inventarioproducto;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import platypusEJB.model.core.entities.InvDescripcionProducto;
import platypusEJB.model.core.entities.InvProducto;
import platypusEJB.model.core.entities.InvTipoProducto;
import platypusEJB.model.inventarioproductos.managers.ManagerInvDescripcionProducto;
import platypusEJB.model.inventarioproductos.managers.ManagerInvTipoProducto;
import platypusEJB.model.inventarioproductos.managers.ManagerInventarioProductos;
import platypusWeb.controller.utilities.JSFUtil;

@Named
@SessionScoped
public class BeanInvDescripcionProducto implements Serializable {
	@EJB
	private ManagerInvDescripcionProducto managerInvDescripcionProducto;
	
	@EJB
	private ManagerInvTipoProducto managerInvTipoProducto;
	
	
	private List<InvDescripcionProducto> listaInvDescripcionProducto;
	private InvDescripcionProducto nuevoInvDescripcionProducto;
	private InvDescripcionProducto edicionInvDescripcionProducto;
	private InvTipoProducto invTipoProducto;
	private int idInvTipoProducto;
	private List<InvTipoProducto> listaInvTipoProducto;

	public BeanInvDescripcionProducto() {
		// TODO Auto-generated constructor stub
		
	}
	
	public String actionMenuInvDescripcionProducto() {
		listaInvDescripcionProducto=managerInvDescripcionProducto.findAllInvDescripcionProductos();
		listaInvTipoProducto=managerInvTipoProducto.findAllInvTipoProductos();
		return "productos";
	}
	
	public String actionMenuNuevoInvDescripcionProducto() {
		nuevoInvDescripcionProducto=new InvDescripcionProducto();
		
		return "productos_nuevo";
	}
	
	public void actionListenerInsertarNuevoProducto() {
		try {
			managerInvDescripcionProducto.insertarInvDescripcionProducto(nuevoInvDescripcionProducto, 
					idInvTipoProducto);
			listaInvDescripcionProducto=managerInvDescripcionProducto.findAllInvDescripcionProductos();
			nuevoInvDescripcionProducto=new InvDescripcionProducto();
			JSFUtil.crearMensajeINFO("Producto insertado.");
		} catch (Exception e) {
			JSFUtil.crearMensajeERROR(e.getMessage());
			e.printStackTrace();
		}
	}
	public String actionSeleccionarEdicionInvDescripcionProducto( InvDescripcionProducto invDescripcionProductos) {
		edicionInvDescripcionProducto=invDescripcionProductos;
		idInvTipoProducto=edicionInvDescripcionProducto.getId();
		return "productos_edicion";
	}
	
	public void actionListenerActualizarEdicionInvDescripcionProductos() {
		try {
			managerInvDescripcionProducto.actualizarInvDescripcionProducto(edicionInvDescripcionProducto,idInvTipoProducto);
			listaInvDescripcionProducto=managerInvDescripcionProducto.findAllInvDescripcionProductos();
			JSFUtil.crearMensajeINFO("Producto actualizado.");
		} catch (Exception e) {
			JSFUtil.crearMensajeERROR(e.getMessage());
			e.printStackTrace();
		}
	}
	public void actionListenerEliminarInvDescripcionProducto(int idInvDescripcionProducto) {
		try {
			managerInvDescripcionProducto.eliminarInvDescripcionProducto(idInvDescripcionProducto);
			listaInvDescripcionProducto=managerInvDescripcionProducto.findAllInvDescripcionProductos();
			JSFUtil.crearMensajeINFO("Producto eliminado.");
		} catch (Exception e) {
			JSFUtil.crearMensajeERROR(e.getMessage());
			e.printStackTrace();
		}
	}

	public ManagerInvDescripcionProducto getManagerInvDescripcionProducto() {
		return managerInvDescripcionProducto;
	}

	public void setManagerInvDescripcionProducto(ManagerInvDescripcionProducto managerInvDescripcionProducto) {
		this.managerInvDescripcionProducto = managerInvDescripcionProducto;
	}

	public List<InvDescripcionProducto> getListaInvDescripcionProducto() {
		return listaInvDescripcionProducto;
	}

	public void setListaInvDescripcionProducto(List<InvDescripcionProducto> listaInvDescripcionProducto) {
		this.listaInvDescripcionProducto = listaInvDescripcionProducto;
	}

	public InvDescripcionProducto getNuevoInvDescripcionProducto() {
		return nuevoInvDescripcionProducto;
	}

	public void setNuevoInvDescripcionProducto(InvDescripcionProducto nuevoInvDescripcionProducto) {
		this.nuevoInvDescripcionProducto = nuevoInvDescripcionProducto;
	}

	public InvDescripcionProducto getEdicionInvDescripcionProducto() {
		return edicionInvDescripcionProducto;
	}

	public void setEdicionInvDescripcionProducto(InvDescripcionProducto edicionInvDescripcionProducto) {
		this.edicionInvDescripcionProducto = edicionInvDescripcionProducto;
	}

	public InvTipoProducto getInvTipoProducto() {
		return invTipoProducto;
	}

	public void setInvTipoProducto(InvTipoProducto invTipoProducto) {
		this.invTipoProducto = invTipoProducto;
	}

	public int getIdInvTipoProducto() {
		return idInvTipoProducto;
	}

	public void setIdInvTipoProducto(int idInvTipoProducto) {
		this.idInvTipoProducto = idInvTipoProducto;
	}

	public List<InvTipoProducto> getListaInvTipoProducto() {
		return listaInvTipoProducto;
	}

	public void setListaInvTipoProducto(List<InvTipoProducto> listaInvTipoProducto) {
		this.listaInvTipoProducto = listaInvTipoProducto;
	}

	

	
	

}
