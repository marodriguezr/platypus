package platypusEJB.model.pos.managers;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import platypusEJB.model.auditoria.managers.ManagerAuditoria;
import platypusEJB.model.core.entities.PosDetalleVenta;
import platypusEJB.model.core.managers.ManagerDAO;

/**
 * Session Bean implementation class DetalleVentaManager
 */
@Stateless
@LocalBean
public class DetalleVentaManager {

	@EJB
	private ManagerDAO dao;
	@EJB
	private ManagerAuditoria auditoria;

	/**
	 * Default constructor.
	 */
	public DetalleVentaManager() {
		// TODO Auto-generated constructor stub
	}

	@SuppressWarnings("unchecked")
	public List<PosDetalleVenta> findAllDetallesVentas() {
		auditoria.mostrarLog(getClass(), "findAllDetallesVentas", "La busqueda de todas las ventas ha sido invocada.");
		return dao.findAll(PosDetalleVenta.class);
	}

	public PosDetalleVenta findDetalleVentaById(int id) throws Exception {
		auditoria.mostrarLog(getClass(), "findDetalleVentaById", "Se ha invocado la busqueda del detalle venta: " + id);
		PosDetalleVenta detalleVenta = (PosDetalleVenta) dao.findById(PosDetalleVenta.class, id);
		if (detalleVenta == null) {
			auditoria.mostrarLog(PosDetalleVenta.class, "findDetalleVentaById",
					"Id de venta: " + id + " no existente.");
			throw new Exception(
					"El detalle venta con id: " + id + " no existe, por favor ingrese un id de un registro válido.");
		}
		auditoria.mostrarLog(getClass(), "findDetalleVentaById", "Detalle venta: " + id + " encontrado y devuelto.");
		return detalleVenta;
	}

	@SuppressWarnings("unchecked")
	public List<PosDetalleVenta> findAllDetallesVentasByVentaId(int id) throws Exception {
		try {
			auditoria.mostrarLog(getClass(), "findAllDetallesVentasByVentaId",
					"Se ha disparado la busqueda de todos los detalles de la venta: " + id);
			return dao.findWhere(PosDetalleVenta.class, "o.posVenta.id=" + id, null);
		} catch (Exception e) {
			// TODO: handle exception
			auditoria.mostrarLog(getClass(), "findAllDetallesVentasByVentaId",
					"Ha ocurrido un error en la busqueda de todos los detalles de la venta: " + id);
			throw new Exception("Ha ocurrido un error en la busqueda de todos los detalles de la venta: " + id);
		}
	}

	public void createDetalleVenta(int idProducto, double precioVenta, int cantidad, int idVenta) {

	}
}
