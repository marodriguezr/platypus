package platypusEJB.model.admcli.managers;

import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import platypusEJB.model.auditoria.managers.ManagerAuditoria;
import platypusEJB.model.core.entities.AdmcliCliente;
import platypusEJB.model.core.entities.AdmdirDireccion;
import platypusEJB.model.core.entities.SegModulo;
import platypusEJB.model.core.managers.ManagerDAO;

/**
 * Session Bean implementation class ManagerCliente
 */
@Stateless
@LocalBean
public class ManagerCliente {
	
	@PersistenceContext
	private EntityManager em;
	@EJB
	private ManagerDAO mDAO;
	@EJB
	private ManagerAuditoria mAuditoria;

    /**
     * Default constructor. 
     */
    public ManagerCliente() {
       
    }
    @SuppressWarnings("unchecked")
    public List<AdmcliCliente> findAllAdmcliClientes(){
    	return mDAO.findAll(AdmcliCliente.class,"apellido");
    }
    /*
     * Metodo de Insertar Cliente
     */
    
    public void insertarCliente(AdmcliCliente nuevaCliente, int idDireccion) throws Exception {
    	AdmdirDireccion direccion= (AdmdirDireccion) mDAO.findById(AdmdirDireccion.class, idDireccion);
    	nuevaCliente.setAdmdirDireccione(direccion);
    	mDAO.insertar(nuevaCliente);
    }
    /*
     * Metodo de Editar y actualizar cliente
      */
    public void actualizarCliente(AdmcliCliente editarCliente,int idDireccion) throws Exception {
    	AdmcliCliente cliente= (AdmcliCliente) mDAO.findById(AdmcliCliente.class, editarCliente.getId());
    	AdmdirDireccion direccion= (AdmdirDireccion) mDAO.findById(AdmdirDireccion.class, idDireccion);
    	
    	cliente.setNombre(editarCliente.getNombre());
    	cliente.setApellido(editarCliente.getApellido());
    	cliente.setAdmdirDireccione(direccion);
    	cliente.setFechaRegistro(editarCliente.getFechaRegistro());
    	
    	mDAO.actualizar(cliente);
    }
    /*
     * Metodo de eliminar Cliente
      */
    public void eliminarCliente(int idPelicula) throws Exception{
    	AdmcliCliente cliente = (AdmcliCliente) mDAO.findById(AdmcliCliente.class,idPelicula);
    	mDAO.eliminar(AdmcliCliente.class, cliente.getId());
    }
    
    /*
     * Metodo para cargar el combobox de Direcciones
      */
    public List <AdmdirDireccion> findAllCargarDirecciones(){
    	return mDAO.findAll(AdmdirDireccion.class, "provincia");
    }
    
    
    public List<SegModulo> findAllModulos(){
    	return mDAO.findAll(SegModulo.class, "nombreModulo");
    }
    

}
