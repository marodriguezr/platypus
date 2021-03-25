package platypusWeb.controller.admcliCliente;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import platypusEJB.model.admcli.managers.ManagerCliente;
import platypusEJB.model.core.entities.AdmcliCliente;
import platypusEJB.model.core.entities.AdmcliDatoAdicional;
import platypusEJB.model.core.entities.AdmdirDireccion;
import platypusEJB.model.core.entities.InvDescripcionProducto;
import platypusEJB.model.core.entities.InvProducto;
import platypusEJB.model.core.managers.ManagerDAO;
import platypusWeb.controller.utilities.JSFUtil;


@Named
@SessionScoped
public class BeanCliente implements Serializable {
	@EJB
	private ManagerCliente mClientes;
	
	private List<AdmcliCliente> listaClientes;
	private AdmcliCliente nuevaCliente;
	private AdmcliCliente edicionCliente;
	private  List<AdmcliDatoAdicional> listarDatosAdicionales;
	private AdmcliDatoAdicional nuevoDatosAdicionales;
	private AdmcliDatoAdicional editarDatosAdicionales;
	
	private List<AdmdirDireccion> listaDirecciones;
	private int idDireccion;
	private String provincia;
	
	private int controlDatosAdicionales=0;
	
	private int idAdminDatosAdicionales;
	
	private int idCliente;
	
	public BeanCliente() {

	}
	@PostConstruct
	public void inicializacion() {
		System.out.println("BeanPeliculas inicializando .......");
		listaDirecciones= mClientes.findAllCargarDirecciones();
	}

//Cargar clientes en una tabla
	public String actionListarClientesDatos() {
		listaClientes = mClientes.findAllAdmcliClientes();
		listarDatosAdicionales= mClientes.findAllAdmcliDatoAdicionals();
		return "cliente.xhtml";
	}
	
	
	public String actionMenuNuevoAdmcliCliente() {
		
		nuevaCliente = new AdmcliCliente();
		nuevoDatosAdicionales = new AdmcliDatoAdicional();
		return "nuevo_cliente";
	}
	
//Cargar Datos Adicionales
	public String actionCargarDatosAdicionalesCliente() {
		listarDatosAdicionales= mClientes.findAllAdmcliDatoAdicionals();
		return "datosAdicionales";
	}
	
//Insertar Nuevo cliente y datos adicionales
	public void actionListenerInsertarNuevoCliente() {
		try {
			mClientes.insertarCliente(nuevaCliente ,nuevoDatosAdicionales, idDireccion,controlDatosAdicionales);
			
			listaClientes = mClientes.findAllAdmcliClientes();
			nuevaCliente = new AdmcliCliente();
			
			listarDatosAdicionales = mClientes.findAllAdmcliDatoAdicionals();
			nuevoDatosAdicionales = new AdmcliDatoAdicional();
			
			JSFUtil.crearMensajeINFO("Registro de Cliente Exitoso.");
		} catch (Exception e) {
			JSFUtil.crearMensajeERROR(e.getMessage());
			e.printStackTrace();
		}
	}
	
	
//Editar datos de clientes y los datos adicionales
	
	public String actionSeleccionarEdicionClienteDatos(AdmcliCliente cliente) {
		edicionCliente = cliente;
		
			idAdminDatosAdicionales= edicionCliente.getAdmcliDatosAdicionale().getIdCliente();
			try {
				editarDatosAdicionales= mClientes.findByIDAdminCLientesAdicionales(idAdminDatosAdicionales);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return "cliente";
	}
	
	
	public void actionListenerActualizarClienteDatos() {
		try {
			mClientes.actualizarCliente(edicionCliente, editarDatosAdicionales, idAdminDatosAdicionales);
			listaClientes = mClientes.findAllAdmcliClientes();
			listarDatosAdicionales = mClientes.findAllAdmcliDatoAdicionals();
			JSFUtil.crearMensajeINFO("Actualizacion de registro Exitoso.");
		} catch (Exception e) {
			JSFUtil.crearMensajeERROR(e.getMessage());
			e.printStackTrace();
		}
	}

	
//Metodos de eliminar
		
	public void actionListenerEliminarClientes(int idCliente) {
		
		try {
			mClientes.eliminarClienteDatos(idCliente);
			listaClientes = mClientes.findAllAdmcliClientes();
			//listarDatosAdicionales =mClientes.findAllAdmcliDatoAdicionals();
			
			JSFUtil.crearMensajeINFO("Registro Eliminado Correctamente.");
		} catch (Exception e) {
			JSFUtil.crearMensajeERROR(e.getMessage());
			e.printStackTrace();
		}
	}
	
	
	//Direccionar a una ventana de Nuevo cliente......................
		public String actionMenuNuevoCliente() {
			nuevaCliente=new AdmcliCliente();
			return "nuevo_cliente";
		}
	
		public void actionControlDatosAdicionales() {
			controlDatosAdicionales=1;
			
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
	public List<AdmcliDatoAdicional> getListarDatosAdicionales() {
		return listarDatosAdicionales;
	}
	public void setListarDatosAdicionales(List<AdmcliDatoAdicional> listarDatosAdicionales) {
		this.listarDatosAdicionales = listarDatosAdicionales;
	}
	public AdmcliDatoAdicional getNuevoDatosAdicionales() {
		return nuevoDatosAdicionales;
	}
	public void setNuevoDatosAdicionales(AdmcliDatoAdicional nuevoDatosAdicionales) {
		this.nuevoDatosAdicionales = nuevoDatosAdicionales;
	}
	public AdmcliDatoAdicional getEditarDatosAdicionales() {
		return editarDatosAdicionales;
	}
	public void setEditarDatosAdicionales(AdmcliDatoAdicional editarDatosAdicionales) {
		this.editarDatosAdicionales = editarDatosAdicionales;
	}
	public int getControlDatosAdicionales() {
		return controlDatosAdicionales;
	}
	public void setControlDatosAdicionales(int controlDatosAdicionales) {
		this.controlDatosAdicionales = controlDatosAdicionales;
	}


}
