package trabalho2;

import java.util.Scanner;

public class FuncionarioParser implements Parser<Funcionario> {
	public Funcionario parse(String dados) {
		Scanner arquivo = new Scanner(dados);
		arquivo.useDelimiter(";");
		
		String nome = arquivo.next();
		String salario = arquivo.next();
		int num_competencias = arquivo.nextInt();
		
		Competencia[] competencias = new Competencia[num_competencias];
		int index = 0;
		
		while (arquivo.hasNext()) {
			 competencias[index] = new Competencia(arquivo.next());			 
			 index++;
		}
		
		Funcionario funcionario = new Funcionario(nome, salario, num_competencias, competencias);
				
		arquivo.close();

		return funcionario;
	}
}