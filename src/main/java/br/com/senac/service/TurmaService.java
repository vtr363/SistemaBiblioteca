package br.com.senac.service;

import java.util.List;
import java.util.Optional;

import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.senac.entity.Turma;
import br.com.senac.repository.TurmaRepository;
import jakarta.persistence.EntityNotFoundException;

@Service
public class TurmaService {

	@Autowired
	TurmaRepository repo;
	
	public List<Turma> listaTodasTurmas() {
		return repo.findAll();
	}
	
	public Turma salvar(Turma turma) {
		return repo.save(turma);
	}
	
	public Turma buscaPorId(Integer id) throws ObjectNotFoundException {
		Optional<Turma> turma = repo.findById(id);
		return turma.orElseThrow(() -> new EntityNotFoundException("Turma não encontrada com o ID: " + id));
	}

	public void excluir(Integer id) {
		repo.deleteById(id);
	}
	
	public Turma alterar(Turma objTurma) {
		Turma turma = buscaPorId(objTurma.getId());
		turma.setNome(objTurma.getNome());
		return salvar(turma);
		
	}
	
}
