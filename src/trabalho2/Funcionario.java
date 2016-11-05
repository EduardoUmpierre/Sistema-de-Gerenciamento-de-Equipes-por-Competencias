package trabalho2;

public class Funcionario {
	
	private String nome;
	private String salario;
	private Integer num_competencias;
	private String[] competencias;
	
	public Funcionario(String nome, String salario, Integer num_competencias, String[] competencias) {
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

	public String[] getCompetencias() {
		return competencias;
	}

	public void setCompetencias(String[] competencias) {
		this.competencias = competencias;
	}
	
	@Override
	public String toString() {	
		return "Funcionario [nome=" + nome +
				", salario=" + salario +
				", num_competencias=" + num_competencias;
	}

}
