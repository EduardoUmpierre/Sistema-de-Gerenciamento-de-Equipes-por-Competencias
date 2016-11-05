package trabalho2;

public class Projeto {
	
	private String nome;
	private String data_inicio;
	private String data_fim;
	private Integer num_competencias;
	private String[] competencias;
	
	public Projeto(String nome, String data_inicio, String data_fim, Integer num_competencias, String[] competencias) {
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

	public String getData_inicio() {
		return data_inicio;
	}

	public void setData_inicio(String data_inicio) {
		this.data_inicio = data_inicio;
	}

	public String getData_fim() {
		return data_fim;
	}

	public void setData_fim(String data_fim) {
		this.data_fim = data_fim;
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
		return "Projeto [nome=" + nome +
				", data_inicio=" + data_inicio +
				", data_fim=" + data_fim +
				", num_competencias=" + num_competencias;
	}
}