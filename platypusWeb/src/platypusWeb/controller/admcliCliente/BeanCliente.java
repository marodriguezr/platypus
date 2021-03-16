package platypusWeb.controller.admcliCliente;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import platypusEJB.model.admcli.managers.ManagerCliente;
import platypusEJB.model.core.entities.AdmcliCliente;
import platypusEJB.model.core.entities.AdmdirDireccion;
import platypusWeb.controller.utilities.JSFUtil;


@Named
@SessionScoped
public class BeanCliente implements Serializable {
	@EJB
	private ManagerCliente mClientes;
	private List<AdmcliCliente> listaClientes;
	private AdmcliCliente nuevaCliente;
	private AdmcliCliente edicionCliente;
	private List<AdmdirDireccion> listaDirecciones;
	private int idDireccion;
	private String provincia;
	public BeanCliente() {

	}
	@PostConstruct
	public void inicializacion() {
		System.out.println("BeanPeliculas inicializando .......");
		listaDirecciones= mClientes.findAllCargarDirecciones();
	}

//Cargar clientes en una tabla
	public String actionCargaMenuClientes() {
		listaClientes = mClientes.findAllAdmcliClientes();
		return "cliente";
	}
	
//Direccionar a una ventana de Nuevo cliente......................
	public String actionMenuNuevoCliente() {
		nuevaCliente=new AdmcliCliente();
		return "nuevo_cliente";
	}

//Insertar Nuevo cliente...........................................
	public void actionListenerInsertarNuevaCliente() {
		try {
			mClientes.insertarCliente(nuevaCliente, idDireccion);
			listaClientes = mClientes.findAllAdmcliClientes();
			nuevaCliente = new AdmcliCliente();
			JSFUtil.crearMensajeINFO("Cliente Insertado insertada.");
		} catch (Exception e) {
			JSFUtil.crearMensajeERROR(e.getMessage());
			e.printStackTrace();
		}
	}
	

	public String actionSeleccionarEdicionCliente(AdmcliCliente cliente) {
		edicionCliente = cliente;
		return "cliente";
	}

	public void actionListenerActualizarCliente() {
		try {
			mClientes.actualizarCliente(edicionCliente, idDireccion);
			listaClientes = mClientes.findAllAdmcliClientes();
			JSFUtil.crearMensajeINFO("Cliente actualizado.");
		} catch (Exception e) {
			JSFUtil.crearMensajeERROR(e.getMessage());
			e.printStackTrace();
		}
	}

	public void actionListenerEliminarClientes(int idCliente) {
		try {
			mClientes.eliminarCliente(idCliente);
			listaClientes = mClientes.findAllAdmcliClientes();
			JSFUtil.crearMensajeINFO("Cliente eliminado.");
		} catch (Exception e) {
			JSFUtil.crearMensajeERROR(e.getMessage());
			e.printStackTrace();
		}
	}
	
	
	//Metodos Get y Set
	
	public List<AdmcliCliente> getListaClientes() {
		return listaClientes;
	}
	public void setListaClientes(List<AdmcliCliente> listaClientes) {
		this.listaClientes = listaClientes;
	}
	public AdmcliCliente getNuevaCliente() {
		return nuevaCliente;
	}
	public void setNuevaCliente(AdmcliCliente nuevaCliente) {
		this.nuevaCliente = nuevaCliente;
	}
	public AdmcliCliente getEdicionCliente() {
		return edicionCliente;
	}
	public void setEdicionCliente(AdmcliCliente edicionCliente) {
		this.edicionCliente = edicionCliente;
	}
	public List<AdmdirDireccion> getListaDirecciones() {
		return listaDirecciones;
	}
	public void setListaDirecciones(List<AdmdirDireccion> listaDirecciones) {
		this.listaDirecciones = listaDirecciones;
	}
	public int getIdDireccion() {
		return idDireccion;
	}
	public void setIdDireccion(int idDireccion) {
		this.idDireccion = idDireccion;
	}
	public String getProvincia() {
		return provincia;
	}
	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}


}
