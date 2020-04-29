package cl.hda.regapp.entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="sector")
public class Sector {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;

	@OneToMany(mappedBy="sector" , fetch = FetchType.EAGER, cascade = CascadeType.ALL,  orphanRemoval = true)
	List<CabAplicacion> cabAplicacion;
	
	String parcela;
	
	String cuartelero;
	
	Float superficieReal;
	
	String especie;
	
	String variedad;
	
	String portainjerto;
	
	Integer plantasReales;
	
	
}
