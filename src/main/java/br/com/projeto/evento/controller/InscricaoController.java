package br.com.projeto.evento.controller;

import java.io.OutputStream;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.projeto.evento.entities.Evento;
import br.com.projeto.evento.entities.Inscricao;
import br.com.projeto.evento.entities.Usuario;
import br.com.projeto.evento.services.InscricaoService;
import br.com.projeto.evento.services.QrCodeService;

@RestController
@CrossOrigin
@RequestMapping("/inscricao")
public class InscricaoController {

	@Autowired
	private InscricaoService service;
	

	@GetMapping
	public @ResponseBody List<Inscricao> listarEventos() {
		return service.findAll();
	}

	@GetMapping("/evento/{id}")
	public List<Evento> listarEventosPorUsuario(@PathVariable("id") Integer usuario) {
		return service.buscarEventoUsuario(usuario);
	}

	@GetMapping("/usuario/{id}")
	public List<Usuario> listarUsuarioPorEvento(@PathVariable("id") Integer evento) {
		return service.buscarUsuarioPorEvento(evento);
	}

	@GetMapping("/{id}")
	public Inscricao findById(@PathVariable Integer id) {
		return findById(id);
	}

	@PostMapping
	public Inscricao cadastrarInscricao(@RequestBody Inscricao inscricao) {
		return service.cadastrarInscricao(inscricao);
	}

	@PutMapping
	public Inscricao atualizarStatusIncricao(@RequestBody Inscricao inscricao) {
		return service.atualizarStatusInscricao(inscricao);
	}

	@GetMapping("/pdf/{id}")
	public void gerarCertificadoPdf(@PathVariable("id") Integer idInscricao, HttpServletResponse response) {
		service.gerarCertificadoPdf(idInscricao, response);

	}

	@GetMapping("/qrcode/{id}")
	public void qrCode(@PathVariable("id") Inscricao inscricao, HttpServletResponse response) throws Exception {
		service.findById(inscricao.getId());
		response.setContentType("image/png");
		OutputStream outputStream = response.getOutputStream();
		outputStream.write(QrCodeService.getImagemQrCode(inscricao));
		outputStream.flush();
		outputStream.close();
	}

}
