package br.com.projeto.evento.enums;

public enum StatusEnum {
	ACEITO("Aceito", "Status aceito"), 
	PROCESSANDO("Processando", "Status processando"),
	RECUSADO("Recusado", "Status recusado"),;

	private String status;
	private String descricao;

	StatusEnum(String status, String descricao) {
		this.status = status;
		this.descricao = descricao;
	}

	public String getStatus() {
		return status;
	}

	public String getDescricao() {
		return descricao;
	}

	public static StatusEnum Status(String status) {
		for (StatusEnum enumn : StatusEnum.values()) {
			if (status.equals(enumn.getStatus())) {
				return enumn;
			}

		}
	
		throw new RuntimeException("Status não encontrado" + status);

	} 


}