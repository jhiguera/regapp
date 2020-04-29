package cl.hda.regapp.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.proxy.HibernateProxy;


@Entity
@Table(name="det_aplicacion")
public class DetAplicacion {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	Long id;
	
	 @ManyToOne
	 @JoinColumn(name="cab_aplicacion_id", nullable=false)
    CabAplicacion cabAplicacion;
	
	 @ManyToOne
	 @JoinColumn(name="producto_id", nullable = false, updatable = true, insertable = true)
	Producto producto;
	
	Float dosis;
	
	Float mojamiento;
	
	@Column(name="um_mojamiento")
	String umMojamiento;
	
	@Column(name="um_carencia")
	String umCarencia;
	
	String aplicadores;
	
	@Column(name="maq_utilizada")
	String maqUtilizada;

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

	public Float getMojamiento() {
		return mojamiento;
	}

	public void setMojamiento(Float mojamiento) {
		this.mojamiento = mojamiento;
	}

	public String getUmMojamiento() {
		return umMojamiento;
	}

	public void setUmMojamiento(String umMojamiento) {
		this.umMojamiento = umMojamiento;
	}

	public String getUmCarencia() {
		return umCarencia;
	}

	public void setUmCarencia(String umCarencia) {
		this.umCarencia = umCarencia;
	}

	public String getAplicadores() {
		return aplicadores;
	}

	public void setAplicadores(String aplicadores) {
		this.aplicadores = aplicadores;
	}

	public String getMaqUtilizada() {
		return maqUtilizada;
	}

	public void setMaqUtilizada(String maqUtilizada) {
		this.maqUtilizada = maqUtilizada;
	}

	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
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
		if(id == null)
		  return null;
		else
		   return id.toString();
	}

	
}
