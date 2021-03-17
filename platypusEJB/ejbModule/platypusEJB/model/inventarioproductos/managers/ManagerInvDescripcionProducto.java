package platypusEJB.model.inventarioproductos.managers;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import platypusEJB.model.core.entities.InvDescripcionProducto;
import platypusEJB.model.core.entities.InvProducto;
import platypusEJB.model.core.entities.InvTipoProducto;
import platypusEJB.model.core.managers.ManagerDAO;

/**
 * Session Bean implementation class ManagerInvDescripcionProducto
 */
@Stateless
@LocalBean
public class ManagerInvDescripcionProducto {

	@EJB
	private ManagerDAO mDAO;
    /**
     * Default constructor. 
     */
    public ManagerInvDescripcionProducto() {
        // TODO Auto-generated constructor stub
    }
    
    public void insertarInvDescripcionProducto(InvDescripcionProducto nuevoInvDescripcionProducto , 
    		int idTipoProducto) throws Exception {
    	InvTipoProducto invTipoProducto= (InvTipoProducto) mDAO.findById(InvTipoProducto.class, 
    			idTipoProducto);
    	nuevoInvDescripcionProducto.setInvTiposProducto(invTipoProducto);
    	mDAO.insertar(nuevoInvDescripcionProducto);
    }
    
    public void actualizarInvDescripcionProducto(InvDescripcionProducto edicionInvDescripcionProducto,int IdInvTipoProductos) throws Exception {
    	InvDescripcionProducto invDescripcionProducto=(InvDescripcionProducto) mDAO.findById(InvDescripcionProducto.class,
    			edicionInvDescripcionProducto.getId());
    	
    	InvTipoProducto invTipoProducto= (InvTipoProducto) mDAO.findById(InvTipoProducto.class,
    			IdInvTipoProductos);  
    	
    	invDescripcionProducto.setId(edicionInvDescripcionProducto.getId());
    	invDescripcionProducto.setDescripcion(edicionInvDescripcionProducto.getDescripcion());
    	invDescripcionProducto.setExpirable(edicionInvDescripcionProducto.getExpirable());
    	invDescripcionProducto.setNombre(edicionInvDescripcionProducto.getNombre());
    	invDescripcionProducto.setInvTiposProducto(invTipoProducto);
    	
    	mDAO.actualizar(invDescripcionProducto);
    }
    
    public void eliminarInvDescripcionProducto(int idInvDescripcionProducto) throws Exception {
    	InvDescripcionProducto invDescripcionProducto=(InvDescripcionProducto) mDAO.findById(InvDescripcionProducto.class, 
    			idInvDescripcionProducto);
    	mDAO.eliminar(InvDescripcionProducto.class, invDescripcionProducto.getId());
    }
    
    public List<InvDescripcionProducto> findAllInvDescripcionProductos(){
    	return mDAO.findAll(InvDescripcionProducto.class, "id");
    }

}
