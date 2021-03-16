package platypusEJB.model.admdir.managers;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import platypusEJB.model.auditoria.managers.ManagerAuditoria;
import platypusEJB.model.core.entities.AdmdirDireccion;
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
		return mDAO.findAll(AdmdirDireccion.class, "provincia");
	}

	/**
	 * Creamos el metodo de insercion de DIRECCIONES con los siguientes atributos.
	 * 
	 * @param marca
	 * @param modelo
	 * @param color
	 */
	public void CrearDireccion(String calle_principal, String calle_secundaria, String provincia, String ciudad, String referencia) {
		AdmdirDireccion d = new AdmdirDireccion();
		d.setCallePrincipal(calle_principal);
		d.setCalleSecundaria(calle_secundaria);
		d.setProvincia(provincia);
		d.setCiudad(ciudad);
		d.setReferencia(referencia);
		em.persist(d);
	}

	/**
	 * Creamos el metodo eliminar
	 * 
	 * @param idSucursal se busca el ide y elimina
	 * @throws Exception
	 */

	public void EliminarDirecciones(int id) throws Exception {
		AdmdirDireccion d = em.find(AdmdirDireccion.class, id);
		if (d == null) {
			throw new Exception("No Exixte el ID de Direcciones");

		}
		em.remove(d);

	}

	/**
	 * Creamos el metodo de Actualizar
	 * 
	 * @param sucursal
	 * @throws Exception
	 */

	public void actualizarDirecciones(AdmdirDireccion admdirDireccion ) throws Exception {
		AdmdirDireccion d = em.find(AdmdirDireccion.class, admdirDireccion.getId());
		if (d == null) {
			throw new Exception("No Exixte el ID de la DIRECCIONES no se puede Actualizar");
		}
		d.setCallePrincipal(admdirDireccion.getCallePrincipal());
		d.setCalleSecundaria(admdirDireccion.getCalleSecundaria());
		d.setProvincia(admdirDireccion.getProvincia());
		d.setCiudad(admdirDireccion.getCiudad());
		d.setReferencia(admdirDireccion.getReferencia());
		em.merge(d);

	}

}
