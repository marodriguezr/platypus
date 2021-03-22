package platypusEJB.model.thumano.dtos;

public class ThmEmpleadoDto {
	
	private int id;
	private int idSegUsuario;
	private String nombres;
	private String apellidos;
	private String cargo;
	private String correo;
	private boolean estado;
	private long clientesAtendidos;
	private long productosVendidos;
	private long ventasRegistradas;
	
	
	public ThmEmpleadoDto() {
		super();
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getIdSegUsuario() {
		return idSegUsuario;
	}
	public void setIdSegUsuario(int idSegUsuario) {
		this.idSegUsuario = idSegUsuario;
	}
	public String getNombres() {
		return nombres;
	}
	public void setNombres(String nombres) {
		this.nombres = nombres;
	}
	public String getApellidos() {
		return apellidos;
	}
	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}
	public String getCargo() {
		return cargo;
	}
	public void setCargo(String cargo) {
		this.cargo = cargo;
	}
	public String getCorreo() {
		return correo;
	}
	public void setCorreo(String correo) {
		this.correo = correo;
	}
	public boolean isEstado() {
		return estado;
	}
	public void setEstado(boolean estado) {
		this.estado = estado;
	}
	public long getClientesAtendidos() {
		return clientesAtendidos;
	}
	public void setClientesAtendidos(long clientesAtendidos) {
		this.clientesAtendidos = clientesAtendidos;
	}
	public long getProductosVendidos() {
		return productosVendidos;
	}
	public void setProductosVendidos(long productosVendidos) {
		this.productosVendidos = productosVendidos;
	}
	public long getVentasRegistradas() {
		return ventasRegistradas;
	}
	public void setVentasRegistradas(long ventasRegistradas) {
		this.ventasRegistradas = ventasRegistradas;
	}

	
}
