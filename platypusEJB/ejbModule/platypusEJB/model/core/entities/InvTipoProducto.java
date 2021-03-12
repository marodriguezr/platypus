package platypusEJB.model.core.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the inv_tipos_productos database table.
 * 
 */
@Entity
@Table(name="inv_tipos_productos")
@NamedQuery(name="InvTipoProducto.findAll", query="SELECT i FROM InvTipoProducto i")
public class InvTipoProducto implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private Integer id;

	@Column(nullable=false, length=32)
	private String descripcion;

	//bi-directional many-to-one association to InvDescripcionProducto
	@OneToMany(mappedBy="invTiposProducto")
	private List<InvDescripcionProducto> invDescripcionesProductos;

	public InvTipoProducto() {
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

	public List<InvDescripcionProducto> getInvDescripcionesProductos() {
		return this.invDescripcionesProductos;
	}

	public void setInvDescripcionesProductos(List<InvDescripcionProducto> invDescripcionesProductos) {
		this.invDescripcionesProductos = invDescripcionesProductos;
	}

	public InvDescripcionProducto addInvDescripcionesProducto(InvDescripcionProducto invDescripcionesProducto) {
		getInvDescripcionesProductos().add(invDescripcionesProducto);
		invDescripcionesProducto.setInvTiposProducto(this);

		return invDescripcionesProducto;
	}

	public InvDescripcionProducto removeInvDescripcionesProducto(InvDescripcionProducto invDescripcionesProducto) {
		getInvDescripcionesProductos().remove(invDescripcionesProducto);
		invDescripcionesProducto.setInvTiposProducto(null);

		return invDescripcionesProducto;
	}

}