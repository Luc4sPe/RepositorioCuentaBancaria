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
import excepciones.CuentaBancariaExisteExcepcion;
import excepciones.CuentaIncompletaexcepcion;
import excepciones.CuentaSaldoNegativoExcepcion;

import interactor.CrearCuentaBancariaUseCase;
import mockito.MockitoExtension;
import modelo.Cliente;
import modelo.CuentaBancaria;
import repositorio.INuemeroCuentaBancaria;
import repositorio.IcrearCuantaBancaria;
import repositorio.IfechaSistema;
import repositorio.IsaldoCuentaBancaria;
@ExtendWith(MockitoExtension.class)
class crearCuantaBancariaUnitTest {
	
	@Mock
	IfechaSistema fechaSistema;
	
	@Mock
	IcrearCuantaBancaria cuentaNuevaBancaria;
	@Mock 
	INuemeroCuentaBancaria numeroCuentaBancaria;
	
	@Test
	public void CrearCuentaBancaria_CuentaBancariaNoExiste_GuardarCorrectamente() throws ClienteIncompletoException,ClienteMenorEdadExeption,CuentaIncompletaexcepcion,CuentaSaldoNegativoExcepcion,CuentaBancariaExisteExcepcion {
		Mockito.when(fechaSistema.getDate()).thenReturn(LocalDate.of(2019, 06, 19));
		Mockito.when(numeroCuentaBancaria.getNumeroCuentaBancariaNuevo()).thenReturn(1);
		Cliente  propietario = Cliente.factoryCliente(fechaSistema,01,"Juan","32.456.567","Chilecito","15415467",LocalDate.of(2001,03,03));
		
		CuentaBancaria nuevaCuentaBancaria = CuentaBancaria.factoryCuenta(numeroCuentaBancaria, propietario, fechaSistema, 30.00);
		Mockito.when(cuentaNuevaBancaria.guardarCuenta(nuevaCuentaBancaria)).thenReturn(true);
		CrearCuentaBancariaUseCase crearCuentaUseCase = new CrearCuentaBancariaUseCase(cuentaNuevaBancaria);
		boolean result = crearCuentaUseCase.crearCuentaBancaria(nuevaCuentaBancaria);
		assertTrue(result);
		System.out.println(nuevaCuentaBancaria);	
	}
	
	@Test
	public void CrearCuentaBancaria_CuentaBancariaExiste_NoSeGuardaCorrectamenta()throws ClienteIncompletoException,ClienteMenorEdadExeption,CuentaIncompletaexcepcion,CuentaSaldoNegativoExcepcion,CuentaBancariaExisteExcepcion {
		Mockito.when(fechaSistema.getDate()).thenReturn(LocalDate.of(2019, 06, 19));
		Mockito.when(numeroCuentaBancaria.getNumeroCuentaBancariaNuevo()).thenReturn(1);
		Cliente  propietario = Cliente.factoryCliente(fechaSistema,01,"Juan","32.456.567","Chilecito","15415467",LocalDate.of(2001,03,03));
		
		CuentaBancaria nuevaCuentaBancaria = CuentaBancaria.factoryCuenta(numeroCuentaBancaria, propietario, fechaSistema, 30.00);
		
		Cliente  propietario2 = Cliente.factoryCliente(fechaSistema,02,"Pepe","31.456.560","Chilecito","15315460",LocalDate.of(2000,04,03));
		CuentaBancaria nuevaCuentaBancaria2 =CuentaBancaria.factoryCuenta(numeroCuentaBancaria, propietario2, fechaSistema, 30.00);
		Mockito.when(cuentaNuevaBancaria.verificarNumeroCuentaBancaria(1)).thenReturn(nuevaCuentaBancaria2);
		CrearCuentaBancariaUseCase crearCuentaUseCase = new CrearCuentaBancariaUseCase(cuentaNuevaBancaria);
		Assertions.assertThrows(CuentaBancariaExisteExcepcion.class, () -> crearCuentaUseCase.crearCuentaBancaria(nuevaCuentaBancaria) );
		System.out.println("No se creo la Cuenta Por que ya Existe");
	}
	

}
