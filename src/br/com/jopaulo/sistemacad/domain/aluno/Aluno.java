package br.com.jopaulo.sistemacad.domain.aluno;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.Year;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;

import br.com.jopaulo.sistemacad.application.util.StringUtils;

@Entity
@Table(name = "aluno")
public class Aluno implements Serializable{
	
	public enum Situacao{
		Ativo, Inativo, Pendente;
	}
	public enum Sexo{
		Masculino, Feminino;
	}
	
	@Id
	@Column(name = "id", nullable = false, length = 8)
	private String matricula;
	
	@Column(name = "nome", nullable = false, length = 64)
	private String nome;
	
	@Enumerated
	@Column(name = "sexo", nullable = false, length = 1)
	private Sexo sexo;
	
	@Column(name = "rg", nullable = true, length = 10)
	private String rg;
	
	@Column(name = "cpf", nullable = false, length = 11)
	private String cpf;
	
	@Column(name = "nascimento", nullable = false)
	private LocalDate dataNascimento;
	
	@Enumerated
	@Column(name = "situacao", nullable = false, length = 1)
	private Situacao situacao;
	
	@Embedded //fazem parte da entidade aluno, vão comprtilhar o mesmo id q aluno
	private Contato contato = new Contato();
	
	@Embedded // pega o id de outra classe
	private Endereco endereco = new Endereco();
	
	public void gerarMatricula(String maxMatricula) {
		Year year = Year.now();
		
		if (maxMatricula == null) {
			maxMatricula = year + StringUtils.leftZeroes(0, 4);
		}
		
		int sequential = Integer.parseInt(maxMatricula.substring(4));
		sequential++;
		
		this.matricula = year + StringUtils.leftZeroes(sequential, 4);
	}
	
	public String getMatricula() {
		return matricula;
	}
	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public Sexo getSexo() {
		return sexo;
	}
	public void setSexo(Sexo sexo) {
		this.sexo = sexo;
	}
	public String getRg() {
		return rg;
	}
	public void setRg(String rg) {
		this.rg = rg;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public LocalDate getDataNascimento() {
		return dataNascimento;
	}
	public void setDataNascimento(LocalDate dataNascimento) {
		this.dataNascimento = dataNascimento;
	}
	public Situacao getSituacao() {
		return situacao;
	}
	public void setSituacao(Situacao situacao) {
		this.situacao = situacao;
	}
	public Contato getContato() {
		return contato;
	}
	public void setContato(Contato contato) {
		this.contato = contato;
	}
	public Endereco getEndereco() {
		return endereco;
	}
	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}
	
	@Override
	public String toString() {
		return "Aluno [codigo=" + matricula + ", nome=" + nome + ", sexo=" + sexo + ", rg=" + rg + ", cpf=" + cpf
				+ ", dataNascimento=" + dataNascimento + ", situacao=" + situacao + ", contato=" + contato
				+ ", endereco=" + endereco + "]";
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((matricula == null) ? 0 : matricula.hashCode());
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
		Aluno other = (Aluno) obj;
		if (matricula == null) {
			if (other.matricula != null)
				return false;
		} else if (!matricula.equals(other.matricula))
			return false;
		return true;
	}
}
