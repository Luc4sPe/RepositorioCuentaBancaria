package interactorUniteTest;

import static org.junit.jupiter.api.Assertions.assertTrue;


import java.time.LocalDate;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;

import excepciones.ClienteExisteException;
import excepciones.ClienteIncompletoException;
import excepciones.ClienteMenorEdadExeption;

import interactor.CrearClienteUseCase;

import mockito.MockitoExtension;
import modelo.Cliente;

import repositorio.IcrearCliente;
import repositorio.IfechaSistema;
@ExtendWith(MockitoExtension.class)
public class crearClienteUnitTest {
	
	@Mock
	IfechaSistema fechaSistema;
	@Mock
	IcrearCliente crearNuevoCliente;

	@Test
	public void CrearCliente_clienteNoExiste_GuardadoCorrectamente() throws ClienteIncompletoException,ClienteMenorEdadExeption,ClienteExisteException  {
		
		Mockito.when(fechaSistema.getDate()).thenReturn(LocalDate.of(2019, 06, 19));
		Cliente nuevoCliente = Cliente.factoryCliente(fechaSistema,01,"Juan","32.456.567","Chilecito","15415467",LocalDate.of(2001,03,03));
		Mockito.when(crearNuevoCliente.guardar(nuevoCliente)).thenReturn(true);
		CrearClienteUseCase crearClienteUseCase = new CrearClienteUseCase(crearNuevoCliente);
		boolean result = crearClienteUseCase.crearCliente(nuevoCliente);
		assertTrue(result);	
		System.out.println(nuevoCliente);
	}
	
	@Test 
	public void CraarCliente_ClienteExiste_NoSeGuardaCorrectaMente_ClienteExisteException() throws ClienteExisteException, ClienteIncompletoException, ClienteMenorEdadExeption {
		
		Mockito.when(fechaSistema.getDate()).thenReturn(LocalDate.of(2019, 06, 19));
		Cliente nuevoCliente = Cliente.factoryCliente(fechaSistema,01,"Juan","32.456.567","Chilecito","15415467",LocalDate.of(2000,03,03));
		Cliente nuevo2Cliente= Cliente.factoryCliente(fechaSistema,02,"Lucas","32.456.567","Anguinan","15411235",LocalDate.of(1999,05,20));
		Mockito.when(crearNuevoCliente.verificacionDNI("32.456.567")).thenReturn(nuevo2Cliente);
		CrearClienteUseCase craerClienteUseCase = new CrearClienteUseCase(crearNuevoCliente);
		Assertions.assertThrows(ClienteExisteException.class, ()-> craerClienteUseCase.crearCliente(nuevoCliente));
		
		
		
		
	}
	
	

}
