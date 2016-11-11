package trabalho2;

public class Funcionario {
	
	private String nome;
	private String salario;
	private Integer num_competencias;
	private Competencia[] competencias;
	
	public Funcionario(String nome, String salario, Integer num_competencias, Competencia[] competencias) {
		setNome(nome);
		setSalario(salario);
		setNum_competencias(num_competencias);
		setCompetencias(competencias);
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSalario() {
		return salario;
	}

	public void setSalario(String salario) {
		this.salario = salario;
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
	
	public String competenciasToString() {
		String competencias = "";
		
		for (int i = 0; i < getNum_competencias(); i++) {
			competencias += getCompetencias()[i].getCompetencia();
			
			if(i == getNum_competencias() - 1) {
				competencias += ".";
			} else {
				competencias += ", ";
			}
		}
		
		return competencias;
	}
	
	public Integer numeroDeProjetos(Vetor<Colaborador> colaboradores) {
		Integer num_projetos = 0;
		
		for(int i = 0; i < colaboradores.size(); i++){			
			Colaborador colaborador = colaboradores.get(i);
			
			if(colaborador.getNome_funcionario().equals(this.getNome())){
				num_projetos++;
			}
		}
		
		return num_projetos;
	}
	
	public void mostrarInformacoes(int index) {		
		System.out.println(
			(index + 1) + 
			" - Nome: " + 
			this.getNome() + 
			", Salário: R$" + 
			this.getSalario() +
			", Número de competências: " +
			this.getNum_competencias() +
			", Competências: " +
			this.competenciasToString()
		);
	}
}
