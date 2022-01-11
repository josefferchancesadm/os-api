package com.apps.os.services;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apps.os.domain.Pessoa;
import com.apps.os.domain.Cliente;
import com.apps.os.dtos.ClienteDTO;
import com.apps.os.repositories.PessoaRepository;
import com.apps.os.repositories.ClienteRepository;
import com.apps.os.services.exceptions.DataIntegratyViolationException;
import com.apps.os.services.exceptions.ObjectNotFoundException;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository repository;
	
	@Autowired
	private PessoaRepository pessoaRepository;

	public Cliente findById(Integer id) {
		Optional<Cliente> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! id: " + id + " tipo: " + Cliente.class.getName()));
	}

	public List<Cliente> findAll() {
		// TODO Auto-generated method stub
		return repository.findAll();
	}

	public Cliente create(ClienteDTO objDTO) {
		if (findByCPF(objDTO) != null) {
			throw new DataIntegratyViolationException("CPF já cadastrado na base de dados!");
		}

		return repository.save(new Cliente(null, objDTO.getNome(), objDTO.getTelefone(), objDTO.getCpf()));
	}

	public Cliente update(Integer id, @Valid ClienteDTO objDTO) {
		
		Cliente oldObj = findById(id);
		
		if (findByCPF(objDTO) != null && findByCPF(objDTO).getId() != id) {
			throw new DataIntegratyViolationException("CPF já cadastrado na base de dados!");
		}
		
		oldObj.setNome(objDTO.getNome());
		oldObj.setTelefone(objDTO.getTelefone());
		oldObj.setCpf(objDTO.getCpf());
		
		return repository.save(oldObj);
		
	}

	public void delete(Integer id) {
		Cliente obj = findById(id);
		if (obj.getList().size() > 0) {
			throw new DataIntegratyViolationException("Cliente possui ordem de serviço! Não pode ser deletado.");
		}
		repository.deleteById(id);
		
	}
	
	private Pessoa findByCPF(ClienteDTO objDTO) {
		Pessoa obj = pessoaRepository.findByCPF(objDTO.getCpf());

		if (obj != null) {
			return obj;
		}
		return null;
	}


}
