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
					+ ",\nsua inscrição de número " + inscricao.getId() + " no evento " + inscricao.getEvento().getTitulo()
					+ "\nfoi realizada com sucesso!\n"
					+ "Aguarde sua aceitação no evento...", BarcodeFormat.QR_CODE, 200, 200);
			ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
			MatrixToImageWriter.writeToStream(bitMatrix, "png", byteArrayOutputStream);
			return byteArrayOutputStream.toByteArray();

		} catch (Exception e) {
			return null;
		}
	}
}
