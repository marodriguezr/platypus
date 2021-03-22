package platypusEJB.model.pos.dtos;

import java.util.Date;

public class VentaDto {

	private int id;
	private int idCliente;
	private String nombresApellidosCliente;
	private Date fechaVenta;
	private int idEmpleado;
	private String nombresApellidosEmpleado;
	private int idPorcentajeIva;
	private int porcentajeIva;
	private int cantidadProductosVendidos;
	private double total;
	private double subtotal;
	private double iva;
	
	public VentaDto() {
		super();
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getIdCliente() {
		return idCliente;
	}
	public void setIdCliente(int idCliente) {
		this.idCliente = idCliente;
	}
	public String getNombresApellidosCliente() {
		return nombresApellidosCliente;
	}
	public void setNombresApellidosCliente(String nombresApellidosCliente) {
		this.nombresApellidosCliente = nombresApellidosCliente;
	}
	public Date getFechaVenta() {
		return fechaVenta;
	}
	public void setFechaVenta(Date fechaVenta) {
		this.fechaVenta = fechaVenta;
	}
	public int getIdEmpleado() {
		return idEmpleado;
	}
	public void setIdEmpleado(int idEmpleado) {
		this.idEmpleado = idEmpleado;
	}
	public String getNombresApellidosEmpleado() {
		return nombresApellidosEmpleado;
	}
	public void setNombresApellidosEmpleado(String nombresApellidosEmpleado) {
		this.nombresApellidosEmpleado = nombresApellidosEmpleado;
	}
	public int getIdPorcentajeIva() {
		return idPorcentajeIva;
	}
	public void setIdPorcentajeIva(int idPorcentajeIva) {
		this.idPorcentajeIva = idPorcentajeIva;
	}
	public int getPorcentajeIva() {
		return porcentajeIva;
	}
	public void setPorcentajeIva(int porcentajeIva) {
		this.porcentajeIva = porcentajeIva;
	}
	public double getTotal() {
		return total;
	}
	public void setTotal(double total) {
		this.total = total;
	}
	public double getSubtotal() {
		return subtotal;
	}
	public void setSubtotal(double subtotal) {
		this.subtotal = subtotal;
	}
	public double getIva() {
		return iva;
	}
	public void setIva(double iva) {
		this.iva = iva;
	}

	public int getCantidadProductosVendidos() {
		return cantidadProductosVendidos;
	}

	public void setCantidadProductosVendidos(int cantidadProductosVendidos) {
		this.cantidadProductosVendidos = cantidadProductosVendidos;
	}
	
}
