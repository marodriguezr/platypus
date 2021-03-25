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
	 * Managers
	 */
	@EJB
	private ManagerInventarioProductos productoManager;
	@EJB
	private VentaManager ventaManager;
	@EJB
	private DetalleVentaManager detalleVentaManager;
	@EJB
	private ManagerCliente clienteManager;
	@EJB
	private ManagerTHumano thumanoManager;
	@EJB
	private PorcentajeIvaManager porcentajeIvaManager;

	/**
	 * Listas de entidades
	 */
	private List<InvProducto> productosDisponibles;
	private List<InvProducto> productosSeleccionados;
	private List<PosVenta> ventas;
	private List<PosDetalleVenta> detallesVentas;
	private List<PosPorcentajeIva> porcentajesIva;
	private DualListModel<InvProducto> productos;
	private List<AdmcliCliente> clientes;

	/**
	 * Listas de dtos
	 */
	private List<VentaDto> ventasDtos;
	private List<ProductoDto> productosDisponiblesDtos;
	private List<ProductoDto> productosDisponiblesSeleccionadosDtos;
	private List<ProductoDto> productosAgregadosDtos;
	private List<ProductoDto> productosAgregadosSeleccionadosDtos;

	/**
	 * Entidades
	 */
	private AdmcliCliente cliente;
	private ThmEmpleado empleado;
	private PosPorcentajeIva porcentajeIva;

	/**
	 * DTOS
	 */
	private ThmEmpleadoDto empleadoDto;
	private VentaDto ventaDto;

	/**
	 * Objetos y datos correspondientes a propiedades de las páginas
	 */
	private boolean addProductsButtonState;
	private boolean porcentajesIvaMenuState;
	private int productosRequeridosState;
	private double pagoIngresoState;
	private double pagoCambioState;

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
			productosDisponiblesDtos = productoManager
					.toProductosDtos(productoManager.findAllNonExpiredAvailableProducts());
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

	public void initAddProductsButtonState() {
		addProductsButtonState = false;
	}

	public void initPorcentajesIvaMenuState() {
		porcentajesIvaMenuState = true;
	}

	public void initProductosRequeridosState() {
		productosRequeridosState = 1;
	}

	public void initProductosAgregadosDtos() {
		productosAgregadosDtos = new ArrayList<ProductoDto>();
	}

	public void initVentaDto() {
		ventaDto = new VentaDto();
	}

	public void initClientes() {
		clientes = clienteManager.findAllAdmcliClientes();
	}

	public void initCliente() {
		cliente = new AdmcliCliente();
		cliente.setId(0);
	}

	public void initPagoIngresoState() {
		pagoIngresoState = 0;
	}

	public void initPagoCambioState() {
		pagoCambioState = 0;
	}

	public String actionOpenRegistrarVentaSeleccionProductosView() {
		initProductosDisponiblesDtos();
		initPorcentajesIva();
		initPorcentajeIva();
		initAddProductsButtonState();
		initPorcentajesIvaMenuState();
		initProductosRequeridosState();
		initProductosAgregadosDtos();
		initVentaDto();
		initClientes();
		initCliente();
		initPagoIngresoState();
		initPagoCambioState();
		return "registrarVentaSeleccionProductos?faces-redirect=true";
	}

	public String actionOpenRegistrarVentaInformacionView() {
		if (hasItems(productosAgregadosDtos)) {
			return "registrarVentaInformacion?faces-redirect=true";
		} else {
			JSFUtil.crearMensajeWARN("Por favor, agregue productos antes de continuar al siguiente paso.");
			return "";
		}
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

	public String actionConfirmarVenta() {
		try {
			/*
			 * ventaManager.createVenta(1, empleadoDto.getId(), porcentajeIva.getId(),
			 * productosAgregadosDtos);
			 */
			ventaManager.createVentaAndDetalles(cliente.getId(), empleadoDto.getId(), porcentajeIva.getId(), productosAgregadosDtos);
			JSFUtil.crearMensajeINFO("Venta registrada con éxito");
			initProductosAgregadosDtos();
			initVentaDto();
			return "registrarVentaFeedback?faces-redirect=true";
		} catch (Exception e) {
			// TODO: handle exception
			JSFUtil.crearMensajeERROR("" + e.getMessage());
			e.printStackTrace();
			return "";
		}
	}

	public void actionListenerAgregarProducto() {
		if (isPorcentajesIvaMenuState()) {
			if (isPorcentajeIvaNull()) {
				JSFUtil.crearMensajeWARN(
						"Por favor seleccione un porcentaje de iva a ser aplicado antes de continuar.");
				return;
			}
		}
		if (!hasSelectedProducts()) {
			JSFUtil.crearMensajeWARN("Por favor seleccione uno o varios productos para continuar");
			return;
		}
	
		setPorcentajesIvaMenuState(false);
		replaceProductosDisponiblesSeleccionadosProductosAgregados();
		System.out.println("Ha pasado" + porcentajeIva.getId());
	}

	public void replaceProductosDisponiblesSeleccionadosProductosAgregados() {
		for (ProductoDto productoDisponibleSeleccionadoDto : productosDisponiblesSeleccionadosDtos) {
			if (productoDisponibleSeleccionadoDto.getCantidadDisponible() < productosRequeridosState) {
				JSFUtil.crearMensajeINFO("Solo existen " + productoDisponibleSeleccionadoDto.getCantidadDisponible()
						+ " " + productoDisponibleSeleccionadoDto.getNombre()
						+ " por favor, seleccione una cantidad adecuada.");
			} else if (productoDisponibleSeleccionadoDto.getCantidadDisponible() > productosRequeridosState) {
				addProductoAgregadoDto(productoDisponibleSeleccionadoDto);
				for (ProductoDto productoDisponibleDto : productosDisponiblesDtos) {
					if (productoDisponibleDto.getId() == productoDisponibleSeleccionadoDto.getId()) {
						decreaseProductoAvailableAmmountDto(productoDisponibleDto, productosRequeridosState);
						break;
					}
				}
			} else {
				addProductoAgregadoDto(productoDisponibleSeleccionadoDto);
				for (ProductoDto productoDisponibleDto : productosDisponiblesDtos) {
					if (productoDisponibleDto.getId() == productoDisponibleSeleccionadoDto.getId()) {
						productosDisponiblesDtos.remove(productoDisponibleDto);
						break;
					}
				}
			}
		}
		productosDisponiblesSeleccionadosDtos = null;
	}

	public void decreaseProductoAvailableAmmountDto(ProductoDto producto, int cantidad) {
		producto.setCantidadDisponible(producto.getCantidadDisponible() - cantidad);
	}

	public void addProductoAgregadoDto(ProductoDto productoSeleccionadoDto) {
		ProductoDto productoAgregadoDto = findProductoDtoOnProductosAgregadosDtos(productoSeleccionadoDto);
		if (productoAgregadoDto == null) {
			System.out.println("Executed the first time with: " + productosRequeridosState);
			productoAgregadoDto = new ProductoDto();
			productoAgregadoDto.setId(productoSeleccionadoDto.getId());
			productoAgregadoDto.setNombre(productoSeleccionadoDto.getNombre());
			productoAgregadoDto.setCantidadSeleccionada(productosRequeridosState);
			productoAgregadoDto.setCostoVenta(productoSeleccionadoDto.getCostoVenta());
			productoAgregadoDto.setCostoTotal(productoSeleccionadoDto.getCostoVenta() * productosRequeridosState);
			productosAgregadosDtos.add(productoAgregadoDto);
		} else {
			productoAgregadoDto
					.setCantidadSeleccionada(productoAgregadoDto.getCantidadSeleccionada() + productosRequeridosState);
			productoAgregadoDto.setCostoTotal(
					productoSeleccionadoDto.getCostoVenta() * productoAgregadoDto.getCantidadSeleccionada());
		}
		updateTotalesOnVentaDto(productoSeleccionadoDto);
	}

	public ProductoDto findProductoDtoOnProductosAgregadosDtos(ProductoDto productoSeleccionadoDto) {
		for (ProductoDto productoAgregadoDto : productosAgregadosDtos) {
			if (productoAgregadoDto.getId() == productoSeleccionadoDto.getId()) {
				return productoAgregadoDto;
			}
		}
		return null;
	}

	public void updateTotalesOnVentaDto(ProductoDto productoDisponibleSeleccionadoDto) {
		if (porcentajeIva.getPorcentaje() == null) {
			try {
				porcentajeIva = porcentajeIvaManager.findPorcentajeIvaById(porcentajeIva.getId());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		ventaDto.setSubtotal(
				ventaDto.getSubtotal() + productoDisponibleSeleccionadoDto.getCostoVenta() * productosRequeridosState);
		ventaDto.setIva(ventaDto.getSubtotal() * porcentajeIva.getPorcentaje().intValue() / 100);
		ventaDto.setTotal(ventaDto.getSubtotal() + ventaDto.getIva());
	}

	public boolean isPorcentajeIvaNull() {
		return porcentajeIva.getId() == null;
	}

	public boolean hasSelectedProducts() {
		return hasItems(this.productosDisponiblesSeleccionadosDtos);
	}

	@SuppressWarnings("rawtypes")
	public boolean hasItems(List list) {
		return list != null && !list.isEmpty();
	}

	public void checkCambio() {
		System.out.println("Cambios");
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

	public boolean isAddProductsButtonState() {
		return addProductsButtonState;
	}

	public void setAddProductsButtonState(boolean addProductsButtonState) {
		this.addProductsButtonState = addProductsButtonState;
	}

	public boolean isPorcentajesIvaMenuState() {
		return porcentajesIvaMenuState;
	}

	public void setPorcentajesIvaMenuState(boolean porcentajesIvaMenuState) {
		this.porcentajesIvaMenuState = porcentajesIvaMenuState;
	}

	public int getProductosRequeridosState() {
		return productosRequeridosState;
	}

	public void setProductosRequeridosState(int productosRequeridosState) {
		this.productosRequeridosState = productosRequeridosState;
	}

	public List<ProductoDto> getProductosDisponiblesSeleccionadosDtos() {
		return productosDisponiblesSeleccionadosDtos;
	}

	public void setProductosDisponiblesSeleccionadosDtos(List<ProductoDto> productosDisponiblesSeleccionadosDtos) {
		this.productosDisponiblesSeleccionadosDtos = productosDisponiblesSeleccionadosDtos;
	}

	public List<ProductoDto> getProductosAgregadosDtos() {
		return productosAgregadosDtos;
	}

	public void setProductosAgregadosDtos(List<ProductoDto> productosAgregadosDtos) {
		this.productosAgregadosDtos = productosAgregadosDtos;
	}

	public List<ProductoDto> getProductosAgregadosSeleccionadosDtos() {
		return productosAgregadosSeleccionadosDtos;
	}

	public void setProductosAgregadosSeleccionadosDtos(List<ProductoDto> productosAgregadosSeleccionadosDtos) {
		this.productosAgregadosSeleccionadosDtos = productosAgregadosSeleccionadosDtos;
	}

	public VentaDto getVentaDto() {
		return ventaDto;
	}

	public void setVentaDto(VentaDto ventaDto) {
		this.ventaDto = ventaDto;
	}

	public List<AdmcliCliente> getClientes() {
		return clientes;
	}

	public void setClientes(List<AdmcliCliente> clientes) {
		this.clientes = clientes;
	}

	public double getPagoIngresoState() {
		return pagoIngresoState;
	}

	public void setPagoIngresoState(double pagoIngresoState) {
		this.pagoIngresoState = pagoIngresoState;
	}

	public double getPagoCambioState() {
		return pagoCambioState;
	}

	public void setPagoCambioState(double pagoCambioState) {
		this.pagoCambioState = pagoCambioState;
	}
}
