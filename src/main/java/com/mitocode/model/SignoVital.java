package com.mitocode.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

@Entity
@Table(name = "signo_vital")
public class SignoVital {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idSignoVital;

	@ManyToOne
	@JoinColumn(name = "id_paciente", nullable = false)
	private Paciente paciente;

	@JsonSerialize(using = ToStringSerializer.class)
	private LocalDateTime fecha;
	
	@Column(name = "temperatura")
	private String temperatura;
	
	@Column(name = "pulso")
	private String pulso;
	
	@Column(name = "ritmoCardiaco")
	private String ritmoCardiaco;

	public int getIdSignoVital() {
		return idSignoVital;
	}

	public void setIdSignoVital(int idSignoVital) {
		this.idSignoVital = idSignoVital;
	}

	public Paciente getPaciente() {
		return paciente;
	}

	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}

	public LocalDateTime getFecha() {
		return fecha;
	}

	public void setFecha(LocalDateTime fecha) {
		this.fecha = fecha;
	}

	public String getTemperatura() {
		return temperatura;
	}

	public void setTemperatura(String temperatura) {
		this.temperatura = temperatura;
	}

	public String getPulso() {
		return pulso;
	}

	public void setPulso(String pulso) {
		this.pulso = pulso;
	}

	public String getRitmoCardiaco() {
		return ritmoCardiaco;
	}

	public void setRitmoCardiaco(String ritmoCardiaco) {
		this.ritmoCardiaco = ritmoCardiaco;
	}
	
}
