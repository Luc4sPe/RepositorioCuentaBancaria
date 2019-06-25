package interactor;

import excepciones.CuentaSaldoNegativoExcepcion;
import excepciones.NumeroCuentaErroneoExcepcion;
import modelo.CuentaBancaria;
import repositorio.IcrearCuantaBancaria;
import repositorio.IextraccionDinero;

public class ExtraerDineroUseCase {
	IextraccionDinero extracionDinero;
	IcrearCuantaBancaria cuentaBancaria;
	public ExtraerDineroUseCase(IextraccionDinero extracionDinero, IcrearCuantaBancaria cuentaBancaria) {
		this.extracionDinero = extracionDinero;
		this.cuentaBancaria = cuentaBancaria;
	}
	
	public boolean RealizarExtracionDinero(CuentaBancaria cuenta,IextraccionDinero dinero) throws NumeroCuentaErroneoExcepcion,CuentaSaldoNegativoExcepcion {
	
		if(NumeroCuentaExiste(cuenta)) {
			 if(dinero.getExtraccionDinero()<= cuenta.getSaldo()) { 
					Double result =cuenta.getSaldo()-dinero.getExtraccionDinero();
					cuenta.setSaldo(result);
					return this.extracionDinero.guardarExtraccionDinero(cuenta);
			}else {
				//System.out.println("El monto a sacar supera el saldo");
				throw new CuentaSaldoNegativoExcepcion();
			}
		}else {
			//System.out.println("el numero de cuenta no es el correcto");
			
			throw new NumeroCuentaErroneoExcepcion();
			
		}
		/*if((NumeroCuentaExiste(cuenta)) && (dinero.getExtraccionDinero()<= cuenta.getSaldo())) {
		Double result =cuenta.getSaldo()-dinero.getExtraccionDinero();
		cuenta.setSaldo(result);
		return this.extracionDinero.guardarExtraccionDinero(cuenta);
	}else {
		System.out.println("el numero de cuenta no es el correcto, o el monto a sacar supera al saldo");
		
		throw new NumeroCuentaErroneoExcepcion();
		
	}*/
		
		
	}
	
	public boolean NumeroCuentaExiste(CuentaBancaria cuenta) {		
		return this.cuentaBancaria.verificarNumeroCuentaBancaria(cuenta.getNumeroCuentaInteger())!=null;	
	}
	
	
	

}
