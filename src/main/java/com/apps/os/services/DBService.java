package com.apps.os.services;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apps.os.domain.Cliente;
import com.apps.os.domain.OS;
import com.apps.os.domain.Tecnico;
import com.apps.os.domain.enuns.Prioridade;
import com.apps.os.domain.enuns.Status;
import com.apps.os.repositories.ClienteRepository;
import com.apps.os.repositories.OSRepository;
import com.apps.os.repositories.TecnicoRepository;

@Service
public class DBService {

	@Autowired
	private TecnicoRepository tecnicoRepository;

	@Autowired
	private ClienteRepository clienteRepository;

	@Autowired
	private OSRepository osRepository;

	public void instaciaDB() {

		Tecnico t1 = new Tecnico(null, "Joseffer Chances", "(62) 9888-8888", "216.487.570-22");
		Tecnico t2 = new Tecnico(null, "Cowboy chances", "(62) 9888-5555", "560.130.010-06");
		Cliente c1 = new Cliente(null, "Herika rosa", "(62) 9888-5555", "075.423.750-83");

		OS os1 = new OS(null, "Fazer massagens", Prioridade.ALTA, Status.ANDAMENTO, t1, c1);
		// OS os1 = new OS(null, "Fazer uma massagem", Prioridade.ALTA,
		// Status.ANDAMENTO, t1, c1);

		t1.getList().add(os1);
		c1.getList().add(os1);

		tecnicoRepository.saveAll(Arrays.asList(t1, t2));
		clienteRepository.saveAll(Arrays.asList(c1));
		osRepository.saveAll(Arrays.asList(os1));

	}

}
