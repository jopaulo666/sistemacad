package br.com.jopaulo.sistemacad.domain.acesso;

import java.time.LocalDateTime;

import br.com.jopaulo.sistemacad.domain.aluno.Aluno;

public class Acesso {
	
	private Integer id;
	private Aluno aluno;
	private LocalDateTime entrada;
	private LocalDateTime saida;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Aluno getAluno() {
		return aluno;
	}
	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}
	public LocalDateTime getEntrada() {
		return entrada;
	}
	public void setEntrada(LocalDateTime entrada) {
		this.entrada = entrada;
	}
	public LocalDateTime getSaida() {
		return saida;
	}
	public void setSaida(LocalDateTime saida) {
		this.saida = saida;
	}
	
	@Override
	public String toString() {
		return "Acesso [id=" + id + ", aluno=" + aluno + ", entrada=" + entrada + ", saida=" + saida + "]";
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Acesso other = (Acesso) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}	
}
