package modelo;

import java.time.LocalDate;

import excepciones.CuentaIncompletaexcepcion;
import repositorio.INuemeroCuentaBancaria;
import repositorio.IfechaSistema;


public class CuentaBancaria {
	private Integer numeroCuentaInteger;
	private Cliente propietario;
	private LocalDate fechaCreacion;
	
	public CuentaBancaria(Integer numeroCuentaInteger, Cliente propietario, LocalDate fechaCreacion) {
		this.numeroCuentaInteger = numeroCuentaInteger;
		this.propietario = propietario;
		this.fechaCreacion = fechaCreacion;
	}
	
	
	 public static CuentaBancaria factoryCuenta(INuemeroCuentaBancaria numeroCuenta, Cliente propietario, IfechaSistema fechaCreacion) throws CuentaIncompletaexcepcion {
	        if(numeroCuenta==null || propietario==null || fechaCreacion==null){
	            throw new CuentaIncompletaexcepcion();
	        }
	        else
	        {
	            return new CuentaBancaria(numeroCuenta.getNumeroCuentaBancariaNuevo(), propietario,fechaCreacion.getDate() );
	        }
	    }


	public Integer getNumeroCuentaInteger() {
		return numeroCuentaInteger;
	}


	public void setNumeroCuentaInteger(Integer numeroCuentaInteger) {
		this.numeroCuentaInteger = numeroCuentaInteger;
	}


	public Cliente getPropietario() {
		return propietario;
	}


	public void setPropietario(Cliente propietario) {
		this.propietario = propietario;
	}


	public LocalDate getFechaCreacion() {
		return fechaCreacion;
	}


	public void setFechaCreacion(LocalDate fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}
	
	
	 
}
