package platypusEJB.model.core.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the admrec_distribuidores database table.
 * 
 */
@Entity
@Table(name="admrec_distribuidores")
@NamedQuery(name="AdmrecDistribuidor.findAll", query="SELECT a FROM AdmrecDistribuidor a")
public class AdmrecDistribuidor implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private Integer id;

	@Column(nullable=false, length=100)
	private String apellido;

	@Column(nullable=false, length=10)
	private String contacto;

	@Column(nullable=false, length=100)
	private String nombre;

	//bi-directional many-to-one association to AdmrecEmpresa
	@ManyToOne
	@JoinColumn(name="id_empresa", nullable=false)
	private AdmrecEmpresa admrecEmpresa;

	//bi-directional many-to-one association to AdmrecRecepcion
	@OneToMany(mappedBy="admrecDistribuidore")
	private List<AdmrecRecepcion> admrecRecepciones;

	public AdmrecDistribuidor() {
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

	public String getContacto() {
		return this.contacto;
	}

	public void setContacto(String contacto) {
		this.contacto = contacto;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public AdmrecEmpresa getAdmrecEmpresa() {
		return this.admrecEmpresa;
	}

	public void setAdmrecEmpresa(AdmrecEmpresa admrecEmpresa) {
		this.admrecEmpresa = admrecEmpresa;
	}

	public List<AdmrecRecepcion> getAdmrecRecepciones() {
		return this.admrecRecepciones;
	}

	public void setAdmrecRecepciones(List<AdmrecRecepcion> admrecRecepciones) {
		this.admrecRecepciones = admrecRecepciones;
	}

	public AdmrecRecepcion addAdmrecRecepcione(AdmrecRecepcion admrecRecepcione) {
		getAdmrecRecepciones().add(admrecRecepcione);
		admrecRecepcione.setAdmrecDistribuidore(this);

		return admrecRecepcione;
	}

	public AdmrecRecepcion removeAdmrecRecepcione(AdmrecRecepcion admrecRecepcione) {
		getAdmrecRecepciones().remove(admrecRecepcione);
		admrecRecepcione.setAdmrecDistribuidore(null);

		return admrecRecepcione;
	}

}