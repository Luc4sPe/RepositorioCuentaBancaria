package modelo;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

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
	private LocalTime hora;
	
	public CuentaBancaria(Integer numeroCuentaInteger, Cliente propietario, LocalDate fechaCreacion, Double saldo, LocalTime hora) {
		this.numeroCuentaInteger = numeroCuentaInteger;
		this.propietario = propietario;
		this.fechaCreacion = fechaCreacion;
		this.saldo=saldo;
		this.hora=hora;
	}
	public CuentaBancaria() {
		
	}
	
	
	 public static CuentaBancaria factoryCuenta(INuemeroCuentaBancaria numeroCuenta, Cliente propietario, IfechaSistema fechaCreacion,Double saldocuenta,LocalTime hora) throws CuentaIncompletaexcepcion, CuentaSaldoNegativoExcepcion {
	        if(numeroCuenta==null || propietario==null || fechaCreacion==null || saldocuenta==null || hora==null){
	            throw new CuentaIncompletaexcepcion();
	        }
	        else if (saldocuenta>=0) {
	        
	            return new CuentaBancaria(numeroCuenta.getNumeroCuentaBancariaNuevo(), propietario,fechaCreacion.getDate(),saldocuenta,hora );
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
	

	public LocalTime getHora() {
		return hora;
	}
	public void setHora(LocalTime hora) {
		this.hora = hora;
	}
	
	
	
	@Override
	public String toString() {
		return "CuentaBancaria [numeroCuentaInteger=" + numeroCuentaInteger + ", propietario=" + propietario
				+ ", fechaCreacion=" + fechaCreacion + ", saldo=" + saldo + ", hora=" + hora + "]";
	}
	public CuentaBancaria getSaldo(Double result) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	 
}
