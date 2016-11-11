package trabalho2;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class ProjetoParser implements Parser<Projeto> {
	public Projeto parse(String dados) {
		Scanner arquivo = new Scanner(dados);
		arquivo.useDelimiter(";");
		
		String nome = arquivo.next();
		
		DateTimeFormatter formato = DateTimeFormatter.ofPattern("yyyy-MM-dd"); 
		
		LocalDate data_inicio = LocalDate.parse(arquivo.next(), formato);
		LocalDate data_fim = LocalDate.parse(arquivo.next(), formato);
		int num_competencias = arquivo.nextInt();
		
		Competencia[] competencias = new Competencia[num_competencias];
		int index = 0;
		
		while (arquivo.hasNext()) {
			 competencias[index] = new Competencia(arquivo.next());			 
			 index++;
		}
		
		Projeto projeto = new Projeto(nome, data_inicio, data_fim, num_competencias, competencias);
				
		arquivo.close();

		return projeto;
	}
}