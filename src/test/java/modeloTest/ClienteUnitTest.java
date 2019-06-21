package modeloTest;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.BeforeAll;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;

import excepciones.ClienteIncompletoException;
import excepciones.ClienteMenorEdadExeption;

import mockito.MockitoExtension;
import modelo.Cliente;
import repositorio.IfechaSistema;

@ExtendWith(MockitoExtension.class)
class ClienteUnitTest {
	
	 Cliente unCliente;
	
	@Mock
	  IfechaSistema fechaSistema;
	
	/*@BeforeAll
	public static void BeforeAll() throws ClienteIncompletoException, ClienteMenorEdadExeption {
		
		Mockito.when(fechaSistema.getDate()).thenReturn(LocalDate.of(2019, 06, 19));
		unCliente = Cliente.factoryCliente(fechaSistema,01,"Juan","32.456.567","Chilecito","15415467",LocalDate.of(2001,03,03)); 
	} */
	
	@Test
	public void clienteInstanciaCorrectaTodosLosArtibutosCorrectos() throws ClienteIncompletoException,ClienteMenorEdadExeption  {
		Mockito.when(fechaSistema.getDate()).thenReturn(LocalDate.of(2019, 06, 19));
		unCliente = Cliente.factoryCliente(fechaSistema,01,"Juan","32.456.567","Chilecito","15415467",LocalDate.of(2001,03,03)); 
		assertNotNull(unCliente);	
	}
	
@Test
	
	public void clienteInstanciaIncorrectaFaltaAlgunAtributo() throws ClienteIncompletoException,ClienteMenorEdadExeption  {
		Assertions.assertThrows(ClienteIncompletoException.class, ()-> Cliente.factoryCliente(null,null, "Juan", "32.456.567", null,"15415467", null));	 
	}
	
	 
	 @Test 
	 public void ClienteInstanciaEdadIncorrecta()throws ClienteIncompletoException,ClienteMenorEdadExeption{
		 Mockito.when(fechaSistema.getDate()).thenReturn(LocalDate.of(2019, 06, 19)); 
		 Assertions.assertThrows(ClienteMenorEdadExeption.class, ()-> Cliente.factoryCliente(fechaSistema,01, "Juan", "32.456.567", "Chilecito","15415467", LocalDate.of(20010,03,03)));
	 }
}
