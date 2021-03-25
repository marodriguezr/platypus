package platypusWeb.controller.admRecepciones;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import platypusEJB.model.admRecepciones.managers.ManagerEmpresas;
import platypusEJB.model.admdir.managers.ManagerDireccion;
import platypusEJB.model.core.entities.AdmdirDireccion;
import platypusEJB.model.core.entities.AdmrecEmpresa;
import platypusEJB.model.core.entities.InvProducto;
import platypusWeb.controller.utilities.JSFUtil;

@Named
@SessionScoped
public class BeanAdmrecEmpresas implements Serializable {
	
	@EJB
	private ManagerEmpresas mEmpresas;
	@EJB
	private ManagerDireccion mDireccion;
	private List<AdmrecEmpresa> listaadmrecEmpresas;
	private AdmrecEmpresa nuevoAdmrecEmpresa;
	private AdmrecEmpresa edicionAdmrecEmpresa;
	private AdmdirDireccion admdirDireccion;
	private int idAdmdirDireccion;
	private List<AdmdirDireccion> listaAdmdirDirecciones;
	
	

	public BeanAdmrecEmpresas() {
		// TODO Auto-generated constructor stub
	}

	public String actionMenuAdmrecEmpresas() {
		listaAdmdirDirecciones=mDireccion.findAllDireccion();
		listaadmrecEmpresas=mEmpresas.findAllAdmrecEmpres();
		return "empresas.xhtml";
	}
	


public String actionMenuNuevoAdmrecEmpresas() {
	nuevoAdmrecEmpresa=new AdmrecEmpresa();
	return "admrecEmpresaNueva";
}
	

public void actionListenerInsertarNuevoAdmrecEmpresa() {
	try {
		
		mEmpresas.insertarAdmrecEmpresas(nuevoAdmrecEmpresa, idAdmdirDireccion);
		listaadmrecEmpresas=mEmpresas.findAllAdmrecEmpres();
		nuevoAdmrecEmpresa= new AdmrecEmpresa();
		JSFUtil.crearMensajeINFO("AdmRecepcionesEmpresa insertada.");
		
	} catch (Exception e) {
		JSFUtil.crearMensajeERROR(e.getMessage());
		e.printStackTrace();
	}
}



public String actionSeleccionarEdicionAdmrecEmpresas(AdmrecEmpresa admrecEmpresa) {
	edicionAdmrecEmpresa=admrecEmpresa;
	idAdmdirDireccion=edicionAdmrecEmpresa.getAdmdirDireccione().getId();
	return "admrecEmpresaEdicion";
}


public void actionListenerActualizarEdicionAdmrecEmpresas() {
	try {
		mEmpresas.actualizarEmpresas(edicionAdmrecEmpresa, idAdmdirDireccion);
		listaadmrecEmpresas=mEmpresas.findAllAdmrecEmpres();
		JSFUtil.crearMensajeINFO("Empresa actualizada.");
	} catch (Exception e) {
		JSFUtil.crearMensajeERROR(e.getMessage());
		e.printStackTrace();
	}
}




public void actionListenerEliminarAdmrecEmpresas(int idAdmrecEmpresas) {
	try {
       mEmpresas.eliminarAdmEmpresas(idAdmrecEmpresas);
       listaadmrecEmpresas=mEmpresas.findAllAdmrecEmpres();
       JSFUtil.crearMensajeINFO("Registro del Empresas eliminado.");
	} catch (Exception e) {
		JSFUtil.crearMensajeERROR(e.getMessage());
		e.printStackTrace();
	}
}

	


	public ManagerDireccion getmDireccion() {
		return mDireccion;
	}



	public void setmDireccion(ManagerDireccion mDireccion) {
		this.mDireccion = mDireccion;
	}



	public List<AdmrecEmpresa> getListaadmrecEmpresas() {
		return listaadmrecEmpresas;
	}



	public void setListaadmrecEmpresas(List<AdmrecEmpresa> listaadmrecEmpresas) {
		this.listaadmrecEmpresas = listaadmrecEmpresas;
	}



	public AdmrecEmpresa getNuevoAdmrecEmpresa() {
		return nuevoAdmrecEmpresa;
	}



	public void setNuevoAdmrecEmpresa(AdmrecEmpresa nuevoAdmrecEmpresa) {
		this.nuevoAdmrecEmpresa = nuevoAdmrecEmpresa;
	}



	public AdmrecEmpresa getEdicionAdmrecEmpresa() {
		return edicionAdmrecEmpresa;
	}



	public void setEdicionAdmrecEmpresa(AdmrecEmpresa edicionAdmrecEmpresa) {
		this.edicionAdmrecEmpresa = edicionAdmrecEmpresa;
	}



	public AdmdirDireccion getAdmdirDireccion() {
		return admdirDireccion;
	}



	public void setAdmdirDireccion(AdmdirDireccion admdirDireccion) {
		this.admdirDireccion = admdirDireccion;
	}



	public int getIdAdmdirDireccion() {
		return idAdmdirDireccion;
	}



	public void setIdAdmdirDireccion(int idAdmdirDireccion) {
		this.idAdmdirDireccion = idAdmdirDireccion;
	}



	public List<AdmdirDireccion> getListaAdmdirDirecciones() {
		return listaAdmdirDirecciones;
	}



	public void setListaAdmdirDirecciones(List<AdmdirDireccion> listaAdmdirDirecciones) {
		this.listaAdmdirDirecciones = listaAdmdirDirecciones;
	}
	
	
	

}
