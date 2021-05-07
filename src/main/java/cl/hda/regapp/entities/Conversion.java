package cl.hda.regapp.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class Conversion implements Serializable {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@Column(name="u_medida_1")
	String uMedida1;
	@Column(name="u_medida_2")
	String uMedida2;
	
	String expresion;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getuMedida1() {
		return uMedida1;
	}

	public void setuMedida1(String uMedida1) {
		this.uMedida1 = uMedida1;
	}

	public String getuMedida2() {
		return uMedida2;
	}

	public void setuMedida2(String uMedida2) {
		this.uMedida2 = uMedida2;
	}

	public String getExpresion() {
		return expresion;
	}

	public void setExpresion(String expresion) {
		this.expresion = expresion;
	}
	
	
	
	
	

}
