package platypusEJB.model.core.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the recepciones database table.
 * 
 */
@Entity
@Table(name="recepciones")
@NamedQuery(name="Recepcione.findAll", query="SELECT r FROM Recepcione r")
public class Recepcione implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private Integer id;

	@Column(name="anexo_factura", nullable=false, length=100)
	private String anexoFactura;

	@Temporal(TemporalType.DATE)
	@Column(nullable=false)
	private Date fecha;

	//bi-directional many-to-one association to InventarioProducto
	@OneToMany(mappedBy="recepcione")
	private List<InventarioProducto> inventarioProductos;

	//bi-directional many-to-one association to Distribuidore
	@ManyToOne
	@JoinColumn(name="id_distribuidor", nullable=false)
	private Distribuidore distribuidore;

	public Recepcione() {
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

	public List<InventarioProducto> getInventarioProductos() {
		return this.inventarioProductos;
	}

	public void setInventarioProductos(List<InventarioProducto> inventarioProductos) {
		this.inventarioProductos = inventarioProductos;
	}

	public InventarioProducto addInventarioProducto(InventarioProducto inventarioProducto) {
		getInventarioProductos().add(inventarioProducto);
		inventarioProducto.setRecepcione(this);

		return inventarioProducto;
	}

	public InventarioProducto removeInventarioProducto(InventarioProducto inventarioProducto) {
		getInventarioProductos().remove(inventarioProducto);
		inventarioProducto.setRecepcione(null);

		return inventarioProducto;
	}

	public Distribuidore getDistribuidore() {
		return this.distribuidore;
	}

	public void setDistribuidore(Distribuidore distribuidore) {
		this.distribuidore = distribuidore;
	}

}