package platypusEJB.model.core.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the ventas database table.
 * 
 */
@Entity
@Table(name="ventas")
@NamedQuery(name="Venta.findAll", query="SELECT v FROM Venta v")
public class Venta implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private Integer id;

	@Temporal(TemporalType.DATE)
	@Column(name="fecha_venta", nullable=false)
	private Date fechaVenta;

	//bi-directional many-to-one association to DetallesVenta
	@OneToMany(mappedBy="venta")
	private List<DetallesVenta> detallesVentas;

	//bi-directional many-to-one association to Cliente
	@ManyToOne
	@JoinColumn(name="id_cliente", nullable=false)
	private Cliente cliente;

	//bi-directional many-to-one association to ThmEmpleado
	@ManyToOne
	@JoinColumn(name="id_thm_empleado", nullable=false)
	private ThmEmpleado thmEmpleado;

	public Venta() {
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

	public List<DetallesVenta> getDetallesVentas() {
		return this.detallesVentas;
	}

	public void setDetallesVentas(List<DetallesVenta> detallesVentas) {
		this.detallesVentas = detallesVentas;
	}

	public DetallesVenta addDetallesVenta(DetallesVenta detallesVenta) {
		getDetallesVentas().add(detallesVenta);
		detallesVenta.setVenta(this);

		return detallesVenta;
	}

	public DetallesVenta removeDetallesVenta(DetallesVenta detallesVenta) {
		getDetallesVentas().remove(detallesVenta);
		detallesVenta.setVenta(null);

		return detallesVenta;
	}

	public Cliente getCliente() {
		return this.cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public ThmEmpleado getThmEmpleado() {
		return this.thmEmpleado;
	}

	public void setThmEmpleado(ThmEmpleado thmEmpleado) {
		this.thmEmpleado = thmEmpleado;
	}

}