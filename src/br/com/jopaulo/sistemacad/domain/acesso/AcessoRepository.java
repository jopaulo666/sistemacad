package br.com.jopaulo.sistemacad.domain.acesso;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import br.com.jopaulo.sistemacad.domain.aluno.Aluno;

@Stateless
public class AcessoRepository {

	@PersistenceContext
	private EntityManager entityManager;
	
	public Acesso findUltimoAcesso (Aluno aluno) {
		try {
			return entityManager.createQuery("SELECT a FROM Acesso a WHERE a.aluno.matricula = :matricula ORDER BY a.id DESC", Acesso.class)
					.setParameter("matricula", aluno.getMatricula())
					.setMaxResults(1)
					.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}	
	}
	
	//grava no banco de dados
	public void store(Acesso acesso) {
		entityManager.persist(acesso);
	}
}
