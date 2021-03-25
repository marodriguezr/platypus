package platypusEJB.model.pos.managers;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import platypusEJB.model.core.entities.PosPorcentajeIva;
import platypusEJB.model.core.managers.ManagerDAO;

/**
 * Session Bean implementation class PorcentajeIvaManager
 */
@Stateless
@LocalBean
public class PorcentajeIvaManager {
	
	@EJB
	ManagerDAO dao;

    /**
     * Default constructor. 
     */
    public PorcentajeIvaManager() {
        // TODO Auto-generated constructor stub
    }
    
    @SuppressWarnings("unchecked")
	public List<PosPorcentajeIva> findAllPorcentajesIva() {
    	return dao.findAll(PosPorcentajeIva.class);
    }
    
    public PosPorcentajeIva findPorcentajeIvaById(int id) throws Exception {
    	return (PosPorcentajeIva) dao.findById(PosPorcentajeIva.class, id);
    }

}
