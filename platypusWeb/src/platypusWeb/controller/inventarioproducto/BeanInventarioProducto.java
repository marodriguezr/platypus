package platypusWeb.controller.inventarioproducto;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;


import platypusEJB.model.core.entities.InvDescripcionProducto;
import platypusEJB.model.core.entities.InvProducto;
import platypusEJB.model.inventarioproductos.managers.ManagerInvDescripcionProducto;
import platypusEJB.model.inventarioproductos.managers.ManagerInventarioProductos;

import platypusWeb.controller.utilities.JSFUtil;



@Named
@SessionScoped
public class BeanInventarioProducto implements Serializable {
	@EJB
	private ManagerInventarioProductos managerInvProducto;
	
	@EJB
	private ManagerInvDescripcionProducto managerInvDescripcionProducto;
	
	private List<InvProducto> listaInvProducto;
	private InvProducto nuevoInvProducto;
	private InvProducto edicionInvProducto;
	private InvDescripcionProducto invDescripcionProducto;
	private int idinvDescProdcuto;
	
	private int idAdminrec_Recepciones;
	
	private List<InvDescripcionProducto> listaInvDescripcionProducto;
	
	
	public BeanInventarioProducto() {
		// TODO Auto-generated constructor stub
		
	}
	
	public String actionMenuInvProducto() {
		listaInvDescripcionProducto=managerInvDescripcionProducto.findAllInvDescripcionProductos();
		listaInvProducto=managerInvProducto.findAllInvProductos();
		return "inv_productos.xhtml";
	}
	
	public String actionMenuNuevoInvProducto() {
		
		nuevoInvProducto=new InvProducto();
		return "inv_productos_nuevo";
	}
	
	public void actionListenerInsertarNuevoInvProducto() {
		try {
			managerInvProducto.insertarInvProducto(nuevoInvProducto, idinvDescProdcuto,idAdminrec_Recepciones);
			listaInvProducto=managerInvProducto.findAllInvProductos();
			nuevoInvProducto=new InvProducto();
			JSFUtil.crearMensajeINFO("Inventario del Producto insertado.");
		} catch (Exception e) {
			JSFUtil.crearMensajeERROR(e.getMessage());
			e.printStackTrace();
		}
	}
	public String actionSeleccionarEdicionInvProductos( InvProducto invProductos) {
		edicionInvProducto=invProductos;
		idinvDescProdcuto=edicionInvProducto.getInvDescripcionesProducto().getId();
		return "inv_productos_edicion";
	}
	
	public void actionListenerActualizarEdicionInvProductos() {
		try {
			managerInvProducto.actualizarInvProducto(edicionInvProducto,idinvDescProdcuto);
			listaInvProducto=managerInvProducto.findAllInvProductos();
			JSFUtil.crearMensajeINFO("Inventario actualizado.");
		} catch (Exception e) {
			JSFUtil.crearMensajeERROR(e.getMessage());
			e.printStackTrace();
		}
	}
	public void actionListenerEliminarInvProducto(int idInvProducto) {
		try {
			managerInvProducto.eliminarInvProducto(idInvProducto);
			listaInvProducto=managerInvProducto.findAllInvProductos();
			JSFUtil.crearMensajeINFO("Registro del Inventario eliminado.");
		} catch (Exception e) {
			JSFUtil.crearMensajeERROR(e.getMessage());
			e.printStackTrace();
		}
	}

	public ManagerInventarioProductos getManagerInvProducto() {
		return managerInvProducto;
	}

	public void setManagerInvProducto(ManagerInventarioProductos managerInvProducto) {
		this.managerInvProducto = managerInvProducto;
	}

	public List<InvProducto> getListaInvProducto() {
		return listaInvProducto;
	}

	public void setListaInvProducto(List<InvProducto> listaInvProducto) {
		this.listaInvProducto = listaInvProducto;
	}

	public InvProducto getNuevoInvProducto() {
		return nuevoInvProducto;
	}

	public void setNuevoInvProducto(InvProducto nuevoInvProducto) {
		this.nuevoInvProducto = nuevoInvProducto;
	}

	public InvProducto getEdicionInvProducto() {
		return edicionInvProducto;
	}

	public void setEdicionInvProducto(InvProducto edicionInvProducto) {
		this.edicionInvProducto = edicionInvProducto;
	}

	public InvDescripcionProducto getInvDescripcionProducto() {
		return invDescripcionProducto;
	}

	public void setInvDescripcionProducto(InvDescripcionProducto invDescripcionProducto) {
		this.invDescripcionProducto = invDescripcionProducto;
	}

	public int getIdinvDescProdcuto() {
		return idinvDescProdcuto;
	}

	public void setIdinvDescProdcuto(int idinvDescProdcuto) {
		this.idinvDescProdcuto = idinvDescProdcuto;
	}

	public int getIdAdminrec_Recepciones() {
		return idAdminrec_Recepciones;
	}

	public void setIdAdminrec_Recepciones(int idAdminrec_Recepciones) {
		this.idAdminrec_Recepciones = idAdminrec_Recepciones;
	}

	public List<InvDescripcionProducto> getListaInvDescripcionProducto() {
		return listaInvDescripcionProducto;
	}

	public void setListaInvDescripcionProducto(List<InvDescripcionProducto> listaInvDescripcionProducto) {
		this.listaInvDescripcionProducto = listaInvDescripcionProducto;
	}
	
	
	
	

}
