package modeloTest;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;

import excepciones.ClienteIncompletoException;
import excepciones.ClienteMenorEdadExeption;
import excepciones.CuentaIncompletaexcepcion;
import excepciones.CuentaSaldoNegativoExcepcion;
import mockito.MockitoExtension;
import modelo.Cliente;
import modelo.CuentaBancaria;
import repositorio.INuemeroCuentaBancaria;
import repositorio.IfechaSistema;
import repositorio.IsaldoCuentaBancaria;
@ExtendWith(MockitoExtension.class)
class CuentaBancariaUnitTest {

	
	@Mock
	IfechaSistema fechaSistema;
	@Mock
	INuemeroCuentaBancaria numeroCuentaBancaria;
	
	@Test
	public void CuentaBancariaInstanciaCorrectaTodosLosAtributosOk() throws CuentaIncompletaexcepcion, ClienteIncompletoException,ClienteMenorEdadExeption, CuentaSaldoNegativoExcepcion {
		
		Mockito.when(fechaSistema.getDate()).thenReturn(LocalDate.of(2019, 06, 19));
		Mockito.when(numeroCuentaBancaria.getNumeroCuentaBancariaNuevo()).thenReturn(1);
		Cliente  propietario = Cliente.factoryCliente(fechaSistema,01,"Juan","32.456.567","Chilecito","15415467",LocalDate.of(2001,03,03));
		
		CuentaBancaria nuevaCuentaBancaria = CuentaBancaria.factoryCuenta(numeroCuentaBancaria, propietario, fechaSistema, 30.00);
		assertNotNull(nuevaCuentaBancaria);
	}
	@Test
	public void CuentaBancariaInstanciaIncorrectaFaltaAlgunAtrubuto() throws ClienteIncompletoException, ClienteMenorEdadExeption,CuentaSaldoNegativoExcepcion {
		Mockito.when(fechaSistema.getDate()).thenReturn(LocalDate.of(2019, 06, 19));
		Mockito.when(numeroCuentaBancaria.getNumeroCuentaBancariaNuevo()).thenReturn(1);
		Cliente  propietario = Cliente.factoryCliente(fechaSistema,01,"Juan","32.456.567","Chilecito","15415467",LocalDate.of(2001,03,03));
		 
		Assertions.assertThrows(CuentaIncompletaexcepcion.class, ()-> CuentaBancaria.factoryCuenta(null,propietario,null,30.00));
	}
	
	@Test
	public void CuentaBancariaInstanciaIncorrectaSaldoNegativo() throws ClienteIncompletoException, ClienteMenorEdadExeption,CuentaSaldoNegativoExcepcion {
		Mockito.when(fechaSistema.getDate()).thenReturn(LocalDate.of(2019, 06, 19));
		Mockito.when(numeroCuentaBancaria.getNumeroCuentaBancariaNuevo()).thenReturn(1);
		Cliente  propietario = Cliente.factoryCliente(fechaSistema,01,"Juan","32.456.567","Chilecito","15415467",LocalDate.of(2001,03,03));
		Assertions.assertThrows(CuentaSaldoNegativoExcepcion.class, ()-> CuentaBancaria.factoryCuenta(numeroCuentaBancaria,propietario,fechaSistema,-30.00));
	}
}
