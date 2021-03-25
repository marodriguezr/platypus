package platypusEJB.model.admdir.managers;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import platypusEJB.model.auditoria.managers.ManagerAuditoria;
import platypusEJB.model.core.entities.AdmdirDireccion;
import platypusEJB.model.core.entities.InvTipoProducto;
import platypusEJB.model.core.managers.ManagerDAO;


/**
 * Session Bean implementation class ManagerCliente
 */
@Stateless
@LocalBean
public class ManagerDireccion {
	@PersistenceContext
	private EntityManager em;

	@EJB
	private ManagerDAO mDAO;
	@EJB
	private ManagerAuditoria mAuditoria;

	/**
	 * Default constructor.
	 */
	public ManagerDireccion() {

	}

	/*
	 * Creamos el metodo para cargar todos los registros
	 */
	public List<AdmdirDireccion> findAllDireccion() {
		return mDAO.findAll(AdmdirDireccion.class, "id");
	}

	/**
	 * Creamos el metodo de insercion de DIRECCIONES con los siguientes atributos.
	 * 
	 * @param marca
	 * @param modelo
	 * @param color
	 */
	  public void insertarDireciion(AdmdirDireccion nuevaDireccion) throws Exception {
	    	mDAO.insertar(nuevaDireccion);
	    
	    }

	/**
	 * Creamos el metodo eliminar
	 * 
	 * @param idSucursal se busca el ide y elimina
	 * @throws Exception
	 */

	    public void eliminarDirecciones(int idDieccion) throws Exception {
	    	AdmdirDireccion AdmDirecciones=(AdmdirDireccion) mDAO.findById(AdmdirDireccion.class, 
	    			idDieccion);
	    	mDAO.eliminar(AdmdirDireccion.class, AdmDirecciones.getId());
	    }

	/**
	 * Creamos el metodo de Actualizar
	 * 
	 * @param sucursal
	 * @throws Exception
	 */

	 public void actualizarDireccion(AdmdirDireccion edicionDireccion) throws Exception {
		 AdmdirDireccion admDirecciones=(AdmdirDireccion) mDAO.findById(AdmdirDireccion.class,edicionDireccion.getId());
		 
		 admDirecciones.setCallePrincipal(edicionDireccion.getCallePrincipal());
		 admDirecciones.setCalleSecundaria(edicionDireccion.getCalleSecundaria());
		 admDirecciones.setProvincia(edicionDireccion.getProvincia());
		 admDirecciones.setCiudad(edicionDireccion.getCiudad());
		 admDirecciones.setReferencia(edicionDireccion.getReferencia());
		 
		 mDAO.insertar(admDirecciones);
     	
	 }
}
