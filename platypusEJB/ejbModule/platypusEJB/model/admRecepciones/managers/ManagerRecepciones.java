package platypusEJB.model.admRecepciones.managers;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import platypusEJB.model.core.entities.AdmdirDireccion;
import platypusEJB.model.core.entities.AdmrecDistribuidor;
import platypusEJB.model.core.entities.AdmrecEmpresa;
import platypusEJB.model.core.entities.AdmrecRecepcion;
import platypusEJB.model.core.managers.ManagerDAO;

/**
 * Session Bean implementation class ManagerRecepciones
 */
@Stateless
@LocalBean
public class ManagerRecepciones {

    /**
     * Default constructor. 
     */
	@EJB
	private ManagerDAO mDAO;
	
    public ManagerRecepciones() {
        // TODO Auto-generated constructor stub
    }
        
    public void insertarAdmRecepciones (AdmrecRecepcion nuevoAdmRecepciones,int idAdmrecDistribuidores) throws Exception {
    	AdmrecDistribuidor admrecDistribuidor = (AdmrecDistribuidor) mDAO.findById(AdmrecDistribuidor.class, idAdmrecDistribuidores);
    	nuevoAdmRecepciones.setAdmrecDistribuidore(admrecDistribuidor);
    	mDAO.insertar(nuevoAdmRecepciones);
    }
 
    
    public void actualizarRecepciones(AdmrecRecepcion edicionAdmRecepciones,int idAdmrecRecepciones) throws Exception {
    	AdmrecRecepcion admrecRecepcion=(AdmrecRecepcion) mDAO.findById(AdmrecRecepcion.class, edicionAdmRecepciones.getId());
    	admrecRecepcion.setId(edicionAdmRecepciones.getId());
    	admrecRecepcion.setAnexoFactura(edicionAdmRecepciones.getAnexoFactura());
    	admrecRecepcion.setFecha(edicionAdmRecepciones.getFecha());
    	
    	AdmrecDistribuidor admrecDistribuidor = (AdmrecDistribuidor) mDAO.findById(AdmrecDistribuidor.class,idAdmrecRecepciones);
    	admrecRecepcion.setAdmrecDistribuidore(admrecDistribuidor);
    	mDAO.actualizar(admrecRecepcion);
    }
    
    public void eliminarAdmRecepciones(int idAdmrecRecepciones) throws Exception {
    	AdmrecRecepcion admrecRecepcion=(AdmrecRecepcion) mDAO.findById(AdmrecRecepcion.class,idAdmrecRecepciones);
    	mDAO.eliminar(AdmrecRecepcion.class, admrecRecepcion.getId());
    }
    
    public List<AdmrecRecepcion> findAllAdmrecRecepciones(){
    	return mDAO.findAll(AdmrecRecepcion.class, "id");
    }
    

}
