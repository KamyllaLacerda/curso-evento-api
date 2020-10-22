package br.com.projeto.evento.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.projeto.evento.entities.Evento;
import br.com.projeto.evento.entities.Inscricao;
import br.com.projeto.evento.services.EventoService;

@RestController
@CrossOrigin
@RequestMapping("/evento")
public class EventoController {

	@Autowired
	private EventoService service;

	@GetMapping
	public List<Evento> listarEventos() {
		return service.findAll();
	}

	@GetMapping("/{id}")
	public Evento findById(@PathVariable Integer id) {
		return service.findById(id);
	}

	@PostMapping()
	@ResponseStatus(HttpStatus.CREATED)
	public Evento cadastrarEvento(@RequestBody Evento evento) {
		return service.cadastrarEvento(evento);
	}

	@DeleteMapping("/{id}")
	public void deletarEvento(@PathVariable Integer id) {
		service.deletarEvento(id);
	}

	@PutMapping("/{id}")
	public Evento editarEvento(@PathVariable Integer id, @RequestBody Evento evento) {
		return service.editarEvento(id, evento);
	}
	
	@GetMapping("/inscricao/{id}")
	public List<Inscricao> listarIncricaoPorEvento(@PathVariable("id") Integer evento){
		return service.buscarInscricaoEvento(evento);
	}
}
