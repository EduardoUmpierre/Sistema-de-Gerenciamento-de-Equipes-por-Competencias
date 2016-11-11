package trabalho2;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Projeto {
	
	private String nome;
	private LocalDate data_inicio;
	private LocalDate data_fim;
	private Integer num_competencias;
	private Competencia[] competencias;
	
	public Projeto(String nome, LocalDate data_inicio, LocalDate data_fim, Integer num_competencias, Competencia[] competencias) {
		setNome(nome);
		setData_inicio(data_inicio);
		setData_fim(data_fim);
		setNum_competencias(num_competencias);
		setCompetencias(competencias);
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public LocalDate getData_inicio() {
		return data_inicio;
	}

	public void setData_inicio(LocalDate data_inicio) {
		this.data_inicio = data_inicio;
	}

	public LocalDate getData_fim() {
		return data_fim;
	}

	public void setData_fim(LocalDate data_fim) {
		this.data_fim = data_fim;
	}

	public Integer getNum_competencias() {
		return num_competencias;
	}

	public void setNum_competencias(Integer num_competencias) {
		this.num_competencias = num_competencias;
	}

	public Competencia[] getCompetencias() {
		return competencias;
	}

	public void setCompetencias(Competencia[] competencias) {
		this.competencias = competencias;
	}
	
	public boolean comparaCompetencia(Funcionario funcionario) {
		for(int i = 0; i < getNum_competencias(); i++){
			for(int y = 0; y < funcionario.getNum_competencias(); y++){
				if(getCompetencias()[i].getCompetencia().equals(funcionario.getCompetencias()[y].getCompetencia())){
					return true;
				}
			}
		}
		
		return false;
	}
	
	public boolean naoContemFuncionario(Funcionario funcionario, Vetor<Colaborador> colaboradores) {
		for(int i = 0; i < colaboradores.size(); i++){			
			Colaborador colaborador = colaboradores.get(i);
			
			if(colaborador.getNome_projeto().equals(this.getNome()) && colaborador.getNome_funcionario().equals(funcionario.getNome())){
				return false;
			}
		}
		
		return true;
	}
	
	public void mostrarInformacoes(int index) {	
		String competencias = "";
		
		for (int i = 0; i < getNum_competencias(); i++) {
			competencias += getCompetencias()[i].getCompetencia();
			
			if(i == getNum_competencias() - 1) {
				competencias += ".";
			} else {
				competencias += ", ";
			}
		}
		
		System.out.println(
			(index + 1) + 
			" - Nome: " + 
			this.getNome() + 
			", Data de início: " + 
			this.getData_inicio().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) +
			", Data de fim: " +
			this.getData_fim().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) +
			", Número de competências: " +
			this.getNum_competencias() +
			", Competências: " +
			competencias
		);
	}
	
	public void mostrarInformacoesPendencias(int index, Vetor<String> pendencias) {	
		String competencias = "";
		
		for (int i = 0; i < pendencias.size(); i++) {
			competencias += pendencias.get(i);
			
			if(i == pendencias.size() - 1) {
				competencias += ".";
			} else {
				competencias += ", ";
			}
		}
		
		System.out.println(
			(index + 1) + 
			" - Nome: " + 
			this.getNome() + 
			", Data de início: " + 
			this.getData_inicio().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) +
			", Data de fim: " +
			this.getData_fim().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) +
			", Competências pendentes: " +
			competencias
		);
	}
}