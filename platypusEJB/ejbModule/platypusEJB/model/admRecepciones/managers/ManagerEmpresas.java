package platypusEJB.model.admRecepciones.managers;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import platypusEJB.model.core.entities.AdmdirDireccion;
import platypusEJB.model.core.entities.AdmrecEmpresa;
import platypusEJB.model.core.managers.ManagerDAO;

/**
 * Session Bean implementation class ManagerEmpresas
 */
@Stateless
@LocalBean
public class ManagerEmpresas {

    /**
     * Default constructor. 
     */
	@EJB
	private ManagerDAO mDAO;
	
    public ManagerEmpresas() {
        // TODO Auto-generated constructor stub
    }
    
    
    public void insertarAdmrecEmpresas (AdmrecEmpresa nuevaAdmEmpresa,int idAdmdir_direcciones) throws Exception {
    	AdmdirDireccion admdirDireccion = (AdmdirDireccion) mDAO.findById(AdmdirDireccion.class, idAdmdir_direcciones);
    	nuevaAdmEmpresa.setAdmdirDireccione(admdirDireccion);
    	mDAO.insertar(nuevaAdmEmpresa);
    }
    
    
    public void actualizarEmpresas(AdmrecEmpresa edicionAdmEmpresa,int idAdmdir_direcciones) throws Exception {
    	AdmrecEmpresa admrecEmpresa=(AdmrecEmpresa) mDAO.findById(AdmrecEmpresa.class, edicionAdmEmpresa.getId());
    	admrecEmpresa.setId(edicionAdmEmpresa.getId());
    	admrecEmpresa.setNombre(edicionAdmEmpresa.getNombre());
    	admrecEmpresa.setContactoEmpresa(edicionAdmEmpresa.getContactoEmpresa());
    	
    	AdmdirDireccion admdirDireccion = (AdmdirDireccion) mDAO.findById(AdmdirDireccion.class, idAdmdir_direcciones);
    	admrecEmpresa.setAdmdirDireccione(admdirDireccion);
    	mDAO.actualizar(admrecEmpresa);
    }
    

    public void eliminarAdmEmpresas(int idAdmrecEmpresas) throws Exception {
    	AdmrecEmpresa admrecEmpresa=(AdmrecEmpresa) mDAO.findById(AdmrecEmpresa.class,idAdmrecEmpresas);
    	mDAO.eliminar(AdmrecEmpresa.class, admrecEmpresa.getId());
    }
    
    public List<AdmrecEmpresa> findAllAdmrecEmpres(){
    	return mDAO.findAll(AdmrecEmpresa.class, "id");
    }
    

}
