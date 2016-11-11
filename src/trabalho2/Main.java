package trabalho2;

import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Main {
	
	static Scanner scanner = new Scanner(System.in);
	
	private Vetor<Projeto> projetos = new Vetor<>();
	private Vetor<Funcionario> funcionarios = new Vetor<>();
	private Vetor<Competencia> competencias = new Vetor<>();
	private Vetor<Colaborador> colaboradores = new Vetor<>();

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
		leituraDeCompetencias();
		leituraDeColaboradores();
		
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
			projetos.append(arquivo.readObject());
		}
		
		arquivo.close();
	}
	
	private void leituraDeCompetencias() throws FileNotFoundException {
		CSVReader<Competencia> arquivo = new CSVReader<>("competencias.csv", new CompetenciaParser());
		
		arquivo.skipLine();
		
		while (arquivo.hasNext()) {
			competencias.append(arquivo.readObject());
		}		
		
		arquivo.close();
	}
	
	private void leituraDeColaboradores() throws FileNotFoundException {
		CSVReader<Colaborador> arquivo = new CSVReader<>("colaboradores.csv", new ColaboradorParser());
		
		arquivo.skipLine();
		
		while (arquivo.hasNext()) {
			colaboradores.append(arquivo.readObject());
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
					menuProjetos();
				break;
			case "3":
					menuCompetencias();
				break;
			default:
					System.out.println();
					System.out.println("Escolha invalida.");
					menuPrincipal();
				break;
		}
	}
	
	// Projetos
	
	private void menuProjetos() {
		System.out.println();
		System.out.println("===== ** =====");
		System.out.println();
		System.out.println("Menu de projetos:");
		System.out.println("1 - Cadastrar novo projeto");
		System.out.println("2 - Listar projetos");
		System.out.println("3 - Listar projetos ativos");
		System.out.println("4 - Listar projetos com pendencias");
		System.out.println("5 - Associar funcionario a um projeto");
		System.out.println("6 - Excluir projeto");
		
		System.out.println("7 - Voltar");
		
		System.out.println();
		System.out.print("Digite o numero de menu desejado: ");
		
		String escolhaMenu = scanner.nextLine();
		
		switch (escolhaMenu) {
			case "1":
					adicionarProjeto();
				break;
			case "2":
					System.out.println();
					System.out.println("===== ** =====");
					System.out.println();
				
					listarProjetos();
					
					menuProjetos();
			case "3":
					System.out.println();
					System.out.println("===== ** =====");
					System.out.println();
				
					listarProjetosAtivos();
					
					menuProjetos();
				break;
			case "4":
					System.out.println();
					System.out.println("===== ** =====");
					System.out.println();
				
					listarProjetosComPendencias();
					
					menuProjetos();
			break;
			case "5":
					menuAssociarFuncionario();
				break;
			case "6":
					menuExcluirProjeto();
				break;
			case "7":
					menuPrincipal();
				break;
			default:
					System.out.println();
					System.out.println("Escolha invalida.");
					menuProjetos();
				break;
		}
	}
	
	private void listarProjetosAtivos() {
		for (int i = 0; i < projetos.size(); i++) {
			Projeto projeto = projetos.get(i);
			
			if(projeto.getData_fim().isAfter(LocalDate.now())) {
				projeto.mostrarInformacoes(i);
			}
		}
	}
	
	private void listarProjetos() {
		for (int i = 0; i < projetos.size(); i++) {
			projetos.get(i).mostrarInformacoes(i);
		}
	}
	
	private void listarProjetosComPendencias() {
		boolean pendencia = false;
		
		for (int i = 0; i < projetos.size(); i++) {
			Vetor<String> pendencias = new Vetor<>();

			Projeto projeto = projetos.get(i);
				
			for (int y = 0; y < colaboradores.size(); y++) {	
				Colaborador colaborador = colaboradores.get(y);
				
				for	(int x = 0; x < competencias.size(); x++) {
					if (
						projeto.getNome().equals(colaborador.getNome_projeto()) && 
						competencias.get(x).getCompetencia().equals(colaborador.getNome_competencia())
					) {						
						pendencia = true;
					}
				}
				
				if(pendencia)
					pendencias.append(colaborador.getNome_competencia());
			}
			
			if(pendencias.size() > 0) {
				projeto.mostrarInformacoesPendencias(i, pendencias);
			}
		}
	}
	
	private void menuAssociarFuncionario() {
		System.out.println();
		System.out.println("===== ** =====");
		System.out.println();
		System.out.println("Escolha um projeto:");
		System.out.println("0 - Voltar");
		System.out.println();
		
		listarProjetosAtivos();
		
		System.out.println();		
		System.out.print("Digite o numero do projeto desejado: ");
		
		String escolhaMenu = scanner.nextLine();
		Projeto projeto = null;
		
		switch (escolhaMenu) {
			case "0":
					menuProjetos();
				break;
			default:
					projeto = projetos.get(Integer.parseInt(escolhaMenu) - 1);
				break;
		}
		
		System.out.println();
		System.out.println("===== ** =====");
		System.out.println();
		System.out.println("Escolha um funcionario para ser vinculado ao projeto " + projeto.getNome() + ": ");
		System.out.println("0 - Voltar");
		System.out.println();
		
		for (int i = 0; i < funcionarios.size(); i++) {
			Funcionario funcionario = funcionarios.get(i);
			
			if(
					projeto.comparaCompetencia(funcionario) &&
					projeto.naoContemFuncionario(funcionario, colaboradores) &&
					funcionario.numeroDeProjetos(colaboradores) <= 2
				) {
				System.out.println(
					(i + 1) +
					" - " +
					funcionarios.get(i).getNome()
				);
			}
		}
		
		System.out.println();		
		System.out.print("Digite o numero do funcionario desejado: ");
		
		escolhaMenu = scanner.nextLine();
		
		if(Integer.parseInt(escolhaMenu) == 0) {
			menuProjetos();
		} else {
			System.out.println("===== ** =====");
			System.out.println();	
			System.out.println("Escolha a competencia a ser vinculada: ");		
			System.out.println("0 - Voltar");
			System.out.println();
			
			Funcionario funcionario = funcionarios.get(Integer.parseInt(escolhaMenu) - 1);
			
			for (int i = 0; i < projeto.getNum_competencias(); i++) {
				for (int x = 0; x < funcionario.getNum_competencias(); x++) {
					if(projeto.getCompetencias()[i].getCompetencia().equals(funcionario.getCompetencias()[x].getCompetencia())) {
						System.out.println(
							(i + 1) +
							" - " +
							projeto.getCompetencias()[i].getCompetencia()
						);
					}
				}
			}
			
			System.out.println();		
			System.out.print("Digite o numero do funcionario desejado: ");
			
			escolhaMenu = scanner.nextLine();
			
			switch (escolhaMenu) {
				case "0":
						menuProjetos();
					break;
				default:
						colaboradores.append(new Colaborador(projeto.getNome(), funcionario.getNome(), projeto.getCompetencias()[Integer.parseInt(escolhaMenu) - 1].getCompetencia())); 
					break;
			}
			
			listarColaboradores();
			
			System.out.println();
			System.out.println("Funcionario " + funcionario.getNome() + " vinculado ao projeto " + projeto.getNome() + " com sucesso!");

			menuProjetos();
		}
	}
	
	private void menuExcluirProjeto() {
		System.out.println();
		System.out.println("===== ** =====");
		System.out.println();
		System.out.println("Escolha um projeto para ser excluido:");
		System.out.println("0 - Voltar");		
		System.out.println();
		
		listarProjetos();
		
		System.out.println();		
		System.out.print("Digite o numero do projeto desejado: ");
		
		String escolhaMenu = scanner.nextLine();
		
		switch (escolhaMenu) {
			case "0":
					menuProjetos();
				break;
			default:
					excluirProjeto(Integer.parseInt(escolhaMenu) - 1);
				break;
		}
	}
	
	private void excluirProjeto(int index) {
		System.out.println();
		System.out.println("===== ** =====");
		System.out.println();
		
		try {
			projetos.remove(index);
			
			System.out.println("Projeto removido com sucesso!");
		} catch(Exception e) {			
			System.out.println("Erro ao remover o projeto. Tente novamente.");
			System.out.println("Erro: " + e);
		}
		
		menuProjetos();
	}
	
	private void adicionarProjeto() {
		System.out.println();
		System.out.println("===== ** =====");
		System.out.println();
		System.out.println("Adicionar projeto:");	
		
		System.out.println();
		
		System.out.print("Nome: ");
		String nome = scanner.nextLine();

		DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy"); 
		
		System.out.print("Data de início (dd/mm/aaaa): ");		
		LocalDate data_inicio = LocalDate.parse(scanner.nextLine(), formato);
		data_inicio = LocalDate.parse(data_inicio.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))); 
		
		System.out.print("Data de fim (dd/mm/aaaa): ");
		LocalDate data_fim = LocalDate.parse(scanner.nextLine(), formato);
		data_fim = LocalDate.parse(data_fim.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))); 
		
		System.out.print("Numero de competencias: ");
		int num_competencias = Integer.parseInt(scanner.nextLine());
		
		System.out.println("Listagem de competencias:");
		
		listarCompetencias();
		
		Competencia[] proj_competencias = new Competencia[num_competencias];
		
		for(int i = 0; i < num_competencias; i++) {
			System.out.print("Competencia " + (i + 1) + ": ");
			
			proj_competencias[i] = new Competencia(competencias.get(Integer.parseInt(scanner.nextLine()) - 1).getCompetencia());
		}
		
		projetos.append(new Projeto(nome, data_inicio, data_fim, num_competencias, proj_competencias));
		
		System.out.println();
		System.out.println("Projeto " + nome + " adicionado com sucesso!");

		menuProjetos();
	}
	
	// Competencias
	// Competencias
	
	private void menuCompetencias() {
		System.out.println();
		System.out.println("===== ** =====");
		System.out.println();
		System.out.println("Menu de competencias:");
		System.out.println("1 - Cadastrar nova competencia");
		System.out.println("2 - Listar competencias");
		System.out.println("3 - Excluir competencia");
		
		System.out.println("4 - Voltar");
		
		System.out.println();
		System.out.print("Digite o numero de menu desejado: ");
		
		String escolhaMenu = scanner.nextLine();
		
		switch (escolhaMenu) {
			case "1":
					adicionarCompetencia();
				break;
			case "2":
					System.out.println();
					System.out.println("===== ** =====");
					System.out.println();
				
					listarCompetencias();
					
					menuCompetencias();
				break;
			case "3":
					menuExcluirCompetencia();
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
	
	private void listarCompetencias() {
		for (int i = 0; i < competencias.size(); i++) {
			competencias.get(i).mostrarInformacoes(i);
		}
	}
	
	private void adicionarCompetencia() {
		System.out.println();
		System.out.println("===== ** =====");
		System.out.println();
		System.out.println("Adicionar competencia:");	
		
		System.out.println();
		
		System.out.print("Nome: ");
		String nome = scanner.nextLine();
		
		competencias.append(new Competencia(nome));
		
		System.out.println();
		System.out.println("Competencia " + nome + " adicionada com sucesso!");

		menuCompetencias();
	}

	private void menuExcluirCompetencia() {
		System.out.println();
		System.out.println("===== ** =====");
		System.out.println();
		System.out.println("Escolha uma competencia para ser excluida:");
		System.out.println("0 - Voltar");		
		System.out.println();
		
		listarCompetencias();
		
		System.out.println();		
		System.out.print("Digite o numero da competencia desejada:");
		
		String escolhaMenu = scanner.nextLine();
		
		switch (escolhaMenu) {
			case "0":
					menuCompetencias();
				break;
			default:
					excluirCompetencia(Integer.parseInt(escolhaMenu) - 1);
				break;
		}
	}
	
	private void excluirCompetencia(int index) {
		System.out.println();
		System.out.println("===== ** =====");
		System.out.println();
		
		try {
			competencias.remove(index);
			
			System.out.println("Competencia removida com sucesso!");
		} catch(Exception e) {			
			System.out.println("Erro ao remover a competencia. Tente novamente.");
			System.out.println("Erro: " + e);
		}
		
		menuCompetencias();
	}
	
	
	// Colaboradores
	// Colaboradores
	
	private void listarColaboradores() {
		for (int i = 0; i < colaboradores.size(); i++) {
			colaboradores.get(i).mostrarInformacoes(i);
		}
	}
	
	
	// Funcionarios
	// Funcionarios
	
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
	
	private void listarFuncionarios() {
		for (int i = 0; i < funcionarios.size(); i++) {
			funcionarios.get(i).mostrarInformacoes(i);
		}
	}

	private void menuExcluirFuncionario() {
		System.out.println();
		System.out.println("===== ** =====");
		System.out.println();
		System.out.println("Escolha um funcionario para ser excluido:");
		System.out.println("0 - Voltar");		
		System.out.println();
		
		listarFuncionarios();
		
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
		
		System.out.println("Listagem de competencias:");
		
		listarCompetencias();
		
		Competencia[] func_competencias = new Competencia[num_competencias];
		
		int input_competencia = 0;
		
		for(int i = 0; i < num_competencias; i++) {
			System.out.print("Competencia " + (i + 1) + ": ");
			
			input_competencia = Integer.parseInt(scanner.nextLine());
			
			func_competencias[i] = new Competencia(competencias.get(input_competencia - 1).getCompetencia());
		}
		
		funcionarios.append(new Funcionario(nome, salario, num_competencias, func_competencias));
		
		System.out.println();
		System.out.println("Funcionario " + nome + " adicionado com sucesso!");

		menuFuncionarios();
	}
}
