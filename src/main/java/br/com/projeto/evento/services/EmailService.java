package br.com.projeto.evento.services;

import java.util.Date;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import br.com.projeto.evento.entities.Inscricao;

public class EmailService {

	@Autowired
	private TemplateEngine templateEngine;

	@Autowired
	private MailSender mailSender;

	@Autowired
	private JavaMailSender javaMailSender;


	public void emailDeConfirmacaoDeInscricao(Inscricao inscricao) {
		SimpleMailMessage sm = prepararSimpleMailMessageParaInscricao(inscricao);
		enviarEmail(sm);
	}

	protected SimpleMailMessage prepararSimpleMailMessageParaInscricao(Inscricao inscricao) {
		SimpleMailMessage sm = new SimpleMailMessage();
		sm.setTo(inscricao.getUsuario().getEmail());
		sm.setSubject("Sua incrição foi feita com sucesso!");
		sm.setSentDate(new Date());
		sm.setText("Olá " + inscricao.getUsuario().getNome() + " Sua inscricão de número " + inscricao.getId()
				+ " foi feita com sucesso! \n Aguarde o aceite da mesma para participar do evento.");
		return sm;
	}

	public void enviarEmail(SimpleMailMessage mensagem) {
		mailSender.send(mensagem);
	}

	// Enviar email confirmação de pedido html

	public void emailDeConfirmacaoDeInscricaoHtml(Inscricao inscricao) {
		try {
			MimeMessage mm = prepararMimeMessageParaInscricao(inscricao);
			enviarEmailHtml(mm);
		} catch (MessagingException e) {
			emailDeConfirmacaoDeInscricao(inscricao);
		}
	}

	protected MimeMessage prepararMimeMessageParaInscricao(Inscricao inscricao) throws MessagingException {
		MimeMessage mimeMessage = javaMailSender.createMimeMessage();
		MimeMessageHelper mmh = new MimeMessageHelper(mimeMessage, true);
		mmh.setTo(inscricao.getUsuario().getEmail());
		mmh.setSubject("Inscrição realizada com sucesso!");
		mmh.setSentDate(new Date());
		mmh.setText(htmlEmailDeConfirmacaoDeInscricao(inscricao), true);
		return mimeMessage;
	}

	public void enviarEmailHtml(MimeMessage mensagem) {
		javaMailSender.send(mensagem);
	}

	protected String htmlEmailDeConfirmacaoDeInscricao(Inscricao inscricao) {
		Context context = new Context();
		context.setVariable("inscricao", inscricao);
		return templateEngine.process("email/confirmacaoInscricao", context);
	}

	// Email para aceite de inscrição

	public void emailDeAceiteDeInscricao(Inscricao inscricao) {
		SimpleMailMessage smAceite = prepararSimpleMailMessageParaAceiteDeInscricao(inscricao);
		enviarEmailAceite(smAceite);
	}

	protected SimpleMailMessage prepararSimpleMailMessageParaAceiteDeInscricao(Inscricao inscricao) {
		SimpleMailMessage smAceite = new SimpleMailMessage();
		smAceite.setTo(inscricao.getUsuario().getEmail());
		smAceite.setSubject("Sua incrição foi aceita!");
		smAceite.setSentDate(new Date());
		smAceite.setText("Olá " + inscricao.getUsuario().getNome() + " Sua inscricão de número " + inscricao.getId()
				+ " foi aceita!! Aguardamos você lá!");
		return smAceite;
	}

	public void enviarEmailAceite(SimpleMailMessage mensagem) {
		mailSender.send(mensagem);
	}
	
	//Enviar email de aceite de inscrição html
	
	public void emailDeAceiteDeInscricaoHtml(Inscricao inscricao) {
		try {
			MimeMessage mmAceite = prepararMimeMessageParaAceiteDeInscricao(inscricao);
			enviarEmailAceiteHtml(mmAceite);
		} catch (MessagingException e) {
			emailDeAceiteDeInscricao(inscricao);
		}
	}

	protected MimeMessage prepararMimeMessageParaAceiteDeInscricao(Inscricao inscricao) throws MessagingException {
		MimeMessage mimeMessageAceite = javaMailSender.createMimeMessage();
		MimeMessageHelper mmhAceite = new MimeMessageHelper(mimeMessageAceite, true);
		mmhAceite.setTo(inscricao.getUsuario().getEmail());
		mmhAceite.setSubject("Sua inscrição no evento foi aceita!!");
		mmhAceite.setSentDate(new Date());
		mmhAceite.setText(htmlEmailDeAceiteDeInscricao(inscricao), true);
		return mimeMessageAceite;
	}

	public void enviarEmailAceiteHtml(MimeMessage mensagemAceite) {
		javaMailSender.send(mensagemAceite);
	}

	protected String htmlEmailDeAceiteDeInscricao(Inscricao inscricao) {
		Context contextAceite = new Context();
		contextAceite.setVariable("inscricao", inscricao);
		return templateEngine.process("email/aceiteInscricao", contextAceite);
	}

	//Eviar email de recusa de inscricao
	
	public void emailDeRecusaDeInscricao(Inscricao inscricao) {
		SimpleMailMessage smRecusa = prepararSimpleMailMessageParaRecusaDeInscricao(inscricao);
		enviarEmailRecusa(smRecusa);
	}

	protected SimpleMailMessage prepararSimpleMailMessageParaRecusaDeInscricao(Inscricao inscricao) {
		SimpleMailMessage smRecusa = new SimpleMailMessage();
		smRecusa.setTo(inscricao.getUsuario().getEmail());
		smRecusa.setSubject("Sua inscrição foi recusada!");
		smRecusa.setSentDate(new Date());
		smRecusa.setText("Olá " + inscricao.getUsuario().getNome() + " lamentamos, mas sua inscricão de número " + inscricao.getId()
				+ " foi recusada. Contudo não deixe de se inscrever em outros eventos!");
		return smRecusa;
	}

	public void enviarEmailRecusa(SimpleMailMessage mensagem) {
		mailSender.send(mensagem);
	}
	
	//Enviar email de recusa de inscricao html
	
	public void emailDeRecusaDeInscricaoHtml(Inscricao inscricao) {
		try {
			MimeMessage mmRecusa = prepararMimeMessageParaRecusaDeInscricao(inscricao);
			enviarEmailRecusaHtml(mmRecusa);
		} catch (MessagingException e) {
			emailDeRecusaDeInscricao(inscricao);
		}
	}

	protected MimeMessage prepararMimeMessageParaRecusaDeInscricao(Inscricao inscricao) throws MessagingException {
		MimeMessage mimeMessageRecusa = javaMailSender.createMimeMessage();
		MimeMessageHelper mmhRecusa = new MimeMessageHelper(mimeMessageRecusa, true);
		mmhRecusa.setTo(inscricao.getUsuario().getEmail());
		mmhRecusa.setSubject("Sua inscrição no evento foi recusada.");
		mmhRecusa.setSentDate(new Date());
		mmhRecusa.setText(htmlEmailDeRecusaDeInscricao(inscricao), true);
		return mimeMessageRecusa;
	}

	public void enviarEmailRecusaHtml(MimeMessage mensagemRecusa) {
		javaMailSender.send(mensagemRecusa);
	}

	protected String htmlEmailDeRecusaDeInscricao(Inscricao inscricao) {
		Context contextRecusa = new Context();
		contextRecusa.setVariable("inscricao", inscricao);
		return templateEngine.process("email/recusaInscricao", contextRecusa);
	}
	
} 
