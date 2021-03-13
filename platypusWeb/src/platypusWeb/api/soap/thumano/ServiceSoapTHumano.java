package platypusWeb.api.soap.thumano;

import java.util.List;

import javax.ejb.EJB;
import javax.jws.WebMethod;
import javax.jws.WebService;

import platypusEJB.model.thumano.dtos.DTOThmCargo;
import platypusEJB.model.thumano.managers.ManagerTHumano;

@WebService(serviceName = "ServiceSoapTHumanoService")
public class ServiceSoapTHumano {
	@EJB
	private ManagerTHumano mTHumano;
	
	@WebMethod(operationName = "findAllDTOThmCargo")
	public List<DTOThmCargo> findAllDTOThmCargo() {
		return mTHumano.findAllDTOThmCargo();
	}
}
