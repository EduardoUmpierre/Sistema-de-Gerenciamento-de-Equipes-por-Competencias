package trabalho2;

public class Colaborador {
	private String nome_projeto;
	private String nome_funcionario;
	private String nome_competencia;
	
	public Colaborador(String nome_projeto, String nome_funcionario, String nome_competencia) {
		setNome_projeto(nome_projeto);
		setNome_funcionario(nome_funcionario);
		setNome_competencia(nome_competencia);
	}

	public String getNome_projeto() {
		return nome_projeto;
	}

	public void setNome_projeto(String nome_projeto) {
		this.nome_projeto = nome_projeto;
	}

	public String getNome_funcionario() {
		return nome_funcionario;
	}

	public void setNome_funcionario(String nome_funcionario) {
		this.nome_funcionario = nome_funcionario;
	}

	public String getNome_competencia() {
		return nome_competencia;
	}

	public void setNome_competencia(String nome_competencia) {
		this.nome_competencia = nome_competencia;
	}
	
	public void mostrarInformacoes(int index) {			
		System.out.println(
			(index + 1) + 
			" - Nome: " + 
			this.getNome_projeto() + 
			", Funcionario: " + 
			this.getNome_funcionario() +
			", Competência: " +
			this.getNome_competencia() + 
			"."
		);
	}
}