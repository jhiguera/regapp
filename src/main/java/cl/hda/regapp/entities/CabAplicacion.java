package cl.hda.regapp.entities;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.hibernate.annotations.CreationTimestamp;

@Entity
@Table(name = "cab_aplicacion")
public class CabAplicacion {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;

	@OneToMany(mappedBy="cabAplicacion" , fetch = FetchType.EAGER, cascade = CascadeType.ALL,  orphanRemoval = true)
	private List<DetAplicacion> detAplicacion;
	
	@ManyToOne
	@JoinColumn(name="sector_id", nullable=false)
	Sector sector;
	
	@Column(name="nro_orden")
	Long nroOrden;
	
	Date fecha;
	
	@CreationTimestamp
	@Column(name="fecha_creacion")
	private LocalDateTime fechaCreacion;
	
	String usuario;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<DetAplicacion> getDetAplicacion() {
		return detAplicacion;
	}

	public void setDetAplicacion(List<DetAplicacion> detAplicacion) {
		this.detAplicacion = detAplicacion;
	}

	public Long getNroOrden() {
		return nroOrden;
	}

	public void setNroOrden(Long nroOrden) {
		this.nroOrden = nroOrden;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public LocalDateTime getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(LocalDateTime fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public Sector getSector() {
		return sector;
	}

	public void setSector(Sector sector) {
		this.sector = sector;
	}
	
	
		
}
