package platypusEJB.model.core.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the inventario_productos database table.
 * 
 */
@Entity
@Table(name="inventario_productos")
@NamedQuery(name="InventarioProducto.findAll", query="SELECT i FROM InventarioProducto i")
public class InventarioProducto implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private Integer id;

	@Column(name="cantidad_disponible", nullable=false)
	private Integer cantidadDisponible;

	@Column(name="costo_compra", nullable=false, precision=8, scale=2)
	private BigDecimal costoCompra;

	@Column(name="costo_venta", nullable=false, precision=8, scale=2)
	private BigDecimal costoVenta;

	@Temporal(TemporalType.DATE)
	@Column(name="fechas_expiracion")
	private Date fechasExpiracion;

	//bi-directional many-to-one association to DetallesVenta
	@OneToMany(mappedBy="inventarioProducto")
	private List<DetallesVenta> detallesVentas;

	//bi-directional many-to-one association to Producto
	@ManyToOne
	@JoinColumn(name="id_producto", nullable=false)
	private Producto producto;

	//bi-directional many-to-one association to Recepcione
	@ManyToOne
	@JoinColumn(name="id_entrega", nullable=false)
	private Recepcione recepcione;

	public InventarioProducto() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getCantidadDisponible() {
		return this.cantidadDisponible;
	}

	public void setCantidadDisponible(Integer cantidadDisponible) {
		this.cantidadDisponible = cantidadDisponible;
	}

	public BigDecimal getCostoCompra() {
		return this.costoCompra;
	}

	public void setCostoCompra(BigDecimal costoCompra) {
		this.costoCompra = costoCompra;
	}

	public BigDecimal getCostoVenta() {
		return this.costoVenta;
	}

	public void setCostoVenta(BigDecimal costoVenta) {
		this.costoVenta = costoVenta;
	}

	public Date getFechasExpiracion() {
		return this.fechasExpiracion;
	}

	public void setFechasExpiracion(Date fechasExpiracion) {
		this.fechasExpiracion = fechasExpiracion;
	}

	public List<DetallesVenta> getDetallesVentas() {
		return this.detallesVentas;
	}

	public void setDetallesVentas(List<DetallesVenta> detallesVentas) {
		this.detallesVentas = detallesVentas;
	}

	public DetallesVenta addDetallesVenta(DetallesVenta detallesVenta) {
		getDetallesVentas().add(detallesVenta);
		detallesVenta.setInventarioProducto(this);

		return detallesVenta;
	}

	public DetallesVenta removeDetallesVenta(DetallesVenta detallesVenta) {
		getDetallesVentas().remove(detallesVenta);
		detallesVenta.setInventarioProducto(null);

		return detallesVenta;
	}

	public Producto getProducto() {
		return this.producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	public Recepcione getRecepcione() {
		return this.recepcione;
	}

	public void setRecepcione(Recepcione recepcione) {
		this.recepcione = recepcione;
	}

}