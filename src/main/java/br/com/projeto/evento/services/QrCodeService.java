package br.com.projeto.evento.services;

import java.io.ByteArrayOutputStream;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

import br.com.projeto.evento.entities.Inscricao;

public class QrCodeService {

	public static byte[] getImagemQrCode(Inscricao inscricao) {
		try {
			QRCodeWriter qrCodeWriter = new QRCodeWriter();
			BitMatrix bitMatrix = qrCodeWriter.encode("Olá " + inscricao.getUsuario().getNome()
					+ ",\n obrigado(a) por participar do evento " + inscricao.getEvento().getTitulo() +"!!"
					+ "\n Você pode gerar seu certificado clicando no link abaixo! \n"
					+ "http://localhost:8080/inscricao/pdf/" + inscricao.getId(), BarcodeFormat.QR_CODE, 200, 200);
			ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
			MatrixToImageWriter.writeToStream(bitMatrix, "png", byteArrayOutputStream);
			return byteArrayOutputStream.toByteArray();

		} catch (Exception e) {
			return null;
		}
	}
}
