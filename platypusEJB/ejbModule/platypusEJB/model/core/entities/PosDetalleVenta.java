package platypusEJB.model.core.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the pos_detalles_ventas database table.
 * 
 */
@Entity
@Table(name="pos_detalles_ventas")
@NamedQuery(name="PosDetalleVenta.findAll", query="SELECT p FROM PosDetalleVenta p")
public class PosDetalleVenta implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private Integer id;

	@Column(nullable=false)
	private Integer cantidad;

	@Column(name="precio_venta", nullable=false, precision=7, scale=2)
	private BigDecimal precioVenta;

	//bi-directional many-to-one association to InvProducto
	@ManyToOne
	@JoinColumn(name="id_producto", nullable=false)
	private InvProducto invProducto;

	//bi-directional many-to-one association to PosVenta
	@ManyToOne
	@JoinColumn(name="id_venta", nullable=false)
	private PosVenta posVenta;

	public PosDetalleVenta() {
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

	public InvProducto getInvProducto() {
		return this.invProducto;
	}

	public void setInvProducto(InvProducto invProducto) {
		this.invProducto = invProducto;
	}

	public PosVenta getPosVenta() {
		return this.posVenta;
	}

	public void setPosVenta(PosVenta posVenta) {
		this.posVenta = posVenta;
	}

}