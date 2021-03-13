package platypusEJB.model.core.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the admrec_recepciones database table.
 * 
 */
@Entity
@Table(name="admrec_recepciones")
@NamedQuery(name="AdmrecRecepcion.findAll", query="SELECT a FROM AdmrecRecepcion a")
public class AdmrecRecepcion implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private Integer id;

	@Column(name="anexo_factura", nullable=false, length=2147483647)
	private String anexoFactura;

	@Temporal(TemporalType.DATE)
	@Column(nullable=false)
	private Date fecha;

	//bi-directional many-to-one association to AdmrecDistribuidor
	@ManyToOne
	@JoinColumn(name="id_distribuidor", nullable=false)
	private AdmrecDistribuidor admrecDistribuidore;

	//bi-directional many-to-one association to InvProducto
	@OneToMany(mappedBy="admrecRecepcione")
	private List<InvProducto> invProductos;

	public AdmrecRecepcion() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getAnexoFactura() {
		return this.anexoFactura;
	}

	public void setAnexoFactura(String anexoFactura) {
		this.anexoFactura = anexoFactura;
	}

	public Date getFecha() {
		return this.fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public AdmrecDistribuidor getAdmrecDistribuidore() {
		return this.admrecDistribuidore;
	}

	public void setAdmrecDistribuidore(AdmrecDistribuidor admrecDistribuidore) {
		this.admrecDistribuidore = admrecDistribuidore;
	}

	public List<InvProducto> getInvProductos() {
		return this.invProductos;
	}

	public void setInvProductos(List<InvProducto> invProductos) {
		this.invProductos = invProductos;
	}

	public InvProducto addInvProducto(InvProducto invProducto) {
		getInvProductos().add(invProducto);
		invProducto.setAdmrecRecepcione(this);

		return invProducto;
	}

	public InvProducto removeInvProducto(InvProducto invProducto) {
		getInvProductos().remove(invProducto);
		invProducto.setAdmrecRecepcione(null);

		return invProducto;
	}

}