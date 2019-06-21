package modelo;

import java.time.LocalDate;

import excepciones.CuentaIncompletaexcepcion;
import excepciones.CuentaSaldoNegativoExcepcion;
import repositorio.INuemeroCuentaBancaria;
import repositorio.IfechaSistema;
import repositorio.IsaldoCuentaBancaria;


public class CuentaBancaria {
	private Integer numeroCuentaInteger;
	private Cliente propietario;
	private LocalDate fechaCreacion;
	private Double saldo;
	
	public CuentaBancaria(Integer numeroCuentaInteger, Cliente propietario, LocalDate fechaCreacion, Double saldo) {
		this.numeroCuentaInteger = numeroCuentaInteger;
		this.propietario = propietario;
		this.fechaCreacion = fechaCreacion;
		this.saldo=saldo;
	}
	
	
	 public static CuentaBancaria factoryCuenta(INuemeroCuentaBancaria numeroCuenta, Cliente propietario, IfechaSistema fechaCreacion,IsaldoCuentaBancaria saldocuenta) throws CuentaIncompletaexcepcion, CuentaSaldoNegativoExcepcion {
	        if(numeroCuenta==null || propietario==null || fechaCreacion==null || saldocuenta==null){
	            throw new CuentaIncompletaexcepcion();
	        }
	        else if (saldocuenta.getSaldoCuenta()>=0) {
	        
	            return new CuentaBancaria(numeroCuenta.getNumeroCuentaBancariaNuevo(), propietario,fechaCreacion.getDate(),saldocuenta.getSaldoCuenta() );
	        }else {
				throw new CuentaSaldoNegativoExcepcion();
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


	public Double getSaldo() {
		return saldo;
	}


	public void setSaldo(Double saldo) {
		this.saldo = saldo;
	}
	
	
	 
}
