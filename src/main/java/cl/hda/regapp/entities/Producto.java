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

@Entity
@Table(name="Producto")
public class Producto implements Serializable{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;

	@OneToMany(mappedBy="producto" , fetch = FetchType.EAGER, cascade = CascadeType.ALL,  orphanRemoval = true)
	List<DetAplicacion> detAplicacion;
	
	
	String caracteristicas;
	
	@Column(name="ingrediente_activo")
	
	String ingredienteActivo;
	
	Integer reingreso;
	
	@Column(name="umedida")
	String uMedida;
	
	@Column(name="color_etiqueta")
	String colorEtiqueta;
	
	@Column(name="mda_ifrac")
	Integer mdaIfrac;
	
	@Column(name="mda_irac")
	Integer mdaIrac;

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

	public Integer getMdaIfrac() {
		return mdaIfrac;
	}

	public void setMdaIfrac(Integer mdaIfrac) {
		this.mdaIfrac = mdaIfrac;
	}

	public Integer getMdaIrac() {
		return mdaIrac;
	}

	public void setMdaIrac(Integer mdaIrac) {
		this.mdaIrac = mdaIrac;
	}
	
	

}
