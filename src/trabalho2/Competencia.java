package trabalho2;

public class Competencia {
	private String competencia;
	
	public Competencia(String competencia) {
		setCompetencia(competencia);
	}

	public String getCompetencia() {
		return competencia;
	}

	public void setCompetencia(String competencia) {
		this.competencia = competencia;
	}
	
	public void mostrarInformacoes(int index) {		
		System.out.println(
			(index + 1) + 
			" - Competência: " +
			getCompetencia()
		);
	}
}
