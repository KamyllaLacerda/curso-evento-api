package br.com.projeto.evento.entities;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

@Entity
@Table(name = "evento")
public class Evento {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@NotNull
	private String titulo;

	@NotNull
	private String local;

	@NotNull
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date dataInicio;

	@NotNull
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date dataFim;

	@NotNull
	@JsonFormat(pattern = "HH:mm")
	private Date horaInicio;

	@JsonFormat(pattern = "HH:mm")
	private Date horaFim;

	private String descricao;

	private String ementa;

	@NotNull
	private Integer numeroVagas;

	@NotNull

	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date dataInicioInscricao;

	@NotNull
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date dataFimInscricao;

	@JsonProperty(access = Access.READ_ONLY)
	@OneToMany(mappedBy = "evento")
	private List<Inscricao> inscricoes;

	public Evento() {

	}

	public Evento(Integer id, String titulo, String local, Date dataInicio, Date dataFim, Date horaInicio, Date horaFim,
			String descricao, String ementa, Integer numeroVagas, Date dataInicioInscricao, Date dataFimIncricao,
			List<Inscricao> inscricoes) {
		super();
		this.id = id;
		this.titulo = titulo;
		this.local = local;
		this.dataInicio = dataInicio;
		this.dataFim = dataFim;
		this.horaInicio = horaInicio;
		this.horaFim = horaFim;
		this.descricao = descricao;
		this.ementa = ementa;
		this.numeroVagas = numeroVagas;
		this.dataInicioInscricao = dataInicioInscricao;
		this.dataFimInscricao = dataFimIncricao;
		this.inscricoes = inscricoes;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getLocal() {
		return local;
	}

	public void setLocal(String local) {
		this.local = local;
	}

	public Date getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(Date dataInicio) {
		this.dataInicio = dataInicio;
	}

	public Date getDataFim() {
		return dataFim;
	}

	public void setDataFim(Date dataFim) {
		this.dataFim = dataFim;
	}

	public Date getHoraInicio() {
		return horaInicio;
	}

	public void setHoraInicio(Date horaInicio) {
		this.horaInicio = horaInicio;
	}

	public Date getHoraFim() {
		return horaFim;
	}

	public void setHoraFim(Date horaFim) {
		this.horaFim = horaFim;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getEmenta() {
		return ementa;
	}

	public void setEmenta(String ementa) {
		this.ementa = ementa;
	}

	public Integer getNumeroVagas() {
		return numeroVagas;
	}

	public void setNumeroVagas(Integer numeroVagas) {
		this.numeroVagas = numeroVagas;
	}

	public Date getDataInicioInscricao() {
		return dataInicioInscricao;
	}

	public void setDataInicioInscricao(Date dataInicioInscricao) {
		this.dataInicioInscricao = dataInicioInscricao;
	}

	public Date getDataFimInscricao() {
		return dataFimInscricao;
	}

	public void setDataFimInscricao(Date dataFimInscricao) {
		this.dataFimInscricao = dataFimInscricao;
	}

	public List<Inscricao> getInscricoes() {
		return inscricoes;
	}
	
	
	public void setInscricoes(List<Inscricao> inscricoes) {
		this.inscricoes = inscricoes;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Evento other = (Evento) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
