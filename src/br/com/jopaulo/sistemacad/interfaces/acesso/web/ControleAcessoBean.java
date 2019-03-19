package br.com.jopaulo.sistemacad.interfaces.acesso.web;

import java.io.Serializable;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.jopaulo.sistemacad.application.service.AcessoService;
import br.com.jopaulo.sistemacad.application.util.ValidationException;
import br.com.jopaulo.sistemacad.domain.acesso.TipoAcesso;

@Named
@RequestScoped
public class ControleAcessoBean implements Serializable{
	
	@EJB
	private AcessoService acessoService;
	
	@Inject
	private FacesContext facesContext;
	
	private String matricula;
	private String cpf;
	
	public String registrarAcesso() {
		TipoAcesso tipoAcesso;
		try {
			tipoAcesso = acessoService.registrarAcesso(matricula, cpf);
		} catch (ValidationException e) {
			facesContext.addMessage(null, new FacesMessage(e.getMessage()));
			return null;
		}		
		
		String msg;
		if (tipoAcesso == TipoAcesso.Entrada) {
			msg = "Entrada registrada!";
		} else if (tipoAcesso == TipoAcesso.Saida){
			msg = "Saída registrada!";
		} else {
			msg = "Acesso inconsistente";
		}
		
		facesContext.addMessage(null, new FacesMessage(msg));
		return null; //volta para própria página
	}
	
	public String getMatricula() {
		return matricula;
	}
	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
}
