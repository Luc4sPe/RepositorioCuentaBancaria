package interactor;


import excepciones.NumeroCuentaErroneoExcepcion;
import modelo.CuentaBancaria;
import repositorio.IcrearCuantaBancaria;
import repositorio.IdepositarDineroCuentaBancaria;

public class DepositarDineroUseCase {
	private IdepositarDineroCuentaBancaria deposito;
	private IcrearCuantaBancaria cuentaBancariaNuevaBancaria;

	
public DepositarDineroUseCase(IdepositarDineroCuentaBancaria deposito,
			IcrearCuantaBancaria cuentaBancariaNuevaBancaria) {
		
		this.deposito = deposito;
		this.cuentaBancariaNuevaBancaria = cuentaBancariaNuevaBancaria;
	}


public boolean realizarDeposito(CuentaBancaria nuevaBancaria ,IdepositarDineroCuentaBancaria dinero) throws NumeroCuentaErroneoExcepcion {
	Double result;
	
	if(existeNumeroCuentaBancaria(nuevaBancaria)) {
			result= nuevaBancaria.getSaldo() + dinero.getDepositoDinero();
		nuevaBancaria.setSaldo(result);
		 return this.deposito.guardarDeposito(nuevaBancaria);	
	}
	else{
	System.out.println("el numero de cuenta no es el correcto");
	throw new NumeroCuentaErroneoExcepcion();
	}
	

}	
	public boolean existeNumeroCuentaBancaria(CuentaBancaria aCuentaBancaria) {
		return this.cuentaBancariaNuevaBancaria.verificarNumeroCuentaBancaria(aCuentaBancaria.getNumeroCuentaInteger())!=null;
		
	}
	
	
		
		
	
	
}
