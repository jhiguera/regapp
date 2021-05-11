package cl.hda.regapp.entities;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
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
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PreRemove;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.proxy.HibernateProxy;
import org.springframework.transaction.annotation.Transactional;


@Entity
@Table(name = "cab_aplicacion")
public class CabAplicacion implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;
	
	@OneToMany(mappedBy="cabAplicacion", fetch = FetchType.LAZY, cascade = {CascadeType.ALL},  orphanRemoval = true)
	//@OnDelete(action=OnDeleteAction.CASCADE)
	private List<DetAplicacion> detAplicacion;
	
	@ManyToOne
	@JoinColumn(name="sector_id", nullable=false)
	Sector sector;
	
	@Column(name="nro_orden")
	Long nroOrden;
	
	Date fecha;
	
	Integer temporada;
	
	
	@CreationTimestamp
	@Column(name="fecha_creacion")
	private LocalDateTime fechaCreacion;
	
	String usuario;
	
	//@fechaMayorOrIgual(horaInicio = "horaInicio",horaTermino = "horaTermino")
		@Column(name="hora_inicio")
		LocalDateTime horaInicio;
		
		//@fechaMayorOrIgual(horaInicio = "horaInicio",horaTermino = "horaTermino")
	    @Column(name="hora_termino")
		LocalDateTime horaTermino;
	
	    String aplicadores;
		
		String metodo;
	
		String maquina;


		@Column(name="um_mojamiento")
		String umMojamiento;
		
		Float mojamiento;
	

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
	
	public LocalDateTime getHoraInicio() {
		return horaInicio;
	}

	public void setHoraInicio(LocalDateTime horaInicio) {
		this.horaInicio = horaInicio;
	}

	public LocalDateTime getHoraTermino() {
		return horaTermino;
	}

	public void setHoraTermino(LocalDateTime horaTermino) {
		this.horaTermino = horaTermino;
	}

	public String getAplicadores() {
		return aplicadores;
	}

	public void setAplicadores(String aplicadores) {
		this.aplicadores = aplicadores;
	}

	public String getMetodo() {
		return metodo;
	}

	public void setMetodo(String metodo) {
		this.metodo = metodo;
	}

	public String getMaquina() {
		return maquina;
	}

	public void setMaquina(String maquina) {
		this.maquina = maquina;
	}

	public String getUmMojamiento() {
		return umMojamiento;
	}

	public void setUmMojamiento(String umMojamiento) {
		this.umMojamiento = umMojamiento;
	}

	public Float getMojamiento() {
		return mojamiento;
	}

	public void setMojamiento(Float mojamiento) {
		this.mojamiento = mojamiento;
	}

	
	
	
	
	public Integer getTemporada() {
		return temporada;
	}

	public void setTemporada(Integer temporada) {
		this.temporada = temporada;
	}

	@Override
	public boolean equals(Object object) {
	
		if (!(object instanceof CabAplicacion)) {
	        return false;
	    }
		
	    CabAplicacion other =null;

	    if(object instanceof HibernateProxy){
	    	 other = (CabAplicacion) ((HibernateProxy)object).getHibernateLazyInitializer().getImplementation();
	    	
	    }else{
	        other = (CabAplicacion) object;
	    	
	    }
	    
	    if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
	        return false;
	    }
	    return true;
	}
	
	
	@Override
	public String toString() {
		if(id == null)
		  return null;
		else
		   return id.toString();
	}
	
	public int hashCode() {
	    final int prime = 31;
	    int result = 1;
	    result = prime * result + (int) (id ^ (id >>> 32));
	    return result;
	}

	
	public void eliminarDetalle(List<DetAplicacion> det) {
		det.forEach(x->{
			this.detAplicacion.remove(x);
			x.setCabAplicacion(null);

		});
	}

	
	  public void eliminarDetalle() {
		   this.detAplicacion.forEach(x->{
			this.detAplicacion.remove(x);
			x.setCabAplicacion(null);

		});
		
		
		
	}
	
	
		
}
