package platypusWeb.controller.admRecepciones;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import platypusEJB.model.admRecepciones.managers.ManagerDistribuidores;
import platypusEJB.model.admRecepciones.managers.ManagerEmpresas;
import platypusEJB.model.core.entities.AdmrecDistribuidor;
import platypusEJB.model.core.entities.AdmrecEmpresa;
import platypusWeb.controller.utilities.JSFUtil;

@Named
@SessionScoped
public class BeanAdmrecDistribuidores implements Serializable {

	@EJB
	private ManagerDistribuidores mDistribuidores;
	@EJB
	private ManagerEmpresas mEmpresas;
	private List<AdmrecDistribuidor> listaAdmrecDistribuidores;
	private AdmrecDistribuidor nuevoAdmrecDistribuidor;
	private AdmrecDistribuidor edicionAdmrecDistribuidor;
	private AdmrecEmpresa admrecEmpresa;
	private int idadmRecEmpresa;
	private List<AdmrecEmpresa> listaAdmrecEmpresas;
	
	
	public BeanAdmrecDistribuidores() {
		// TODO Auto-generated constructor stub
	}

	

	
	public String actionMenuAdmrecDistribuidores() {
		listaAdmrecEmpresas=mEmpresas.findAllAdmrecEmpres();
		listaAdmrecDistribuidores=mDistribuidores.findAllAdmrecDistribuidor();
		return "distribuidores";
	}
	
	
	public String actionMenuNuevoAdmrecDistribuidor() {
		nuevoAdmrecDistribuidor=new AdmrecDistribuidor();
		return "admrecDistribuidorNuevo";
	}
		

	
	public void actionListenerInsertarNuevoAdmrecDistribuidor() {
		try {
			mDistribuidores.insertarAdmrecDistribuidores(nuevoAdmrecDistribuidor, idadmRecEmpresa);
			listaAdmrecDistribuidores=mDistribuidores.findAllAdmrecDistribuidor();
			nuevoAdmrecDistribuidor= new AdmrecDistribuidor();
			JSFUtil.crearMensajeINFO("Distribuidor insertado.");
		} catch (Exception e) {
			JSFUtil.crearMensajeERROR(e.getMessage());
			e.printStackTrace();
		}
	}

	

	public String actionSeleccionarEdicionAdmrecDistribuidor(AdmrecDistribuidor admrecDistribuidor) {
		edicionAdmrecDistribuidor=admrecDistribuidor;
		idadmRecEmpresa=edicionAdmrecDistribuidor.getAdmrecEmpresa().getId();
		return "admrecDistribuidorEdicion";
	}
	

	
	public void actionListenerActualizarEdicionAdmrecDistribuidor() {
		try {
			mDistribuidores.actualizarDistribuidor(edicionAdmrecDistribuidor, idadmRecEmpresa);
			listaAdmrecDistribuidores=mDistribuidores.findAllAdmrecDistribuidor();
			JSFUtil.crearMensajeINFO("Distribuidor actualizado.");
		} catch (Exception e) {
			JSFUtil.crearMensajeERROR(e.getMessage());
			e.printStackTrace();
		}
	}

		
	public void actionListenerEliminarAdmrecDistribuidor(int idAdmrecDistribuidor) {
		try {
			mDistribuidores.eliminarDistribuidores(idAdmrecDistribuidor);
			listaAdmrecDistribuidores=mDistribuidores.findAllAdmrecDistribuidor();
			 JSFUtil.crearMensajeINFO("Registro del Distribuidor eliminado.");
		} catch (Exception e) {
			JSFUtil.crearMensajeERROR(e.getMessage());
			e.printStackTrace();
		}
	}
	


	public ManagerEmpresas getmEmpresas() {
		return mEmpresas;
	}


	public void setmEmpresas(ManagerEmpresas mEmpresas) {
		this.mEmpresas = mEmpresas;
	}


	public List<AdmrecDistribuidor> getListaAdmrecDistribuidores() {
		return listaAdmrecDistribuidores;
	}


	public void setListaAdmrecDistribuidores(List<AdmrecDistribuidor> listaAdmrecDistribuidores) {
		this.listaAdmrecDistribuidores = listaAdmrecDistribuidores;
	}


	public AdmrecDistribuidor getNuevoAdmrecDistribuidor() {
		return nuevoAdmrecDistribuidor;
	}


	public void setNuevoAdmrecDistribuidor(AdmrecDistribuidor nuevoAdmrecDistribuidor) {
		this.nuevoAdmrecDistribuidor = nuevoAdmrecDistribuidor;
	}


	public AdmrecDistribuidor getEdicionAdmrecDistribuidor() {
		return edicionAdmrecDistribuidor;
	}


	public void setEdicionAdmrecDistribuidor(AdmrecDistribuidor edicionAdmrecDistribuidor) {
		this.edicionAdmrecDistribuidor = edicionAdmrecDistribuidor;
	}


	public AdmrecEmpresa getAdmrecEmpresa() {
		return admrecEmpresa;
	}


	public void setAdmrecEmpresa(AdmrecEmpresa admrecEmpresa) {
		this.admrecEmpresa = admrecEmpresa;
	}




	public int getIdadmRecEmpresa() {
		return idadmRecEmpresa;
	}




	public void setIdadmRecEmpresa(int idadmRecEmpresa) {
		this.idadmRecEmpresa = idadmRecEmpresa;
	}




	public List<AdmrecEmpresa> getListaAdmrecEmpresas() {
		return listaAdmrecEmpresas;
	}




	public void setListaAdmrecEmpresas(List<AdmrecEmpresa> listaAdmrecEmpresas) {
		this.listaAdmrecEmpresas = listaAdmrecEmpresas;
	}


	
	
	
	
	

}
