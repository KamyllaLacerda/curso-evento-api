package br.com.projeto.evento.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.projeto.evento.entities.Evento;
import br.com.projeto.evento.entities.Inscricao;

@Repository
public interface EventoRepository extends JpaRepository<Evento, Integer> {
	@Query("SELECT i.inscricoes FROM Evento i WHERE i.id = ?1") 
	List<Inscricao> findInscricaobyIdEvento(Integer idEvento);
}
