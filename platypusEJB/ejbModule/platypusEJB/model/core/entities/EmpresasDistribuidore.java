package platypusEJB.model.core.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the empresas_distribuidores database table.
 * 
 */
@Entity
@Table(name="empresas_distribuidores")
@NamedQuery(name="EmpresasDistribuidore.findAll", query="SELECT e FROM EmpresasDistribuidore e")
public class EmpresasDistribuidore implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private Integer id;

	@Column(name="contacto_empresa", nullable=false, length=10)
	private String contactoEmpresa;

	@Column(name="direccion_sucursal", nullable=false, length=100)
	private String direccionSucursal;

	@Column(nullable=false, length=100)
	private String nombre;

	//bi-directional many-to-one association to Distribuidore
	@OneToMany(mappedBy="empresasDistribuidore")
	private List<Distribuidore> distribuidores;

	public EmpresasDistribuidore() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getContactoEmpresa() {
		return this.contactoEmpresa;
	}

	public void setContactoEmpresa(String contactoEmpresa) {
		this.contactoEmpresa = contactoEmpresa;
	}

	public String getDireccionSucursal() {
		return this.direccionSucursal;
	}

	public void setDireccionSucursal(String direccionSucursal) {
		this.direccionSucursal = direccionSucursal;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<Distribuidore> getDistribuidores() {
		return this.distribuidores;
	}

	public void setDistribuidores(List<Distribuidore> distribuidores) {
		this.distribuidores = distribuidores;
	}

	public Distribuidore addDistribuidore(Distribuidore distribuidore) {
		getDistribuidores().add(distribuidore);
		distribuidore.setEmpresasDistribuidore(this);

		return distribuidore;
	}

	public Distribuidore removeDistribuidore(Distribuidore distribuidore) {
		getDistribuidores().remove(distribuidore);
		distribuidore.setEmpresasDistribuidore(null);

		return distribuidore;
	}

}