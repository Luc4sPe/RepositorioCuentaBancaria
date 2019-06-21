package repositorio;

import modelo.Cliente;


public interface IcrearCliente {
	boolean guardar(Cliente uncliente );
	Cliente verificacionDNI(String aNumeroDocumento);
	
}

