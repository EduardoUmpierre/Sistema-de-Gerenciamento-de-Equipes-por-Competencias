package trabalho2;

import java.util.Scanner;

public class ColaboradorParser implements Parser<Colaborador> {
	public Colaborador parse(String dados) {
		Scanner arquivo = new Scanner(dados);
		arquivo.useDelimiter(";");
		
		String nome_projeto = arquivo.next();
		String nome_funcionario = arquivo.next();
		String nome_competencia = arquivo.next();
		
		Colaborador colaborador = new Colaborador(nome_projeto, nome_funcionario, nome_competencia);
				
		arquivo.close();

		return colaborador;
	}
}