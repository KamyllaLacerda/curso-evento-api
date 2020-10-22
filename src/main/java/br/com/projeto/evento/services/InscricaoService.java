package br.com.projeto.evento.services;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import br.com.projeto.evento.entities.Evento;
import br.com.projeto.evento.entities.Inscricao;
import br.com.projeto.evento.entities.Usuario;
import br.com.projeto.evento.repositories.InscricaoRepository;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;

@Service
public class InscricaoService {

	@Autowired
	private InscricaoRepository repository;

	@Autowired
	private UsuarioService usuarioService;

	@Autowired
	private EventoService eventoService;

	@Autowired
	private EmailService emailService;
	
	//@Autowired
	//private DataSource dataSource;


	public List<Inscricao> findAll() {
		return repository.findAll();
	}

	public List<Evento> buscarEventoUsuario(Integer usuario) {
		return repository.findEventosbyIdUsuario(usuario);
	}

	public List<Usuario> buscarUsuarioPorEvento(Integer evento) {
		return repository.findUsuariobyEvento(evento);
	}

	public Inscricao findById(Integer id) {
		Optional<Inscricao> inscricao = repository.findById(id);
		return inscricao.get();
	}

	public Inscricao buscarUsuarioPorCpf(String cpfUsuario, Integer idEvento) {
		return repository.findUsuariobyCpfinEvento(cpfUsuario, idEvento);
	}

	public Inscricao cadastrarInscricaoAntes(Inscricao inscricao) {
		return repository.save(inscricao);
	}

	public Inscricao cadastrarInscricao(Inscricao inscricao) {

		if (null != buscarUsuarioPorCpf(inscricao.getUsuario().getCpf(), inscricao.getEvento().getId())) {
			throw new RuntimeException("Usuario já cadastrado");
		}

		inscricao.setUsuario(usuarioService.cadastrarUsuario(inscricao.getUsuario()));

		inscricao.setDataHora(new Date());
		inscricao.setStatus("Processando");

		inscricao.setEvento(eventoService.findById(inscricao.getEvento().getId()));

		cadastrarInscricaoAntes(inscricao);

		emailService.emailDeConfirmacaoDeInscricaoHtml(inscricao);

		return inscricao;

	}

	public Inscricao atualizarStatusInscricao(Inscricao inscricao) {
		Inscricao novaInscricao = repository.findUsuariobyCpfinEvento(inscricao.getUsuario().getCpf(),
				inscricao.getEvento().getId());
		novaInscricao.setStatus(inscricao.getStatus());
		repository.save(novaInscricao);

		if (inscricao.getStatus().equals("Aceito")) {
			emailService.emailDeAceiteDeInscricaoHtml(novaInscricao);
		} else if (inscricao.getStatus().equals("Recusado")) {
			emailService.emailDeRecusaDeInscricaoHtml(novaInscricao);
		}

		return novaInscricao;
	}

	public Map<String, Object> gerarParametros(Integer idIncricao) {
		Inscricao inscricao = findById(idIncricao);
		Map<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("nome", inscricao.getUsuario().getNome());
		parametros.put("sobrenome", inscricao.getUsuario().getSobrenome());
		parametros.put("cpf", inscricao.getUsuario().getCpf());
		parametros.put("titulo", inscricao.getEvento().getTitulo());
		parametros.put("dataInicio", inscricao.getEvento().getDataInicio());
		parametros.put("dataFim", inscricao.getEvento().getDataFim());

		return parametros;
	}

	public void gerarCertificadoPdf(Integer idIncricao, HttpServletResponse response) {

		try {
			OutputStream outPut = null;
			InputStream inputStream = new ClassPathResource("/report/Certificado.jasper").getInputStream();
			JasperReport report = (JasperReport) JRLoader.loadObject(inputStream);
			JasperPrint jasperPrint = JasperFillManager.fillReport(report, gerarParametros(idIncricao), new JREmptyDataSource());
			response.setContentType("application/pdf");
			response.setHeader("Content-Disposition", "inline; filename=Certificado.pdf");
			outPut = response.getOutputStream();
			JasperExportManager.exportReportToPdfStream(jasperPrint, outPut);
			
		} catch (Exception exception) {
			throw new RuntimeException(exception.getMessage());
		}
		
	}
	
	public void teste() {
		try {
		System.out.println(new ClassPathResource("/report/Certificado.jasper").getFilename());
		}
		catch(Exception exception) {
			throw new RuntimeException(exception.getMessage());
		}
	}

}
