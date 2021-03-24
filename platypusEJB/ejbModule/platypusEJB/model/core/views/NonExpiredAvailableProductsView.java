package platypusEJB.model.core.views;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the non_expired_available_products_view database table.
 * 
 */
@Entity
@Table(name="non_expired_available_products_view")
@NamedQuery(name="NonExpiredAvailableProductsView.findAll", query="SELECT n FROM NonExpiredAvailableProductsView n")
public class NonExpiredAvailableProductsView implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="cantidad_disponible")
	private Integer cantidadDisponible;

	@Column(name="costo_compra", precision=8, scale=2)
	private BigDecimal costoCompra;

	@Column(name="costo_venta", precision=8, scale=2)
	private BigDecimal costoVenta;

	@Temporal(TemporalType.DATE)
	@Column(name="fecha_expiracion")
	private Date fechaExpiracion;

	@Id
	private Integer id;

	@Column(name="id_descripcion_producto")
	private Integer idDescripcionProducto;

	@Column(name="id_recepcion")
	private Integer idRecepcion;

	public NonExpiredAvailableProductsView() {
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

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getIdDescripcionProducto() {
		return this.idDescripcionProducto;
	}

	public void setIdDescripcionProducto(Integer idDescripcionProducto) {
		this.idDescripcionProducto = idDescripcionProducto;
	}

	public Integer getIdRecepcion() {
		return this.idRecepcion;
	}

	public void setIdRecepcion(Integer idRecepcion) {
		this.idRecepcion = idRecepcion;
	}

}