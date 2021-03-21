package platypusEJB.model.core.entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the id_empleado_cantidad_clientes_view database table.
 * 
 */
@Entity
@Table(name="id_empleado_cantidad_clientes_view")
@NamedQuery(name="IdEmpleadoCantidadClientesView.findAll", query="SELECT i FROM IdEmpleadoCantidadClientesView i")
public class IdEmpleadoCantidadClientesView implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long count;

	@Id
	private Integer id;

	public IdEmpleadoCantidadClientesView() {
	}

	public Long getCount() {
		return this.count;
	}

	public void setCount(Long count) {
		this.count = count;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

}