package interactor;

import excepciones.ClienteExisteException;

import modelo.Cliente;
import repositorio.IcrearCliente;

public class CrearClienteUseCase {
	
	private IcrearCliente crearNuevosclientes;

	public CrearClienteUseCase(IcrearCliente crearNuevosclientes) {
		
		this.crearNuevosclientes = crearNuevosclientes;
	}
	
	public boolean crearCliente(Cliente nCliente) throws ClienteExisteException {
		if(existeCliente(nCliente)) {
			throw new ClienteExisteException();
			
		}
		else {
			return this.crearNuevosclientes.guardar(nCliente);
		}
	
	}
	
	public boolean existeCliente(Cliente pCliente) {
		return this.crearNuevosclientes.verificacionDNI(pCliente.getNumeroDocumento())!=null;
	}
	


}
