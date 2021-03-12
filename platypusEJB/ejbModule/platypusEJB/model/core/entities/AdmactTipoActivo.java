package platypusEJB.model.core.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the admact_tipos_activos database table.
 * 
 */
@Entity
@Table(name="admact_tipos_activos")
@NamedQuery(name="AdmactTipoActivo.findAll", query="SELECT a FROM AdmactTipoActivo a")
public class AdmactTipoActivo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private Integer id;

	@Column(length=32)
	private String descripcion;

	//bi-directional many-to-one association to AdmactDescripcionActivo
	@OneToMany(mappedBy="admactTiposActivo")
	private List<AdmactDescripcionActivo> admactDescripcionesActivos;

	public AdmactTipoActivo() {
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

	public List<AdmactDescripcionActivo> getAdmactDescripcionesActivos() {
		return this.admactDescripcionesActivos;
	}

	public void setAdmactDescripcionesActivos(List<AdmactDescripcionActivo> admactDescripcionesActivos) {
		this.admactDescripcionesActivos = admactDescripcionesActivos;
	}

	public AdmactDescripcionActivo addAdmactDescripcionesActivo(AdmactDescripcionActivo admactDescripcionesActivo) {
		getAdmactDescripcionesActivos().add(admactDescripcionesActivo);
		admactDescripcionesActivo.setAdmactTiposActivo(this);

		return admactDescripcionesActivo;
	}

	public AdmactDescripcionActivo removeAdmactDescripcionesActivo(AdmactDescripcionActivo admactDescripcionesActivo) {
		getAdmactDescripcionesActivos().remove(admactDescripcionesActivo);
		admactDescripcionesActivo.setAdmactTiposActivo(null);

		return admactDescripcionesActivo;
	}

}