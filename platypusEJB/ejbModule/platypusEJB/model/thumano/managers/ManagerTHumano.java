package platypusEJB.model.thumano.managers;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import platypusEJB.model.auditoria.managers.ManagerAuditoria;
import platypusEJB.model.core.entities.ThmCargo;
import platypusEJB.model.core.entities.ThmEmpleado;
import platypusEJB.model.core.entities.ThmRolCabecera;
import platypusEJB.model.core.entities.ThmRolDetalle;
import platypusEJB.model.core.managers.ManagerDAO;
import platypusEJB.model.pos.managers.VentaManager;
import platypusEJB.model.seguridades.managers.ManagerSeguridades;
import platypusEJB.model.thumano.dtos.DTOThmCargo;
import platypusEJB.model.thumano.dtos.ThmEmpleadoDto;

/**
 * Session Bean implementation class ManagerTHumano
 */
@Stateless
@LocalBean
public class ManagerTHumano {
	public final static double PORCENTAJE_IESS = 0.0935;
	public final static double PORCENTAJE_FONDOS_RESERVA = 0.0833;
	public final static int INCREMENTO_HEXTRA = 2;

	@EJB
	private ManagerDAO mDAO;
	@EJB
	private ManagerSeguridades mSeguridades;
	@EJB
	private ManagerAuditoria mAuditoria;
	@EJB
	private VentaManager ventaManager;

	/**
	 * Default constructor.
	 */
	public ManagerTHumano() {

	}

	// EMPLEADOS:
	@SuppressWarnings("unchecked")
	public List<ThmEmpleado> findAllThmEmpleado() {
		return mDAO.findAll(ThmEmpleado.class);
	}

	public ThmEmpleado insertarThmEmpleado(ThmEmpleado nuevoEmpleado, int idThmCargo, int idSegUsuario)
			throws Exception {
		ThmEmpleado nuevo = new ThmEmpleado();
		Random rnd = new Random();
		nuevo.setCuotaPrestamo(new BigDecimal(100 * rnd.nextDouble()));// prestamo entre 0 y 100
		nuevo.setHorasExtra(rnd.nextInt(20));// maximo 20 horas extra
		nuevo.setHorasTrabajadas(160);// 160 horas mensuales
		nuevo.setSegUsuario(mSeguridades.findByIdSegUsuario(idSegUsuario));
		nuevo.setThmCargo(findByIdThmCargo(idThmCargo));
		mDAO.insertar(nuevo);
		return nuevo;
	}

	// CARGOS:
	public ThmCargo findByIdThmCargo(int idThmCargo) throws Exception {
		return (ThmCargo) mDAO.findById(ThmCargo.class, idThmCargo);
	}

	@SuppressWarnings("unchecked")
	public List<ThmCargo> findAllThmCargo() {
		return mDAO.findAll(ThmCargo.class, "nombreCargo");
	}

	public List<DTOThmCargo> findAllDTOThmCargo() {
		List<DTOThmCargo> listaDTO = new ArrayList<DTOThmCargo>();
		for (ThmCargo cargo : findAllThmCargo()) {
			DTOThmCargo c = new DTOThmCargo(cargo.getIdThmCargo(), cargo.getNombreCargo(),
					cargo.getRemuneracionMensual().doubleValue());
			listaDTO.add(c);
		}
		return listaDTO;
	}

	// ROL DE PAGOS:

	@SuppressWarnings("unchecked")
	public List<ThmRolCabecera> findAllThmRolCabecera() {
		return mDAO.findAll(ThmRolCabecera.class);
	}

	public void generarRolPagos(String periodoRol) throws Exception {
		// Iteramos la lista de empleados:
		List<ThmEmpleado> listaEmpleados = findAllThmEmpleado();
		if (listaEmpleados.size() == 0)
			throw new Exception("No existen empleados registrados.");
		for (ThmEmpleado empleado : listaEmpleados) {
			// por cada empleado se genera la cabecera del rol:
			ThmRolCabecera cab = new ThmRolCabecera();
			cab.setHorasExtras(empleado.getHorasExtra());
			cab.setHorasTrabajadas(empleado.getHorasTrabajadas());
			cab.setNombreCargo(empleado.getThmCargo().getNombreCargo());
			cab.setPeriodoRol(periodoRol);
			cab.setThmEmpleado(empleado);
			// generar el detalle de cada rol cabecera:
			
			//obtener datos de facturas desde api rest:
			
			
			
			generarDetalleRolPagos(cab, empleado);
			mDAO.insertar(cab);
		}
	}

	private void generarDetalleRolPagos(ThmRolCabecera cab, ThmEmpleado emp) {
		List<ThmRolDetalle> detalles = new ArrayList<ThmRolDetalle>();
		double subtotal = 0;
		double total = 0;

		ThmRolDetalle det = new ThmRolDetalle();
		det.setDescripcion("Sueldo");
		det.setOrden(1);
		det.setThmRolCabecera(cab);
		det.setTipoDetalle("IN");
		det.setValor(emp.getThmCargo().getRemuneracionMensual());
		detalles.add(det);
		subtotal += det.getValor().doubleValue();

		det = new ThmRolDetalle();
		det.setDescripcion("Horas extras");
		det.setOrden(2);
		det.setThmRolCabecera(cab);
		det.setTipoDetalle("IN");
		det.setValor(new BigDecimal(emp.getHorasExtra() * emp.getThmCargo().getRemuneracionMensual().doubleValue()
				/ emp.getHorasTrabajadas() * INCREMENTO_HEXTRA));
		detalles.add(det);
		subtotal += det.getValor().doubleValue();

		cab.setSubtotalIngresos(new BigDecimal(subtotal));
		subtotal = 0;

		det = new ThmRolDetalle();
		det.setDescripcion("Décimo 3er sueldo");
		det.setOrden(3);
		det.setThmRolCabecera(cab);
		det.setTipoDetalle("IA");
		det.setValor(new BigDecimal(emp.getThmCargo().getRemuneracionMensual().doubleValue() / 12));
		detalles.add(det);
		subtotal += det.getValor().doubleValue();

		det = new ThmRolDetalle();
		det.setDescripcion("Fondos de reserva");
		det.setOrden(4);
		det.setThmRolCabecera(cab);
		det.setTipoDetalle("IA");
		det.setValor(
				new BigDecimal(emp.getThmCargo().getRemuneracionMensual().doubleValue() * PORCENTAJE_FONDOS_RESERVA));
		detalles.add(det);
		subtotal += det.getValor().doubleValue();

		cab.setSubtotalIngresosAdicionales(new BigDecimal(subtotal));
		subtotal = 0;

		det = new ThmRolDetalle();
		det.setDescripcion("Aporte IESS");
		det.setOrden(5);
		det.setThmRolCabecera(cab);
		det.setTipoDetalle("EG");
		det.setValor(new BigDecimal(cab.getSubtotalIngresos().doubleValue() * PORCENTAJE_IESS));
		detalles.add(det);
		subtotal += det.getValor().doubleValue();

		det = new ThmRolDetalle();
		det.setDescripcion("Préstamos");
		det.setOrden(6);
		det.setThmRolCabecera(cab);
		det.setTipoDetalle("EG");
		det.setValor(emp.getCuotaPrestamo());
		detalles.add(det);
		subtotal += det.getValor().doubleValue();

		cab.setSubtotalEgresos(new BigDecimal(subtotal));
		subtotal = 0;

		total = cab.getSubtotalIngresos().doubleValue() + cab.getSubtotalIngresosAdicionales().doubleValue()
				- cab.getSubtotalEgresos().doubleValue();
		cab.setTotal(new BigDecimal(total));

		cab.setThmRolDetalles(detalles);
	}

	public ThmEmpleado findEmpleadoById(int id) throws Exception {
		mAuditoria.mostrarLog(getClass(), "findEmpleadoById", "Se ha disparado la busqueda del cliente: " + id);
		return (ThmEmpleado) mDAO.findById(ThmEmpleado.class, id);
	}
	
	@SuppressWarnings("unchecked")
	public ThmEmpleado findEmpleadoByUsuarioId(int id) throws Exception {
		List<ThmEmpleado> empleado = (List<ThmEmpleado>) mDAO.findWhere(ThmEmpleado.class, "o.segUsuario.idSegUsuario = " + id, null);
		if (empleado != null) {
			return empleado.get(0);
		}
		return null;
	}
	
	public ThmEmpleadoDto toThmEmpleadoDto(ThmEmpleado empleado) {
		ThmEmpleadoDto empleadoDto = new ThmEmpleadoDto();
		empleadoDto.setId(empleado.getIdThmEmpleado());
		empleadoDto.setIdSegUsuario(empleado.getSegUsuario().getIdSegUsuario());
		empleadoDto.setNombres(empleado.getSegUsuario().getNombres());
		empleadoDto.setApellidos(empleado.getSegUsuario().getApellidos());
		empleadoDto.setCargo(empleado.getThmCargo().getNombreCargo());
		empleadoDto.setCorreo(empleado.getSegUsuario().getCorreo());
		empleadoDto.setEstado(empleado.getSegUsuario().getActivo());
		empleadoDto.setClientesAtendidos(ventaManager.getAtendedClientsNumberByEmployeeId(empleado.getIdThmEmpleado()));
		empleadoDto.setProductosVendidos(ventaManager.getSoldProductsAmmountByEmployeeId(empleado.getIdThmEmpleado()));
		empleadoDto.setVentasRegistradas(ventaManager.getSellsNumberByEmployeeId(empleado.getIdThmEmpleado()));
		return empleadoDto;
	}
}
