package trabalho2;

import java.util.Scanner;

public class CompetenciaParser implements Parser<Competencia> {
	public Competencia parse(String dados) {
		Scanner arquivo = new Scanner(dados);
		
		String nome = arquivo.next();
		
		Competencia competencia = new Competencia(nome);
				
		arquivo.close();

		return competencia;
	}
}