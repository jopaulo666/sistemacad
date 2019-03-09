package br.com.jopaulo.sistemacad.domain.aluno;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

//compartilha o Id
@Embeddable
public class Contato implements Serializable {
	
	@Column(name = "email", nullable = true, length = 64)
	private String email;
	
	@Column(name = "ddd_celular", nullable = false, length = 2)
	private Integer dddCelular;
	
	@Column(name = "numero_celular", nullable = false, length = 9)
	private Integer numeroCelular;
	
	@Column(name = "ddd_fixo", nullable = true, length = 2)
	private Integer dddfixo;
	
	@Column(name = "numero_fixo", nullable = true, length = 9)
	private Integer numeroFixo;
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Integer getDddCelular() {
		return dddCelular;
	}
	public void setDddCelular(Integer dddCelular) {
		this.dddCelular = dddCelular;
	}
	public Integer getNumeroCelular() {
		return numeroCelular;
	}
	public void setNumeroCelular(Integer numeroCelular) {
		this.numeroCelular = numeroCelular;
	}
	public Integer getDddfixo() {
		return dddfixo;
	}
	public void setDddfixo(Integer dddfixo) {
		this.dddfixo = dddfixo;
	}
	public Integer getNumeroFixo() {
		return numeroFixo;
	}
	public void setNumeroFixo(Integer numeroFixo) {
		this.numeroFixo = numeroFixo;
	}
	@Override
	public String toString() {
		return "Contato [email=" + email + ", dddCelular=" + dddCelular + ", numeroCelular=" + numeroCelular
				+ ", dddfixo=" + dddfixo + ", numeroFixo=" + numeroFixo + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dddCelular == null) ? 0 : dddCelular.hashCode());
		result = prime * result + ((dddfixo == null) ? 0 : dddfixo.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((numeroCelular == null) ? 0 : numeroCelular.hashCode());
		result = prime * result + ((numeroFixo == null) ? 0 : numeroFixo.hashCode());
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
		Contato other = (Contato) obj;
		if (dddCelular == null) {
			if (other.dddCelular != null)
				return false;
		} else if (!dddCelular.equals(other.dddCelular))
			return false;
		if (dddfixo == null) {
			if (other.dddfixo != null)
				return false;
		} else if (!dddfixo.equals(other.dddfixo))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (numeroCelular == null) {
			if (other.numeroCelular != null)
				return false;
		} else if (!numeroCelular.equals(other.numeroCelular))
			return false;
		if (numeroFixo == null) {
			if (other.numeroFixo != null)
				return false;
		} else if (!numeroFixo.equals(other.numeroFixo))
			return false;
		return true;
	}	
}
