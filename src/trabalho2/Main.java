package trabalho2;

import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
	
	static Scanner scanner = new Scanner(System.in);

	public static void main(String[] args) {
		try {
			(new Main()).run();
		} catch (FileNotFoundException e) {
			System.err.println("Nao encontrei o arquivo.");
			System.err.println(e.getMessage());
		}
	}

	private void run() throws FileNotFoundException {
		leituraDeFuncionarios();
		leituraDeProjetos();
		
		menuPrincipal();
	}
	
	private void leituraDeFuncionarios() throws FileNotFoundException {
		CSVReader<Funcionario> arquivo = new CSVReader<>("funcionarios.csv", new FuncionarioParser());
		
		arquivo.skipLine();
		
		while (arquivo.hasNext()) {
			arquivo.readObject();
		}
		
		arquivo.close();
	}
	
	private void leituraDeProjetos() throws FileNotFoundException {
		CSVReader<Projeto> arquivo = new CSVReader<>("projetos.csv", new ProjetoParser());
		
		arquivo.skipLine();

		while (arquivo.hasNext()) {
			arquivo.readObject();
		}
		
		arquivo.close();
	}
	
	private void menuPrincipal() {
		System.out.println();
		System.out.println("===== ** =====");
		System.out.println();
		System.out.println("Menu principal:");
		System.out.println("1 - Funcionarios");
		System.out.println("2 - Projetos");
		System.out.println("3 - Competencias");
		
		System.out.println();
		System.out.print("Digite o numero de menu desejado: ");
		
		String escolhaMenu = scanner.next();
		
		switch (escolhaMenu) {
			case "1":
					menuFuncionarios();
				break;
			case "2":
					//menuProjetos();
				break;
			case "3":
					//menuCompetencias();
				break;
			default:
					System.out.println();
					System.out.println("Escolha invalida.");
					menuPrincipal();
				break;
		}
	}
	
	private void menuFuncionarios() {
		System.out.println();
		System.out.println("===== ** =====");
		System.out.println();
		System.out.println("Menu de funcionarios:");
		System.out.println("1 - Cadastrar novo funcionario");
		System.out.println("2 - Excluir funcionario");
		
		System.out.println("3 - Voltar");
		
		System.out.println();
		System.out.print("Digite o numero de menu desejado: ");
		
		String escolhaMenu = scanner.next();
		
		switch (escolhaMenu) {
			case "1":
					adicionarFuncionario();
				break;
			case "2":
					//excluirFuncionario();
				break;
			case "3":
					menuPrincipal();
				break;
			default:
					System.out.println();
					System.out.println("Escolha invalida.");
					menuFuncionarios();
				break;
		}
	}
	
	private void adicionarFuncionario() {
		System.out.println();
		System.out.println("===== ** =====");
		System.out.println();
		System.out.println("Adicionar funcionario:");		
		System.out.println("0 - Voltar");
		
		System.out.println();
		
		// Nome
		System.out.print("Nome: ");
		String nome = scanner.nextLine();

		// Salario
		System.out.print("Salario: ");
		String salario = scanner.nextLine();
		
		// Numero de competencias
		System.out.print("Numero de competencias: ");
		int num_competencias = scanner.nextInt();
		
		// Competencias
		String[] competencias = new String[num_competencias];
		
		int index = 0;
		while(index < num_competencias) {
			System.out.print("Competencia " + (index + 1) + ": ");
			competencias[index] = scanner.nextLine();
			
			index++;
		}
		
		Funcionario funcionario = new Funcionario(nome, salario, num_competencias, competencias);
		
		funcionario.toString();
	}
}
