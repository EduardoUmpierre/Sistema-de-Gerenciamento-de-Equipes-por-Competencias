package trabalho2;

import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
	
	static Scanner scanner = new Scanner(System.in);
	
	private Vetor<Funcionario> funcionarios = new Vetor<>();

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
			funcionarios.append(arquivo.readObject());
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
		
		String escolhaMenu = scanner.nextLine();
		
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
		System.out.println("2 - Listar funcionarios");
		System.out.println("3 - Excluir funcionario");
		
		System.out.println("4 - Voltar");
		
		System.out.println();
		System.out.print("Digite o numero de menu desejado: ");
		
		String escolhaMenu = scanner.nextLine();
		
		switch (escolhaMenu) {
			case "1":
					adicionarFuncionario();
				break;
			case "2":
					System.out.println();
					System.out.println("===== ** =====");
					System.out.println();
				
					listarFuncionarios();
					
					menuFuncionarios();
				break;
			case "3":
					menuExcluirFuncionario();
				break;
			case "4":
					menuPrincipal();
				break;
			default:
					System.out.println();
					System.out.println("Escolha invalida.");
					menuFuncionarios();
				break;
		}
	}
	
	private void menuExcluirFuncionario() {
		System.out.println();
		System.out.println("===== ** =====");
		System.out.println();
		System.out.println("Escolha um funcionario para ser excluido:");
		System.out.println("0 - Voltar");
		
		System.out.println();
		
		listarFuncionarios(1);
		
		System.out.println();
		
		System.out.print("Digite o numero do funcionario desejado:");
		
		String escolhaMenu = scanner.nextLine();
		
		switch (escolhaMenu) {
			case "0":
					menuFuncionarios();
				break;
			default:
					excluirFuncionario(Integer.parseInt(escolhaMenu) - 1);
				break;
		}
	}
	
	private void excluirFuncionario(int index) {
		System.out.println();
		System.out.println("===== ** =====");
		System.out.println();
		
		try {
			funcionarios.remove(index);
			
			System.out.println("Funcionario removido com sucesso!");
		} catch(Exception e) {			
			System.out.println("Erro ao remover o funcionario. Tente novamente.");
			System.out.println("Erro: " + e);
		}
		
		menuFuncionarios();
	}
	
	private void listarFuncionarios(int index) {				
		for (int i = 0; i < funcionarios.size(); i++) {
			funcionarios.get(i).mostrarInformacoes(i, index);
		}
	}
	
	private void listarFuncionarios() {
		listarFuncionarios(1);
	}
	
	private void adicionarFuncionario() {
		System.out.println();
		System.out.println("===== ** =====");
		System.out.println();
		System.out.println("Adicionar funcionario:");	
		
		System.out.println();
		
		System.out.print("Nome: ");
		String nome = scanner.nextLine();

		System.out.print("Salario: ");
		String salario = scanner.nextLine();
		
		System.out.print("Numero de competencias: ");
		int num_competencias = Integer.parseInt(scanner.nextLine());
		
		String[] competencias = new String[num_competencias];
		
		int index = 0;
		while(index < num_competencias) {
			System.out.print("Competencia " + (index + 1) + ": ");
			competencias[index] = scanner.nextLine();
			
			index++;
		}
		
		funcionarios.append(new Funcionario(nome, salario, num_competencias, competencias));
		
		System.out.println();
		System.out.println("Funcionario " + nome + " adicionado com sucesso!");
		
		// Volta para o menu de funcionarios
		menuFuncionarios();
	}
}
