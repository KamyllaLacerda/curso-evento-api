package br.com.projeto.evento.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.projeto.evento.entities.Evento;
import br.com.projeto.evento.entities.Inscricao;
import br.com.projeto.evento.repositories.EventoRepository;

@Service
public class EventoService {

	@Autowired
	private EventoRepository repository;

	public List<Evento> findAll() {
		return repository.findAll();
	}

	public Evento findById(Integer id) {
		Optional<Evento> evento = repository.findById(id);
		return evento.get();
	}

	public Evento cadastrarEvento(Evento evento) {
		return repository.save(evento);
	}

	public void deletarEvento(Integer id) {
		repository.deleteById(id);
	}

	@Transactional
	public Evento editarEvento(Integer id, Evento evento) {
		Evento eventoEntity = repository.getOne(id);
		editarDadosEvento(eventoEntity, evento);
		return repository.save(eventoEntity);
	}
	
	private void editarDadosEvento(Evento eventoEntity, Evento evento) {
		eventoEntity.setTitulo(evento.getTitulo());
		eventoEntity.setLocal(evento.getLocal());
		eventoEntity.setDataInicio(evento.getDataInicio());
		eventoEntity.setDataFim(evento.getDataFim());
		eventoEntity.setHoraInicio(evento.getHoraInicio());
		eventoEntity.setHoraFim(evento.getHoraFim());
		eventoEntity.setDescricao(evento.getDescricao());
		eventoEntity.setEmenta(evento.getEmenta());
		eventoEntity.setNumeroVagas(evento.getNumeroVagas());
		eventoEntity.setDataInicioInscricao(evento.getDataInicioInscricao());
		eventoEntity.setDataFimInscricao(evento.getDataFimInscricao());
	}
	
	public List<Inscricao> buscarInscricaoEvento(Integer evento){
		return repository.findInscricaobyIdEvento(evento);
		
	}
	
}
