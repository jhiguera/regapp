package cl.hda.regapp.entities;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.hibernate.proxy.HibernateProxy;


@Entity
@Table(name="sector")
public class Sector implements Serializable  {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;

	@OneToMany(mappedBy="sector" , fetch = FetchType.LAZY, cascade = CascadeType.REMOVE,  orphanRemoval = false)
	List<CabAplicacion> cabAplicacion;
	
	@Column(name="sector_productivo")
	String sector;
	
	String pseudonimo;
	
	
	
	String parcela;
	
	@Column(name="nombre_cuartelero")
	String cuartelero;
	
	@Column(name="superficie_real_plantada")
	Float superficieReal;
	
	@Column(name="especie_plantada")
	String especie;
	
	@Column(name="variedad_copa")
	String variedad;
	
	@Column(name="variedad_portainjerto")
	String portainjerto;
	
	@Column(name="plantas_reales")
	Double plantasReales;
	
	@Column(name = "temporada", precision=4, length=4)
	Integer temporada;
	
	
	
    public Integer getTemporada() {
		return temporada;
	}

	public void setTemporada(Integer temporada) {
		this.temporada = temporada;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<CabAplicacion> getCabAplicacion() {
		return cabAplicacion;
	}

	public void setCabAplicacion(List<CabAplicacion> cabAplicacion) {
		this.cabAplicacion = cabAplicacion;
	}

	public String getSector() {
		return sector;
	}

	public void setSector(String sector) {
		this.sector = sector;
	}

	public String getParcela() {
		return parcela;
	}

	public void setParcela(String parcela) {
		this.parcela = parcela;
	}

	public String getCuartelero() {
		return cuartelero;
	}

	public void setCuartelero(String cuartelero) {
		this.cuartelero = cuartelero;
	}

	public Float getSuperficieReal() {
		return superficieReal;
	}

	public void setSuperficieReal(Float superficieReal) {
		this.superficieReal = superficieReal;
	}

	public String getEspecie() {
		return especie;
	}

	public void setEspecie(String especie) {
		this.especie = especie;
	}

	public String getVariedad() {
		return variedad;
	}

	public void setVariedad(String variedad) {
		this.variedad = variedad;
	}

	public String getPortainjerto() {
		return portainjerto;
	}

	public void setPortainjerto(String portainjerto) {
		this.portainjerto = portainjerto;
	}

	public Double getPlantasReales() {
		return plantasReales;
	}

	public void setPlantasReales(Double plantasReales) {
		this.plantasReales = plantasReales;
	}

	public String getPseudonimo() {
		return pseudonimo;
	}

	public void setPseudonimo(String pseudonimo) {
		this.pseudonimo = pseudonimo;
	}

	@Override
	public int hashCode() {
	    final int prime = 31;
	    int result = 1;
	    result = prime * result + (int) (id ^ (id >>> 32));
	    return result;
	}

	
	
	@Override
	public boolean equals(Object object) {
	
		if (!(object instanceof Sector)) {
	        return false;
	    }
		
	    Sector other =null;

	    if(object instanceof HibernateProxy){
	    	 other = (Sector) ((HibernateProxy)object).getHibernateLazyInitializer().getImplementation();
	    	
	    }else{
	        other = (Sector) object;
	    	
	    }
	    
	    if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
	        return false;
	    }
	    return true;
	}

    
    @Override
	public String toString() {
		return id.toString();
	}
	
}
