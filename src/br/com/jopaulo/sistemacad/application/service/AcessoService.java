package br.com.jopaulo.sistemacad.application.service;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.validation.ValidationException;

import br.com.jopaulo.sistemacad.application.util.StringUtils;
import br.com.jopaulo.sistemacad.domain.acesso.Acesso;
import br.com.jopaulo.sistemacad.domain.acesso.AcessoRepository;
import br.com.jopaulo.sistemacad.domain.acesso.TipoAcesso;
import br.com.jopaulo.sistemacad.domain.aluno.Aluno;
import br.com.jopaulo.sistemacad.domain.aluno.AlunoRepository;

@Stateless
public class AcessoService {

	@EJB
	private AcessoRepository acessoRepository;
	
	@EJB
	private AlunoRepository alunoRepository;
	
	public TipoAcesso registrarAcesso(String matricula, String cpf) {
		if (StringUtils.isEmpty(matricula) && StringUtils.isEmpty(cpf) ) {
			throw new ValidationException("Forneça a matrícula ou CPF do aluno");
		}
		
		Aluno aluno;
		if (StringUtils.isEmpty(matricula)) {
			aluno = alunoRepository.findByCPF(cpf);
		} else {
			aluno = alunoRepository.findByMatricula(matricula);
		}
		
		if (aluno == null) {
			throw new ValidationException("Aluno não encontrado");
		}
		
		Acesso ultimoAcesso = acessoRepository.findUltimoAcesso(aluno);
		TipoAcesso tipoAcesso;
		
		if (ultimoAcesso == null || ultimoAcesso.isEntradaSaidaPreenchidas()) {
			ultimoAcesso = new Acesso();
			ultimoAcesso.setAluno(aluno);
			tipoAcesso = ultimoAcesso.registrarAcesso();
			acessoRepository.store(ultimoAcesso);
		} else {
			tipoAcesso = ultimoAcesso.registrarAcesso();
		}
		
		return tipoAcesso;		
	}
}
