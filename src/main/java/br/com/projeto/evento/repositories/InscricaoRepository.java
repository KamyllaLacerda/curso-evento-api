package br.com.projeto.evento.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.projeto.evento.entities.Evento;
import br.com.projeto.evento.entities.Inscricao;
import br.com.projeto.evento.entities.Usuario;


@Repository
public interface InscricaoRepository extends JpaRepository<Inscricao, Integer> {
	@Query("SELECT i.evento FROM Inscricao i WHERE i.usuario.id = ?1") 
	List<Evento> findEventosbyIdUsuario(Integer idUsuario);
	
	@Query("SELECT i.usuario FROM Inscricao i WHERE i.evento.id = ?1")
	List<Usuario> findUsuariobyEvento(Integer idEvento);
	
	@Query("SELECT i FROM Inscricao i WHERE i.usuario.cpf = ?1 AND i.evento.id = ?2")
	Inscricao findUsuariobyCpfinEvento(String cpf, Integer id);
	
}
