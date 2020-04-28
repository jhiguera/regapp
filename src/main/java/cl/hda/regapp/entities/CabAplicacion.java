package cl.hda.regapp.entities;

import java.time.LocalDateTime;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
@Table(name = "cab_aplicacion")
public class CabAplicacion {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	Long id;
	
	Long nroOrden;
	
	Date fecha;
	
	@CreationTimestamp
	private LocalDateTime fechaCreacion;
	
	String usuario;
	
		
}
