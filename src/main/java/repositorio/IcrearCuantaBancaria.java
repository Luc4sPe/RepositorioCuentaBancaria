package repositorio;

import modelo.CuentaBancaria;

public interface IcrearCuantaBancaria {
	boolean guardarCuenta(CuentaBancaria unaCuenta);
	CuentaBancaria verificarNumeroCuentaBancaria(Integer NumeroCuentaBancaria);
}
