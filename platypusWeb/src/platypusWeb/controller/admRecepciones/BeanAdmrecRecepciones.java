package platypusWeb.controller.admRecepciones;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import platypusEJB.model.admRecepciones.managers.ManagerDistribuidores;
import platypusEJB.model.admRecepciones.managers.ManagerRecepciones;
import platypusEJB.model.core.entities.AdmrecDistribuidor;
import platypusEJB.model.core.entities.AdmrecRecepcion;
import platypusWeb.controller.utilities.JSFUtil;

@Named
@SessionScoped
public class BeanAdmrecRecepciones implements Serializable {

	@EJB
	private ManagerRecepciones mRecepciones;
	@EJB
	private ManagerDistribuidores mDistribuidores;
	private List<AdmrecRecepcion> listaRecepciones;
	private AdmrecRecepcion nuevoAdmrecrecepcion;
	private AdmrecRecepcion edicionAdmrecrecepcion;
	private AdmrecDistribuidor admrecDistribuidor;
	private int idAdmrecDistribuidor;
	private List<AdmrecDistribuidor> listaDistribuidor;
	
	public BeanAdmrecRecepciones() {
		// TODO Auto-generated constructor stub
	}
	
	
	public String actionMenuAdmrecRecepciones() {
		listaDistribuidor=mDistribuidores.findAllAdmrecDistribuidor();
		listaRecepciones=mRecepciones.findAllAdmrecRecepciones();
		return "recepciones";
	}
	
	public String actionMenuNuevoAdmrecRecepciones() {
		nuevoAdmrecrecepcion=new AdmrecRecepcion();
		return "admrecRecepcionesNuevo";
	}
	
	public void actionListenerInsertarNuevoAdmrecRecepciones() {
		try {
			mRecepciones.insertarAdmRecepciones(nuevoAdmrecrecepcion, idAdmrecDistribuidor);
			listaRecepciones=mRecepciones.findAllAdmrecRecepciones();
			nuevoAdmrecrecepcion= new AdmrecRecepcion();
			JSFUtil.crearMensajeINFO("Recepcion insertado.");
		} catch (Exception e) {
			JSFUtil.crearMensajeERROR(e.getMessage());
			e.printStackTrace();
		}
	}
	
	
	public String actionSeleccionarEdicionAdmrecRecepciones(AdmrecRecepcion admrecRecepcion) {
		edicionAdmrecrecepcion=admrecRecepcion;
		idAdmrecDistribuidor=edicionAdmrecrecepcion.getAdmrecDistribuidore().getId();
		return "admrecRecepcionesEdicion";
	}
	
	public void actionListenerActualizarEdicionAdmrecRecepciones() {
		try {
			mRecepciones.actualizarRecepciones(edicionAdmrecrecepcion, idAdmrecDistribuidor);
			listaRecepciones=mRecepciones.findAllAdmrecRecepciones();
			JSFUtil.crearMensajeINFO("Recepcion actualizada.");
		} catch (Exception e) {
			JSFUtil.crearMensajeERROR(e.getMessage());
			e.printStackTrace();
		}
	}
	
	public void actionListenerEliminarAdmrecRecepciones(int idAdmrecRecepciones) {
		try {
			mRecepciones.eliminarAdmRecepciones(idAdmrecRecepciones);
			listaRecepciones=mRecepciones.findAllAdmrecRecepciones();
			JSFUtil.crearMensajeINFO("Recepcion eliminada.");
		} catch (Exception e) {
			JSFUtil.crearMensajeERROR(e.getMessage());
			e.printStackTrace();
		}
	}


	public ManagerDistribuidores getmDistribuidores() {
		return mDistribuidores;
	}


	public void setmDistribuidores(ManagerDistribuidores mDistribuidores) {
		this.mDistribuidores = mDistribuidores;
	}


	public List<AdmrecRecepcion> getListaRecepciones() {
		return listaRecepciones;
	}


	public void setListaRecepciones(List<AdmrecRecepcion> listaRecepciones) {
		this.listaRecepciones = listaRecepciones;
	}


	public AdmrecRecepcion getNuevoAdmrecrecepcion() {
		return nuevoAdmrecrecepcion;
	}


	public void setNuevoAdmrecrecepcion(AdmrecRecepcion nuevoAdmrecrecepcion) {
		this.nuevoAdmrecrecepcion = nuevoAdmrecrecepcion;
	}


	public AdmrecRecepcion getEdicionAdmrecrecepcion() {
		return edicionAdmrecrecepcion;
	}


	public void setEdicionAdmrecrecepcion(AdmrecRecepcion edicionAdmrecrecepcion) {
		this.edicionAdmrecrecepcion = edicionAdmrecrecepcion;
	}


	public AdmrecDistribuidor getAdmrecDistribuidor() {
		return admrecDistribuidor;
	}


	public void setAdmrecDistribuidor(AdmrecDistribuidor admrecDistribuidor) {
		this.admrecDistribuidor = admrecDistribuidor;
	}


	public int getIdAdmrecDistribuidor() {
		return idAdmrecDistribuidor;
	}


	public void setIdAdmrecDistribuidor(int idAdmrecDistribuidor) {
		this.idAdmrecDistribuidor = idAdmrecDistribuidor;
	}


	public List<AdmrecDistribuidor> getListaDistribuidor() {
		return listaDistribuidor;
	}


	public void setListaDistribuidor(List<AdmrecDistribuidor> listaDistribuidor) {
		this.listaDistribuidor = listaDistribuidor;
	}
	
	

}
