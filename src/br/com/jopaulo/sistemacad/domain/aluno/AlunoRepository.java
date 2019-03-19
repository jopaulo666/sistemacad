package br.com.jopaulo.sistemacad.domain.aluno;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Year;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import br.com.jopaulo.sistemacad.application.util.StringUtils;
import br.com.jopaulo.sistemacad.domain.acesso.Acesso;
import br.com.jopaulo.sistemacad.domain.aluno.Aluno.Situacao;

@Stateless
public class AlunoRepository {

	@PersistenceContext
	private EntityManager em;

	public void create(Aluno aluno) {
		em.persist(aluno);
	}

	public void update(Aluno aluno) {
		em.merge(aluno);
	}

	public Aluno findByMatricula(String matricula) {
		return em.find(Aluno.class, matricula);
	}
	
	public Aluno findByCPF(String cpf) {
		try {
			return em.createQuery("SELECT a FROM Aluno a WHERE a.cpf = :cpf", Aluno.class)
					.setParameter("cpf", cpf).getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

	public void delete(String matricula) {
		Aluno aluno = findByMatricula(matricula);
		if (aluno != null) {
			em.remove(aluno);
		}
	}

	public String getMaxMatriculaAno() {
		return em.createQuery("SELECT MAX(a.matricula) FROM Aluno a WHERE a.matricula LIKE :ano", String.class)
				.setParameter("ano", Year.now() + "%").getSingleResult();
	}
	
	public List<Aluno> listAlunos(String matricula, String nome, String cpf) {
		StringBuilder jpql = new StringBuilder("SELECT a FROM Aluno a WHERE ");
		
		if (!StringUtils.isEmpty(matricula)) {
			jpql.append("a.matricula = :matricula AND ");
		}		
		if (!StringUtils.isEmpty(nome)) {
			jpql.append("a.nome LIKE :nome AND ");
		}		
		if (!StringUtils.isEmpty(cpf)) {
			jpql.append("a.cpf LIKE :cpf AND ");
		}
		
		jpql.append("1 = 1");
		TypedQuery<Aluno> q = em.createQuery(jpql.toString(), Aluno.class);
		
		if (!StringUtils.isEmpty(matricula)) {
			q.setParameter("matricula", matricula);
		}		
		if (!StringUtils.isEmpty(nome)) {
			q.setParameter("nome", "%" + nome + "%");
		}		
		if (!StringUtils.isEmpty(cpf)) {
			q.setParameter("cpf", "%" + cpf + "%");
		}		
		return q.getResultList();
	}
	
	public List<Aluno> listSituacaoAlunos(Situacao situacao){
		return em.createQuery("SELECT a FROM Aluno a WHERE a.situacao = :situacao ORDER BY a.nome", Aluno.class)
				.setParameter("situacao", situacao)
				.getResultList();
	}
	
	public List<Acesso> listAcessosAlunos(String matricula, LocalDate dataInicial, LocalDate dataFinal){
		StringBuilder jpql = new StringBuilder("SELECT a FROM Acesso a WHERE ");
		
		if (!StringUtils.isEmpty(matricula)) {
			jpql.append("a.aluno.matricula = :matricula AND ");
		}
		if (dataInicial != null) {
			jpql.append("a.entrada >= :entradaInicio AND ");
		}
		if (dataFinal != null) {
			jpql.append("a.saida <= :saidaFim AND ");
		}
		
		jpql.append("1 = 1 ORDER BY a.entrada");
		
		TypedQuery<Acesso> q = em.createQuery(jpql.toString(), Acesso.class);
		
		if (!StringUtils.isEmpty(matricula)) {
			q.setParameter("matricula", matricula);
		}
		if (dataInicial != null) {
			LocalDateTime dateTime = LocalDateTime.of(dataInicial, LocalTime.of(0, 0, 0));
			q.setParameter("entradaInicio", dateTime);
		}
		if (dataFinal != null) {
			
			LocalDateTime dateTime = LocalDateTime.of(dataFinal, LocalTime.of(23, 59, 59));
			q.setParameter("saidaFim", dateTime);
		}
		return q.getResultList();
	}
}
