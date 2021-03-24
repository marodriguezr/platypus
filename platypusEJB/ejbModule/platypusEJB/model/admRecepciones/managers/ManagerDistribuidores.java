package platypusEJB.model.admRecepciones.managers;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;


import platypusEJB.model.core.entities.AdmrecDistribuidor;
import platypusEJB.model.core.entities.AdmrecEmpresa;
import platypusEJB.model.core.managers.ManagerDAO;

/**
 * Session Bean implementation class ManagerDistribuidores
 */
@Stateless
@LocalBean
public class ManagerDistribuidores {

    /**
     * Default constructor. 
     */
	
	@EJB
	private ManagerDAO mDAO;
    public ManagerDistribuidores() {
        // TODO Auto-generated constructor stub
    }
    
    public void insertarAdmrecDistribuidores (AdmrecDistribuidor  nuevaAdmDistribuidor,int idAdmrec_empresa) throws Exception {
    	AdmrecEmpresa admrecempresa =(AdmrecEmpresa) mDAO.findById(AdmrecEmpresa.class,idAdmrec_empresa);
    	nuevaAdmDistribuidor.setAdmrecEmpresa(admrecempresa);
    	mDAO.insertar(nuevaAdmDistribuidor);
    }
    
   
    
    
    public void actualizarDistribuidor(AdmrecDistribuidor edicionAdmDistribuidor,int idAdmreEmpresa) throws Exception {
    	
    	AdmrecDistribuidor admrecDistribuidor=(AdmrecDistribuidor) mDAO.findById(AdmrecDistribuidor.class,edicionAdmDistribuidor.getId());
    	admrecDistribuidor.setId(edicionAdmDistribuidor.getId());
    	admrecDistribuidor.setNombre(edicionAdmDistribuidor.getNombre());
    	admrecDistribuidor.setApellido(edicionAdmDistribuidor.getApellido());
    	admrecDistribuidor.setContacto(edicionAdmDistribuidor.getContacto());
    	
    	AdmrecEmpresa admrecEmpresa =(AdmrecEmpresa) mDAO.findById(AdmrecEmpresa.class,idAdmreEmpresa);
    	admrecDistribuidor.setAdmrecEmpresa(admrecEmpresa);
    	mDAO.actualizar(admrecDistribuidor);
    }
    
    
    public void eliminarDistribuidores(int idAdmrecDistribuidores) throws Exception {
    	AdmrecDistribuidor admrecDistribuidor=(AdmrecDistribuidor) mDAO.findById(AdmrecDistribuidor.class,idAdmrecDistribuidores);
    	mDAO.eliminar(AdmrecDistribuidor.class, admrecDistribuidor.getId());
    }
    

    
    public List<AdmrecDistribuidor> findAllAdmrecDistribuidor(){
    	return mDAO.findAll(AdmrecDistribuidor.class, "id");
    }
    
    

}
