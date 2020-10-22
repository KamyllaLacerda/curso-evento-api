package br.com.projeto.evento.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.projeto.evento.entities.Usuario;
import br.com.projeto.evento.repositories.UsuarioRepository;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository repository;

	public List<Usuario> findAll() {
		return repository.findAll();
	}

	public Usuario findById(Integer id) {
		Optional<Usuario> usuario = repository.findById(id);
		return usuario.get();
	}

	public Usuario cadastrarUsuario(Usuario usuario) {
		return repository.save(usuario);
	}

	public void deletarUsuario(Integer id) {
		repository.deleteById(id);
	}
}
