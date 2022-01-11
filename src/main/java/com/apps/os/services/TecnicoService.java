package com.apps.os.services;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apps.os.domain.Pessoa;
import com.apps.os.domain.Tecnico;
import com.apps.os.dtos.TecnicoDTO;
import com.apps.os.repositories.PessoaRepository;
import com.apps.os.repositories.TecnicoRepository;
import com.apps.os.services.exceptions.DataIntegratyViolationException;
import com.apps.os.services.exceptions.ObjectNotFoundException;

@Service
public class TecnicoService {

	@Autowired
	private TecnicoRepository repository;
	
	@Autowired
	private PessoaRepository pessoaRepository;

	public Tecnico findById(Integer id) {
		Optional<Tecnico> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! id: " + id + " tipo: " + Tecnico.class.getName()));
	}

	public List<Tecnico> findAll() {
		// TODO Auto-generated method stub
		return repository.findAll();
	}

	public Tecnico create(TecnicoDTO objDTO) {
		if (findByCPF(objDTO) != null) {
			throw new DataIntegratyViolationException("CPF já cadastrado na base de dados!");
		}

		return repository.save(new Tecnico(null, objDTO.getNome(), objDTO.getTelefone(), objDTO.getCpf()));
	}

	public Tecnico update(Integer id, @Valid TecnicoDTO objDTO) {
		
		Tecnico oldObj = findById(id);
		
		if (findByCPF(objDTO) != null && findByCPF(objDTO).getId() != id) {
			throw new DataIntegratyViolationException("CPF já cadastrado na base de dados!");
		}
		
		oldObj.setNome(objDTO.getNome());
		oldObj.setTelefone(objDTO.getTelefone());
		oldObj.setCpf(objDTO.getCpf());
		
		return repository.save(oldObj);
		
	}

	public void delete(Integer id) {
		Tecnico obj = findById(id);
		if (obj.getList().size() > 0) {
			throw new DataIntegratyViolationException("Técnicos possui ordem de serviço! Não pode ser deletado.");
		}
		repository.deleteById(id);
		
	}
	
	private Pessoa findByCPF(TecnicoDTO objDTO) {
		Pessoa obj = pessoaRepository.findByCPF(objDTO.getCpf());

		if (obj != null) {
			return obj;
		}
		return null;
	}


}
