package platypusEJB.model.core.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the admrec_empresas database table.
 * 
 */
@Entity
@Table(name="admrec_empresas")
@NamedQuery(name="AdmrecEmpresa.findAll", query="SELECT a FROM AdmrecEmpresa a")
public class AdmrecEmpresa implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private Integer id;

	@Column(name="contacto_empresa", nullable=false, length=10)
	private String contactoEmpresa;

	@Column(nullable=false, length=100)
	private String nombre;

	//bi-directional many-to-one association to AdmrecDistribuidor
	@OneToMany(mappedBy="admrecEmpresa")
	private List<AdmrecDistribuidor> admrecDistribuidores;

	//bi-directional many-to-one association to AdmdirDireccion
	@ManyToOne
	@JoinColumn(name="id_direccion", nullable=false)
	private AdmdirDireccion admdirDireccione;

	public AdmrecEmpresa() {
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

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<AdmrecDistribuidor> getAdmrecDistribuidores() {
		return this.admrecDistribuidores;
	}

	public void setAdmrecDistribuidores(List<AdmrecDistribuidor> admrecDistribuidores) {
		this.admrecDistribuidores = admrecDistribuidores;
	}

	public AdmrecDistribuidor addAdmrecDistribuidore(AdmrecDistribuidor admrecDistribuidore) {
		getAdmrecDistribuidores().add(admrecDistribuidore);
		admrecDistribuidore.setAdmrecEmpresa(this);

		return admrecDistribuidore;
	}

	public AdmrecDistribuidor removeAdmrecDistribuidore(AdmrecDistribuidor admrecDistribuidore) {
		getAdmrecDistribuidores().remove(admrecDistribuidore);
		admrecDistribuidore.setAdmrecEmpresa(null);

		return admrecDistribuidore;
	}

	public AdmdirDireccion getAdmdirDireccione() {
		return this.admdirDireccione;
	}

	public void setAdmdirDireccione(AdmdirDireccion admdirDireccione) {
		this.admdirDireccione = admdirDireccione;
	}

}