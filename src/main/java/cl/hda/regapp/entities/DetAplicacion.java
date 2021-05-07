package cl.hda.regapp.entities;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;

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
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.proxy.HibernateProxy;

import cl.hda.regapp.validation.fechaMayorOrIgual;


@Entity
@Table(name="det_aplicacion")
//@fechaMayorOrIgual(horaInicio = "horaInicio",horaTermino = "horaTermino")
public class DetAplicacion implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	Long id;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="cab_aplicacion_id",  nullable = false, updatable = true, insertable = true )
	CabAplicacion cabAplicacion;
	
    @ManyToOne
	@JoinColumn(name="producto_id", nullable = false,updatable=true,insertable=true)
	Producto producto;
	
  
	Float dosis;
	
	
	@Column(name="um_dosis")
	String umDosis;
		
	String objetivo;
	
	Integer carencia;
	
	@Column(name="gasto_total")
	Double gastoTotal;
	
	@Column(name="mojamiento_real")
	Double mojamientoReal;
	
	@Column(name="fecha_viable_cosecha")
	Date fechaViableCosecha;
	
	
	
	//@fechaMayorOrIgual(horaInicio = "horaInicio",horaTermino = "horaTermino")
	@Column(name="hora_inicio")
	LocalDateTime horaInicio;
	
	//@fechaMayorOrIgual(horaInicio = "horaInicio",horaTermino = "horaTermino")
    @Column(name="hora_termino")
	LocalDateTime horaTermino;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public CabAplicacion getCabAplicacion() {
		return cabAplicacion;
	}

	public void setCabAplicacion(CabAplicacion cabAplicacion) {
		this.cabAplicacion = cabAplicacion;
	}

	public Float getDosis() {
		return dosis;
	}

	public void setDosis(Float dosis) {
		this.dosis = dosis;
	}

	public String getUmDosis() {
		return umDosis;
	}

	public void setUmDosis(String umDosis) {
		this.umDosis = umDosis;
	}

	

	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}
	

	
	
	public Integer getCarencia() {
		return carencia;
	}

	public void setCarencia(Integer carencia) {
		this.carencia = carencia;
	}

	public String getObjetivo() {
		return objetivo;
	}

	public void setObjetivo(String objetivo) {
		this.objetivo = objetivo;
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
	
	public Double getGastoTotal() {
		return gastoTotal;
	}

	public void setGastoTotal(Double gastoTotal) {
		this.gastoTotal = gastoTotal;
	}

	
	
	
	
	
	public Date getFechaViableCosecha() {
		return fechaViableCosecha;
	}

	public void setFechaViableCosecha(Date fechaViableCosecha) {
		this.fechaViableCosecha = fechaViableCosecha;
	}

	public Double getMojamientoReal() {
		return mojamientoReal;
	}

	public void setMojamientoReal(Double mojamientoReal) {
		this.mojamientoReal = mojamientoReal;
	}

	@Override
	public boolean equals(Object object) {
	
		if (!(object instanceof DetAplicacion)) {
	        return false;
	    }
		
	    DetAplicacion other =null;

	    if(object instanceof HibernateProxy){
	    	 other = (DetAplicacion) ((HibernateProxy)object).getHibernateLazyInitializer().getImplementation();
	    	
	    }else{
	        other = (DetAplicacion) object;
	    	
	    }
	    
	    if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
	        return false;
	    }
	    return true;
	}
		
	@Override
	public String toString() {
		//if(id == null)
		 // return null;
		//else
		   return id.toString();
	}

	/*
	@PreUpdate
	public void pre() throws Exception{
		
		if(horaInicio.isAfter(horaTermino)){
			 throw new  Exception("Valor no puede ser mayor");
		}
	}
	*/
	
}
