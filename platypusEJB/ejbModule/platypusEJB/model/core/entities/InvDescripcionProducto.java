package platypusEJB.model.core.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the inv_descripciones_productos database table.
 * 
 */
@Entity
@Table(name="inv_descripciones_productos")
@NamedQuery(name="InvDescripcionProducto.findAll", query="SELECT i FROM InvDescripcionProducto i")
public class InvDescripcionProducto implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private Integer id;

	@Column(nullable=false, length=2147483647)
	private String descripcion;

	@Column(nullable=false)
	private Boolean expirable;

	@Column(nullable=false, length=32)
	private String nombre;

	//bi-directional many-to-one association to InvTipoProducto
	@ManyToOne
	@JoinColumn(name="id_tipo_producto", nullable=false)
	private InvTipoProducto invTiposProducto;

	//bi-directional many-to-one association to InvProducto
	@OneToMany(mappedBy="invDescripcionesProducto")
	private List<InvProducto> invProductos;

	public InvDescripcionProducto() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Boolean getExpirable() {
		return this.expirable;
	}

	public void setExpirable(Boolean expirable) {
		this.expirable = expirable;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public InvTipoProducto getInvTiposProducto() {
		return this.invTiposProducto;
	}

	public void setInvTiposProducto(InvTipoProducto invTiposProducto) {
		this.invTiposProducto = invTiposProducto;
	}

	public List<InvProducto> getInvProductos() {
		return this.invProductos;
	}

	public void setInvProductos(List<InvProducto> invProductos) {
		this.invProductos = invProductos;
	}

	public InvProducto addInvProducto(InvProducto invProducto) {
		getInvProductos().add(invProducto);
		invProducto.setInvDescripcionesProducto(this);

		return invProducto;
	}

	public InvProducto removeInvProducto(InvProducto invProducto) {
		getInvProductos().remove(invProducto);
		invProducto.setInvDescripcionesProducto(null);

		return invProducto;
	}

}