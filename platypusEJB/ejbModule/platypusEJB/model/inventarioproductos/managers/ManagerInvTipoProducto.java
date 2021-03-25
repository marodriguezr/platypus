package platypusEJB.model.inventarioproductos.managers;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import platypusEJB.model.core.entities.InvTipoProducto;
import platypusEJB.model.core.managers.ManagerDAO;

/**
 * Session Bean implementation class ManagerInvTipoProducto
 */
@Stateless
@LocalBean
public class ManagerInvTipoProducto {
	
	@EJB
	private ManagerDAO mDAO;

    /**
     * Default constructor. 
     */
    public ManagerInvTipoProducto() {
        // TODO Auto-generated constructor stub
    }

    public void insertarInvTipoProducto(InvTipoProducto nuevoInvTipoProducto ) throws Exception {
    	mDAO.insertar(nuevoInvTipoProducto);
    }
    
    public void actualizarInvTipoProducto(InvTipoProducto edicionInvTipoProducto) throws Exception {
    	InvTipoProducto invTipoProducto=(InvTipoProducto) mDAO.findById(InvTipoProducto.class,
    			edicionInvTipoProducto.getId());
    	
    	
         invTipoProducto.setDescripcion(edicionInvTipoProducto.getDescripcion());
    	
    	mDAO.actualizar(invTipoProducto);
    }
    
    public void eliminarInvTipoProducto(int idInvTipoProducto) throws Exception {
    	InvTipoProducto invTipoProducto=(InvTipoProducto) mDAO.findById(InvTipoProducto.class, 
    			idInvTipoProducto);
    	mDAO.eliminar(InvTipoProducto.class, invTipoProducto.getId());
    }
    
    public List<InvTipoProducto> findAllInvTipoProductos(){
    	return mDAO.findAll(InvTipoProducto.class, "id");
    }
}
