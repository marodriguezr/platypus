package platypusEJB.model.inventarioproductos.managers;


import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import platypusEJB.model.core.entities.AdmrecRecepcion;
import platypusEJB.model.core.entities.InvDescripcionProducto;
import platypusEJB.model.core.entities.InvProducto;
import platypusEJB.model.core.managers.ManagerDAO;

/**
 * Session Bean implementation class ManagerInventarioProductos
 */
@Stateless
@LocalBean
public class ManagerInventarioProductos {

	@EJB
	private ManagerDAO mDAO;
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
    
    public List<InvProducto> findAllInvProductos(){
    	return mDAO.findAll(InvProducto.class, "id");
    }

}
