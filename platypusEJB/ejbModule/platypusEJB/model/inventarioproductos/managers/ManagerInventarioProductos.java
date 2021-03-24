package platypusEJB.model.inventarioproductos.managers;


import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import platypusEJB.model.core.entities.AdmrecRecepcion;
import platypusEJB.model.core.entities.InvDescripcionProducto;
import platypusEJB.model.core.entities.InvProducto;
import platypusEJB.model.core.managers.ManagerDAO;
import platypusEJB.model.core.views.NonExpiredAvailableProductsView;
import platypusEJB.model.inventarioproductos.dtos.ProductoDto;

/**
 * Session Bean implementation class ManagerInventarioProductos
 */
@Stateless
@LocalBean
public class ManagerInventarioProductos {

	@EJB
	private ManagerDAO mDAO;
	
	@EJB
	private ManagerInvDescripcionProducto mInvDescripcionProducto;
    /**
     * Default constructor. 
     */
    public ManagerInventarioProductos() {
        // TODO Auto-generated constructor stub
    }
    
    public void insertarInvProducto(InvProducto nuevoInvProducto , int idInvDescProductos,int idAdmrec_Recepciones) throws Exception {
    	InvDescripcionProducto invDescripcionProducto= (InvDescripcionProducto) mDAO.findById(InvDescripcionProducto.class, 
    			idInvDescProductos);
    	nuevoInvProducto.setInvDescripcionesProducto(invDescripcionProducto);
    	
    	AdmrecRecepcion adminRecRecepcion=(AdmrecRecepcion) mDAO.findById(AdmrecRecepcion.class, 
    			idAdmrec_Recepciones);
    	nuevoInvProducto.setAdmrecRecepcione(adminRecRecepcion);
    	
    	mDAO.insertar(nuevoInvProducto);
    }
    
    public void actualizarInvProducto(InvProducto edicionInvProductos,int idnvDescProdcuto) throws Exception {
    	InvProducto invProducto=(InvProducto) mDAO.findById(InvProducto.class, edicionInvProductos.getId());
    	
    	InvDescripcionProducto invDescripcionProducto= (InvDescripcionProducto) mDAO.findById(InvDescripcionProducto.class,
    			idnvDescProdcuto);
    	
    	invProducto.setId(edicionInvProductos.getId());
    	invProducto.setCantidadDisponible(edicionInvProductos.getCantidadDisponible());
    	invProducto.setCostoCompra(edicionInvProductos.getCostoCompra());
    	invProducto.setCostoVenta(edicionInvProductos.getCostoVenta());
    	invProducto.setFechaExpiracion(edicionInvProductos.getFechaExpiracion());
    	
    	AdmrecRecepcion admrec_Recepciones= (AdmrecRecepcion) mDAO.findById(AdmrecRecepcion.class,
    			invProducto.getAdmrecRecepcione().getId());
    	
    	invProducto.setAdmrecRecepcione(admrec_Recepciones);
    	invProducto.setInvDescripcionesProducto(invDescripcionProducto);
    	mDAO.actualizar(invProducto);
    }
    
    public void eliminarInvProducto(int idInvProducto) throws Exception {
    	InvProducto invProducto=(InvProducto) mDAO.findById(InvProducto.class, idInvProducto);
    	mDAO.eliminar(InvProducto.class, invProducto.getId());
    }
    
    @SuppressWarnings("unchecked")
	public List<InvProducto> findAllInvProductos(){
    	return mDAO.findAll(InvProducto.class, "id");
    }
    
    @SuppressWarnings("unchecked")
	public List<NonExpiredAvailableProductsView> findAllNonExpiredAvailableProducts() {
    	return mDAO.findAll(NonExpiredAvailableProductsView.class);
    }
    
    public ProductoDto toProductoDto(InvProducto producto) {
    	ProductoDto productoDto = new ProductoDto();
    	productoDto.setId(producto.getId());
    	productoDto.setNombre(producto.getInvDescripcionesProducto().getNombre());
    	productoDto.setCantidadDisponible(producto.getCantidadDisponible());
    	productoDto.setCostoCompra(producto.getCostoCompra().doubleValue());
    	productoDto.setCostoVenta(producto.getCostoVenta().doubleValue());
    	productoDto.setFechaExpiracion(producto.getFechaExpiracion());
    	productoDto.setCantidadSeleccionada(0);
    	productoDto.setSeleccionado(false);
    	return productoDto;
    }
    
    public ProductoDto toProductoDto(NonExpiredAvailableProductsView producto) throws Exception {
    	ProductoDto productoDto = new ProductoDto();
    	productoDto.setId(producto.getId());
    	InvDescripcionProducto descripcionProducto = (InvDescripcionProducto) mDAO.findById(InvDescripcionProducto.class, producto.getIdDescripcionProducto());
    	productoDto.setNombre(descripcionProducto.getNombre());
    	productoDto.setCantidadDisponible(producto.getCantidadDisponible());
    	productoDto.setCostoCompra(producto.getCostoCompra().doubleValue());
    	productoDto.setCostoVenta(producto.getCostoVenta().doubleValue());
    	productoDto.setFechaExpiracion(producto.getFechaExpiracion());
    	productoDto.setCantidadSeleccionada(0);
    	productoDto.setSeleccionado(false);
    	return productoDto;
    }
    
    public List<ProductoDto> toProductosDtos(List<NonExpiredAvailableProductsView> productos) throws Exception {
    	List<ProductoDto> productosDtos = new ArrayList<ProductoDto>();
    	for (NonExpiredAvailableProductsView producto : productos) {
    		productosDtos.add(toProductoDto(producto));
    	}
    	return productosDtos;
    }
}
