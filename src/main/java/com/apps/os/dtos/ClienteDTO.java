package com.apps.os.dtos;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.br.CPF;

import com.apps.os.domain.Cliente;

public class ClienteDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Integer id;
	@NotEmpty(message = "O campo NOME é requerido!")
	private String nome;
	@NotEmpty(message = "O campo TELEFONE é requerido!")
	private String telefone;
	
	@CPF
	@NotEmpty(message = "O campo CFP é requerido!")
	private String cpf;

	public ClienteDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ClienteDTO(Cliente obj) {
		super();
		this.id = obj.getId();
		this.nome = obj.getNome();
		this.telefone = obj.getTelefone();
		this.cpf = obj.getCpf();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	

}
