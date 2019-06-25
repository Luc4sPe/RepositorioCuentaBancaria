package repositorio;

import modelo.CuentaBancaria;

public interface IextraccionDinero {
	boolean guardarExtraccionDinero(CuentaBancaria extraccion);
	public Double getExtraccionDinero();

}
