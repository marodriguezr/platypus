package platypusWeb.controller.acactivos;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import platypusEJB.model.admactactivos.managers.managerAdmactActivos;
import platypusEJB.model.core.entities.AdmactActivo;
import platypusEJB.model.core.entities.AdmactDescripcionActivo;
import platypusEJB.model.core.entities.AdmactTipoActivo;
import platypusWeb.controller.utilities.JSFUtil;

@Named
@SessionScoped
public class BeanActActivos implements Serializable{

	@EJB
	private managerAdmactActivos mActivos;
	
	private List<AdmactTipoActivo> listaToposActivos;
	private List<AdmactDescripcionActivo> listaDescripcionActivos;
	private List<AdmactActivo> listaActivos;
	private AdmactTipoActivo nuevoTipoActivo;
	private AdmactDescripcionActivo nuevoDescripcionActivo;
	private AdmactActivo nuevoActivo;
	private AdmactTipoActivo edicionTipoActivo;
	private AdmactDescripcionActivo edicionDescripcionActivo;
	private AdmactActivo edicionActivo;
	private Integer tipoActivoSeleccionado;
	private Integer descripcionActivoSeleccionado;
	private Integer tipoActivoEdicionSeleccionado;
	private Integer descripcionActivoEdicionSeleccionado;
	
	
	public BeanActActivos() {
		// TODO Auto-generated constructor stub
	}
	
	public String actionMenuTiposActivos() {
		listaToposActivos=mActivos.findAllTiposACtivvos();
		return "tipo_activos";
	}
	
	public String actionMenuDetallesActivos() {
		listaDescripcionActivos=mActivos.findAllActivosDescripcion();
		return "detalle_activos";
	}
	
	public String actionMenuActivos() {
		listaActivos=mActivos.findAllActivos();
		return "activos";
	}
	
	public String actionMenuNuevoTipoActivo() {
		nuevoTipoActivo= new AdmactTipoActivo();
		return "tipo_activo_nuevo";
	}
	
	public String actionMenuNuevoDescripcionActivo() {
		nuevoDescripcionActivo=new AdmactDescripcionActivo();
		listaToposActivos=mActivos.findAllTiposACtivvos();
		return "descripcion_activo_nuevo";
	}
	
	public String actionMenuNuevoActivo() {
		nuevoActivo= new AdmactActivo();
		listaDescripcionActivos=mActivos.findAllActivosDescripcion();
		return "activo_nuevo";
	}
	
	public void actionListenerIngresarNuevoTipoActivo() {
		try {
			mActivos.insertarTipoACtivo(nuevoTipoActivo);
			listaToposActivos=mActivos.findAllTiposACtivvos();
			nuevoActivo= new AdmactActivo();
			JSFUtil.crearMensajeINFO("Tipo activo creado");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			JSFUtil.crearMensajeERROR("No se a podido ingresar el tipo de activo");
		}
	}
	
	public void actionListeneIngresaDescripcionActivo() {
		try {
			mActivos.insertarDetalleACtivo(nuevoDescripcionActivo, tipoActivoSeleccionado);
			listaDescripcionActivos=mActivos.findAllActivosDescripcion();
			nuevoDescripcionActivo= new AdmactDescripcionActivo();
			JSFUtil.crearMensajeINFO("Descripcion activo creado");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			JSFUtil.crearMensajeERROR("Descripcion activo no creado");
		}
	}
	
	public void actionListenesIngresarActivo() {
		try {
			mActivos.insertarActivo(nuevoActivo, descripcionActivoSeleccionado);
			listaActivos=mActivos.findAllActivos();
			nuevoActivo=new AdmactActivo();
			JSFUtil.crearMensajeINFO("Activo creado");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			JSFUtil.crearMensajeERROR("No se pudo crear el activo");
		}
	}
	
	public String actionSeleccionarDetalleActivo(AdmactDescripcionActivo descripcionActivo) {
		edicionDescripcionActivo=descripcionActivo;
		listaToposActivos=mActivos.findAllTiposACtivvos();
		return "editar_des_activo";
	}
	
	public String actionSelecionarActivo(AdmactActivo activo) {
		edicionActivo= activo;
		listaDescripcionActivos=mActivos.findAllActivosDescripcion();
		return "editar_activo";
	}
	
	
	public String actionSelecionarTipoACtivo(AdmactTipoActivo tipoActivo) {
		edicionTipoActivo= tipoActivo;
		return "editar_tipo_activo";
	}
	
	public void actionListenerActualizarTipoActivo() {
		try {
			mActivos.actualizarTipoActivo(edicionTipoActivo);
			listaToposActivos=mActivos.findAllTiposACtivvos();
			JSFUtil.crearMensajeINFO("Tipo activo editado");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			JSFUtil.crearMensajeERROR("No se pudo editar");
		}
	}
	
	public void actionListenerActualizarDescripocionActivo() {
		try {
			mActivos.actualizarDetalleACtivo(edicionDescripcionActivo, tipoActivoEdicionSeleccionado);
			listaDescripcionActivos=mActivos.findAllActivosDescripcion();
			JSFUtil.crearMensajeINFO("Se a actualizado el detalle del activo");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			JSFUtil.crearMensajeERROR("No se a podido actualizar");
		}
	}
	
	
	public void actionListenerActualizarActivo() {
		try {
			mActivos.actualizarActivo(edicionActivo, descripcionActivoEdicionSeleccionado);
			listaActivos=mActivos.findAllActivos();
			JSFUtil.crearMensajeINFO("Activo actualizado");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			JSFUtil.crearMensajeERROR("No se a podido actualizar");
		}
	}

	public void actionEliminarTipoActivo(int idTipoActivo) {
		try {
			mActivos.eliminarTipoActivo(idTipoActivo);
			listaToposActivos=mActivos.findAllTiposACtivvos();
			JSFUtil.crearMensajeINFO("Se a eliminado el tipo activo");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			JSFUtil.crearMensajeERROR("No se ha podido eliminar");
		}
	}
	
	public void actionEliminarDescripcionActivo(int idDescripcionActivo) {
		try {
			mActivos.eliminarDescripcionActivo(idDescripcionActivo);
			listaDescripcionActivos=mActivos.findAllActivosDescripcion();
			JSFUtil.crearMensajeINFO("Se ha eliminado");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void actionEliminarActivo(int idActivo) {
		try {
			mActivos.eliminarActivo(descripcionActivoSeleccionado);
			listaActivos=mActivos.findAllActivos();
			JSFUtil.crearMensajeINFO("Activo eliminado");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			JSFUtil.crearMensajeERROR("No se puedo eliminar");
		}
	}
	
	public List<AdmactTipoActivo> getListaToposActivos() {
		return listaToposActivos;
	}

	public void setListaToposActivos(List<AdmactTipoActivo> listaToposActivos) {
		this.listaToposActivos = listaToposActivos;
	}

	public List<AdmactDescripcionActivo> getListaDescripcionActivos() {
		return listaDescripcionActivos;
	}

	public void setListaDescripcionActivos(List<AdmactDescripcionActivo> listaDescripcionActivos) {
		this.listaDescripcionActivos = listaDescripcionActivos;
	}

	public List<AdmactActivo> getListaActivos() {
		return listaActivos;
	}

	public void setListaActivos(List<AdmactActivo> listaActivos) {
		this.listaActivos = listaActivos;
	}

	public AdmactTipoActivo getNuevoTipoActivo() {
		return nuevoTipoActivo;
	}

	public void setNuevoTipoActivo(AdmactTipoActivo nuevoTipoActivo) {
		this.nuevoTipoActivo = nuevoTipoActivo;
	}

	public AdmactDescripcionActivo getNuevoDescripcionActivo() {
		return nuevoDescripcionActivo;
	}

	public void setNuevoDescripcionActivo(AdmactDescripcionActivo nuevoDescripcionActivo) {
		this.nuevoDescripcionActivo = nuevoDescripcionActivo;
	}

	public AdmactActivo getNuevoActivo() {
		return nuevoActivo;
	}

	public void setNuevoActivo(AdmactActivo nuevoActivo) {
		this.nuevoActivo = nuevoActivo;
	}

	public AdmactTipoActivo getEdicionTipoActivo() {
		return edicionTipoActivo;
	}

	public void setEdicionTipoActivo(AdmactTipoActivo edicionTipoActivo) {
		this.edicionTipoActivo = edicionTipoActivo;
	}

	public AdmactDescripcionActivo getEdicionDescripcionActivo() {
		return edicionDescripcionActivo;
	}

	public void setEdicionDescripcionActivo(AdmactDescripcionActivo edicionDescripcionActivo) {
		this.edicionDescripcionActivo = edicionDescripcionActivo;
	}

	public AdmactActivo getEdicionActivo() {
		return edicionActivo;
	}

	public void setEdicionActivo(AdmactActivo edicionActivo) {
		this.edicionActivo = edicionActivo;
	}

	public Integer getTipoActivoSeleccionado() {
		return tipoActivoSeleccionado;
	}

	public void setTipoActivoSeleccionado(Integer tipoActivoSeleccionado) {
		this.tipoActivoSeleccionado = tipoActivoSeleccionado;
	}

	public Integer getDescripcionActivoSeleccionado() {
		return descripcionActivoSeleccionado;
	}

	public void setDescripcionActivoSeleccionado(Integer descripcionActivoSeleccionado) {
		this.descripcionActivoSeleccionado = descripcionActivoSeleccionado;
	}

	public Integer getTipoActivoEdicionSeleccionado() {
		return tipoActivoEdicionSeleccionado;
	}

	public void setTipoActivoEdicionSeleccionado(Integer tipoActivoEdicionSeleccionado) {
		this.tipoActivoEdicionSeleccionado = tipoActivoEdicionSeleccionado;
	}

	public Integer getDescripcionActivoEdicionSeleccionado() {
		return descripcionActivoEdicionSeleccionado;
	}

	public void setDescripcionActivoEdicionSeleccionado(Integer descripcionActivoEdicionSeleccionado) {
		this.descripcionActivoEdicionSeleccionado = descripcionActivoEdicionSeleccionado;
	}
	
	
}
