package modelo;

import java.time.LocalDate;
import java.time.Period;

import excepciones.ClienteIncompletoException;
import excepciones.ClienteMenorEdadExeption;
import repositorio.IfechaSistema;


public class Cliente {
	
	private Integer idCliente;
	private String nombre;
	private String numeroDocumento;
	private String domicilio;
	private String telefono;
	private LocalDate fechaNacimiento;
	
	
	
	
	
	private Cliente(Integer idCliente,String nombre, String numeroDocumento, String domicilio, String telefono,
			LocalDate fechaNacimiento) {
		this.idCliente= idCliente;
		this.nombre = nombre;
		this.numeroDocumento = numeroDocumento;
		this.domicilio = domicilio;
		this.telefono = telefono;
		this.fechaNacimiento = fechaNacimiento;
	}



	public static Cliente factoryCliente(IfechaSistema fechaSistema,Integer idCliente, String nombre, String numeroDocumento, String domicilio, String telefono,
			LocalDate fechaNacimiento) throws ClienteIncompletoException, ClienteMenorEdadExeption  {
				
		
		if(idCliente==null || nombre==null || numeroDocumento==null || domicilio==null || telefono==null || fechaNacimiento==null) {
			
			throw new ClienteIncompletoException();
		}
		
		else if ((Period.between(fechaNacimiento, fechaSistema.getDate()).getYears()) >=18) {
			
			return new Cliente(idCliente, nombre, numeroDocumento, domicilio, telefono, fechaNacimiento);
		}else {
			System.out.println("Menor de Edad");
			throw new ClienteMenorEdadExeption();
		}
		
		
	}
	
	public LocalDate getFechaNacimiento() {
		return this.fechaNacimiento;
	} 
	public String getDocumento() {
		
		return this.numeroDocumento;
	}
	
	
	
	

	  



	

	
	

}
