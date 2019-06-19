package modelo;

import java.time.LocalDate;

import excepciones.CuentaIncompletaexcepcion;


public class CuentaBancaria {
	private Integer numeroCuentaInteger;
	private String nombrePropietario;
	private LocalDate fechaCreacion;
	
	public CuentaBancaria(Integer numeroCuentaInteger, String nombrePropietario, LocalDate fechaCreacion) {
		this.numeroCuentaInteger = numeroCuentaInteger;
		this.nombrePropietario = nombrePropietario;
		this.fechaCreacion = fechaCreacion;
	}
	
	
	 public static CuentaBancaria factoryCuenta(Integer numeroCuenta, String nombrePropietario, LocalDate fechaCreacion) throws CuentaIncompletaexcepcion {
	        if(numeroCuenta==null || nombrePropietario==null || fechaCreacion==null){
	            throw new CuentaIncompletaexcepcion();
	        }
	        else
	        {
	            return new CuentaBancaria(numeroCuenta, nombrePropietario, fechaCreacion);
	        }
	    }
	
	
	 
}
