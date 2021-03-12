package platypusEJB.model.core.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the admdir_direcciones database table.
 * 
 */
@Entity
@Table(name="admdir_direcciones")
@NamedQuery(name="AdmdirDireccion.findAll", query="SELECT a FROM AdmdirDireccion a")
public class AdmdirDireccion implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private Integer id;

	@Column(name="calle_principal", nullable=false, length=32)
	private String callePrincipal;

	@Column(name="calle_secundaria", nullable=false, length=32)
	private String calleSecundaria;

	@Column(nullable=false, length=32)
	private String ciudad;

	@Column(nullable=false, length=32)
	private String provincia;

	@Column(length=32)
	private String referencia;

	//bi-directional many-to-one association to AdmcliCliente
	@OneToMany(mappedBy="admdirDireccione")
	private List<AdmcliCliente> admcliClientes;

	//bi-directional many-to-one association to AdmrecEmpresa
	@OneToMany(mappedBy="admdirDireccione")
	private List<AdmrecEmpresa> admrecEmpresas;

	public AdmdirDireccion() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCallePrincipal() {
		return this.callePrincipal;
	}

	public void setCallePrincipal(String callePrincipal) {
		this.callePrincipal = callePrincipal;
	}

	public String getCalleSecundaria() {
		return this.calleSecundaria;
	}

	public void setCalleSecundaria(String calleSecundaria) {
		this.calleSecundaria = calleSecundaria;
	}

	public String getCiudad() {
		return this.ciudad;
	}

	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}

	public String getProvincia() {
		return this.provincia;
	}

	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}

	public String getReferencia() {
		return this.referencia;
	}

	public void setReferencia(String referencia) {
		this.referencia = referencia;
	}

	public List<AdmcliCliente> getAdmcliClientes() {
		return this.admcliClientes;
	}

	public void setAdmcliClientes(List<AdmcliCliente> admcliClientes) {
		this.admcliClientes = admcliClientes;
	}

	public AdmcliCliente addAdmcliCliente(AdmcliCliente admcliCliente) {
		getAdmcliClientes().add(admcliCliente);
		admcliCliente.setAdmdirDireccione(this);

		return admcliCliente;
	}

	public AdmcliCliente removeAdmcliCliente(AdmcliCliente admcliCliente) {
		getAdmcliClientes().remove(admcliCliente);
		admcliCliente.setAdmdirDireccione(null);

		return admcliCliente;
	}

	public List<AdmrecEmpresa> getAdmrecEmpresas() {
		return this.admrecEmpresas;
	}

	public void setAdmrecEmpresas(List<AdmrecEmpresa> admrecEmpresas) {
		this.admrecEmpresas = admrecEmpresas;
	}

	public AdmrecEmpresa addAdmrecEmpresa(AdmrecEmpresa admrecEmpresa) {
		getAdmrecEmpresas().add(admrecEmpresa);
		admrecEmpresa.setAdmdirDireccione(this);

		return admrecEmpresa;
	}

	public AdmrecEmpresa removeAdmrecEmpresa(AdmrecEmpresa admrecEmpresa) {
		getAdmrecEmpresas().remove(admrecEmpresa);
		admrecEmpresa.setAdmdirDireccione(null);

		return admrecEmpresa;
	}

}