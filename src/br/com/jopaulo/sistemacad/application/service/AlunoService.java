package br.com.jopaulo.sistemacad.application.service;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.com.jopaulo.sistemacad.application.util.StringUtils;
import br.com.jopaulo.sistemacad.application.util.Validation;
import br.com.jopaulo.sistemacad.application.util.ValidationException;
import br.com.jopaulo.sistemacad.domain.aluno.Aluno;
import br.com.jopaulo.sistemacad.domain.aluno.AlunoRepository;

@Stateless
public class AlunoService {

	@EJB
	private AlunoRepository alunoRepository;
	
	public void createOrUpdate(Aluno aluno) {
		if (StringUtils.isEmpty(aluno.getMatricula())) {
			create(aluno);
		} else {
			update(aluno);
		}
	}
	
	private void create(Aluno aluno) {
		Validation.assetNotEmpty(aluno);//garante q não está vazio
		String maxMatricula = alunoRepository.getMaxMatriculaAno();
		aluno.gerarMatricula(maxMatricula);
		alunoRepository.create(aluno);
		
	}
	
	private void update(Aluno aluno) {
		Validation.assetNotEmpty(aluno);
		Validation.assetNotEmpty(aluno.getMatricula());
		alunoRepository.update(aluno);		
	}
	
	public void delete(String matricula) {
		alunoRepository.delete(matricula);
	}
	
	public Aluno findByMatricula(String matricula) {
		return alunoRepository.findByMatricula(matricula);
	}
	
	public List<Aluno> listAlunos(String matricula, String nome, String cpf){
		if (StringUtils.isEmpty(matricula) && StringUtils.isEmpty(nome) && StringUtils.isEmpty(cpf)) {
			throw new ValidationException("Ao menos um campo de pesquisa deve ser preenchido");
		}
		return alunoRepository.listAlunos(matricula, nome, cpf);
	}
}
