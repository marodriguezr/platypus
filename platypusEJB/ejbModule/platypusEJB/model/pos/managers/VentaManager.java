package platypusEJB.model.pos.managers;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import platypusEJB.model.auditoria.managers.ManagerAuditoria;
import platypusEJB.model.core.entities.AdmcliCliente;
import platypusEJB.model.core.entities.InvProducto;
import platypusEJB.model.core.entities.PosDetalleVenta;
import platypusEJB.model.core.entities.PosPorcentajeIva;
import platypusEJB.model.core.entities.PosVenta;
import platypusEJB.model.core.entities.ThmEmpleado;
import platypusEJB.model.core.managers.ManagerDAO;
import platypusEJB.model.core.views.IdEmpleadoCantidadClientesView;
import platypusEJB.model.core.views.IdEmpleadoCantidadProductosView;
import platypusEJB.model.core.views.IdEmpleadoCantidadVentasView;
import platypusEJB.model.inventarioproductos.dtos.ProductoDto;
import platypusEJB.model.pos.dtos.VentaDto;
import platypusEJB.model.thumano.managers.ManagerTHumano;

/**
 * Session Bean implementation class VentaManager
 */
@Stateless
@LocalBean
public class VentaManager {

	/**
	 * Manager para la interacción con los datos.
	 */
	@EJB
	private ManagerDAO dao;
	/**
	 * Manager para la interacción con el módulo de auditoria.
	 */
	@EJB
	private ManagerAuditoria auditoria;
	@EJB
	private ManagerTHumano thumano;

	@EJB
	private DetalleVentaManager detalleVentaManager;

	/**
	 * Default constructor.
	 */
	public VentaManager() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * Método para la busqueda de todas las ventas.
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<PosVenta> findAllVentas() {
		auditoria.mostrarLog(getClass(), "findAllVentas", "Proceso de buscado de todas las ventas invocado");
		return dao.findAll(PosVenta.class);
	}

	public PosVenta findVentaById(int id) throws Exception {
		auditoria.mostrarLog(getClass(), "findVentaById", "Se ha invocado la busqueda de la venta: " + id);
		PosVenta venta = (PosVenta) dao.findById(PosVenta.class, id);
		if (venta == null) {
			auditoria.mostrarLog(getClass(), "findVentaById", "La venta: " + id + " no está registrada.");
			throw new Exception("La venta: " + id + " no está registrada.");
		}
		auditoria.mostrarLog(getClass(), "findVentaById", "Se ha devuelto la venta: " + id);
		return venta;
	}

	public void createVenta(int idCliente, int idThmEmpleado, int idPorcentajeIva) throws Exception {
		auditoria.mostrarLog(getClass(), "createVenta", "Create ventas iniciado para el cleinte" + idCliente);
		AdmcliCliente cliente = (AdmcliCliente) dao.findById(AdmcliCliente.class, idCliente);
		if (cliente == null) {
			auditoria.mostrarLog(getClass(), "createVenta", "El cliente especificado no existe" + idCliente);
			throw new Exception("El cliente especificado no está presente en la base de datos");
		}
		ThmEmpleado empleado = (ThmEmpleado) dao.findById(ThmEmpleado.class, idThmEmpleado);
		if (empleado == null) {
			auditoria.mostrarLog(getClass(), "createVenta", "El empleado especificado no existe" + idThmEmpleado);
			throw new Exception("El empleado especificado no está presente en la base de datos");
		}
		PosPorcentajeIva porcentajeIva = (PosPorcentajeIva) dao.findById(PosPorcentajeIva.class, idPorcentajeIva);
		if (porcentajeIva == null) {
			auditoria.mostrarLog(getClass(), "createVenta",
					"El porcentaje de iva especificado no existe" + idPorcentajeIva);
			throw new Exception("El porcentaje de iva especificado no está presente en la base de datos");
		}
		PosVenta venta = new PosVenta();
		auditoria.mostrarLog(getClass(), "createVenta", "Creacion de cabecera de venta exitosa.");
		venta.setAdmcliCliente(cliente);
		venta.setFechaVenta(new Date());
		venta.setPosPorcentajesIva(porcentajeIva);
		venta.setThmEmpleado(empleado);
		dao.insertar(venta);
		auditoria.mostrarLog(getClass(), "createVenta", "Inserción de la cabecera de venta exitosa.");
	}

	private void asignarDetalles(List<ProductoDto> productos, PosVenta venta) throws Exception {
		List<PosDetalleVenta> detalles = new ArrayList<PosDetalleVenta>();
		for (ProductoDto productoDto : productos) {
			InvProducto producto = (InvProducto) dao.findById(InvProducto.class, productoDto.getId());
			if (producto == null) {
				throw new Exception("El producto que ha especificado no existe");
			}
			if (productoDto.getCostoVenta() <= 0) {
				throw new Exception("Ingrese un precio válido.");
			}
			if (productoDto.getCantidadSeleccionada() <= 0) {
				throw new Exception("Ingrese una cantidad válida.");
			}
			if (productoDto.getCantidadSeleccionada() > producto.getCantidadDisponible()) {
				throw new Exception("Ingrese una cantidad válida, la cantidad actual excede la cantidad disponible.");
			}
			PosDetalleVenta detalleVenta = new PosDetalleVenta();
			detalleVenta.setInvProducto(producto);
			producto.setCantidadDisponible(producto.getCantidadDisponible() - productoDto.getCantidadSeleccionada());
			dao.actualizar(producto);
			detalleVenta.setPrecioVenta(new BigDecimal(productoDto.getCostoVenta()));
			detalleVenta.setCantidad(productoDto.getCantidadSeleccionada());
			detalleVenta.setPosVenta(venta);
			detalles.add(detalleVenta);
		}
		venta.setPosDetallesVentas(detalles);
	}

	private VentaDto toVentaDto(PosVenta venta) {
		VentaDto ventaDto = new VentaDto();

		ventaDto.setCantidadProductosVendidos(venta.getPosDetallesVentas().size());
		ventaDto.setFechaVenta(venta.getFechaVenta());
		ventaDto.setId(venta.getId());
		ventaDto.setIdCliente(venta.getAdmcliCliente().getId());
		ventaDto.setIdEmpleado(venta.getThmEmpleado().getIdThmEmpleado());
		ventaDto.setIdPorcentajeIva(venta.getPosPorcentajesIva().getId());
		ventaDto.setNombresApellidosCliente(
				venta.getAdmcliCliente().getNombre() + " " + venta.getAdmcliCliente().getApellido());
		ventaDto.setNombresApellidosEmpleado(venta.getThmEmpleado().getSegUsuario().getNombres() + " "
				+ venta.getThmEmpleado().getSegUsuario().getApellidos());
		ventaDto.setPorcentajeIva(venta.getPosPorcentajesIva().getPorcentaje().intValue());
		ventaDto.setSubtotal(0);
		for (PosDetalleVenta detalleVenta : venta.getPosDetallesVentas()) {
			ventaDto.setSubtotal(ventaDto.getSubtotal() + (detalleVenta.getPrecioVenta().doubleValue() * detalleVenta.getCantidad()));
		}
		ventaDto.setIva(ventaDto.getSubtotal() * ventaDto.getPorcentajeIva() / 100);
		ventaDto.setTotal(ventaDto.getSubtotal() + ventaDto.getIva());
		return ventaDto;
	}

	private List<VentaDto> toVentasDtos(List<PosVenta> ventas) {
		List<VentaDto> ventasDtos = new ArrayList<VentaDto>();
		for (PosVenta venta : ventas) {
			ventasDtos.add(toVentaDto(venta));
		}
		return ventasDtos;
	}

	public List<VentaDto> findAllVentasDtos() {
		return toVentasDtos(findAllVentas());
	}

	/**
	 * 
	 * @param idEmpleado
	 * @return
	 */
	public long getSellsNumberByEmployeeId(int idEmpleado) {
		try {
			auditoria.mostrarLog(getClass(), "getSellsNumberByEmployeeId",
					"Ha iniciado la busqueda de la cantidad de ventas del empleado: " + idEmpleado);
			IdEmpleadoCantidadVentasView empleadoVentas = new IdEmpleadoCantidadVentasView();
			empleadoVentas = (IdEmpleadoCantidadVentasView) dao.findById(IdEmpleadoCantidadVentasView.class,
					idEmpleado);
			return empleadoVentas.getCount();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			auditoria.mostrarLog(getClass(), "getSellsNumberByEmployeeId",
					"Ha ocurrido un error en la busqueda de la cantidad de ventas correspondientes al empleado: "
							+ idEmpleado);
			return 0;
		}
	}

	public long getSoldProductsAmmountByEmployeeId(int idEmpleado) {
		try {
			auditoria.mostrarLog(getClass(), "getSoldProductsAmmountByEmployeeId",
					"Ha iniciado la busqueda de la cantidad de productos del empleado: " + idEmpleado);
			IdEmpleadoCantidadProductosView empleadoProductos;
			empleadoProductos = (IdEmpleadoCantidadProductosView) dao.findById(IdEmpleadoCantidadProductosView.class,
					idEmpleado);
			return empleadoProductos.getCount();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			auditoria.mostrarLog(getClass(), "getSellsNumberByEmployeeId",
					"Ha ocurrido un error en la busqueda de la cantidad de ventas correspondientes al empleado: "
							+ idEmpleado);
			return 0;
		}
	}

	public long getAtendedClientsNumberByEmployeeId(int idEmpleado) {
		try {
			auditoria.mostrarLog(getClass(), "getAtendedClientsNumberByEmployeeId",
					"Ha iniciado la busqueda de la cantidad de clientes del empleado: " + idEmpleado);
			IdEmpleadoCantidadClientesView empleadoClientes;
			empleadoClientes = (IdEmpleadoCantidadClientesView) dao.findById(IdEmpleadoCantidadClientesView.class,
					idEmpleado);
			return empleadoClientes.getCount();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			auditoria.mostrarLog(getClass(), "getSellsNumberByEmployeeId",
					"Ha ocurrido un error en la busqueda de la cantidad de ventas correspondientes al empleado: "
							+ idEmpleado);
			return 0;
		}
	}

	@SuppressWarnings("unchecked")
	public void createVentaAndDetalles(int idCliente, int idThmEmpleado, int idPorcentajeIva,
			List<ProductoDto> productosDtos) throws Exception {

		if (productosDtos.isEmpty()) {
			throw new Exception(
					"No existen productos en el carrito de compras, por favor registre algunos o reinicie el proceso.");
		}
		auditoria.mostrarLog(getClass(), "createVentaAndDetalles",
				"Creacion de cabecera de venta y detalles iniciado para " + productosDtos.size() + " detalles.");
		createVenta(idCliente, idThmEmpleado, idPorcentajeIva);
		auditoria.mostrarLog(getClass(), "createVenta",
				"Inicio del proceso de busqueda de la útima cabecera registrada");
		int lastValue = 0;
		List<PosVenta> ventas = dao.findAll(PosVenta.class);
		for (PosVenta venta : ventas) {
			if (venta.getId() > lastValue) {
				lastValue = venta.getId();
			}
		}

		auditoria.mostrarLog(getClass(), "createVenta", "Busqueda de la ultima cabecera exitosa " + lastValue);

		for (ProductoDto productoDto : productosDtos) {
			detalleVentaManager.createDetalleVenta(productoDto.getId(), productoDto.getCostoVenta(),
					productoDto.getCantidadSeleccionada(), lastValue);
		}

		auditoria.mostrarLog(getClass(), "createVenta", "Finalización del proceso de creación exitosa.");

	}
}
