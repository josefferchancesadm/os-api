package com.apps.os.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apps.os.domain.Cliente;
import com.apps.os.domain.OS;
import com.apps.os.domain.Tecnico;
import com.apps.os.domain.enuns.Prioridade;
import com.apps.os.domain.enuns.Status;
import com.apps.os.dtos.OSDTO;
import com.apps.os.repositories.OSRepository;
import com.apps.os.services.exceptions.ObjectNotFoundException;

@Service
public class OsService {
	
	@Autowired
	private OSRepository repository;
	
	@Autowired
	private TecnicoService tecnicoService;
	
	@Autowired
	private ClienteService clienteService;
	
	public OS findById(Integer id) {
		Optional<OS> obj = repository.findById(id);
		
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! id: " + id + " tipo: " + OS.class.getName()));
	}
	
	public List<OS> findAll() {
		return repository.findAll();
	}

	public OS create(@Valid OSDTO obj) {		
		return fromDTO(obj);
	}
	
	public OS update(@Valid OSDTO obj) {
		findById(obj.getId());
		
		return fromDTO(obj);
	}
	
	public OS fromDTO(OSDTO obj) {
		OS newObj = new OS();
		
		newObj.setId(obj.getId());
		newObj.setObs(obj.getObs());
		newObj.setPrioridade(Prioridade.toEnum(obj.getPrioridade()));
		newObj.setStatus(Status.toEnum(obj.getStatus()));
		
		Tecnico tec = tecnicoService.findById(obj.getTecnico());
		Cliente cli = clienteService.findById(obj.getCliente());
		
		if (newObj.getStatus().getCod().equals(2)) {
			newObj.setDataFechamento(LocalDateTime.now());
		}
		
		newObj.setCliente(cli);
		newObj.setTecnico(tec);
		
		return repository.save(newObj);
		
	}

	
	

}
