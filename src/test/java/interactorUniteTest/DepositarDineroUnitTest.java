package interactorUniteTest;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import javax.sound.midi.Soundbank;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;

import excepciones.ClienteIncompletoException;
import excepciones.ClienteMenorEdadExeption;
import excepciones.CuentaIncompletaexcepcion;
import excepciones.CuentaSaldoNegativoExcepcion;
import excepciones.NumeroCuentaErroneoExcepcion;
import interactor.DepositarDineroUseCase;
import mockito.MockitoExtension;
import modelo.Cliente;
import modelo.CuentaBancaria;
import repositorio.INuemeroCuentaBancaria;
import repositorio.IcrearCuantaBancaria;
import repositorio.IdepositarDineroCuentaBancaria;
import repositorio.IfechaSistema;
import repositorio.IsaldoCuentaBancaria;
@ExtendWith(MockitoExtension.class)
class DepositarDineroUnitTest {

	
	@Mock
	IfechaSistema fechaSistema;
	@Mock
	IsaldoCuentaBancaria saldoCuenta;
	@Mock
	IcrearCuantaBancaria cuentaNuevaBancaria;
	@Mock 
	INuemeroCuentaBancaria numeroCuentaBancaria;
	@Mock
	IdepositarDineroCuentaBancaria deposito;
	
	@Test
	public void DepositarDinero_NumeroCuentaCorrecto_DepositoRealizado() throws ClienteIncompletoException,ClienteMenorEdadExeption,CuentaSaldoNegativoExcepcion,CuentaIncompletaexcepcion,NumeroCuentaErroneoExcepcion {
		Mockito.when(fechaSistema.getDate()).thenReturn(LocalDate.of(2019, 07, 19));
		Mockito.when(numeroCuentaBancaria.getNumeroCuentaBancariaNuevo()).thenReturn(1);
		Cliente  propietario = Cliente.factoryCliente(fechaSistema,01,"Juan","32.456.567","Chilecito","15415467",LocalDate.of(2001,03,03));
		Mockito.when(saldoCuenta.getSaldoCuenta()).thenReturn(10.00);
		Mockito.when(deposito.getDepositoDinero()).thenReturn(10.00);
		CuentaBancaria nuevaCuentaBancaria = CuentaBancaria.factoryCuenta(numeroCuentaBancaria, propietario, fechaSistema, saldoCuenta);
		Mockito.when(cuentaNuevaBancaria.verificarNumeroCuentaBancaria(1)).thenReturn(nuevaCuentaBancaria);
		System.out.println(nuevaCuentaBancaria);
		Mockito.when(deposito.guardarDeposito(nuevaCuentaBancaria)).thenReturn(true);
		DepositarDineroUseCase depositarUseCase = new DepositarDineroUseCase( deposito, cuentaNuevaBancaria);
		boolean result = depositarUseCase.realizarDeposito(nuevaCuentaBancaria,deposito);
		assertTrue(result);
		System.out.println(nuevaCuentaBancaria);
		System.out.println(depositarUseCase.realizarDeposito(nuevaCuentaBancaria,deposito));
		
	}
	
	@Test
	public void DepositarDinero_NumeroCuentaIncorrectoCorrecto_DepositoRechazado() throws ClienteIncompletoException,ClienteMenorEdadExeption,CuentaSaldoNegativoExcepcion,CuentaIncompletaexcepcion,NumeroCuentaErroneoExcepcion {
		Mockito.when(fechaSistema.getDate()).thenReturn(LocalDate.of(2019, 07, 19));
		Mockito.when(numeroCuentaBancaria.getNumeroCuentaBancariaNuevo()).thenReturn(2);
		Cliente  propietario = Cliente.factoryCliente(fechaSistema,01,"Juan","32.456.567","Chilecito","15415467",LocalDate.of(2001,03,03));
		Mockito.when(saldoCuenta.getSaldoCuenta()).thenReturn(10.00);
		Mockito.when(deposito.getDepositoDinero()).thenReturn(10.00);
		CuentaBancaria nuevaCuentaBancaria = CuentaBancaria.factoryCuenta(numeroCuentaBancaria, propietario, fechaSistema, saldoCuenta);
		Mockito.when(cuentaNuevaBancaria.verificarNumeroCuentaBancaria(1)).thenReturn(nuevaCuentaBancaria);
		Mockito.when(deposito.guardarDeposito(nuevaCuentaBancaria)).thenReturn(true);
		DepositarDineroUseCase depositarUseCase = new DepositarDineroUseCase( deposito, cuentaNuevaBancaria);
		Assertions.assertThrows(NumeroCuentaErroneoExcepcion.class, () -> depositarUseCase.realizarDeposito(nuevaCuentaBancaria, deposito));
	
		System.out.println("No se realizo el deposito numer erroneo"+ nuevaCuentaBancaria);
		
	}
}
