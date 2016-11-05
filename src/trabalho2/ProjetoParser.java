package trabalho2;

import java.util.Scanner;

public class ProjetoParser implements Parser<Projeto> {
	public Projeto parse(String dados) {
		Scanner arquivo = new Scanner(dados);
		arquivo.useDelimiter(";");
		
		String nome = arquivo.next();
		String data_inicio = arquivo.next();
		String data_fim = arquivo.next();
		int num_competencias = arquivo.nextInt();
		
		String[] competencias = new String[num_competencias];
		int index = 0;
		
		while (arquivo.hasNext()) {
			 competencias[index] = arquivo.next();			 
			 index++;
		}
		
		Projeto projeto = new Projeto(nome, data_inicio, data_fim, num_competencias, competencias);
				
		arquivo.close();

		return projeto;
	}
}