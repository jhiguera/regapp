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
	
	String producto;
	
	String caracteristicas;
	
	@Column(name="ingrediente_activo")
	String ingredienteActivo;
	
	Integer reingreso;
	
	@Column(name="umedida")
	String uMedida;
	
	@Column(name="color_etiqueta")
	String colorEtiqueta;
	
	@Column(name="mda_frac")
	String mdaFrac;
	
	@Column(name="mda_irac")
	String mdaIrac;

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
	
	
	

}
