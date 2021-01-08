package br.com.projeto.evento.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.projeto.evento.entities.Usuario;
import br.com.projeto.evento.services.UsuarioService;

@RestController
@CrossOrigin
@RequestMapping("/usuario")
public class UsuarioController {

	@Autowired
	private UsuarioService service;

	@GetMapping
	public @ResponseBody List<Usuario> listarUsuario() {
		return service.findAll();
	}

	@GetMapping("/{id}")
	public Usuario findById(@PathVariable Integer id) {
		return service.findById(id);
	}

	@PostMapping()
	public Usuario cadastrarUsuario(@RequestBody @Valid Usuario usuario) {
		return service.cadastrarUsuario(usuario);
	}

}
