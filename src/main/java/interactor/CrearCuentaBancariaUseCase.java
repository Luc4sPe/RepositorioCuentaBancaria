package interactor;

import excepciones.CuentaBancariaExisteExcepcion;
import modelo.Cliente;
import modelo.CuentaBancaria;
import repositorio.IcrearCuantaBancaria;

public class CrearCuentaBancariaUseCase {
	
	private IcrearCuantaBancaria cuentaBancariaNuevaBancaria;

	public CrearCuentaBancariaUseCase(IcrearCuantaBancaria cuentaBancariaNuevaBancaria) {
		this.cuentaBancariaNuevaBancaria = cuentaBancariaNuevaBancaria;
	}
	
	public boolean crearCuentaBancaria(CuentaBancaria pCuentaBancaria) throws CuentaBancariaExisteExcepcion {
		
		if(existeCuentaBancaria(pCuentaBancaria)) {
			throw new CuentaBancariaExisteExcepcion();	
		}else {
			return this.cuentaBancariaNuevaBancaria.guardarCuenta(pCuentaBancaria);
		}
				
	}
	public boolean existeCuentaBancaria(CuentaBancaria aCuentaBancaria) {
		return this.cuentaBancariaNuevaBancaria.verificarNumeroCuentaBancaria(aCuentaBancaria.getNumeroCuentaInteger())!=null;
	}
	
	

}
