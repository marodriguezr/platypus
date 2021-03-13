package platypusEJB.model.core.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the admact_activos database table.
 * 
 */
@Entity
@Table(name="admact_activos")
@NamedQuery(name="AdmactActivo.findAll", query="SELECT a FROM AdmactActivo a")
public class AdmactActivo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private Integer id;

	@Column(nullable=false)
	private Integer cantidad;

	@Column(name="costo_adquisicion", nullable=false, precision=7, scale=2)
	private BigDecimal costoAdquisicion;

	@Temporal(TemporalType.DATE)
	@Column(name="fecha_adquisicion", nullable=false)
	private Date fechaAdquisicion;

	//bi-directional many-to-one association to AdmactDescripcionActivo
	@ManyToOne
	@JoinColumn(name="id_descripcion_activo", nullable=false)
	private AdmactDescripcionActivo admactDescripcionesActivo;

	public AdmactActivo() {
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

	public BigDecimal getCostoAdquisicion() {
		return this.costoAdquisicion;
	}

	public void setCostoAdquisicion(BigDecimal costoAdquisicion) {
		this.costoAdquisicion = costoAdquisicion;
	}

	public Date getFechaAdquisicion() {
		return this.fechaAdquisicion;
	}

	public void setFechaAdquisicion(Date fechaAdquisicion) {
		this.fechaAdquisicion = fechaAdquisicion;
	}

	public AdmactDescripcionActivo getAdmactDescripcionesActivo() {
		return this.admactDescripcionesActivo;
	}

	public void setAdmactDescripcionesActivo(AdmactDescripcionActivo admactDescripcionesActivo) {
		this.admactDescripcionesActivo = admactDescripcionesActivo;
	}

}