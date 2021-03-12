package platypusEJB.model.core.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the pos_ventas database table.
 * 
 */
@Entity
@Table(name="pos_ventas")
@NamedQuery(name="PosVenta.findAll", query="SELECT p FROM PosVenta p")
public class PosVenta implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private Integer id;

	@Temporal(TemporalType.DATE)
	@Column(name="fecha_venta", nullable=false)
	private Date fechaVenta;

	//bi-directional many-to-one association to PosDetalleVenta
	@OneToMany(mappedBy="posVenta")
	private List<PosDetalleVenta> posDetallesVentas;

	//bi-directional many-to-one association to PosPorcentajeIva
	@ManyToOne
	@JoinColumn(name="id_porcentaje_iva", nullable=false)
	private PosPorcentajeIva posPorcentajesIva;

	//bi-directional many-to-one association to ThmEmpleado
	@ManyToOne
	@JoinColumn(name="id_thm_empleado", nullable=false)
	private ThmEmpleado thmEmpleado;

	//bi-directional many-to-one association to AdmcliCliente
	@ManyToOne
	@JoinColumn(name="id_cliente", nullable=false)
	private AdmcliCliente admcliCliente;

	public PosVenta() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getFechaVenta() {
		return this.fechaVenta;
	}

	public void setFechaVenta(Date fechaVenta) {
		this.fechaVenta = fechaVenta;
	}

	public List<PosDetalleVenta> getPosDetallesVentas() {
		return this.posDetallesVentas;
	}

	public void setPosDetallesVentas(List<PosDetalleVenta> posDetallesVentas) {
		this.posDetallesVentas = posDetallesVentas;
	}

	public PosDetalleVenta addPosDetallesVenta(PosDetalleVenta posDetallesVenta) {
		getPosDetallesVentas().add(posDetallesVenta);
		posDetallesVenta.setPosVenta(this);

		return posDetallesVenta;
	}

	public PosDetalleVenta removePosDetallesVenta(PosDetalleVenta posDetallesVenta) {
		getPosDetallesVentas().remove(posDetallesVenta);
		posDetallesVenta.setPosVenta(null);

		return posDetallesVenta;
	}

	public PosPorcentajeIva getPosPorcentajesIva() {
		return this.posPorcentajesIva;
	}

	public void setPosPorcentajesIva(PosPorcentajeIva posPorcentajesIva) {
		this.posPorcentajesIva = posPorcentajesIva;
	}

	public ThmEmpleado getThmEmpleado() {
		return this.thmEmpleado;
	}

	public void setThmEmpleado(ThmEmpleado thmEmpleado) {
		this.thmEmpleado = thmEmpleado;
	}

	public AdmcliCliente getAdmcliCliente() {
		return this.admcliCliente;
	}

	public void setAdmcliCliente(AdmcliCliente admcliCliente) {
		this.admcliCliente = admcliCliente;
	}

}