package platypusEJB.model.inventarioproductos.dtos;

import java.util.Date;

public class ProductoDto {
	
	private int id;
	private String nombre;
	private double costoCompra;
	private double costoVenta;
	private double costoTotal;
	private int cantidadDisponible;
	private Date fechaExpiracion;
	private int cantidadSeleccionada;
	public ProductoDto() {
		super();
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public double getCostoCompra() {
		return costoCompra;
	}
	public void setCostoCompra(double costoCompra) {
		this.costoCompra = costoCompra;
	}
	public double getCostoVenta() {
		return costoVenta;
	}
	public void setCostoVenta(double costoVenta) {
		this.costoVenta = costoVenta;
	}
	public int getCantidadDisponible() {
		return cantidadDisponible;
	}
	public void setCantidadDisponible(int cantidadDisponible) {
		this.cantidadDisponible = cantidadDisponible;
	}
	public Date getFechaExpiracion() {
		return fechaExpiracion;
	}
	public void setFechaExpiracion(Date fechaExpiracion) {
		this.fechaExpiracion = fechaExpiracion;
	}
	public int getCantidadSeleccionada() {
		return cantidadSeleccionada;
	}
	public void setCantidadSeleccionada(int cantidadSeleccionada) {
		this.cantidadSeleccionada = cantidadSeleccionada;
	}
	public double getCostoTotal() {
		return costoTotal;
	}
	public void setCostoTotal(double costoTotal) {
		this.costoTotal = costoTotal;
	}
}
