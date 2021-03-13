package platypusWeb.api.rest.auditoria;

import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import platypusEJB.model.auditoria.managers.ManagerAuditoria;
import platypusEJB.model.core.entities.AudBitacora;
import platypusEJB.model.core.utilities.ModelUtil;

@RequestScoped
@Path("auditoria")
@Produces("application/json")
@Consumes("application/json")
public class ServiceRestAuditoria {
	@EJB
	private ManagerAuditoria mAuditoria;
	
	/**
	 * Ruta completa /rest/auditoria/bitacoraAyer
	 * @return
	 */
	@GET
	@Path(value = "bitacoraAyer")
	public List<AudBitacora> findBitacoraAyer() {
		return mAuditoria.findBitacoraByFecha(ModelUtil.addDays(new Date(), -1), new Date());
	}
}
