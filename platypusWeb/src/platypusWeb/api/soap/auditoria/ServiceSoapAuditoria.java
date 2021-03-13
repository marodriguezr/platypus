package platypusWeb.api.soap.auditoria;

import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.jws.WebMethod;
import javax.jws.WebService;

import platypusEJB.model.auditoria.managers.ManagerAuditoria;
import platypusEJB.model.core.entities.AudBitacora;
import platypusEJB.model.core.utilities.ModelUtil;

@WebService(serviceName = "ServiceSoapAuditoria")
public class ServiceSoapAuditoria {
	
	@EJB
	private ManagerAuditoria mAuditoria;
	
	@WebMethod(operationName = "findBitacoraAyer")
	public List<AudBitacora> findBitacoraAyer() {
		return mAuditoria.findBitacoraByFecha(ModelUtil.addDays(new Date(), -1), new Date());
	}
	
	@WebMethod(operationName = "eliminarBitacora")
	public void eliminarBitacora() {
		mAuditoria.eliminarBitacora();
	}
}
