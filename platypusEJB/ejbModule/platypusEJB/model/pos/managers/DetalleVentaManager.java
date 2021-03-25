package platypusEJB.model.pos.managers;

import java.math.BigDecimal;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import platypusEJB.model.auditoria.managers.ManagerAuditoria;
import platypusEJB.model.core.entities.InvProducto;
import platypusEJB.model.core.entities.PosDetalleVenta;
import platypusEJB.model.core.entities.PosVenta;
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

	public void createDetalleVenta(int idProducto, double precioVenta, int cantidad, int idVenta)
			throws Exception {
		InvProducto producto = (InvProducto) dao.findById(InvProducto.class, idProducto);
		auditoria.mostrarLog(getClass(), "createDetalleVenta", "Inicio del proceso de creación de detalle para la venta " + idVenta);
		if (producto == null) {
			auditoria.mostrarLog(getClass(), "createDetalleVenta", "Producto " + idProducto + "inexistente");
			throw new Exception("El producto que ha especificado no existe");
		}
		if (precioVenta <= 0) {
			auditoria.mostrarLog(getClass(), "createDetalleVenta", "Precio de venta inválido");
			throw new Exception("Ingrese un precio válido.");
		}
		if (cantidad <= 0) {
			auditoria.mostrarLog(getClass(), "createDetalleVenta", "Cantidad inválida.");
			throw new Exception("Ingrese una cantidad válida.");
		}
		if (cantidad > producto.getCantidadDisponible()) {
			auditoria.mostrarLog(getClass(), "createDetalleVenta", "Cantidad insuficiente.");
			throw new Exception("Ingrese una cantidad válida, la cantidad actual excede la cantidad disponible.");
		}
		PosVenta venta = (PosVenta) dao.findById(PosVenta.class, idVenta);
		if (venta == null) {
			auditoria.mostrarLog(getClass(), "createDetalleVenta", "Venta " + idVenta + " inexistente.");
			throw new Exception("La venta que ha especificado no existe;");
		}
		auditoria.mostrarLog(getClass(), "createDetalleVenta", "Inicio de creación del nuevo detalle.");
		PosDetalleVenta detalleVenta = new PosDetalleVenta();
		detalleVenta.setInvProducto(producto);
		producto.setCantidadDisponible(producto.getCantidadDisponible() - cantidad);
		dao.actualizar(producto);
		detalleVenta.setPrecioVenta(new BigDecimal(precioVenta));
		detalleVenta.setCantidad(cantidad);
		detalleVenta.setPosVenta(venta);
		auditoria.mostrarLog(getClass(), "createDetalleVenta", "Asignación de datos exitosa.");
		dao.insertar(detalleVenta);
		auditoria.mostrarLog(getClass(), "createDetalleVenta", "Inserción de datos exitosa.");
	}

	/*
	public PosDetalleVenta createDetalleVenta(ProductoDto productoDto, PosVenta venta) throws Exception {
		return createDetalleVenta(productoDto.getId(), productoDto.getCostoVenta(),
				productoDto.getCantidadSeleccionada(), venta);
	}
	*/

	/*
	public void createDetallesVentas(List<ProductoDto> productosDtos, PosVenta venta) throws Exception {
		List<PosDetalleVenta> detallesVentas = new ArrayList<PosDetalleVenta>();
		for (ProductoDto productoDto : productosDtos) {
			PosDetalleVenta detalleVenta = createDetalleVenta(productoDto, venta);
			if (detalleVenta != null) {
				System.out.println("Detalle venta no es null :D");
			}
			detallesVentas.add(detalleVenta);
		}
		System.out.println(detallesVentas.size() + "El tamaño del arreglo");
		venta.setPosDetallesVentas(detallesVentas);
	}
	*/
}
