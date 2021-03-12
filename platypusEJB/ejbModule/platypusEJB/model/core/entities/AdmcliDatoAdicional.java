package platypusEJB.model.core.entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the admcli_datos_adicionales database table.
 * 
 */
@Entity
@Table(name="admcli_datos_adicionales")
@NamedQuery(name="AdmcliDatoAdicional.findAll", query="SELECT a FROM AdmcliDatoAdicional a")
public class AdmcliDatoAdicional implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id_cliente", unique=true, nullable=false)
	private Integer idCliente;

	@Column(nullable=false, length=10)
	private String cedula;

	@Column(nullable=false, length=10)
	private String contacto;

	@Column(nullable=false, length=256)
	private String email;

	//bi-directional one-to-one association to AdmcliCliente
	@OneToOne
	@JoinColumn(name="id_cliente", nullable=false, insertable=false, updatable=false)
	private AdmcliCliente admcliCliente;

	public AdmcliDatoAdicional() {
	}

	public Integer getIdCliente() {
		return this.idCliente;
	}

	public void setIdCliente(Integer idCliente) {
		this.idCliente = idCliente;
	}

	public String getCedula() {
		return this.cedula;
	}

	public void setCedula(String cedula) {
		this.cedula = cedula;
	}

	public String getContacto() {
		return this.contacto;
	}

	public void setContacto(String contacto) {
		this.contacto = contacto;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public AdmcliCliente getAdmcliCliente() {
		return this.admcliCliente;
	}

	public void setAdmcliCliente(AdmcliCliente admcliCliente) {
		this.admcliCliente = admcliCliente;
	}

}