package platypusEJB.model.core.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the inv_productos database table.
 * 
 */
@Entity
@Table(name="inv_productos")
@NamedQuery(name="InvProducto.findAll", query="SELECT i FROM InvProducto i")
public class InvProducto implements Serializable {
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
	@Column(name="fecha_expiracion")
	private Date fechaExpiracion;

	//bi-directional many-to-one association to InvDescripcionProducto
	@ManyToOne
	@JoinColumn(name="id_descripcion_producto", nullable=false)
	private InvDescripcionProducto invDescripcionesProducto;

	//bi-directional many-to-one association to PosDetalleVenta
	@OneToMany(mappedBy="invProducto")
	private List<PosDetalleVenta> posDetallesVentas;

	//bi-directional many-to-one association to AdmrecRecepcion
	@ManyToOne
	@JoinColumn(name="id_recepcion", nullable=false)
	private AdmrecRecepcion admrecRecepcione;

	public InvProducto() {
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

	public Date getFechaExpiracion() {
		return this.fechaExpiracion;
	}

	public void setFechaExpiracion(Date fechaExpiracion) {
		this.fechaExpiracion = fechaExpiracion;
	}

	public InvDescripcionProducto getInvDescripcionesProducto() {
		return this.invDescripcionesProducto;
	}

	public void setInvDescripcionesProducto(InvDescripcionProducto invDescripcionesProducto) {
		this.invDescripcionesProducto = invDescripcionesProducto;
	}

	public List<PosDetalleVenta> getPosDetallesVentas() {
		return this.posDetallesVentas;
	}

	public void setPosDetallesVentas(List<PosDetalleVenta> posDetallesVentas) {
		this.posDetallesVentas = posDetallesVentas;
	}

	public PosDetalleVenta addPosDetallesVenta(PosDetalleVenta posDetallesVenta) {
		getPosDetallesVentas().add(posDetallesVenta);
		posDetallesVenta.setInvProducto(this);

		return posDetallesVenta;
	}

	public PosDetalleVenta removePosDetallesVenta(PosDetalleVenta posDetallesVenta) {
		getPosDetallesVentas().remove(posDetallesVenta);
		posDetallesVenta.setInvProducto(null);

		return posDetallesVenta;
	}

	public AdmrecRecepcion getAdmrecRecepcione() {
		return this.admrecRecepcione;
	}

	public void setAdmrecRecepcione(AdmrecRecepcion admrecRecepcione) {
		this.admrecRecepcione = admrecRecepcione;
	}

}