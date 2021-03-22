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
import platypusEJB.model.core.entities.AdmcliDatoAdicional;
import platypusEJB.model.core.entities.AdmdirDireccion;
import platypusEJB.model.core.entities.InvDescripcionProducto;
import platypusEJB.model.core.entities.InvTipoProducto;
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
/*
 * Metodos de enlistar clientes y datos de los cliente
 */
    public List<AdmcliCliente> findAllAdmcliClientes(){
    	return mDAO.findAll(AdmcliCliente.class,"id");
    }
    
    
    public List<AdmcliDatoAdicional> findAllAdmcliDatoAdicionals(){
    	return mDAO.findAll(AdmcliDatoAdicional.class, "id");
    }
    
    /*
     * Metodo de Insertar Cliente
     */
    
    public void insertarCliente(AdmcliCliente nuevaCliente,AdmcliDatoAdicional adminDatosAdicionales, int idDireccion, int controlDatosAdicionales) throws Exception {
    	AdmdirDireccion direccion= (AdmdirDireccion) mDAO.findById(AdmdirDireccion.class, idDireccion);
    	nuevaCliente.setAdmdirDireccione(direccion);
    	mDAO.insertar(nuevaCliente);
    	
    	if(adminDatosAdicionales.getCedula()!=null && adminDatosAdicionales.getContacto()!=null && adminDatosAdicionales.getEmail()!=null) {
    		
    		adminDatosAdicionales.setAdmcliCliente(nuevaCliente);
    		adminDatosAdicionales.setIdCliente(nuevaCliente.getId());
        	mDAO.insertar(adminDatosAdicionales);
    	}else {
    		AdmcliDatoAdicional nuevoDatoAdi= new AdmcliDatoAdicional();
        	nuevoDatoAdi.setAdmcliCliente(nuevaCliente);
        	nuevoDatoAdi.setCedula("000000000");
        	nuevoDatoAdi.setContacto("00000000");
        	nuevoDatoAdi.setEmail("vacio");
        	nuevoDatoAdi.setIdCliente(nuevaCliente.getId());
        	mDAO.insertar(nuevoDatoAdi);
    		
    	}	
    	
    }
    
    
    /*
     * Metodo de Editar y actualizar cliente
      */
    public void actualizarCliente(AdmcliCliente editarCliente, AdmcliDatoAdicional editardatosAdicionales,int idAdminCLientesAdicionales) throws Exception {
    	AdmcliCliente cliente= (AdmcliCliente) mDAO.findById(AdmcliCliente.class, editarCliente.getId());
    	AdmcliDatoAdicional datos=(AdmcliDatoAdicional) mDAO.findById(AdmcliDatoAdicional.class, idAdminCLientesAdicionales);
    	
    	
    	
    	cliente.setNombre(editarCliente.getNombre());
    	cliente.setApellido(editarCliente.getApellido());
    	cliente.setFechaRegistro(editarCliente.getFechaRegistro());
    	mDAO.actualizar(cliente);
    	
    	datos.setCedula(editardatosAdicionales.getCedula());
    	datos.setEmail(editardatosAdicionales.getEmail());
    	datos.setContacto(editardatosAdicionales.getContacto());
    	mDAO.actualizar(datos);
    	
    }
    
    /*
     * Metodo de eliminar Cliente y datos de 
      */
    public void eliminarClienteDatos(int idCliente) throws Exception{
    	//AdmcliDatoAdicional datos = (AdmcliDatoAdicional) mDAO.findById(AdmcliDatoAdicional.class, idCliente);
    	AdmcliCliente cliente = (AdmcliCliente) mDAO.findById(AdmcliCliente.class,idCliente);
    	
    	mDAO.eliminar(AdmcliDatoAdicional.class, cliente.getId());
    	mDAO.eliminar(AdmcliCliente.class, cliente.getId());
    }
    
    
    /*
     * Metodo para cargar el combobox de Direcciones
      */
    public List <AdmdirDireccion> findAllCargarDirecciones(){
    	return mDAO.findAll(AdmdirDireccion.class, "provincia");
    }
    
    
    public AdmcliDatoAdicional findByIDAdminCLientesAdicionales(int idAdminCLiAdi) throws Exception  {
    	
			AdmcliDatoAdicional admCliAdic= (AdmcliDatoAdicional)mDAO.findById(AdmcliDatoAdicional.class,idAdminCLiAdi);
			return admCliAdic;
    }

    
    public List<SegModulo> findAllModulos(){
    	return mDAO.findAll(SegModulo.class, "nombreModulo");
    }
    

}
