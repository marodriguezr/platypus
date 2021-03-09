package platypusEJB.model.core.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the productos database table.
 * 
 */
@Entity
@Table(name="productos")
@NamedQuery(name="Producto.findAll", query="SELECT p FROM Producto p")
public class Producto implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private Integer id;

	@Column(nullable=false)
	private Boolean expirable;

	@Column(nullable=false, length=32)
	private String nombre;

	//bi-directional many-to-one association to InventarioProducto
	@OneToMany(mappedBy="producto")
	private List<InventarioProducto> inventarioProductos;

	public Producto() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public List<InventarioProducto> getInventarioProductos() {
		return this.inventarioProductos;
	}

	public void setInventarioProductos(List<InventarioProducto> inventarioProductos) {
		this.inventarioProductos = inventarioProductos;
	}

	public InventarioProducto addInventarioProducto(InventarioProducto inventarioProducto) {
		getInventarioProductos().add(inventarioProducto);
		inventarioProducto.setProducto(this);

		return inventarioProducto;
	}

	public InventarioProducto removeInventarioProducto(InventarioProducto inventarioProducto) {
		getInventarioProductos().remove(inventarioProducto);
		inventarioProducto.setProducto(null);

		return inventarioProducto;
	}

}