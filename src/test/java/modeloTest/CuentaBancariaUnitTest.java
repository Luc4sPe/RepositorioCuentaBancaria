package modeloTest;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import excepciones.CuentaIncompletaexcepcion;
import modelo.CuentaBancaria;

class CuentaBancariaUnitTest {

	static CuentaBancaria unaCuenta;
	@BeforeAll
	public static void BeforeAll() throws CuentaIncompletaexcepcion{
		
		unaCuenta = CuentaBancaria.factoryCuenta(1,"Juan Zanches",LocalDate.of(2019, 05, 25));
	}
	
	@Test
	public void CuentaBancariaInstanciaCorrectaTodosLosAtributosOk() throws CuentaIncompletaexcepcion {
		assertNotNull(unaCuenta);
	}
	@Test
	public void CuentaBancariaInstanciaIncorrectaFaltaAlgunAtrubuto() {
		Assertions.assertThrows(CuentaIncompletaexcepcion.class, ()-> CuentaBancaria.factoryCuenta(null,"Juan Zanches",null));
	}
}
