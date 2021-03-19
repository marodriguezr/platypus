package platypusWeb.controller.admdirDirecciones;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import platypusEJB.model.admdir.managers.ManagerDireccion;
import platypusEJB.model.core.entities.AdmdirDireccion;
import platypusEJB.model.core.entities.InvDescripcionProducto;
import platypusEJB.model.core.entities.InvTipoProducto;
import platypusWeb.controller.utilities.JSFUtil;

@Named
@SessionScoped
public class BeanDirecciones implements Serializable {
	@EJB
	private ManagerDireccion mDireccion;

	private List<AdmdirDireccion> listarDirecciones;
	private AdmdirDireccion nuevaDireccion;
	private AdmdirDireccion editarDireccion;

	public BeanDirecciones() {
		// TODO Auto-generated constructor stub
	}

	// Direccionar Una Pagina
	public String actionMenuNuevaDireccion() {
		nuevaDireccion = new AdmdirDireccion();
		return "nueva_direccion";
	}

	// Cargar clientes en una tabla
	public String actionListarDirecciones() {
		listarDirecciones = mDireccion.findAllDireccion();
		return "direcciones";
	}

	// Metodo de Insercion de Productos
	public void actionListenerInsertarNuevaDireccion() {
		try {
			mDireccion.insertarDireciion(nuevaDireccion);
			listarDirecciones = mDireccion.findAllDireccion();
			nuevaDireccion = new AdmdirDireccion();

			JSFUtil.crearMensajeINFO("Registro Insertado Exitosamente.");
		} catch (Exception e) {
			JSFUtil.crearMensajeERROR(e.getMessage());
			e.printStackTrace();
		}
	}

	// Metodo de edicion de Productos
	public String actionSeleccionarEdicionDierecciones(AdmdirDireccion admDirecciones) {
		editarDireccion = admDirecciones;
		return "tipo_producto_edicion";
	}

	// Metodo de actualizar Direcciones
	public void actionListenerActualizarEdicionDirecciones() {
		try {
			mDireccion.actualizarDireccion(editarDireccion);
			;
			listarDirecciones = mDireccion.findAllDireccion();
			JSFUtil.crearMensajeINFO("Registro Actualizado Correctamente.");
		} catch (Exception e) {
			JSFUtil.crearMensajeERROR(e.getMessage());
			e.printStackTrace();
		}
	}

	// Metodos de eliminar

	public void actionListenerEliminarDireccion(int idCliente) {

		try {
			mDireccion.eliminarDirecciones(idCliente);;
			listarDirecciones = mDireccion.findAllDireccion();
			// listarDatosAdicionales =mClientes.findAllAdmcliDatoAdicionals();

			JSFUtil.crearMensajeINFO("Registro Eliminado Correctamente.");
		} catch (Exception e) {
			JSFUtil.crearMensajeERROR(e.getMessage());
			e.printStackTrace();
		}
	}

	public List<AdmdirDireccion> getListarDirecciones() {
		return listarDirecciones;
	}

	public void setListarDirecciones(List<AdmdirDireccion> listarDirecciones) {
		this.listarDirecciones = listarDirecciones;
	}

	public AdmdirDireccion getNuevaDireccion() {
		return nuevaDireccion;
	}

	public void setNuevaDireccion(AdmdirDireccion nuevaDireccion) {
		this.nuevaDireccion = nuevaDireccion;
	}

	public AdmdirDireccion getEditarDireccion() {
		return editarDireccion;
	}

	public void setEditarDireccion(AdmdirDireccion editarDireccion) {
		this.editarDireccion = editarDireccion;
	}

}
