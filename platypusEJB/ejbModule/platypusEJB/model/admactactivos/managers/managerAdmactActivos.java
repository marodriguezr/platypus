package platypusEJB.model.admactactivos.managers;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import platypusEJB.model.core.entities.AdmactActivo;
import platypusEJB.model.core.entities.AdmactDescripcionActivo;
import platypusEJB.model.core.entities.AdmactTipoActivo;
import platypusEJB.model.core.managers.ManagerDAO;

/**
 * Session Bean implementation class managerAdmactActivos
 */
@Stateless
@LocalBean
public class managerAdmactActivos {

	@EJB
	private ManagerDAO mDAO;
	
    public managerAdmactActivos() {
        // TODO Auto-generated constructor stub
    }

    @SuppressWarnings("unchecked")
    public List<AdmactActivo> findAllActivos(){
    	return mDAO.findAll(AdmactActivo.class,"id");
    }
    
    @SuppressWarnings("unchecked")
    public List<AdmactDescripcionActivo> findAllActivosDescripcion(){
    	return mDAO.findAll(AdmactDescripcionActivo.class,"id");
    }
    
    @SuppressWarnings("unchecked")
    public List<AdmactTipoActivo> findAllTiposACtivvos(){
    	return mDAO.findAll(AdmactTipoActivo.class,"id");
    }
    
    public void insertarTipoACtivo(AdmactTipoActivo nuevoTipoActivo) throws Exception {
    	mDAO.insertar(nuevoTipoActivo);
    }
    
    public void insertarDetalleACtivo(AdmactDescripcionActivo nuevoDescripcionActivo, Integer idTipoActivo) throws Exception {
    	AdmactTipoActivo tipoActivo=(AdmactTipoActivo) mDAO.findById(AdmactTipoActivo.class, idTipoActivo);
    	nuevoDescripcionActivo.setAdmactTiposActivo(tipoActivo);
    	mDAO.insertar(nuevoDescripcionActivo);
    }
    
    public void insertarActivo(AdmactActivo nuevoActivo, Integer idDEscripcionActivo) throws Exception {
    	AdmactDescripcionActivo descripcionActivo=(AdmactDescripcionActivo) mDAO.findById(AdmactDescripcionActivo.class, idDEscripcionActivo);
    	nuevoActivo.setAdmactDescripcionesActivo(descripcionActivo);
    	mDAO.insertar(nuevoActivo);
    }
    
    public void actualizarTipoActivo(AdmactTipoActivo tipoActivo) throws Exception {
    	AdmactTipoActivo activo=(AdmactTipoActivo) mDAO.findById(AdmactTipoActivo.class, tipoActivo.getId());
    	activo.setDescripcion(tipoActivo.getDescripcion());
    	mDAO.actualizar(activo);
    }
    
    public void actualizarDetalleACtivo(AdmactDescripcionActivo edicionDetalleActivo, Integer idTipoActivo) throws Exception {
    	AdmactDescripcionActivo descripcionACtivo=(AdmactDescripcionActivo) mDAO.findById(AdmactDescripcionActivo.class, edicionDetalleActivo.getId());
    	AdmactTipoActivo activo=(AdmactTipoActivo) mDAO.findById(AdmactTipoActivo.class, idTipoActivo);
    	descripcionACtivo.setNombre(edicionDetalleActivo.getNombre());
    	descripcionACtivo.setDescripcion(edicionDetalleActivo.getDescripcion());
    	descripcionACtivo.setAdmactTiposActivo(activo);
    	mDAO.actualizar(descripcionACtivo);
    }
    
    public void actualizarActivo(AdmactActivo edicionActivo, Integer idDetalleActivo) throws Exception {
    	AdmactActivo activo=(AdmactActivo) mDAO.findById(AdmactActivo.class, edicionActivo.getId());
    	AdmactDescripcionActivo detalleActivo=(AdmactDescripcionActivo) mDAO.findById(AdmactDescripcionActivo.class, idDetalleActivo);
    	activo.setFechaAdquisicion(edicionActivo.getFechaAdquisicion());
    	activo.setCostoAdquisicion(edicionActivo.getCostoAdquisicion());
    	activo.setCantidad(edicionActivo.getCantidad());
    	activo.setAdmactDescripcionesActivo(detalleActivo);
    	mDAO.actualizar(activo);
    }
    
    public void eliminarTipoActivo(Integer idTipoActivo) throws Exception {
    	AdmactTipoActivo tipoActivo=(AdmactTipoActivo) mDAO.findById(AdmactTipoActivo.class, idTipoActivo);
    	mDAO.eliminar(AdmactTipoActivo.class, tipoActivo.getId());
    }
    
    public void eliminarDescripcionActivo(Integer idDescripcionActivo) throws Exception {
    	AdmactDescripcionActivo descripcionActivo=(AdmactDescripcionActivo) mDAO.findById(AdmactDescripcionActivo.class, idDescripcionActivo);
    	mDAO.eliminar(AdmactDescripcionActivo.class, descripcionActivo.getId());
    }
    
    public void eliminarActivo(Integer idActivo) throws Exception {
    	AdmactActivo activo=(AdmactActivo) mDAO.findById(AdmactActivo.class, idActivo);
    	mDAO.eliminar(AdmactActivo.class, activo.getId());
    }
    
}
