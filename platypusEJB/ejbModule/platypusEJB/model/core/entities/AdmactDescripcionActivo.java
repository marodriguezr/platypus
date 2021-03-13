package platypusEJB.model.core.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the admact_descripciones_activos database table.
 * 
 */
@Entity
@Table(name="admact_descripciones_activos")
@NamedQuery(name="AdmactDescripcionActivo.findAll", query="SELECT a FROM AdmactDescripcionActivo a")
public class AdmactDescripcionActivo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private Integer id;

	@Column(nullable=false, length=2147483647)
	private String descripcion;

	@Column(nullable=false, length=32)
	private String nombre;

	//bi-directional many-to-one association to AdmactActivo
	@OneToMany(mappedBy="admactDescripcionesActivo")
	private List<AdmactActivo> admactActivos;

	//bi-directional many-to-one association to AdmactTipoActivo
	@ManyToOne
	@JoinColumn(name="id_tipo_activo", nullable=false)
	private AdmactTipoActivo admactTiposActivo;

	public AdmactDescripcionActivo() {
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

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<AdmactActivo> getAdmactActivos() {
		return this.admactActivos;
	}

	public void setAdmactActivos(List<AdmactActivo> admactActivos) {
		this.admactActivos = admactActivos;
	}

	public AdmactActivo addAdmactActivo(AdmactActivo admactActivo) {
		getAdmactActivos().add(admactActivo);
		admactActivo.setAdmactDescripcionesActivo(this);

		return admactActivo;
	}

	public AdmactActivo removeAdmactActivo(AdmactActivo admactActivo) {
		getAdmactActivos().remove(admactActivo);
		admactActivo.setAdmactDescripcionesActivo(null);

		return admactActivo;
	}

	public AdmactTipoActivo getAdmactTiposActivo() {
		return this.admactTiposActivo;
	}

	public void setAdmactTiposActivo(AdmactTipoActivo admactTiposActivo) {
		this.admactTiposActivo = admactTiposActivo;
	}

}