package interactorUniteTest;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

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

import interactor.ExtraerDineroUseCase;
import mockito.MockitoExtension;
import modelo.Cliente;
import modelo.CuentaBancaria;
import repositorio.INuemeroCuentaBancaria;
import repositorio.IcrearCuantaBancaria;
import repositorio.IextraccionDinero;
import repositorio.IfechaSistema;
import repositorio.IsaldoCuentaBancaria;
@ExtendWith(MockitoExtension.class)

class ExtraccionDineroUnitTest {
	@Mock
	IfechaSistema fechaSistema;
	
	@Mock
	IcrearCuantaBancaria cuentaNuevaBancaria;
	@Mock 
	INuemeroCuentaBancaria numeroCuentaBancaria;
	@Mock
	IextraccionDinero extracionDinero;
	
	@Test
	public void ExtraccionDinero_NumeroCuentaCorrecto_SaldoPositivo_ExtraccionCorrecta() throws ClienteIncompletoException,ClienteMenorEdadExeption,CuentaSaldoNegativoExcepcion,CuentaIncompletaexcepcion,NumeroCuentaErroneoExcepcion  {
		//creo el Cliente
		Mockito.when(fechaSistema.getDate()).thenReturn(LocalDate.of(2019, 07, 19));
		Cliente  propietario = Cliente.factoryCliente(fechaSistema,01,"Juan","32.456.567","Chilecito","15415467",LocalDate.of(2000,03,03));
		//creo La cuenta
		Mockito.when(numeroCuentaBancaria.getNumeroCuentaBancariaNuevo()).thenReturn(1);
		
		CuentaBancaria nuevaCuentaBancaria = CuentaBancaria.factoryCuenta(numeroCuentaBancaria, propietario, fechaSistema, 50.00);
		Mockito.when(cuentaNuevaBancaria.verificarNumeroCuentaBancaria(1)).thenReturn(nuevaCuentaBancaria);
		//Extracion dinero
		Mockito.when(extracionDinero.getExtraccionDinero()).thenReturn(20.00);
		Mockito.when(extracionDinero.guardarExtraccionDinero(nuevaCuentaBancaria)).thenReturn(true);		
		System.out.println("Saldo de la cuenta: "+nuevaCuentaBancaria.getSaldo());
		ExtraerDineroUseCase extraccionUseCase = new ExtraerDineroUseCase(extracionDinero,cuentaNuevaBancaria);
		boolean result = extraccionUseCase.RealizarExtracionDinero(nuevaCuentaBancaria,extracionDinero);
		assertTrue(result);
		System.out.println(" Extracion: "+extracionDinero.getExtraccionDinero()+" Saldo de la Cuenta: "+nuevaCuentaBancaria.getSaldo());
	//	System.out.println(extraccionUseCase.RealizarExtracionDinero(nuevaCuentaBancaria, extracionDinero));
		
	}
	
	@Test
	public void ExtraccionDinero_NumeroCuentaIncorrecto_ExtraccionCancelada() throws ClienteIncompletoException,ClienteMenorEdadExeption,CuentaSaldoNegativoExcepcion,CuentaIncompletaexcepcion,NumeroCuentaErroneoExcepcion  {
		//creo el Cliente
		Mockito.when(fechaSistema.getDate()).thenReturn(LocalDate.of(2019, 07, 19));
		Cliente  propietario = Cliente.factoryCliente(fechaSistema,01,"Juan","32.456.567","Chilecito","15415467",LocalDate.of(2000,03,03));
		//creo La cuenta
		Mockito.when(numeroCuentaBancaria.getNumeroCuentaBancariaNuevo()).thenReturn(1);
		
		
		CuentaBancaria nuevaCuentaBancaria = CuentaBancaria.factoryCuenta(numeroCuentaBancaria, propietario, fechaSistema, 50.00);
		Mockito.when(cuentaNuevaBancaria.verificarNumeroCuentaBancaria(2)).thenReturn(nuevaCuentaBancaria);
		//Extracion dinero
		Mockito.when(extracionDinero.getExtraccionDinero()).thenReturn(20.00);
		Mockito.when(extracionDinero.guardarExtraccionDinero(nuevaCuentaBancaria)).thenReturn(true);		
		//System.out.println(nuevaCuentaBancaria);
		ExtraerDineroUseCase extraccionUseCase = new ExtraerDineroUseCase(extracionDinero,cuentaNuevaBancaria);
		Assertions.assertThrows(NumeroCuentaErroneoExcepcion.class, () -> extraccionUseCase.RealizarExtracionDinero(nuevaCuentaBancaria, extracionDinero));
		System.out.println("No se realizo la Extraccion Numero cuenta Erroneo; Numero Correcto: "+nuevaCuentaBancaria.getNumeroCuentaInteger());
	}
	
	@Test
	public void ExtraccionDinero_NumeroCuentaCorrecta_ExtraccionSuperaElsaldo() throws ClienteIncompletoException,ClienteMenorEdadExeption,CuentaSaldoNegativoExcepcion,CuentaIncompletaexcepcion,NumeroCuentaErroneoExcepcion  {
		//creo el Cliente
		Mockito.when(fechaSistema.getDate()).thenReturn(LocalDate.of(2019, 07, 19));
		Cliente  propietario = Cliente.factoryCliente(fechaSistema,01,"Juan","32.456.567","Chilecito","15415467",LocalDate.of(2000,03,03));
		//creo La cuenta
		Mockito.when(numeroCuentaBancaria.getNumeroCuentaBancariaNuevo()).thenReturn(1);
		
		
		CuentaBancaria nuevaCuentaBancaria = CuentaBancaria.factoryCuenta(numeroCuentaBancaria, propietario, fechaSistema, 50.00);
		Mockito.when(cuentaNuevaBancaria.verificarNumeroCuentaBancaria(1)).thenReturn(nuevaCuentaBancaria);
		//Extracion dinero
		Mockito.when(extracionDinero.getExtraccionDinero()).thenReturn(60.00);
		Mockito.when(extracionDinero.guardarExtraccionDinero(nuevaCuentaBancaria)).thenReturn(true);		
		//System.out.println(nuevaCuentaBancaria);
		ExtraerDineroUseCase extraccionUseCase = new ExtraerDineroUseCase(extracionDinero,cuentaNuevaBancaria);
		Assertions.assertThrows(CuentaSaldoNegativoExcepcion.class, () -> extraccionUseCase.RealizarExtracionDinero(nuevaCuentaBancaria, extracionDinero));
		System.out.println("No se realizo la Extraccion Saldo Insuficiente; Saldo de La cuenta: "+nuevaCuentaBancaria.getSaldo());
	}
}
