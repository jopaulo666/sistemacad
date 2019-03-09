package br.com.jopaulo.sistemacad.domain.aluno;

import java.time.Year;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import br.com.jopaulo.sistemacad.application.util.StringUtils;

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
}
