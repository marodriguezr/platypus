package platypusEJB.model.pos.managers;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import platypusEJB.model.auditoria.managers.ManagerAuditoria;
import platypusEJB.model.core.entities.AdmcliCliente;
import platypusEJB.model.core.entities.PosDetalleVenta;
import platypusEJB.model.core.entities.PosPorcentajeIva;
import platypusEJB.model.core.entities.PosVenta;
import platypusEJB.model.core.entities.ThmEmpleado;
import platypusEJB.model.core.managers.ManagerDAO;
import platypusEJB.model.core.views.IdEmpleadoCantidadClientesView;
import platypusEJB.model.core.views.IdEmpleadoCantidadProductosView;
import platypusEJB.model.core.views.IdEmpleadoCantidadVentasView;
import platypusEJB.model.pos.dtos.VentaDto;
import platypusEJB.model.thumano.managers.ManagerTHumano;

/**
 * Session Bean implementation class VentaManager
 */
@Stateless
@LocalBean
public class VentaManager {

	/**
	 * Manager para la interacci�n con los datos.
	 */
	@EJB
	private ManagerDAO dao;
	/**
	 * Manager para la interacci�n con el m�dulo de auditoria.
	 */
	@EJB
	private ManagerAuditoria auditoria;
	@EJB
	private ManagerTHumano thumano;

	/**
	 * Default constructor.
	 */
	public VentaManager() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * M�todo para la busqueda de todas las ventas.
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
			auditoria.mostrarLog(getClass(), "findVentaById", "La venta: " + id + " no est� registrada.");
			throw new Exception("La venta: " + id + " no est� registrada.");
		}
		auditoria.mostrarLog(getClass(), "findVentaById", "Se ha devuelto la venta: " + id);
		return venta;
	}

	public void createVenta(int idCliente, int idThmEmpleado, int idPorcentajeIva) throws Exception {
		AdmcliCliente cliente = (AdmcliCliente) dao.findById(AdmcliCliente.class, idCliente);
		if (cliente == null) {
			throw new Exception("El cliente especificado no est� presente en la base de datos");
		}
		ThmEmpleado empleado = (ThmEmpleado) dao.findById(ThmEmpleado.class, idThmEmpleado);
		if (empleado == null) {
			throw new Exception("El empleado especificado no est� presente en la base de datos");
		}
		PosPorcentajeIva porcentajeIva = (PosPorcentajeIva) dao.findById(PosPorcentajeIva.class, idPorcentajeIva);
		if (porcentajeIva == null) {
			throw new Exception("El porcentaje de iva especificado no est� presente en la base de datos");
		}
		PosVenta venta = new PosVenta();
		venta.setAdmcliCliente(cliente);
		venta.setFechaVenta(new Date());
		venta.setPosPorcentajesIva(porcentajeIva);
		venta.setThmEmpleado(empleado);
		dao.insertar(venta);
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
		for (PosDetalleVenta detalleVenta : venta.getPosDetallesVentas()) {
			ventaDto.setSubtotal(ventaDto.getSubtotal() + detalleVenta.getPrecioVenta().doubleValue());
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
    		auditoria.mostrarLog(getClass(), "getSellsNumberByEmployeeId", "Ha iniciado la busqueda de la cantidad de ventas del empleado: " + idEmpleado);
    		IdEmpleadoCantidadVentasView empleadoVentas = new IdEmpleadoCantidadVentasView();
			empleadoVentas = (IdEmpleadoCantidadVentasView) dao.findById(IdEmpleadoCantidadVentasView.class, idEmpleado);
			return empleadoVentas.getCount();
    	} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			auditoria.mostrarLog(getClass(), "getSellsNumberByEmployeeId", "Ha ocurrido un error en la busqueda de la cantidad de ventas correspondientes al empleado: " + idEmpleado);
			return 0;
		}
    }
    
    public long getSoldProductsAmmountByEmployeeId(int idEmpleado) {
    	try {
    		auditoria.mostrarLog(getClass(), "getSoldProductsAmmountByEmployeeId", "Ha iniciado la busqueda de la cantidad de productos del empleado: " + idEmpleado);
    		IdEmpleadoCantidadProductosView empleadoProductos;
			empleadoProductos = (IdEmpleadoCantidadProductosView) dao.findById(IdEmpleadoCantidadProductosView.class, idEmpleado);
			return empleadoProductos.getCount();
    	} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			auditoria.mostrarLog(getClass(), "getSellsNumberByEmployeeId", "Ha ocurrido un error en la busqueda de la cantidad de ventas correspondientes al empleado: " + idEmpleado);
			return 0;
		}
    }
    
    public long getAtendedClientsNumberByEmployeeId(int idEmpleado) {
    	try {
    		auditoria.mostrarLog(getClass(), "getAtendedClientsNumberByEmployeeId", "Ha iniciado la busqueda de la cantidad de clientes del empleado: " + idEmpleado);
    		IdEmpleadoCantidadClientesView empleadoClientes;
    		empleadoClientes = (IdEmpleadoCantidadClientesView) dao.findById(IdEmpleadoCantidadClientesView.class, idEmpleado);
    		return empleadoClientes.getCount();
    	} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			auditoria.mostrarLog(getClass(), "getSellsNumberByEmployeeId", "Ha ocurrido un error en la busqueda de la cantidad de ventas correspondientes al empleado: " + idEmpleado);
			return 0;
		}
    }
}
