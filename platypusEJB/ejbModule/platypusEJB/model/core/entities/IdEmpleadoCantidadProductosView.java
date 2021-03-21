package platypusEJB.model.core.entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the id_empleado_cantidad_productos_view database table.
 * 
 */
@Entity
@Table(name="id_empleado_cantidad_productos_view")
@NamedQuery(name="IdEmpleadoCantidadProductosView.findAll", query="SELECT i FROM IdEmpleadoCantidadProductosView i")
public class IdEmpleadoCantidadProductosView implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long count;

	@Id
	private Integer id;

	public IdEmpleadoCantidadProductosView() {
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