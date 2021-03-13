package platypusEJB.model.core.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;


/**
 * The persistent class for the pos_porcentajes_iva database table.
 * 
 */
@Entity
@Table(name="pos_porcentajes_iva")
@NamedQuery(name="PosPorcentajeIva.findAll", query="SELECT p FROM PosPorcentajeIva p")
public class PosPorcentajeIva implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private Integer id;

	@Column(nullable=false, precision=2)
	private BigDecimal porcentaje;

	//bi-directional many-to-one association to PosVenta
	@OneToMany(mappedBy="posPorcentajesIva")
	private List<PosVenta> posVentas;

	public PosPorcentajeIva() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public BigDecimal getPorcentaje() {
		return this.porcentaje;
	}

	public void setPorcentaje(BigDecimal porcentaje) {
		this.porcentaje = porcentaje;
	}

	public List<PosVenta> getPosVentas() {
		return this.posVentas;
	}

	public void setPosVentas(List<PosVenta> posVentas) {
		this.posVentas = posVentas;
	}

	public PosVenta addPosVenta(PosVenta posVenta) {
		getPosVentas().add(posVenta);
		posVenta.setPosPorcentajesIva(this);

		return posVenta;
	}

	public PosVenta removePosVenta(PosVenta posVenta) {
		getPosVentas().remove(posVenta);
		posVenta.setPosPorcentajesIva(null);

		return posVenta;
	}

}