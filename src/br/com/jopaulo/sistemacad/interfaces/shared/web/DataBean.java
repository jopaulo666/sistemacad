package br.com.jopaulo.sistemacad.interfaces.shared.web;

import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;

import br.com.jopaulo.sistemacad.application.service.DataService;
import br.com.jopaulo.sistemacad.domain.aluno.Aluno.Sexo;
import br.com.jopaulo.sistemacad.domain.aluno.Aluno.Situacao;
import br.com.jopaulo.sistemacad.domain.aluno.Estado;

@Named
@ApplicationScoped //único para aplicação
public class DataBean {
	
	@EJB
	private DataService dataService;
	
	public Sexo[] getSexos() {
		return dataService.getSexos();
	}
	
	public Situacao[] getSituacoes() {
		return dataService.getSituacoes();
	}
	
	public List<Estado> getEstados(){
		return dataService.listEstados(); 
	}

	public String formatTelefone(Integer ddd, Integer numero) {
		if (ddd == null || numero == null) {
			return "";
		}
		return "(" + ddd + ") " + numero;
	}
}
