package cl.hda.regapp.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.proxy.HibernateProxy;


@Entity
@Table(name="Producto")
public class Producto implements Serializable{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;

	@OneToMany(mappedBy="producto")
	List<DetAplicacion> detAplicacion;
	
	String producto;
	
	String caracteristicas;
	
	String ingredienteActivo;
	
	Integer reingreso;
	
	@Column(name="umedida" ,length=5)
	String uMedida;
	
	@Column(name="color_etiqueta" ,length=10)
	String colorEtiqueta;
	
	@Column(name="mda_frac",length=10)
	String mdaFrac;
	
	@Column(name="mda_irac", length=10)
	String mdaIrac;
	
	Boolean vigente;
	
	@Column(name = "temporada", length = 4)
	Integer temporada;

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

	public String getCaracteristicas() {
		return caracteristicas;
	}

	public void setCaracteristicas(String caracteristicas) {
		this.caracteristicas = caracteristicas;
	}

	public String getIngredienteActivo() {
		return ingredienteActivo;
	}

	public void setIngredienteActivo(String ingredienteActivo) {
		this.ingredienteActivo = ingredienteActivo;
	}

	public Integer getReingreso() {
		return reingreso;
	}

	public void setReingreso(Integer reingreso) {
		this.reingreso = reingreso;
	}

	public String getuMedida() {
		return uMedida;
	}

	public void setuMedida(String uMedida) {
		this.uMedida = uMedida;
	}

	public String getColorEtiqueta() {
		return colorEtiqueta;
	}

	public void setColorEtiqueta(String colorEtiqueta) {
		this.colorEtiqueta = colorEtiqueta;
	}
	
	public String getMdaFrac() {
		return mdaFrac;
	}

	public void setMdaFrac(String mdaFrac) {
		this.mdaFrac = mdaFrac;
	}

	public String getMdaIrac() {
		return mdaIrac;
	}

	public void setMdaIrac(String mdaIrac) {
		this.mdaIrac = mdaIrac;
	}

	public String getProducto() {
		return producto;
	}

	public void setProducto(String producto) {
		this.producto = producto;
	}
	
	
	
	public Boolean getVigente() {
		return vigente;
	}

	public void setVigente(Boolean vigente) {
		this.vigente = vigente;
	}

	public int hashCode() {
	    final int prime = 33;
	    int result = 1;
	    result = prime * result + (int) (id ^ (id >>> 32));
	    return result;
	}

	
	
	
	@Override
	public boolean equals(Object object) {
	
		if (!(object instanceof Producto)) {
	        return false;
	    }
		
	    Producto other =null;

	    if(object instanceof HibernateProxy){
	    	 other = (Producto) ((HibernateProxy)object).getHibernateLazyInitializer().getImplementation();
	    	
	    }else{
	        other = (Producto) object;
	    	
	    }
	    
	    if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
	        return false;
	    }
	    return true;
	}
	
	
	
	
	
    public Integer getTemporada() {
		return temporada;
	}

	public void setTemporada(Integer temporada) {
		this.temporada = temporada;
	}

	@Override
	public String toString() {
		return id.toString();
	}
	

}
