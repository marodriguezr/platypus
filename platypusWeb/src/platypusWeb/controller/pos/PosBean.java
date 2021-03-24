package platypusWeb.controller.pos;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.model.DualListModel;

import platypusEJB.model.admcli.managers.ManagerCliente;
import platypusEJB.model.core.entities.AdmcliCliente;
import platypusEJB.model.core.entities.InvProducto;
import platypusEJB.model.core.entities.PosDetalleVenta;
import platypusEJB.model.core.entities.PosPorcentajeIva;
import platypusEJB.model.core.entities.PosVenta;
import platypusEJB.model.core.entities.ThmEmpleado;
import platypusEJB.model.inventarioproductos.dtos.ProductoDto;
import platypusEJB.model.inventarioproductos.managers.ManagerInventarioProductos;
import platypusEJB.model.pos.dtos.VentaDto;
import platypusEJB.model.pos.managers.DetalleVentaManager;
import platypusEJB.model.pos.managers.PorcentajeIvaManager;
import platypusEJB.model.pos.managers.VentaManager;
import platypusEJB.model.thumano.dtos.ThmEmpleadoDto;
import platypusEJB.model.thumano.managers.ManagerTHumano;
import platypusWeb.controller.seguridades.BeanSegLogin;
import platypusWeb.controller.utilities.JSFUtil;

@SuppressWarnings("serial")
@Named
@SessionScoped
public class PosBean implements Serializable {

	/**
	 * Manager para la interacción con los productos
	 */
	@EJB
	private ManagerInventarioProductos productoManager;

	/**
	 * Manager para la interacción con la venta
	 */
	@EJB
	private VentaManager ventaManager;

	/**
	 * Mánager para la interación con los detalles de venta
	 */
	@EJB
	private DetalleVentaManager detalleVentaManager;

	/**
	 * Manager para la interación con el módulo de clientes
	 */
	@EJB
	private ManagerCliente clienteManager;

	/**
	 * Manager para la interacción con el módulo de talento humano.
	 */
	@EJB
	private ManagerTHumano thumanoManager;
	
	/**
	 * Manager para la interacción con el manager de los porcentajes de iva
	 */
	@EJB
	private PorcentajeIvaManager porcentajeIvaManager;

	/**
	 * Lista de productos disponibles que pueden ser comprados
	 */
	private List<InvProducto> productosDisponibles;

	/**
	 * Lista de productos que se han seleccionado
	 */
	private List<InvProducto> productosSeleccionados;

	/**
	 * Lista de todas las ventas
	 */
	private List<PosVenta> ventas;

	/**
	 * Lista de detalles de ventas;
	 */
	private List<PosDetalleVenta> detallesVentas;

	/**
	 * Lista de todos los dtos ventas
	 */
	private List<VentaDto> ventasDtos;

	/**
	 * Lista dual de productos disponibles y seleccionadoss
	 */
	private DualListModel<InvProducto> productos;
	
	/**
	 * Lista de dtos de productos disponibles
	 */
	private List<ProductoDto> productosDisponiblesDtos;
	
	/**
	 * Lista de todos los porcentajes de iva disponibles
	 */
	private List<PosPorcentajeIva> porcentajesIva;

	/**
	 * Objeto tipo cliente con propositos de vistas
	 */
	private AdmcliCliente cliente;

	/**
	 * Objeto tipo thmEmpleado con propositos de vistas
	 */
	private ThmEmpleado empleado;
	
	/**
	 * Objeto tipo ThmEmpleadoDto con propositos de vistas
	 */
	private ThmEmpleadoDto empleadoDto;
	
	/**
	 * Objeto tipo PorcentajeIva con propositos de manipulación de datos
	 */
	private PosPorcentajeIva porcentajeIva;
	
	/**
	 * Objetos partes de una inyección
	 */
	@Inject
	private BeanSegLogin beanSegLogin;

	public PosBean() {
		// TODO Auto-generated constructor stub
	}
	@PostConstruct
	public void init() {
		initEmpleadoDtoBySegUsuarioId(beanSegLogin.getIdSegUsuario());
	}

	public void initProductosDisponibles() {
		productosDisponibles = productoManager.findAllInvProductos();
	}

	public void initProductosSeleccionados() {
		productosSeleccionados = new ArrayList<>();
	}

	public void initVentasDtos() {
		ventasDtos = ventaManager.findAllVentasDtos();
	}

	public void initProductos() {
		productos = new DualListModel<>(productosDisponibles, productosSeleccionados);
	}

	public void initVentas() {
		ventas = ventaManager.findAllVentas();
	}
	
	public void initPorcentajeIva() {
		porcentajeIva = new PosPorcentajeIva();
	}

	public void initDetallesVentasByVentaId(int id) {
		try {
			detallesVentas = detalleVentaManager.findAllDetallesVentasByVentaId(id);
			JSFUtil.crearMensajeINFO("Busqueda de los detalles de venta exitosa.");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			JSFUtil.crearMensajeINFO("Ha ocurrido un error en la busqueda de los detalles de venta.");
		}
	}

	public void initClienteById(int idCliente) {
		try {
			cliente = clienteManager.findCienteById(idCliente);
			JSFUtil.crearMensajeINFO("Busqueda de datos de cliente exitosa.");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			JSFUtil.crearMensajeERROR("Ha ocurrido un error en la busqueda de los datos del cliente.");
		}
	}

	public void initEmpleadoById(int idEmpleado) {
		try {
			empleado = thumanoManager.findEmpleadoById(idEmpleado);
			JSFUtil.crearMensajeINFO("Busqueda de datos de empleado exitosa.");
		} catch (Exception e) {
			// TODO: handle exception
			JSFUtil.crearMensajeERROR("Ha ocurrido un error en la busqueda de los datos del empleado.");
		}
	}
	
	public void initEmpleadoDtoBySegUsuarioId(int id) {
		try {
			empleadoDto = thumanoManager.toThmEmpleadoDto(thumanoManager.findEmpleadoByUsuarioId(id));
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	public void initProductosDisponiblesDtos() {
		try {
			productosDisponiblesDtos = productoManager.toProductosDtos(productoManager.findAllNonExpiredAvailableProducts());
		} catch (Exception e) {
			// TODO: handle exception
			JSFUtil.crearMensajeERROR("Ha ocurrido un error" + e.getMessage());
		}
	}
	
	public void initPorcentajesIva() {
		try {
			porcentajesIva = porcentajeIvaManager.findAllPorcentajesIva();
		} catch (Exception e) {
			// TODO: handle exception
			JSFUtil.crearMensajeERROR("Ha ocurrido un error " + e.getMessage());
		}
	}

	public String actionOpenRegistrarVentaSeleccionProductosView() {
		initProductosDisponiblesDtos();
		initPorcentajesIva();
		initPorcentajeIva();
		return "registrarVentaSeleccionProductos?faces-redirect=true";
	}

	public String actionOpenRegistrarVentaInformacionView() {
		return "registrarVentaInformacion?faces-redirect=true";
	}

	public String actionOpenRegistrarVentaPagoView() {
		return "registrarVentaPago?faces-redirect=true";
	}

	public String actionOpenRegistrarVentaConfirmacionView() {
		return "registrarVentaConfirmacion?faces-redirect=true";
	}

	public String actionOpenRevisarVentasView() {
		initVentas();
		initVentasDtos();
		return "revisarVentas?faces-redirect=true";
	}

	public List<InvProducto> getProductosDisponibles() {
		return productosDisponibles;
	}

	public void setProductosDisponibles(List<InvProducto> productosDisponibles) {
		this.productosDisponibles = productosDisponibles;
	}

	public List<InvProducto> getProductosSeleccionados() {
		return productosSeleccionados;
	}

	public void setProductosSeleccionados(List<InvProducto> productosSeleccionados) {
		this.productosSeleccionados = productosSeleccionados;
	}

	public DualListModel<InvProducto> getProductos() {
		return productos;
	}

	public void setProductos(DualListModel<InvProducto> productos) {
		this.productos = productos;
	}

	public List<PosVenta> getVentas() {
		return ventas;
	}

	public void setVentas(List<PosVenta> ventas) {
		this.ventas = ventas;
	}

	public List<VentaDto> getVentasDtos() {
		return ventasDtos;
	}

	public void setVentasDtos(List<VentaDto> ventasDtos) {
		this.ventasDtos = ventasDtos;
	}

	public AdmcliCliente getCliente() {
		return cliente;
	}

	public void setCliente(AdmcliCliente cliente) {
		this.cliente = cliente;
	}

	public ThmEmpleado getEmpleado() {
		return empleado;
	}

	public void setEmpleado(ThmEmpleado empleado) {
		this.empleado = empleado;
	}

	public List<PosDetalleVenta> getDetallesVentas() {
		return detallesVentas;
	}

	public void setDetallesVentas(List<PosDetalleVenta> detallesVentas) {
		this.detallesVentas = detallesVentas;
	}

	public ThmEmpleadoDto getEmpleadoDto() {
		return empleadoDto;
	}

	public void setEmpleadoDto(ThmEmpleadoDto empleadoDto) {
		this.empleadoDto = empleadoDto;
	}
	public List<ProductoDto> getProductosDisponiblesDtos() {
		return productosDisponiblesDtos;
	}
	public void setProductosDisponiblesDtos(List<ProductoDto> productosDisponiblesDtos) {
		this.productosDisponiblesDtos = productosDisponiblesDtos;
	}
	public List<PosPorcentajeIva> getPorcentajesIva() {
		return porcentajesIva;
	}
	public void setPorcentajesIva(List<PosPorcentajeIva> porcentajesIva) {
		this.porcentajesIva = porcentajesIva;
	}
	public PosPorcentajeIva getPorcentajeIva() {
		return porcentajeIva;
	}
	public void setPorcentajeIva(PosPorcentajeIva porcentajeIva) {
		this.porcentajeIva = porcentajeIva;
	}
}
