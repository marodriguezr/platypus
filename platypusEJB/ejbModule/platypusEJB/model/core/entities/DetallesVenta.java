package platypusEJB.model.core.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the detalles_ventas database table.
 * 
 */
@Entity
@Table(name="detalles_ventas")
@NamedQuery(name="DetallesVenta.findAll", query="SELECT d FROM DetallesVenta d")
public class DetallesVenta implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private Integer id;

	@Column(nullable=false)
	private Integer cantidad;

	@Column(name="precio_venta", nullable=false, precision=7, scale=2)
	private BigDecimal precioVenta;

	//bi-directional many-to-one association to InventarioProducto
	@ManyToOne
	@JoinColumn(name="id_inventario_producto", nullable=false)
	private InventarioProducto inventarioProducto;

	//bi-directional many-to-one association to Venta
	@ManyToOne
	@JoinColumn(name="id_cabecera_venta", nullable=false)
	private Venta venta;

	public DetallesVenta() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getCantidad() {
		return this.cantidad;
	}

	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}

	public BigDecimal getPrecioVenta() {
		return this.precioVenta;
	}

	public void setPrecioVenta(BigDecimal precioVenta) {
		this.precioVenta = precioVenta;
	}

	public InventarioProducto getInventarioProducto() {
		return this.inventarioProducto;
	}

	public void setInventarioProducto(InventarioProducto inventarioProducto) {
		this.inventarioProducto = inventarioProducto;
	}

	public Venta getVenta() {
		return this.venta;
	}

	public void setVenta(Venta venta) {
		this.venta = venta;
	}

}