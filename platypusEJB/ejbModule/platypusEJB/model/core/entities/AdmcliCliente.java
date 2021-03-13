package platypusEJB.model.core.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the admcli_clientes database table.
 * 
 */
@Entity
@Table(name="admcli_clientes")
@NamedQuery(name="AdmcliCliente.findAll", query="SELECT a FROM AdmcliCliente a")
public class AdmcliCliente implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private Integer id;

	@Column(nullable=false, length=32)
	private String apellido;

	@Temporal(TemporalType.DATE)
	@Column(name="fecha_registro", nullable=false)
	private Date fechaRegistro;

	@Column(nullable=false, length=32)
	private String nombre;

	//bi-directional many-to-one association to AdmdirDireccion
	@ManyToOne
	@JoinColumn(name="id_direccion", nullable=false)
	private AdmdirDireccion admdirDireccione;

	//bi-directional one-to-one association to AdmcliDatoAdicional
	@OneToOne(mappedBy="admcliCliente")
	private AdmcliDatoAdicional admcliDatosAdicionale;

	//bi-directional many-to-one association to PosVenta
	@OneToMany(mappedBy="admcliCliente")
	private List<PosVenta> posVentas;

	public AdmcliCliente() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getApellido() {
		return this.apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public Date getFechaRegistro() {
		return this.fechaRegistro;
	}

	public void setFechaRegistro(Date fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public AdmdirDireccion getAdmdirDireccione() {
		return this.admdirDireccione;
	}

	public void setAdmdirDireccione(AdmdirDireccion admdirDireccione) {
		this.admdirDireccione = admdirDireccione;
	}

	public AdmcliDatoAdicional getAdmcliDatosAdicionale() {
		return this.admcliDatosAdicionale;
	}

	public void setAdmcliDatosAdicionale(AdmcliDatoAdicional admcliDatosAdicionale) {
		this.admcliDatosAdicionale = admcliDatosAdicionale;
	}

	public List<PosVenta> getPosVentas() {
		return this.posVentas;
	}

	public void setPosVentas(List<PosVenta> posVentas) {
		this.posVentas = posVentas;
	}

	public PosVenta addPosVenta(PosVenta posVenta) {
		getPosVentas().add(posVenta);
		posVenta.setAdmcliCliente(this);

		return posVenta;
	}

	public PosVenta removePosVenta(PosVenta posVenta) {
		getPosVentas().remove(posVenta);
		posVenta.setAdmcliCliente(null);

		return posVenta;
	}

}