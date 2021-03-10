package platypusEJB.model.core.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the distribuidores database table.
 * 
 */
@Entity
@Table(name="distribuidores")
@NamedQuery(name="Distribuidore.findAll", query="SELECT d FROM Distribuidore d")
public class Distribuidore implements Serializable {
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

	//bi-directional many-to-one association to EmpresasDistribuidore
	@ManyToOne
	@JoinColumn(name="id_empresa", nullable=false)
	private EmpresasDistribuidore empresasDistribuidore;

	//bi-directional many-to-one association to Recepcione
	@OneToMany(mappedBy="distribuidore")
	private List<Recepcione> recepciones;

	public Distribuidore() {
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

	public EmpresasDistribuidore getEmpresasDistribuidore() {
		return this.empresasDistribuidore;
	}

	public void setEmpresasDistribuidore(EmpresasDistribuidore empresasDistribuidore) {
		this.empresasDistribuidore = empresasDistribuidore;
	}

	public List<Recepcione> getRecepciones() {
		return this.recepciones;
	}

	public void setRecepciones(List<Recepcione> recepciones) {
		this.recepciones = recepciones;
	}

	public Recepcione addRecepcione(Recepcione recepcione) {
		getRecepciones().add(recepcione);
		recepcione.setDistribuidore(this);

		return recepcione;
	}

	public Recepcione removeRecepcione(Recepcione recepcione) {
		getRecepciones().remove(recepcione);
		recepcione.setDistribuidore(null);

		return recepcione;
	}

}