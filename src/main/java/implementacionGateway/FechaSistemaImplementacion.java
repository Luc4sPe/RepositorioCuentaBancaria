package implementacionGateway;

import java.time.LocalDate;

import repositorio.IfechaSistema;

public class FechaSistemaImplementacion implements IfechaSistema {
	
	public LocalDate getDate() {
		return LocalDate.now();
		
	}
}
